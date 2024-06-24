package com.arahansa.controller;

import com.arahansa.service.MemberService;
import com.arahansa.vo.MemberVO;

public class LoginController {

    private MemberService service;

    public LoginController(MemberService service) {
        this.service = service;
    }

    public boolean checkLogin(String input_id, String input_pw) {
        boolean flag = false;

        try {
            flag = service.checkMember(input_id, input_pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;

    }
    
    public MemberVO getMember(String input_id, String input_pw) {
        MemberVO member = null;

        try {
            return member = service.getMember(input_id, input_pw);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return member;
    }
}
