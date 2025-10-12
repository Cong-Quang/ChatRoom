package database;

import chatFrom.FromServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    public static Connection connect() {
        try {
            // Nếu chưa có kết nối hoặc đã bị đóng thì tạo kết nối mới
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        DatabaseConfig.getJdbcUrl(),
                        DatabaseConfig.USERNAME,
                        DatabaseConfig.PASSWORD
                );
                FromServer.addLog("[MYSQL] Kết nối MySQL thành công!");
            }
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] Lỗi kết nối MySQL: " + e.getMessage());
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                FromServer.addLog("[MYSQL] Đã ngắt kết nối MySQL!");
            }
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL] khi ngắt kết nối: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                return connect();
            }
        } catch (SQLException e) {
            FromServer.addLog("[MYSQL Lỗi khi lấy kết nối: " + e.getMessage());
        }
        return connection;
    }
}
