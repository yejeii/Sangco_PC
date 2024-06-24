package com.arahansa.controller;

import com.arahansa.service.MemberServiceImpl;
import com.arahansa.view.LoginFrame;
import com.arahansa.view.ManagerFrame;

public class MainController {

    private static LoginFrame loginFrame;

    // 클라이언트 시작점 : 메인
    public static void main(String[] args) {
        MainController controller = new MainController();
        loginFrame = new LoginFrame(controller, new LoginController(new MemberServiceImpl()));
    }

    public void showManageFrame() {
        loginFrame.dispose();   // 창 닫기
        
        new ManagerFrame();
    }

}
