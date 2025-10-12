package chatFrom;

import database.DatabaseConfig;
import database.DatabaseConnection;

import javax.swing.JOptionPane;
import network.NetworkHandle;
import network.NetworkServer;
import network.networkConfig;

public class FromServer extends javax.swing.JFrame {

    private static java.awt.List staticListTT;

    public FromServer() {
        initComponents();
        staticListTT = listTT;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        PaneServer = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btn_start = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfl_ipServer = new javax.swing.JTextField();
        tfl_portServer = new javax.swing.JTextField();
        btn_stop = new javax.swing.JButton();
        btn_Clear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfl_ipMysql = new javax.swing.JTextField();
        tfl_PortMysql = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfl_dtbName = new javax.swing.JTextField();
        tfl_userNameDTB = new javax.swing.JTextField();
        tfl_passworDTB = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_updateTTDTB = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        listTT = new java.awt.List();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_start.setText("start");
        btn_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_startActionPerformed(evt);
            }
        });

        jLabel1.setText("IP");

        tfl_ipServer.setText("127.0.0.1");

        tfl_portServer.setText("8888");
        tfl_portServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfl_portServerActionPerformed(evt);
            }
        });

        btn_stop.setText("stop");
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });

        btn_Clear.setText("Clear Log");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfl_ipServer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfl_portServer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addComponent(btn_Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_stop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfl_ipServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfl_portServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_start)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_stop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Clear)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        PaneServer.addTab("server", jPanel1);

        jLabel2.setText("IP");

        tfl_ipMysql.setText("localhost");

        tfl_PortMysql.setText("3306");
        tfl_PortMysql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfl_PortMysqlActionPerformed(evt);
            }
        });

        jLabel3.setText("DATABASE_NAME");

        tfl_dtbName.setText("chatapp");

        tfl_userNameDTB.setText("quang");

        tfl_passworDTB.setText("deptrai@123!");
        tfl_passworDTB.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                tfl_passworDTBComponentHidden(evt);
            }
        });

        jLabel4.setText("user name");

        jLabel5.setText("password");

        btn_updateTTDTB.setText("update");
        btn_updateTTDTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateTTDTBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfl_passworDTB, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfl_userNameDTB, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tfl_ipMysql, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfl_PortMysql, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfl_dtbName, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_updateTTDTB, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfl_ipMysql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfl_PortMysql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfl_dtbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfl_userNameDTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfl_passworDTB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_updateTTDTB)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        PaneServer.addTab("mysql", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        PaneServer.addTab("user", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PaneServer, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(listTT, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaneServer)
            .addComponent(listTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfl_portServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfl_portServerActionPerformed
    }//GEN-LAST:event_tfl_portServerActionPerformed

    private void tfl_PortMysqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfl_PortMysqlActionPerformed
    }//GEN-LAST:event_tfl_PortMysqlActionPerformed

    private void btn_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_startActionPerformed
        try {
            // 1. Lấy IP và Port từ 2 ô nhập
            String ip = tfl_ipServer.getText().trim();
            String portText = tfl_portServer.getText().trim();

            if (ip.isEmpty() || portText.isEmpty()) {
                listTT.add("[LỖI] IP hoặc Port không được để trống!");
                return;
            }

            int port = Integer.parseInt(portText);

            // 2. Cập nhật cấu hình mạng
            networkConfig config = new networkConfig();
            config.setServerHost(ip);
            config.setPort(port);
            config.setRunning(true);

            // 3. Kết nối MySQL
            DatabaseConnection.connect();
            listTT.add("[MYSQL] Kết nối DB: " + DatabaseConfig.DATABASE_NAME);

            // 4. Bắt đầu server
            NetworkServer server = NetworkServer.getInstance(config);
            server.startServer();

            // 5. Ghi log
            listTT.add("[SERVER] Đang khởi động server tại " + ip + ":" + port);

            // 6. Vô hiệu hóa nút Start, bật nút Stop
            btn_start.setEnabled(false);
            btn_stop.setEnabled(true);

        } catch (NumberFormatException e) {
            listTT.add("[LỖI] Port phải là số nguyên!");
        } catch (Exception e) {
            listTT.add("[LỖI] Không thể khởi động server: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_startActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        try {
            // 1. Dừng server nếu đang chạy
            if (NetworkServer.getInstance(null).isRunning()) {
                NetworkServer.getInstance(null).stopServer();
                listTT.add("[SERVER] Server đã dừng!");
            } else {
                listTT.add("[SERVER] Server chưa chạy.");
            }

            // 2. Ngắt kết nối MySQL
            DatabaseConnection.disconnect();

            // 3. Bật lại nút Start, vô hiệu hóa nút Stop
            btn_start.setEnabled(true);
            btn_stop.setEnabled(false);

        } catch (Exception e) {
            listTT.add("[LỖI] Không thể dừng server: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_stopActionPerformed

    private void tfl_passworDTBComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tfl_passworDTBComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_tfl_passworDTBComponentHidden

    private void btn_updateTTDTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateTTDTBActionPerformed
        try {
            String host = tfl_ipMysql.getText().trim();
            int port = Integer.parseInt(tfl_PortMysql.getText().trim());
            String dbName = tfl_dtbName.getText().trim();
            String user = tfl_userNameDTB.getText().trim();
            String pass = tfl_passworDTB.getText();

            // Cập nhật cấu hình
            DatabaseConfig.update(host, port, dbName, user, pass);

            listTT.add("[MYSQL] Update config thành công -> "
                    + "host=" + host + ", port=" + port + ", db=" + dbName + ", user=" + user);

            // Nếu có kết nối cũ, ngắt rồi kết nối lại để test cấu hình mới
            DatabaseConnection.disconnect();
            DatabaseConnection.connect(); // sẽ sử dụng DatabaseConfig.getJdbcUrl()

            listTT.add("[MYSQL] Thử kết nối lại sau khi cập nhật config.");

        } catch (NumberFormatException ex) {
            listTT.add("[MYSQL] Port sai định dạng: " + tfl_PortMysql.getText());
            JOptionPane.showMessageDialog(this, "Port phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            listTT.add("[MYSQL] Lỗi khi cập nhật config: " + ex.getMessage());
        }
    }//GEN-LAST:event_btn_updateTTDTBActionPerformed

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        listTT.clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new FromServer().setVisible(true));
    }

    public static void addLog(String text) {
        if (staticListTT != null) {
            staticListTT.add(text);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane PaneServer;
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_start;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_updateTTDTB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private java.awt.List listTT;
    private javax.swing.JTextField tfl_PortMysql;
    private javax.swing.JTextField tfl_dtbName;
    private javax.swing.JTextField tfl_ipMysql;
    private javax.swing.JTextField tfl_ipServer;
    private javax.swing.JTextField tfl_passworDTB;
    private javax.swing.JTextField tfl_portServer;
    private javax.swing.JTextField tfl_userNameDTB;
    // End of variables declaration//GEN-END:variables

}
