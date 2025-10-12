package network;

import chatFrom.FromServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {

    private static NetworkServer instance;

    private ServerSocket serverSocket;
    private networkConfig config;
    private boolean running = false;

    // Singleton thread-safe (không đồng bộ hóa phức tạp vì dùng trong GUI)
    public static NetworkServer getInstance(networkConfig config) {
        if (instance == null) {
            instance = new NetworkServer();
        }
        instance.setConfig(config); // luôn cập nhật config
        return instance;
    }

    private NetworkServer() {
        // private constructor
    }

    public void setConfig(networkConfig config) {
        this.config = config;
    }

    // Khởi động server
    public void startServer() {
        if (config == null) {
            FromServer.addLog("[SERVER] Lỗi: config chưa được thiết lập!");
            return;
        }

        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(config.getPort());
                running = true;
                FromServer.addLog("[SERVER] Đang lắng nghe tại " + config.getServerHost() + ":" + config.getPort());

                while (running) {
                    Socket clientSocket = serverSocket.accept();
                    FromServer.addLog("[SERVER] Client mới: " + clientSocket.getInetAddress());

                    // Mỗi client chạy trong luồng riêng bằng NetworkHandle
                    NetworkHandle handler = new NetworkHandle(clientSocket);
                    new Thread(handler).start();
                }

            } catch (IOException e) {
                if (running) { // nếu đang chạy mà lỗi, mới log
                    FromServer.addLog("[SERVER] Lỗi khi khởi động server: " + e.getMessage());
                }
            } finally {
                stopServer();
            }
        }).start();
    }

    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                FromServer.addLog("[SERVER] Đã dừng server!");
            }
        } catch (IOException e) {
            FromServer.addLog("[SERVER] Lỗi khi dừng server: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        return running;
    }
}
