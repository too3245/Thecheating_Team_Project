package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.MemberDTO;
import com.member.util.SHA256Util;

/**
 * 각 쿼리를 실제로 수행하는 class
 * 생성자를 통해 Context로 부터 DB 접속
 * 조회, 검색, 수정, 삭제 등의 쿼리를 수행하고
 * 결과를 반환
 * 
 * 각 Controller에서 호출되는 메서드들을 보유
 * 
 * */


/** Connection, PreparedStatement, 쿼리 실행관련*/

public class MemberDAO {
DataSource ds; // => Context.xml <Resource type="~~">
	
	// 생성자 : DataSource 얻기 : InitialContext와 JNDI명
	public MemberDAO() {
		try {
			// InitialContext ctx = new InitialContext(); 이것도 가능
			// 이유 : InitialContext implements Context
			Context ctx = new InitialContext();
			
			// lookup("java:comp/env/찾고자하는 JNDI이름")
			// 찾고자하는 JNDI이름 : => Context.xml <Resource name="~~">
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds : " + ds);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 회원가입
	public void memberJoin(String mem_email, String mem_password, String mem_username, 
			String mem_nickname, String  mem_sex, String mem_birthday, String mem_phone, String mem_joinip) {
		String sql = "insert into member (mem_idx, mem_email, mem_password, mem_username, mem_nickname, mem_birthday, "
				+ "mem_sex, mem_phone, mem_joinip, mem_salt) values (member_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		String salt = SHA256Util.generateSalt();
		mem_password = SHA256Util.getEncrypt(mem_password, salt);
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			stmt.setString(2, mem_password);
			stmt.setString(3, mem_username);
			stmt.setString(4, mem_nickname);
			stmt.setString(5, mem_birthday);
			stmt.setString(6, mem_sex);
			stmt.setString(7, mem_phone);
			stmt.setString(8, mem_joinip);
			stmt.setString(9, salt);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("memberJoin ERR : " + e.getMessage());
		}
		
	}
	
	// 친구테이블명 리턴
	public String memberIDX(String mem_email) {
		String sql = "select mem_idx from member where mem_email = ?";
		String result = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			result = rs.getString("mem_idx");
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	// 친구테이블 생성
	public void firendInsert(String mem_idx) {
		String sql = "create table a" + mem_idx + " ("
				+ "friend_idx int primary key,"
				+ " friend_friend varchar2(40) unique,"
				+ " friend_request varchar2(40),"
				+ " friend_response varchar2(40),"
				+ " friend_regdate date,"
				+ " friend_okdate date,"
				+ " friend_deldate date)";
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			
			System.out.println(sql + " 생성");
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 친구테이블 시퀀스 생성
	public void friendSeq(String mem_idx) {
		String sql = "create sequence a" + mem_idx + "_seq "
				+ "start with 120000 increment by 1 maxvalue 129999 nocache";
		
		Connection con;
		try {
			con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			
			System.out.println(sql + " 생성");
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
				
	}
	
	
	
	// 로그인
	public String memberLogin(String mem_email, String mem_password) {
		
		String sql = "select * from member where mem_email = ? and mem_password = ?";
		String result = null;
		
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, mem_email);
			stmt.setString(2, mem_password);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = "1";
			} else {
				result = "아이디 또는 비밀번호가 틀립니다.";
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			System.out.println("memberLogin ERR : " + e.getMessage());
		}
		return result;
	}
	
	// salt값을 가져오는 메소드
	public String memberSalt(String mem_email) {
		
		String sql = "select mem_salt from member where mem_email = ?";
		String salt = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			salt = rs.getString("mem_salt");
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("salt ERR : " + e.getMessage());
		}
		return salt;
	}
	
	
	
	// 로그인 세션값 지정
	public MemberDTO loginSession(String mem_email, String mem_password) {
		
		String sql = "select mem_idx, mem_email, mem_password, mem_username, mem_nickname,"
				+ " mem_birthday, mem_sex, mem_phone, mem_loginip, seq_num from member"
				+ " where mem_email = ? and mem_password = ?";
		MemberDTO memberDTO = new MemberDTO();
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			stmt.setString(2, mem_password);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			memberDTO.setMem_idx(rs.getString("mem_idx"));
			memberDTO.setMem_email(rs.getString("mem_email"));
			memberDTO.setMem_password(rs.getString("mem_password"));
			memberDTO.setMem_username(rs.getString("mem_username"));
			memberDTO.setMem_nickname(rs.getString("mem_nickname"));
			memberDTO.setMem_birthday(rs.getString("mem_birthday"));
			memberDTO.setMem_sex(rs.getString("mem_sex"));
			memberDTO.setMem_phone(rs.getString("mem_phone"));
			memberDTO.setMem_loginip(rs.getString("mem_loginip"));
			memberDTO.setSeq_num(rs.getString("seq_num"));
			
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("loginSession ERR : " + e.getMessage());
		}
		
		return memberDTO;
	}
	
	// 이메일 중복확인
	public String emailCheck(String mem_email) {
		String sql = "select mem_email from member where mem_email = ?";
		String result = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = "<script>" + 
						"alert('이미 존재하는 이메일입니다.');" 
						+ "</script>";
			} else {
				result = "<script>" +
						"alert('사용 가능한 이메일입니다.');" 
						+ "</script>";
			}
				
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("emailCheck ERR : " + e.getMessage());
		}
		return result;
	}
	
	// 계좌체크
	public String accountCheck(String holder, String account_num, int bank_name, String val_date) {
		
		String sql = "select holder, account_num, bank_name, val_date from acc_check where holder = ? and account_num = ? and bank_name = ? and val_date = to_date(?,'yyyy-mm-dd')";
		System.out.println("accountCheck : " + holder);
		System.out.println("accountCheck : " + account_num);
		System.out.println("accountCheck : " + bank_name);
		System.out.println("accountCheck : " + val_date);
		String result = null;
		System.out.println("계좌 체크 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, holder);
			stmt.setString(2, account_num);
			stmt.setInt(3, bank_name);
			stmt.setString(4, val_date);
			ResultSet rs = stmt.executeQuery();
			
			System.out.println(rs.next());
			if(rs.next()) {
				result = "일치하는 계좌가 없습니다.";
				System.out.println(result);
				
			} else {
				result = "1";
				System.out.println(result);
			}
			
			rs.close();
			stmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			System.out.println("accountCheck ERR : " + e.getMessage());
		}
		
		
		return result;
	}
	
	// 이메일 찾기
	
	public String emailFind(String mem_username, String mem_birthday, String mem_phone) {
		
		String sql = "select mem_email from member where mem_username = ? and mem_birthday = ? and mem_phone = ?";
		String result = null;
		System.out.println("이메일 찾기 성공 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_username);
			stmt.setString(2, mem_birthday);
			stmt.setString(3, mem_phone);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("mem_email");
			} else {
				result = "정확히 입력하세요.";
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("emailFind ERR : " + e.getMessage());
		}
		
		return result;
		
	}
	
	
	// 비밀번호 찾기
	
	public String passwordFind(String mem_email, String mem_username, String mem_birthday, String mem_phone) {
		
		String sql = "select mem_password from member where mem_email = ? and mem_username = ? and mem_birthday = ? and mem_phone = ?";
		String result = null;
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			stmt.setString(2, mem_username);
			stmt.setString(3, mem_birthday);
			stmt.setString(4, mem_phone);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = "1";
			} else {
				result = "2";
			}
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("passwordFind ERR : " + e.getMessage());
		}
		
		return result;
	}
	
	// 비밀번호 변경
	
	public void passwordChange(String mem_password, String mem_email, String mem_username, String mem_birthday, String mem_phone) {
		
		String sql = "update member set mem_password = ?, mem_salt = ? where mem_email = ? and mem_username = ? and mem_birthday = ? and mem_phone = ?";
		
		String salt = SHA256Util.generateSalt();
		mem_password = SHA256Util.getEncrypt(mem_password, salt);
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_password);
			stmt.setString(2, salt);
			stmt.setString(3, mem_email);
			stmt.setString(4, mem_username);
			stmt.setString(5, mem_birthday);
			stmt.setString(6, mem_phone);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("passwordChange ERR : " + e.getMessage());
		}
		
		
		
	}
	
	
	// 프로필정보 변경
	public void profileModify(String mem_nickname, String mem_birthday, String mem_phone, String mem_email, String mem_username) {
		
		String sql = "update member set mem_nickname = ?, mem_birthday = ?, mem_phone = ? where mem_email = ? and mem_username = ?";
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_nickname);
			stmt.setString(2, mem_birthday);
			stmt.setString(3, mem_phone);
			stmt.setString(4, mem_email);
			stmt.setString(5, mem_username);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			System.out.println("profileModify ERR : " + e.getMessage());
		}
		
	}


	
}
