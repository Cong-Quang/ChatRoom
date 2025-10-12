package protocol;
/*
 * Đối tượng DData Transfer Object chung cho mọi loại tin nhắn.
 * Class này cần implement Serializable để có thể gửi qua stream.
 */
import java.io.Serializable;
public class Message implements Serializable {
   // private static final long serialVersionUID = 1L;

    private MessageType type;       // Loại tin nhắn (LOGIN, CHAT, etc.)
    private ActionsType action;      // Hành động cụ thể (SEND_TO_ROOM, ADD_FRIEND,...)
    private String sender;          // Tên người gửi
    private String recipient;       // Tên người nhận (có thể là tên phòng)
    private String content;         // Nội dung tin nhắn
    private Object payload;         // Dữ liệu đính kèm (ví dụ: danh sách bạn bè)
   
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public ActionsType getAction() {
        return action;
    }

    public void setAction(ActionsType action) {
        this.action = action;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", action=" + action +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                ", payload=" + (payload != null ? payload.toString() : "null") +
                '}';
    }

}
