package com.hy.Chat.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import com.hy.Chat.ChatCommand;
import com.hy.Chat.DTO.ChatDTO;
import com.hy.Chat.DTO.MoneyDTO;

public class ChatDAO {

	DataSource ds;
	public ChatDAO(){
		try {
			// InitialContext ctx=new InitialContext(); 이것도 가능.
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
	public int selectCount(String SearchType,String SearchText){
		
		int totalCount = 0;
		String searchType = SearchType;
		String searchText = SearchText;
		String whereSQL ="";
		
		
		
		
		try {
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE CHAT_NAME LIKE '%'||?||'%' OR chat_leader LIKE '%'||?||'%'";
				}else if(searchType.equals("NAME")){//제목검색일 경우
					whereSQL =" WHERE CHAT_NAME LIKE '%'||?||'%'";
				}else if(searchType.equals("LEADER")){//작성자 검색일 경우
					whereSQL =" WHERE chat_leader LIKE '%'||?||'%'";
				}
			}
			
			String query = "SELECT COUNT(chat_idx) AS TOTAL FROM chating"+whereSQL;
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			if(!"".equals(whereSQL)){//검색어가 있는 경우에만
				if("ALL".equals(searchType)){//전체 검색일 경우? 세개
					pstmt.setString(1,searchText);
					pstmt.setString(2,searchText);
				}else{//그외 검색일 경우? 한개
					pstmt.setString(1,searchText);
				}
			}
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				totalCount = rs.getInt("TOTAL");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalCount;//결과 값 반환...(BoardListServlet의 doGet()에게
	}// selectCount()END
	
	
	public ArrayList<ChatDTO> list(String SearchType,String SearchText,int PageNum,int lastNum) {
		ArrayList<ChatDTO> list = new ArrayList<ChatDTO>();
		String searchText = SearchText;
		String searchType = SearchType;
		String whereSQL ="";
		int listCount = 10;
		int pageNum = PageNum;
		int last_Num = lastNum;
		int sv_num = (int) Math.ceil(last_Num/10.0);
		int last_num = last_Num-(10*(pageNum-1));
		int first_num = last_num-9;
		
		if(first_num <0){
			first_num = 0;
		}
		System.out.println("페이지"+pageNum);
		System.out.println("처음"+first_num);
		System.out.println("마지막"+last_num);
		try {
			
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE CHAT_NAME LIKE '%'||?||'%' OR chat_leader LIKE '%'||?||'%'";
				}else if(searchType.equals("NAME")){//제목검색일 경우
					whereSQL =" WHERE CHAT_NAME LIKE '%'||?||'%'";
				}else if(searchType.equals("LEADER")){//작성자 검색일 경우
					whereSQL =" WHERE chat_leader LIKE '%'||?||'%'";
				}
			}
			
			String first="SELECT * FROM (select row_number() over (order by chat_idx) num, A.* FROM chating A ";
			String end = " ORDER BY chat_idx DESC) where num between ? and ?";
			String query= first+ whereSQL+end;
			System.out.println(query);
			Connection con = ds.getConnection();

			PreparedStatement pstmt = con.prepareStatement(query);
			if(!"".equals(whereSQL)){//검색 쿼리가 있는 경우에만
				if(searchType.equals("ALL")){//전체 검색일 경우 ? 다섯 개
					pstmt.setString(1,searchText);
					pstmt.setString(2,searchText);
					pstmt.setInt(3,listCount*(pageNum-1)+1);//페이지의 시작 번호
					pstmt.setInt(4, listCount*pageNum);//조회 갯수					
					System.out.println("all");
					
				}else{//그외의 검색일 경우 ?가 세개
					pstmt.setString(1,searchText);
					pstmt.setInt(2,listCount*(first_num-1)+1);
					pstmt.setInt(3, listCount*pageNum);
					System.out.println("other");
				}
			}else{
				pstmt.setInt(1, first_num);
				pstmt.setInt(2, last_num);
				System.out.println("no");
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ChatDTO data = new ChatDTO();
				data.setChatIdx(rs.getInt("chat_idx"));
				data.setChatName(rs.getString("chat_name"));
				data.setChatPassword(rs.getString("chat_password"));
				data.setChatSecret(rs.getInt("chat_secret"));
				data.setChatLeader(rs.getString("chat_leader"));
				data.setChatHead(rs.getInt("chat_head"));
				data.setChatLog(rs.getString("chat_log"));
				list.add(data);
			} // end while

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;	// ListActionController 로 반환
	} // list() END
	
	public void create(ChatCommand command){
		try{
			String sql ="INSERT INTO chating(chat_idx,chat_name,chat_password,chat_leader,chat_secret,chat_head) VALUES(CHATING_SEQ.NEXTVAL,?,?,?,?,?)";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, command.getChatName());
			pstmt.setString(2,command.getChatPassword());
			pstmt.setString(3, command.getChatHuman());
			pstmt.setInt(4, command.getChatSecret());
			pstmt.setInt(5, 1);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e){
			System.out.println("Create ERR"+e);
		}
	}
	public int searchID(String room,String id){
		String room_c = room;
		String id_c = id;
		int num = 0;
		try{
			String query = "SELECT COUNT(*) AS TOTAL FROM ChatingMoney WHERE room_idx = ? AND room_member = ?";
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, room_c);
			pstmt.setString(2,id_c);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt("TOTAL");
			}
			
			System.out.println("중복회원수"+num);
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e){
			System.out.println("Search ERR"+e);
		}
		return num;
	}
	public void insertRoom(String room,String id){
		String room_c = room;
		String id_c = id;
		try{
			String sql ="INSERT INTO ChatingMoney(room_idx,room_check,room_member,room_money) VALUES(?,0,?,0)";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, room_c);
			pstmt.setString(2,id_c);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e){
			System.out.println("Create ERR"+e);
		}
	}
	public void getMoney(String room,String title,String money){
		String room_g = room;
		String title_g = title;
		String money_g = money;
		try{
			String sql ="INSERT INTO getMoney(get_idx,room_idx,get_title,get_money,get_left) VALUES(getMoney_SEQ.NEXTVAL,?,?,?,?)";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, room_g);
			pstmt.setString(2,title_g);
			pstmt.setString(3,money_g);
			pstmt.setString(4,money_g);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e){
			System.out.println("getMoney ERR"+e);
		}
	}
	public ArrayList<MoneyDTO> money_choose(String room){
		String room_g = room;
		ArrayList<MoneyDTO> moneydtos = new ArrayList<MoneyDTO>();
		try{
			String sql ="Select get_idx,room_idx,get_title,get_left from getMoney where room_idx = ?";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, room_g);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				MoneyDTO moneydto = new MoneyDTO();
				moneydto.setMoneyIdx(rs.getInt("get_idx"));
				moneydto.setMoneyRoom(rs.getString("room_idx"));
				moneydto.setMoneyTitle(rs.getString("get_title"));
				moneydto.setLeftMoney(rs.getInt("get_left"));
				System.out.println(moneydto.getMoneyTitle());
				moneydtos.add(moneydto);
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("sendMoney ERR"+e);
		}
		return moneydtos;
	}
	public void sendMoney(int leftmoney,String get_idx,String room_idx,String send_user,String send_money){
		int leftMoney_g = leftmoney;
		String get_idx_g = get_idx;
		String room_idx_s = room_idx;
		String send_user_s = send_user;
		String send_money_s = send_money;
		try{
			String sql ="Update getMoney set get_left = ? where get_idx = ?";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,leftMoney_g);
			pstmt.setString(2, get_idx_g);
			pstmt.executeUpdate();
			sql ="INSERT INTO sendMoney(send_idx,room_idx,send_user,send_money,get_idx)values(sendMoney_SEQ.NEXTVAL,?,?,?,?)";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, room_idx_s);
			pstmt.setString(2, send_user_s);
			pstmt.setString(3, send_money_s);
			pstmt.setString(4, get_idx_g);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e){
			System.out.println("sendMoney ERR"+e);
		}
	}
	
	public ArrayList<MoneyDTO> delete_money_choose(String room){
		String room_g = room;
		ArrayList<MoneyDTO> moneydtos = new ArrayList<MoneyDTO>();
		try{
			String sql ="Select get_idx,room_idx,get_title,get_left from getMoney where get_left = 0 and room_idx = ?";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, room_g);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				MoneyDTO moneydto = new MoneyDTO();
				moneydto.setMoneyIdx(rs.getInt("get_idx"));
				moneydto.setMoneyRoom(rs.getString("room_idx"));
				moneydto.setMoneyTitle(rs.getString("get_title"));
				moneydto.setLeftMoney(rs.getInt("get_left"));
				System.out.println(moneydto.getMoneyTitle());
				moneydtos.add(moneydto);
			}
			rs.close();
			pstmt.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("sendMoney ERR"+e);
		}
		return moneydtos;
	}
	public void delete_money(String idx){
		String idx_g = idx;
		try{
			String sql ="delete from getMoney where get_idx = ?";
			System.out.println(sql);
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idx_g);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		}catch(Exception e){
			System.out.println("sendMoney ERR"+e);
		}
	}
	public ArrayList<String> memberList(String room){
		ArrayList<String> member = new ArrayList<String>();
		String room_c = room;
		try{
			String query = "SELECT room_member FROM ChatingMoney WHERE room_idx = ? ";
			Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, room_c);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String m ="";
				m = rs.getString("ROOM_MEMBER");
				member.add(m);
			}
			
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e){
			System.out.println("Search ERR"+e);
		}
		return member;
	}
}
