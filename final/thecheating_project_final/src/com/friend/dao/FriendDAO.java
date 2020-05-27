package com.friend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.MemberDTO;

public class FriendDAO {

	DataSource ds; // => Context.xml <Resource type="~~">

	// 생성자 : DataSource 얻기 : InitialContext와 JNDI명
	public FriendDAO() {
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

	// --------------------------------------------------------
	// --------------------------friend------------------------
	// --------------------------------------------------------
	// 친구 검색하기
	public ArrayList friendsearch(String name, String value, String mem_email, String friend_idx) {
		
		ArrayList list = new ArrayList();

		try {
			String sql = "select mem_email, mem_nickname, mem_username from member";
			sql += " where " + name + " = '" + value;
			sql += "' and " + name + " not in ('" + mem_email + "')";
			sql += " and " + name + " not in (select friend_friend from a" + friend_idx + " where friend_friend = '" + value + "')";
			sql += " and " + name + " not in (select friend_request from a" + friend_idx + " where friend_request = '" + value + "')";
			sql += " and " + name + " not in (select friend_response from a" + friend_idx + " where friend_response = '" + value + "')";
			System.out.println("친구검색 완료 : " + sql);
			
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberDTO data = new MemberDTO();

				data.setMem_email(rs.getString("mem_email"));
				data.setMem_nickname(rs.getString("mem_nickname"));
				data.setMem_username(rs.getString("mem_username"));

				list.add(data);

			}
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 친구신청하기
	public void friendRequest(String mem_idx, String mem_email) {

		String sql = "insert into a" + mem_idx + " (" + "friend_idx, friend_request, friend_regdate) values " + "(a"
				+ mem_idx + "_seq.nextval, '" + mem_email + "', sysdate)";
		System.out.println("친구신청 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 친구신청 받은사람
	public void friendResponse(String mem_idx, String mem_email) {

		String sql = "insert into a" + mem_idx + " (" + "friend_idx, friend_response, friend_regdate) values " + "(a"
				+ mem_idx + "_seq.nextval, '" + mem_email + "', sysdate)";
		System.out.println("친구신청 보내기 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 친구신청온 목록
	public ArrayList friendRequestList(String mem_idx) {

		ArrayList list = new ArrayList();

		String sql = "select mem_email, mem_username, mem_nickname, mem_regdate from member where mem_email in ("
				+ "select friend_response from a" + mem_idx + ")";
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberDTO data = new MemberDTO();

				data.setMem_email(rs.getString("mem_email"));
				data.setMem_username(rs.getString("mem_username"));
				data.setMem_nickname(rs.getString("mem_nickname"));
				data.setMem_regdate(rs.getString("mem_regdate"));

				list.add(data);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	// 친구신청한 목록
	public ArrayList friendResponseList(String mem_idx) {
		ArrayList list = new ArrayList();

		String sql = "select mem_email, mem_username, mem_nickname, mem_regdate from member where mem_email in ("
				+ "select friend_request from a" + mem_idx + ")";

		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberDTO data = new MemberDTO();

				data.setMem_email(rs.getString("mem_email"));
				data.setMem_username(rs.getString("mem_username"));
				data.setMem_nickname(rs.getString("mem_nickname"));
				data.setMem_regdate(rs.getString("mem_regdate"));

				list.add(data);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 친구 FriendIDX 고유테이블값 찾기
	public String friendFriendIDX(String mem_idx, String mem_email) {
		String sql = "select friend_idx from a" + mem_idx + " where friend_friend = '" + mem_email + "'";
		String result = null;
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("friendFriendIDX = " + sql);
			rs.next();
			result = rs.getString("friend_idx");
			rs.close();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 친구  ResponseIDX 고유테이블값 찾기
	public String friendResponseIDX(String mem_idx, String mem_email) {
		String sql = "select friend_idx from a" + mem_idx + " where friend_response = '" + mem_email + "'";
		String result = null;
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("friendResoponseIDX = " + sql);
			rs.next();
			result = rs.getString("friend_idx");
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 친구 RequestIDX 고유테이블값 찾기
	public String friendRequestIDX(String mem_idx, String mem_email) {
		String sql = "select friend_idx from a" + mem_idx + " where friend_request = '" + mem_email + "'";
		String result = null;

		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			System.out.println("friendRequestIDX : " + sql);
			
			rs.next();
			result = rs.getString("friend_idx");
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 친구신청 받은 사람이 친구 수락시 수정
	public void friendYES(String mem_idx, String mem_email, String friend_idx) {

		String sql = "update a" + mem_idx + " set friend_response = '1', friend_friend = '" + mem_email
				+ "', friend_okdate = sysdate where friend_idx = " + friend_idx;
		System.out.println("친구수락 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// 친구수락시 친구신청 한 사람 테이블 수정
	public void friendYES2(String mem_idx, String mem_email, String friend_idx) {

		String sql = "update a" + mem_idx + " set friend_request = '1', friend_friend = '" + mem_email
				+ "', friend_okdate = sysdate where friend_idx = " + friend_idx;
		System.out.println("상대방 친구수락 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// 친구 전체 목록
	public ArrayList friendList(String mem_idx) {

		ArrayList list = new ArrayList();

		String sql = "select mem_email, mem_username, mem_nickname from member where mem_email in ("
				+ "select friend_friend from a" + mem_idx + ")";

		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				MemberDTO data = new MemberDTO();

				data.setMem_email(rs.getString("mem_email"));
				data.setMem_username(rs.getString("mem_username"));
				data.setMem_nickname(rs.getString("mem_nickname"));

				list.add(data);
			}
			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 친구요청 받은 사람의 친구신청 데이서 삭제(친구신청 거절)
	public void friendNO(String mem_idx, String friend_idx) {

		String sql = "delete from a" + mem_idx + " where friend_idx = " + friend_idx;
		System.out.println("친구신청 거절 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 친구요청 한 사람의 친구신청 데이서 삭제(친구신청 거절)
	public void friendNO2(String mem_idx, String friend_idx) {

		String sql = "delete from a" + mem_idx + " where friend_idx = " + friend_idx;
		System.out.println("상대방 친구신청 거절 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();

			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	

	// 친구삭제 (자신의 친구테이블에서 삭제)
	public void friendRequestDelete(String mem_idx, String friend_idx) {
		
		String sql = "delete from a" + mem_idx + " where friend_idx = " + friend_idx;
		System.out.println("친구삭제 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
						
	}

	// 친구삭제 (상대방의 친구테이블에서 삭제)
	public void friendResponseDelete(String mem_idx, String friend_idx) {
		
		String sql = "delete from a" + mem_idx + " where friend_idx = " + friend_idx;
		System.out.println("상대방 친구삭제 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
