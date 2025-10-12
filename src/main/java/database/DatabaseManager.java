package database;

import chatFrom.FromServer;
import static database.DatabaseConnection.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import maHoa.PasswordUtil;
import ulti.*;

public class DatabaseManager {

    // ============================================================
    // Kiểm tra đăng nhập (so sánh mật khẩu sau khi mã hoá SHA-1)
    // ============================================================
    public static Optional<User> login(String username, String password) {
        // Lấy user theo username (không so sánh password trong SQL)
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Lấy mật khẩu đã hash từ DB
                String storedHash = rs.getString("password");

                //  Hash lại mật khẩu người dùng nhập vào để so sánh
                if (PasswordUtil.verifyPassword(password, storedHash)) {
                    // Mật khẩu hợp lệ
                    User user = mapUser(rs);
                    int userId = user.getId();

                    // Cập nhật trạng thái và last_login sau khi xác thực
                    updateUserStatus(userId, true);
                    updateLastLogin(userId);

                    FromServer.addLog("[MYSQL] User đăng nhập thành công: " + username);
                    return Optional.of(user);
                } else {
                    // Sai mật khẩu
                    FromServer.addLog("[MYSQL] Sai mật khẩu cho user: " + username);
                }
            } else {
                // Không tồn tại username
                FromServer.addLog("[MYSQL] Không tìm thấy user: " + username);
            }

        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi login: " + e.getMessage());
        }

        // Trả về rỗng nếu login thất bại
        return Optional.empty();
    }

    // Cập nhật trạng thái online/offline
    public static void updateUserStatus(int userId, boolean online) {
        String sql = "UPDATE users SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, online ? "online" : "offline");
            ps.setInt(2, userId);
            ps.executeUpdate();
            FromServer.addLog("[MYSQL] Update trạng thái user " + userId + " → " + (online ? "online" : "offline"));
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi updateUserStatus: " + e.getMessage());
        }
    }

    // Cập nhật last_login
    private static void updateLastLogin(int userId) {
        String sql = "UPDATE users SET last_login = NOW() WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            FromServer.addLog("[MYSQL] Update last_login cho userId=" + userId);
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi updateLastLogin: " + e.getMessage());
        }
    }

    // Lấy danh sách tất cả bạn bè của user
    public static List<User> getFriends(int userId) {
        List<User> friends = new ArrayList<>();
        String sql = """
            SELECT u.* FROM users u
            JOIN friends f ON (u.id = f.friend_id AND f.user_id = ?)
                            OR (u.id = f.user_id AND f.friend_id = ?)
            WHERE f.status = 'accepted';
        """;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                friends.add(mapUser(rs));
            }
            FromServer.addLog("[MYSQL] Lấy danh sách bạn bè của userId=" + userId + " (" + friends.size() + " bạn)");
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi getFriends: " + e.getMessage());
        }
        return friends;
    }

    // lấy ra user 
    public static Integer getUserIdByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // kiểm tra có user name đó chưa
    public static boolean userExists(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            boolean exists = rs.next();

            FromServer.addLog("[MYSQL] userExists(" + username + ") → " + exists);
            return exists;

        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi userExists: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // Đăng ký user mới (mã hoá mật khẩu bằng SHA-1 trước khi lưu DB)
    // ============================================================
    public static boolean registerUser(String username, String password) {
        //  Bước 1: Hash mật khẩu bằng SHA-1 (thay vì lưu plaintext)
        String hashed = PasswordUtil.hashPassword(password);

        String sql = "INSERT INTO users (username, password, status) VALUES (?, ?, 'offline')";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Bước 2: Lưu username và mật khẩu đã mã hoá vào DB
            ps.setString(1, username);
            ps.setString(2, hashed);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                FromServer.addLog("[MYSQL] Tạo user mới thành công (đã mã hoá): " + username);
                return true;
            } else {
                FromServer.addLog("[MYSQL] registerUser thất bại, không có dòng nào được chèn.");
                return false;
            }

        } catch (SQLIntegrityConstraintViolationException e) {
            FromServer.addLog("[MYSQL] Username đã tồn tại: " + username);
            return false;
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi registerUser: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // ROOMS
    // ============================================================
    // Lấy danh sách phòng mà user tham gia
    public static List<Room> getUserRooms(int userId) {
        List<Room> rooms = new ArrayList<>();
        String sql = """
            SELECT r.* FROM rooms r
            JOIN room_members m ON r.id = m.room_id
            WHERE m.user_id = ?
            ORDER BY r.created_at;
        """;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rooms.add(mapRoom(rs));
            }
            FromServer.addLog("[MYSQL] Lấy danh sách phòng của userId=" + userId + " (" + rooms.size() + " phòng)");
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi getUserRooms: " + e.getMessage());
        }
        return rooms;
    }

    // Thêm user vào phòng
    public static boolean addUserToRoom(int userId, int roomId) {
        String sql = "INSERT IGNORE INTO room_members(room_id, user_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            ps.setInt(2, userId);
            int rows = ps.executeUpdate();
            FromServer.addLog("[MYSQL] Thêm userId=" + userId + " vào roomId=" + roomId + " (rows=" + rows + ")");
            return true;
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi addUserToRoom: " + e.getMessage());
            return false;
        }
    }

    // ============================================================
    // MESSAGES
    // ============================================================
    // Gửi tin nhắn (1-1 hoặc trong phòng)
    public static boolean saveMessage(int senderId, Integer recipientId, Integer roomId,
            String content, String messageType) {

        String sql = "INSERT INTO messages(sender_id, recipient_id, room_id, content, message_type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, senderId);
            if (recipientId != null) {
                ps.setInt(2, recipientId);
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            if (roomId != null) {
                ps.setInt(3, roomId);
            } else {
                ps.setNull(3, Types.INTEGER);
            }
            ps.setString(4, content);  // lưu bản mã hoá
            ps.setString(5, messageType);
            ps.executeUpdate();

            FromServer.addLog("[MYSQL] Lưu message (đã mã hoá)");
            return true;
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi saveMessage: " + e.getMessage());
            return false;
        }
    }

    // Lấy lịch sử tin nhắn cá nhân
    public static List<Message> getPrivateMessages(int userA, int userB) {
        List<Message> list = new ArrayList<>();
        String sql = """
            SELECT * FROM messages
            WHERE (sender_id = ? AND recipient_id = ?)
               OR (sender_id = ? AND recipient_id = ?)
            ORDER BY sent_at ASC;
        """;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userA);
            ps.setInt(2, userB);
            ps.setInt(3, userB);
            ps.setInt(4, userA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapMessage(rs));
            }
            FromServer.addLog("[MYSQL] Lấy lịch sử chat giữa " + userA + " ↔ " + userB + " (" + list.size() + " tin nhắn)");
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi getPrivateMessages: " + e.getMessage());
        }
        return list;
    }

    // Lấy tin nhắn trong phòng
    public static List<Message> getRoomMessages(int roomId) {
        List<Message> list = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE room_id = ? ORDER BY sent_at ASC";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roomId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapMessage(rs));
            }
            FromServer.addLog("[MYSQL] Lấy lịch sử phòng roomId=" + roomId + " (" + list.size() + " tin nhắn)");
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi getRoomMessages: " + e.getMessage());
        }
        return list;
    }

    // ============================================================
    // MAPPERS
    // ============================================================
    private static User mapUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("display_name"),
                rs.getString("status"),
                rs.getTimestamp("last_login"),
                rs.getTimestamp("created_at")
        );
    }

    private static Room mapRoom(ResultSet rs) throws SQLException {
        return new Room(
                rs.getInt("id"),
                rs.getString("room_name"),
                rs.getInt("created_by"),
                rs.getTimestamp("created_at")
        );
    }

    private static Message mapMessage(ResultSet rs) throws SQLException {
        // mã hoá nó trước khi trả về giá trị
        String rawContent = rs.getString("content");
        return new Message(
                rs.getInt("id"),
                rs.getInt("sender_id"),
                rs.getObject("recipient_id") != null ? rs.getInt("recipient_id") : null,
                rs.getObject("room_id") != null ? rs.getInt("room_id") : null,
                rawContent,
                rs.getString("message_type"),
                rs.getTimestamp("sent_at")
        );
    }

}
