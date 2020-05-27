package com.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.member.dto.MemberDTO;

public class AccountDAO {
	
	DataSource ds; // => Context.xml <Resource type="~~">

	// 생성자 : DataSource 얻기 : InitialContext와 JNDI명
	public AccountDAO() {
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
	
	// 내가 등록한 카드 목록
	
	// 내가 등록한 계좌 목록
	public ArrayList AccountList(String seq_num) {
		ArrayList list = new ArrayList();

		String sql = "select bank_name, account_num, holder, val_date from "
				+ "acc_check where " + seq_num + " = seq_num";
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO data = new MemberDTO();
				data.setBank_name(rs.getString("bank_name"));
				data.setAccount_num(rs.getString("account_num"));
				data.setHolder(rs.getString("holder"));
				data.setVal_date(rs.getString("val_date"));
			
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

		
	// 계좌 seq_num 찾기
	public String AccountSeq_num(String holder) {
		String sql = "select seq_num from acc_check where holder = ?";
		System.out.println("계좌 seq_num : " + sql);
		String result = null;
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, holder);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			result = rs.getString("seq_num");
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	// 카드 등록하기
	
	// 계좌 등록하기
	public void AccountRegister(String seq_num, String mem_email) {
		String sql = "update member set seq_num = ? where mem_email = ?";
		System.out.println("계좌 등록 완료 : " + sql);
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, seq_num);
			stmt.setString(2, mem_email);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 내가 등록한 카드 삭제
	
	// 내가 등록한 계좌 삭제
	public void AccountDelete(String mem_email) {
		String sql = "update member set seq_num = 1 where mem_email = ?";
		
		try {
			Connection con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, mem_email);
			stmt.executeUpdate();
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
}
