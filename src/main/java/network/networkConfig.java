package network;

public class networkConfig {

    // --- Thuộc tính ---
    private String serverHost = "localhost";
    private int port = 1234;
    private boolean running = false;

    // --- Constructor ---
    public networkConfig() {
    }

    public networkConfig(String serverHost, int port) {
        this.serverHost = serverHost;
        this.port = port;
    }

    // --- Getter & Setter ---
    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
