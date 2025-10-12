package Client;

import protocol.Message;
import java.io.*;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Thread listenThread;
    private boolean running = false;

    public boolean connect(String ip, int port) {
        try {
            socket = new Socket(ip, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            running = true;

            FromClient.addLog("[CLIENT] Đã kết nối tới server " + ip + ":" + port);
            startListening(); // bắt đầu nhận dữ liệu trả về từ server
            return true;
        } catch (IOException e) {
            FromClient.addLog("[CLIENT] Lỗi kết nối: " + e.getMessage());
            return false;
        }
    }

    public void disconnect() {
        running = false;
        try {
            if (in != null) in.close();
            if (out != null) {
                out.flush();
                out.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            FromClient.addLog("[CLIENT] Đã ngắt kết nối khỏi server");
        } catch (IOException ignored) { }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    // ======================================================
    // GỬI TIN NHẮN (CÓ MÃ HOÁ)
    // ======================================================
    public boolean sendMessage(Message message) {
        try {
            if (isConnected() && out != null) {
                // ===> Mã hoá nội dung trước khi gửi
                if (message.getContent() != null) {
                    String encoded = message.getContent();
                    message.setContent(encoded);
                }

                out.writeObject(message);
                out.flush();

                FromClient.addLog("[SEND] (encoded) " + message.toString());
                return true;
            }
        } catch (IOException e) {
            FromClient.addLog("[CLIENT] Lỗi gửi message: " + e);
        }
        return false;
    }

    // ======================================================
    // LẮNG NGHE PHẢN HỒI TỪ SERVER
    // ======================================================
    private void startListening() {
        listenThread = new Thread(() -> {
            try {
                while (running && !socket.isClosed()) {
                    Object obj = in.readObject();
                    if (obj instanceof Message msg) {
                        handleServerResponse(msg);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                FromClient.addLog("[CLIENT] Mất kết nối tới server hoặc lỗi đọc dữ liệu: " + e.getMessage());
                running = false;
            }
        });
        listenThread.start();
    }

    // ======================================================
    // XỬ LÝ DỮ LIỆU SERVER GỬI VỀ (CÓ GIẢI MÃ)
    // ======================================================
    private void handleServerResponse(Message msg) {
        try {
            if (msg.getContent() != null) {
                // giải mã
                String decoded = msg.getContent();
                msg.setContent(decoded);
            }
        } catch (Exception e) {
            FromClient.addLog("[CLIENT] Lỗi giải mã dữ liệu nhận được: " + e.getMessage());
        }

        // Khi login thành công server có thể gửi lại nội dung, ta show ra list1
        FromClient.addLog("[SERVER] " + msg.toString());
    }
}

    