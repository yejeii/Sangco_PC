package com.arahansa.asset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberClientByJDBCTest {
    public static void main(String[] args) {
        boolean test = loginTest("ulla", "ulla202");
        System.out.println("로그인 결과 : " + test);
    }   

    public static boolean loginTest(String id, String pw) {
        boolean flag = false;

        DBConnectionMgr pool = DBConnectionMgr.getInstance();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String getPass = null;

        try {
            con = pool.getConnection();
            
            sql = "select pw from member_test where id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                getPass = rs.getString(1);
                if(getPass.equals(pw)) {
                    System.out.println("받아온 비밀번호 : " + getPass);
                    flag = true;    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(con, pstmt, rs);
        }

        return flag;
    }
}

