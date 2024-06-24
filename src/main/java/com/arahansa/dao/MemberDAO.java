package com.arahansa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.arahansa.util.DBConnectionMgr;
import com.arahansa.vo.MemberVO;

public class MemberDAO {

	// JDBC 변수 선언
	private DBConnectionMgr pool = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = null;

	public boolean checkId(String input_str) {
		boolean resultFlag = false;
		pool = DBConnectionMgr.getInstance();
		
		try {
			conn = pool.getConnection();
			sql = "SELECT id FROM MEMBER WHERE id= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_str);
			rs = pstmt.executeQuery();
			
			resultFlag = rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}

		return resultFlag;
	}	

	public boolean checkMember(String input_id, String input_pw) {
		pool = DBConnectionMgr.getInstance();
		String getPass = null;
		boolean flag = false;

		try {
			conn = pool.getConnection();
			sql = "SELECT pw FROM MEMBER WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				getPass = rs.getString("pw");
				if (getPass.equals(input_pw)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			// 원래 예외처리는 크게 잡으면 안되고, 따로 처리가 되어야 합니다.
			e.printStackTrace();
		} finally {
			// 자원반납
			pool.freeConnection(conn, pstmt, rs);
		}
		return flag;

	}

	public MemberVO getMember(String input_id, String input_pw) {
		pool = DBConnectionMgr.getInstance();
		MemberVO member = null;

		try {
			conn = pool.getConnection();
			sql = "SELECT * FROM MEMBER WHERE id = ? and pw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, input_id);
			pstmt.setString(2, input_pw);
			if(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setAge(rs.getInt("age"));
				member.setPhone(rs.getString("phone"));
			}
		} catch (Exception e) {
			// 원래 예외처리는 크게 잡으면 안되고, 따로 처리가 되어야 합니다.
			e.printStackTrace();
		} finally {
			// 자원반납
			pool.freeConnection(conn, pstmt, rs);
		}
		return member;
	}

	public int insertMember(MemberVO vo) throws Exception {
		pool = DBConnectionMgr.getInstance();
		conn = pool.getConnection();
		
		sql = "INSERT INTO MEMBER (id, pw, age, phone) values (?, ?, ?, ?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPw());
		pstmt.setInt(3, vo.getAge());
		pstmt.setString(4, vo.getPhone());
		return pstmt.executeUpdate();
	}

	
}
