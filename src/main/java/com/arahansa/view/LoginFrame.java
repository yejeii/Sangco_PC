package com.arahansa.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

    BufferedImage img = null;
    JTextField loginTextField;
    JPasswordField passwordField;
    JButton bt;

    // 메인
    public static void main(String[] args) {
        new LoginFrame();
    }

    // 생성자 : 프레임 세팅
    public LoginFrame() {
        setTitle("로그인테스트");
        setSize(1600, 900); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);    

        // 이미지 받아오기
        try {
            img = ImageIO.read(new File("src/main/resources/img/login.png"));
        } catch (IOException e) {
            System.err.println("이미지 불러오기 실패");
            System.exit(0);
        }

        // 레이아웃 설정
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1600, 900);
        layeredPane.setLayout(null);

        // 패널 1
        MyPanel panel = new MyPanel();
        panel.setBounds(0, 0, 1600, 900);

        // 로그인 필드
        loginTextField = new JTextField(15);
        loginTextField.setBounds(731, 399, 280, 30);
        loginTextField.setOpaque(false);    // 투명처리
        loginTextField.setForeground(Color.white);  // 글자색
        loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());    // 경계선 삭제
        
        layeredPane.add(loginTextField);
        
        // 비밀번호
        passwordField = new JPasswordField();
        passwordField.setBounds(731, 529, 280, 30);
        passwordField.setOpaque(false);
        passwordField.setForeground(Color.white);
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        layeredPane.add(passwordField);

        // 버튼 추가
        bt = new JButton(new ImageIcon("src/main/resources/img/btLogin_hud.png"));
        bt.setBounds(755, 689, 104, 48);
        
        // 버튼 투명처리
        bt.setBorderPainted(false);
        bt.setFocusPainted(false);
        bt.setContentAreaFilled(false);
        layeredPane.add(bt);

        // 패스워드

        // 마지막 추가
        layeredPane.add(panel);
        add(layeredPane);

        setVisible(true);
    }

    class MyPanel extends JPanel {
        public void paint(Graphics g) {
            g.drawImage(img, 0, 0, null);
        }
    }
}
