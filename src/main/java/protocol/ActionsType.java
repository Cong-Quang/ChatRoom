package protocol;

public enum ActionsType {
    // --- AUTH ACTIONS ---
    LOGIN_REQUEST, // Yêu cầu đăng nhập
    LOGIN_SUCCESS, // Đăng nhập thành công
    LOGIN_FAILURE, // Đăng nhập thất bại
    REGISTER_REQUEST, // Yêu cầu đăng ký
    REGISTER_SUCCESS, // Đăng ký thành công
    REGISTER_FAILURE, // Đăng ký thất bại
    LOGOUT_REQUEST, // Yêu cầu đăng xuất

    // --- CHAT ACTIONS ---
    SEND_PRIVATE_MESSAGE, // Gửi tin nhắn riêng
    SEND_ROOM_MESSAGE, // Gửi tin nhắn vào phòng
    RECEIVE_MESSAGE, // Nhận một tin nhắn (từ server)
    GET_MESSAGE_HISTORY, // Yêu cầu lấy lịch sử tin nhắn

    // --- SYSTEM ACTIONS ---
    USER_STATUS_UPDATE, // Cập nhật trạng thái user (online/offline)
    SERVER_NOTIFICATION, // Thông báo từ server
    ERROR_RESPONSE          // Phản hồi lỗi
}
