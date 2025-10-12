package ulti;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private int id;
    private String username;
    private String displayName;
    private String status;
    private Timestamp lastLogin;
    private Timestamp createdAt;

    public User(int id, String username, String displayName, String status, Timestamp lastLogin, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.status = status;
        this.lastLogin = lastLogin;
        this.createdAt = createdAt;
    }

    // Getter
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getDisplayName() { return displayName; }
    public String getStatus() { return status; }
    public Timestamp getLastLogin() { return lastLogin; }
    public Timestamp getCreatedAt() { return createdAt; }

    // Setter nếu cần
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", status='" + status + '\'' +
                ", lastLogin=" + lastLogin +
                ", createdAt=" + createdAt +
                '}';
    }
}
