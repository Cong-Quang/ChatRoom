package database;

public class DatabaseConfig {

    public static String HOST = "localhost";
    public static int PORT = 3306;
    public static String DATABASE_NAME = "chatapp";

    // Thông tin đăng nhập
    public static String USERNAME = "quang";
    public static String PASSWORD = "deptrai@123!";

    // Trả về JDBC URL tính toán tại runtime
    public static String getJdbcUrl() {
        return "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE_NAME
                + "?useSSL=true" // bật ssl để mã hoá
                + "&allowPublicKeyRetrieval=true" // cho phép lấy public key khi xác thực bằng caching_sha2_password
                + "&characterEncoding=UTF-8"
                + "&serverTimezone=UTC";
    }

    // Tiện ích để cập nhật tất cả cùng lúc (nếu muốn)
    public static void update(String host, int port, String dbName, String user, String pass) {
        HOST = host;
        PORT = port;
        DATABASE_NAME = dbName;
        USERNAME = user;
        PASSWORD = pass;
    }
}
