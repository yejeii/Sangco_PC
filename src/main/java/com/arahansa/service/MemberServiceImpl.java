/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.arahansa.service;

import com.arahansa.dao.MemberDAO;
import com.arahansa.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO = new MemberDAO(); 

    public MemberServiceImpl() {
    }

    @Override
    public boolean checkMember(String input_id, String input_pw) throws Exception {
        return memberDAO.checkMember(input_id, input_pw);
    }

    @Override
    public MemberVO getMember(String input_id, String input_pw) throws Exception {
        return memberDAO.getMember(input_id, input_pw);
    }

   

}
