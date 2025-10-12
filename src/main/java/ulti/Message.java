package ulti;

import java.sql.Timestamp;

 public class Message {
        public int id;
        public int senderId;
        public Integer recipientId;
        public Integer roomId;
        public String content;
        public String type;
        public Timestamp sentAt;

        public Message(int id, int senderId, Integer recipientId, Integer roomId,
                       String content, String type, Timestamp sentAt) {
            this.id = id;
            this.senderId = senderId;
            this.recipientId = recipientId;
            this.roomId = roomId;
            this.content = content;
            this.type = type;
            this.sentAt = sentAt;
        }
    }
