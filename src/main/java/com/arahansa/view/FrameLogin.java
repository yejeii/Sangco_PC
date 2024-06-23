package com.arahansa.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.arahansa.Main;
import com.arahansa.dao.H2DB_Initializer;
import com.arahansa.dao.LoginDAO;

public class FrameLogin extends JFrame implements ActionListener{

    private BufferedImage img = null;
    private JTextField loginTextField;
    private JPasswordField passwordField;
    private static LoginDAO loginDAO;
    private JButton bt;
    private Main main;

    // 메인
    public static void main(String[] args) throws Exception {
        FrameLogin loginFrame = new FrameLogin();

        // DB 초기화
        H2DB_Initializer hdbi = new H2DB_Initializer();
        hdbi.initDatabase();
        loginDAO = new LoginDAO();
        loginDAO.insertUser();
        
        loginFrame.setLoginDAO(loginDAO);
    }

    // 생성자 : 프레임 세팅
    public FrameLogin() {
        setTitle("로그인테스트");
        setSize(1600, 900); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 레이아웃 설정
        setLayout(null);    
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,1600, 900);
        layeredPane.setLayout(null);
        
        // 이미지 받아오기
        try {
            img = ImageIO.read(new File("src/main/resources/img/login.png"));
        } catch (IOException e) {
            System.err.println("이미지 불러오기 실패");
            System.exit(0);
        }
        
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
        bt.addActionListener(this); // 액션 걸림
        
        // 버튼 투명처리
        bt.setBorderPainted(false);
        bt.setFocusPainted(false);
        bt.setContentAreaFilled(false);
        layeredPane.add(bt);

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

    public void setMain(Main main) {
        this.main = main;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("액션 체크됨");
        var input_id = loginTextField.getText();
        var input_pw = passwordField.getPassword();

        if(input_id.equals("") || new String(input_pw).equals("")) {
            JOptionPane.showMessageDialog(null, 
                "아이디나 비밀번호를 입력해주세요", 
                "아이디나 비밀번호 입력",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            // DB 와 비교
            boolean loginCheck = loginDAO.loginCheck(input_id, new String(input_pw));
            System.out.println("로그인 성공여부 : " + loginCheck);

            if(loginCheck) {
                JOptionPane.showMessageDialog(null, 
                "환영합니다.", 
                "로그인 성공",
                JOptionPane.INFORMATION_MESSAGE);

                // LoingFrame 페이지 close, 매니저 뷰 화면 요청
                main.showManageFrame(this);
            } else {
                JOptionPane.showMessageDialog(null, 
                "아이디나 비밀번호가 틀립니다.", 
                "로그인 실패",
                JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
