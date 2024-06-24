package com.arahansa.service;

import com.arahansa.vo.MemberVO;

public interface MemberService {

    boolean checkMember(String input_id, String input_pw) throws Exception;
    MemberVO getMember(String input_id, String input_pw) throws Exception;
}
