package com.arahansa;

import java.sql.SQLException;

import com.arahansa.asset.Setting;
import com.arahansa.dao.H2DB_Initializer;
import com.arahansa.dao.LoginDAO;
import com.arahansa.view.FrameLogin;
import com.arahansa.view.FrameManage;

public class Main {

    private FrameManage mainFrame;
    private static FrameLogin loginFrame;

    private static LoginDAO loginDAO;

    public static void main(String[] args) throws SQLException, Exception {
        
        Main main = new Main();
		main.loginFrame = new FrameLogin();
		main.loginFrame.setMain(main);

        //database 초기화(테이블 만듦)
        H2DB_Initializer hdInitializer = new H2DB_Initializer();
        hdInitializer.initDatabase();

        //login을 위한 데이터 액세스 오브젝트 만들고, 사용자 입력.
        loginDAO = new LoginDAO();
        loginDAO.insertUser();

        //프레임에 dao 주입
        loginFrame.setLoginDAO(loginDAO);
    }

    public void showManageFrame(FrameLogin loginFrame) {
        loginFrame.dispose();   // close
        
        mainFrame = new FrameManage();
        try {
            mainFrame.setRectangles(FrameManage.class, mainFrame, Setting.class, Setting.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
