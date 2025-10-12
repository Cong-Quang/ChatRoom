package Client;

import protocol.Message;

class FromClient extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FromClient.class.getName());
    private static java.awt.List staticListTT;
    private ClientConnection clientConnection = new ClientConnection();
    public static String currentUser;

    public FromClient() {
        initComponents();
        staticListTT = list1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btn_Clear = new javax.swing.JButton();
        btn_connect = new javax.swing.JButton();
        btn_disconnect = new javax.swing.JButton();
        tfl_ipServer = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfl_portServer = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfl_userName = new javax.swing.JTextField();
        tfl_passWord = new javax.swing.JTextField();
        btn_Login = new javax.swing.JButton();
        btn_Register = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        list_online = new java.awt.List();
        tfl_Mess = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_sendMess = new javax.swing.JButton();
        tfl_sendTo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        list1 = new java.awt.List();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_Clear.setText("Clear Log");
        btn_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ClearActionPerformed(evt);
            }
        });

        btn_connect.setText("Connect");
        btn_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_connectActionPerformed(evt);
            }
        });

        btn_disconnect.setText("Disconnect");
        btn_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disconnectActionPerformed(evt);
            }
        });

        tfl_ipServer.setText("127.0.0.1");

        jLabel1.setText("IP");

        tfl_portServer.setText("8888");
        tfl_portServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfl_portServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_connect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_disconnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfl_ipServer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfl_portServer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 72, Short.MAX_VALUE))
                    .addComponent(btn_Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(btn_connect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_disconnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Clear)
                .addContainerGap(292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Server", jPanel1);

        jLabel2.setText("user name");

        jLabel3.setText("password");

        btn_Login.setText("Login");
        btn_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LoginActionPerformed(evt);
            }
        });

        btn_Register.setText("Register");
        btn_Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegisterActionPerformed(evt);
            }
        });

        jLabel4.setText("LOGIN");

        tfl_Mess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfl_MessActionPerformed(evt);
            }
        });

        jLabel5.setText("Chat:");

        btn_sendMess.setText("Send");
        btn_sendMess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sendMessActionPerformed(evt);
            }
        });

        tfl_sendTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfl_sendToActionPerformed(evt);
            }
        });

        jLabel6.setText("To :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(list_online, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Register, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfl_userName))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfl_passWord, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                            .addComponent(btn_sendMess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfl_sendTo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfl_Mess))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfl_userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfl_passWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Login)
                    .addComponent(btn_Register))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfl_Mess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfl_sendTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_sendMess, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list_online, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("user", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ClearActionPerformed
        list1.clear();
    }//GEN-LAST:event_btn_ClearActionPerformed

    private void btn_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_connectActionPerformed
        String ip = tfl_ipServer.getText().trim();
        int port = Integer.parseInt(tfl_portServer.getText().trim());

        if (clientConnection.connect(ip, port)) {
            staticListTT.add("[Client]: Kết nối thành công" + ip + ":" + port);
            btn_connect.setEnabled(false);
            btn_disconnect.setEnabled(true);
        } else {
            staticListTT.add("[Client]: Kết nối thất bại! kiểm tra lại ip/port.");
            btn_connect.setEnabled(true);
            btn_disconnect.setEnabled(false);
        }
    }//GEN-LAST:event_btn_connectActionPerformed

    private void btn_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disconnectActionPerformed
        if (clientConnection.isConnected()) {
            clientConnection.disconnect();
            staticListTT.add("[Client]: Đã rời server chat.");
        } else {
            staticListTT.add("[Client]: Chưa kết nối tới server nào");
        }

        btn_connect.setEnabled(true);
        btn_disconnect.setEnabled(false);
    }//GEN-LAST:event_btn_disconnectActionPerformed

    private void tfl_portServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfl_portServerActionPerformed

    }//GEN-LAST:event_tfl_portServerActionPerformed

    private void btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LoginActionPerformed
        String username = tfl_userName.getText().trim();
        String password = tfl_passWord.getText().trim();
        if (!clientConnection.isConnected()) {
            staticListTT.add("[Client]: Bạn chưa kết nối tới server!");
            return;
        }
        if (username.isEmpty() || password.isEmpty()) {
            staticListTT.add("[Client]: Vui lòng nhập đầy đủ username và password!");
            return;
        } // --- Tạo Message theo chuẩn protocol --- 
        protocol.Message loginMsg = new protocol.Message();
        loginMsg.setType(protocol.MessageType.AUTH);
        loginMsg.setAction(protocol.ActionsType.LOGIN_REQUEST);
        loginMsg.setSender(username);
        loginMsg.setContent(password); // --- Gửi message --- 
        boolean sent = clientConnection.sendMessage(loginMsg);
        if (sent) {
            staticListTT.add("[Client]: Đã gửi yêu cầu đăng nhập của user [" + username + "]");
        } else {
            staticListTT.add("[Client]: Lỗi khi gửi yêu cầu đăng nhập!");
        }
    }//GEN-LAST:event_btn_LoginActionPerformed

    private void btn_sendMessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sendMessActionPerformed
        try {
            if (clientConnection == null || !clientConnection.isConnected()) {
                FromClient.addLog("[CLIENT] Chưa kết nối tới server.");
                return;
            }

            String content = tfl_Mess.getText().trim();
            if (content.isEmpty()) {
                FromClient.addLog("[CLIENT] Vui lòng nhập nội dung tin nhắn.");
                return;
            }

            // -------------------------------
            // XÁC ĐỊNH KIỂU GỬI: CÔNG KHAI / PHÒNG / RIÊNG
            //| Trường hợp bạn muốn       | Nhập vào `txtRecipient` | Hành vi                                   |
            //| ------------------------- | ----------------------- | ----------------------------------------- |
            //| Gửi công khai toàn server | *(để trống)* hoặc `all` | Gửi `roomId = 0`                          |
            //| Gửi trong phòng cụ thể    | `room:1`                | Gửi `roomId = 1`                          |
            //| Gửi riêng cho user khác   | `bob`                   | Gửi `SEND_PRIVATE_MESSAGE` đến user `bob` |
            // -------------------------------
            Message msg = new Message();
            msg.setType(protocol.MessageType.CHAT);

            String recipient = tfl_sendTo.getText().trim();

            if (recipient.isEmpty() || recipient.equalsIgnoreCase("all")) {
                // ===> GỬI TIN CÔNG KHAI (toàn server)
                msg.setAction(protocol.ActionsType.SEND_ROOM_MESSAGE);
                msg.setRecipient("0"); // roomId = 0 → public
                FromClient.addLog("[CLIENT] Gửi tin nhắn công khai.");
            } else if (recipient.startsWith("room:")) {
                // ===> GỬI TRONG PHÒNG CỤ THỂ
                String roomId = recipient.substring(5);
                msg.setAction(protocol.ActionsType.SEND_ROOM_MESSAGE);
                msg.setRecipient(roomId);
                FromClient.addLog("[CLIENT] Gửi tin nhắn trong phòng " + roomId);
            } else {
                // ===> GỬI RIÊNG
                msg.setAction(protocol.ActionsType.SEND_PRIVATE_MESSAGE);
                msg.setRecipient(recipient);
                FromClient.addLog("[CLIENT] Gửi tin nhắn riêng cho " + recipient);
            }

            msg.setSender(currentUser != null ? currentUser : "Unknown");
            msg.setContent(content);

            // -------------------------------
            // GỬI TỚI SERVER
            // -------------------------------
            boolean sent = clientConnection.sendMessage(msg);

            if (sent) {
                addLog("[CLIENT] (" + msg.getSender() + " → " + msg.getRecipient() + "): " + content);
                tfl_Mess.setText("");
            } else {
                addLog("[CLIENT] Gửi tin nhắn thất bại.");
            }

        } catch (Exception e) {
            addLog("[CLIENT] Lỗi khi gửi tin nhắn: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_sendMessActionPerformed

    private void tfl_MessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfl_MessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfl_MessActionPerformed

    private void tfl_sendToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfl_sendToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfl_sendToActionPerformed

    private void btn_RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegisterActionPerformed
        try {
            if (clientConnection == null || !clientConnection.isConnected()) {
                FromClient.addLog("[CLIENT] Chưa kết nối tới server.");
                return;
            }

            String username = tfl_userName.getText().trim();
            String password = tfl_passWord.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                FromClient.addLog("[CLIENT] Vui lòng nhập đủ username và password.");
                return;
            }

            // --- Tạo message đăng ký ---
            Message msg = new Message();
            msg.setType(protocol.MessageType.AUTH);
            msg.setAction(protocol.ActionsType.REGISTER_REQUEST);
            msg.setSender(username);
            msg.setContent(password);

            boolean sent = clientConnection.sendMessage(msg);
            if (sent) {
                FromClient.addLog("[CLIENT] Gửi yêu cầu đăng ký user: " + username);
            } else {
                FromClient.addLog("[CLIENT] Gửi yêu cầu đăng ký thất bại.");
            }

        } catch (Exception e) {
            FromClient.addLog("[CLIENT] Lỗi khi gửi yêu cầu đăng ký: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_RegisterActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FromClient().setVisible(true));
    }

    public static void addLog(String text) {
        if (staticListTT != null) {
            staticListTT.add(text);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Clear;
    private javax.swing.JButton btn_Login;
    private javax.swing.JButton btn_Register;
    private javax.swing.JButton btn_connect;
    private javax.swing.JButton btn_disconnect;
    private javax.swing.JButton btn_sendMess;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.List list1;
    private java.awt.List list_online;
    private javax.swing.JTextField tfl_Mess;
    private javax.swing.JTextField tfl_ipServer;
    private javax.swing.JTextField tfl_passWord;
    private javax.swing.JTextField tfl_portServer;
    private javax.swing.JTextField tfl_sendTo;
    private javax.swing.JTextField tfl_userName;
    // End of variables declaration//GEN-END:variables
}
