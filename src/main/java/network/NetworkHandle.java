package network;

import chatFrom.FromServer;
import database.DatabaseManager;

import protocol.Message;
import protocol.ActionsType;
import protocol.MessageType;

import ulti.*;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Optional;

/**
 * Lớp xử lý riêng cho từng client kết nối vào server. Mỗi client chạy trong 1
 * thread độc lập.
 */
public class NetworkHandle implements Runnable {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean running = true;
    private User currentUser; // user đã đăng nhập

    public NetworkHandle(Socket socket) {
        this.socket = socket;
        try {
            // Luồng gửi phải tạo trước luồng nhận
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            FromServer.addLog("[NETWORK] Client kết nối: " + socket.getInetAddress());
        } catch (IOException e) {
            FromServer.addLog("[NETWORK] Lỗi khởi tạo luồng: " + e.getMessage());
            stop();
        }
    }

    @Override
    public void run() {
        try {
            while (running && !socket.isClosed()) {
                Object obj = in.readObject();
                if (obj instanceof Message msg) {
                    handleMessage(msg);
                }
            }
        } catch (EOFException e) {
            FromServer.addLog("[NETWORK] Client ngắt kết nối: " + getClientName());
        } catch (Exception e) {
            FromServer.addLog("[NETWORK] Lỗi trong luồng client: " + e.getMessage());
        } finally {
            disconnect();
        }
    }

    private void handleMessage(Message msg) {
        FromServer.addLog("[RECEIVED] " + msg);
        if (msg.getContent() != null) {
            try {
                msg.setContent(msg.getContent());
            } catch (Exception e) {
                FromServer.addLog("[NETWORK] Không giải mã được nội dung: " + e.getMessage());
            }
        }
        try {
            switch (msg.getType()) {
                case AUTH ->
                    handleAuthMessage(msg);
                case CHAT ->
                    handleChatMessage(msg);
                case ROOM ->
                    handleRoomMessage(msg);
                case FRIEND ->
                    handleFriendMessage(msg);
                case SYSTEM ->
                    handleSystemMessage(msg);
                default ->
                    FromServer.addLog("[NETWORK] Loại MessageType chưa xử lý: " + msg.getType());
            }
        } catch (Exception e) {
            FromServer.addLog("[NETWORK] Lỗi xử lý message: " + e.getMessage());
            sendError("Lỗi xử lý message: " + e.getMessage());
        }
    }

    // ============================================================
    // AUTH
    // ============================================================
    private void handleAuthMessage(Message msg) {
        switch (msg.getAction()) {
            case LOGIN_REQUEST -> {
                String username = msg.getSender();
                String password = msg.getContent();
                Optional<User> userOpt = DatabaseManager.login(username, password);
                if (userOpt.isPresent()) {
                    currentUser = userOpt.get();
                    Message res = new Message();
                    res.setType(MessageType.AUTH);
                    res.setAction(ActionsType.LOGIN_SUCCESS);
                    res.setContent("Đăng nhập thành công!");
                    res.setPayload(currentUser);
                    send(res);
                } else {
                    Message res = new Message();
                    res.setType(MessageType.AUTH);
                    res.setAction(ActionsType.LOGIN_FAILURE);
                    res.setContent("Sai tên đăng nhập hoặc mật khẩu.");
                    send(res);
                }
            }

            case REGISTER_REQUEST -> {
                String username = msg.getSender();
                String password = msg.getContent();

                // Kiểm tra tồn tại user
                boolean exists = DatabaseManager.userExists(username);

                Message res = new Message();
                res.setType(MessageType.AUTH);

                if (exists) {
                    res.setAction(ActionsType.REGISTER_FAILURE);
                    res.setContent("Tên người dùng đã tồn tại!");
                } else {
                    boolean ok = DatabaseManager.registerUser(username, password);
                    if (ok) {
                        res.setAction(ActionsType.REGISTER_SUCCESS);
                        res.setContent("Đăng ký tài khoản thành công!");
                        FromServer.addLog("[AUTH] User mới đăng ký: " + username);
                    } else {
                        res.setAction(ActionsType.REGISTER_FAILURE);
                        res.setContent("Lỗi khi ghi dữ liệu vào cơ sở dữ liệu.");
                    }
                }
                send(res);
            }

            case LOGOUT_REQUEST -> {
                if (currentUser != null) {
                    DatabaseManager.updateUserStatus(currentUser.getId(), false);
                    FromServer.addLog("[AUTH] User đăng xuất: " + currentUser.getUsername());
                    stop();
                }
            }

            default ->
                FromServer.addLog("[AUTH] Action chưa hỗ trợ: " + msg.getAction());
        }
    }

    // ============================================================
    // CHAT
    // ============================================================
    private void handleChatMessage(Message msg) {
        switch (msg.getAction()) {
            case SEND_PRIVATE_MESSAGE -> {
                // Gửi tin nhắn 1-1
                String recipient = msg.getRecipient();
                String content = msg.getContent();

                if (currentUser == null) {
                    sendError("Chưa đăng nhập, không thể gửi tin nhắn!");
                    return;
                }

                // Lưu DB
                DatabaseManager.saveMessage(currentUser.getId(), findUserId(recipient), null, content, "text");
                FromServer.addLog("[CHAT] " + currentUser.getUsername() + " → " + recipient + ": " + content);

                // Gửi phản hồi lại client (echo)
                Message response = new Message();
                response.setType(MessageType.CHAT);
                response.setAction(ActionsType.RECEIVE_MESSAGE);
                response.setSender(currentUser.getUsername());
                response.setRecipient(recipient);
                response.setContent(content);
                send(response);
            }

            case SEND_ROOM_MESSAGE -> {
                if (currentUser == null) {
                    sendError("Chưa đăng nhập!");
                    return;
                }

                int roomId = Integer.parseInt(msg.getRecipient());
                String content = msg.getContent();

                DatabaseManager.saveMessage(currentUser.getId(), null, roomId, content, "text");
                FromServer.addLog("[ROOM] " + currentUser.getUsername() + " gửi tin trong room " + roomId);

                Message response = new Message();
                response.setType(MessageType.CHAT);
                response.setAction(ActionsType.RECEIVE_MESSAGE);
                response.setSender(currentUser.getUsername());
                response.setRecipient("room:" + roomId);
                response.setContent(content);
                send(response);
            }

            case GET_MESSAGE_HISTORY -> {
                int roomId = Integer.parseInt(msg.getRecipient());
                List<ulti.Message> history = DatabaseManager.getRoomMessages(roomId);
                Message response = new Message();
                response.setType(MessageType.CHAT);
                response.setAction(ActionsType.GET_MESSAGE_HISTORY);
                response.setPayload(history);
                send(response);
            }

            default ->
                FromServer.addLog("[CHAT] Action chưa hỗ trợ: " + msg.getAction());
        }
    }

    // ============================================================
    // ROOM / FRIEND / SYSTEM
    // ============================================================
    private void handleRoomMessage(Message msg) {
        // Có thể mở rộng xử lý tạo room, thêm user, v.v.
        FromServer.addLog("[ROOM] Nhận action: " + msg.getAction());
    }

    private void handleFriendMessage(Message msg) {
        // Xử lý bạn bè: thêm, xóa, chấp nhận
        FromServer.addLog("[FRIEND] Nhận action: " + msg.getAction());
    }

    private void handleSystemMessage(Message msg) {
        FromServer.addLog("[SYSTEM] " + msg.getContent());
    }

    // ============================================================
    // UTILITIES
    // ============================================================
    private void send(Message msg) {
        try {
            // mã hoá nó trước khi gửi
            if (msg.getContent() != null) {
                msg.setContent(msg.getContent());
            }

            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            FromServer.addLog("[NETWORK] Lỗi gửi message: " + e.getMessage());
        }
    }

    private void sendError(String message) {
        Message err = new Message();
        err.setType(MessageType.SYSTEM);
        err.setAction(ActionsType.ERROR_RESPONSE);
        err.setContent(message);
        send(err);
    }

    private void stop() {
        running = false;
        disconnect();
    }

    private void disconnect() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            FromServer.addLog("[NETWORK] Ngắt kết nối client: " + getClientName());
        } catch (IOException e) {
            FromServer.addLog("[NETWORK] Lỗi đóng socket: " + e.getMessage());
        }
    }

    private String getClientName() {
        return currentUser != null ? currentUser.getUsername() : socket.getInetAddress().toString();
    }

    private Integer findUserId(String username) {
        try {
            // Tra từ DB, trả về ID hoặc null nếu không tìm thấy
            return DatabaseManager.getUserIdByUsername(username);
        } catch (Exception e) {
            FromServer.addLog("[NETWORK] Lỗi khi tra cứu userId cho " + username + ": " + e.getMessage());
            return null;
        }
    }
}
