package com.arahansa.chat.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerGUI extends JFrame implements ActionListener {

    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private ServerBackground server = new ServerBackground();   // GUI와 서버 연동

    public static void main(String[] args) throws IOException {
        new ServerGUI();
    }

    public ServerGUI() throws IOException {

        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200, 100, 400, 600);
        setTitle("서버부분");

        // 서버 연결
        server.setGui(this);
        server.setting();
    }

    /*
     * 클라로 msg 전송
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = "관리자 : " + jtf.getText() + "\n";
        jta.append(msg);
        System.out.printf("msg || %s", msg);

        // 서버 백그라운드로 msg 전송
        server.sendMessage(msg);

        // 텍스트 필드 초기화
        jtf.setText("");
    }    

    public void appendMsg(String msg) {
        System.out.println("날아온 메시지 || " + msg);
        jta.append(msg);
    }
}
