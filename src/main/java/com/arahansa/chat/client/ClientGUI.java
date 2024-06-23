package com.arahansa.chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements ActionListener {

    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private ClientBackground client = new ClientBackground();

    private static String nickName;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("당신의 닉네임을 설정하세요. ");
        nickName = scanner.nextLine();
        scanner.close();

        new ClientGUI();
    }

    public ClientGUI() throws IOException {

        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(800, 100, 400, 600);
        setTitle("클라이언트");

        client.setGui(this);
        client.setNickname(nickName);
        client.connect();
    }

    /*
     * 서버로 msg 전송
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = nickName + " : " + jtf.getText()+"\n";
        // jta.append("클라이언트 : " + msg);
        System.out.printf("msg || %s", msg);

        // 클라 백그라운드로 msg 전송
        try {
            client.sendMessage(msg);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // 텍스트 필드 초기화
        jtf.setText("");
    }

    /*
     * 서버로부터 넘어온 메시지 GUI 에 부착
     */
	public void apppendMsg(String msg) {
        System.out.println("날아온 메시지 || " + msg);
        jta.append(msg);
	}
}
