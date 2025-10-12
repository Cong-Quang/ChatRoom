package protocol;


public enum MessageType {
     //Yêu cầu xác thực (đăng nhập, đăng ký).
    AUTH,
    //Tin nhắn trò chuyện.
    CHAT,
    // Yêu cầu liên quan đến bạn bè (thêm, xóa, danh sách).
    FRIEND,
    //Yêu cầu liên quan đến phòng trò chuyện.
    ROOM,
    // Tin nhắn hệ thống (thông báo, lỗi).
    SYSTEM
}
