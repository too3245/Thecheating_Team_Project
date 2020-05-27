package com.event.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.event.command.EventCommand;
import com.event.dto.EventDTO;


/**
 *  EVENT - 업데이트 게시판 DAO 클래스
 */	
public class EventDAO {
	DataSource ds;
	
	private Connection con = null; // 사용할 객체 초기화
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/* JDBC Connection */	
	public EventDAO() {
		try {
			Context ctx = new InitialContext();
			
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds : " + ds);
	
		} catch (Exception e) {
			System.out.println("JDBC ERR : " + e.getMessage());

		}
	} // EventDAO() END
	
	/**
	 *  검색 메소드 & 글 갯수 카운트 메소드
	 * 
	 *  총 게시글 수를 조회하여 전체검색일 경우 글 제목과 작성자를 모두 검색
	 *  제목 검색일 경우에는 제목만 검색
	 *  작성자 검색일 경우에는 작성자만 검색
	 */
	public int selectCount(String SearchType,String SearchText){
		
		int totalCount = 0; // 게시글 전체 갯수
		String searchType = SearchType; // 검색종류(전체검색/작성자/제목)
		String searchText = SearchText; // SearchText : 실제 검색어
		String whereSQL ="";  // 실행할 쿼리문 변수 지정

		try {
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE event_title LIKE '%'||?||'%' OR event_author LIKE '%'||?||'%'"; // 글 제목과 작성자 모두 검색하는 쿼리문
				}else if(searchType.equals("event_title")){//제목검색일 경우 
					whereSQL =" WHERE event_title LIKE '%'||?||'%'"; // 제목 게시글 중에서 검색어로 입력 받아 검색
				}else if(searchType.equals("event_author")){//작성자 검색일 경우
					whereSQL =" WHERE event_author LIKE '%'||?||'%'"; // 작성자 게시글 중에서 검색어로 입력 받아 검색
				}
			}
			
			String query = "SELECT COUNT(event_idx) AS TOTAL FROM event"+whereSQL;
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(query);
			
			if(!"".equals(whereSQL)){  //검색어가 있는 경우에만
				if("ALL".equals(searchType)){  // 전체 검색일 경우 - 검색 받는 검색어 2가지(글제목, 작성자에서 동시 검색)
					this.pstmt.setString(1,searchText);
					this.pstmt.setString(2,searchText);
				}else{ // 그 외 검색일 경우 - 작성자 검색 / 제목 검색
					this.pstmt.setString(1,searchText);
				}
			}
			this.rs = this.pstmt.executeQuery();
			if(rs.next()){
				totalCount = rs.getInt("TOTAL");
			}
			
		} catch (SQLException e) {
			System.out.println("selectCount ERR : " + e);
		}	finally {
			this.close(rs, pstmt, con);
		}
		
		return totalCount;//결과 값 반환 / BoardListServlet의 doGet()에게
	}// selectCount()END
	
	/**
	 *  글목록 조회 & 페이지 넘버 설정해주는 메소드
	 *  
	 *  한 페이지당 게시글 갯수 : 10개
	 *  페이지 갯수 : 10개  ex) 1~10, 11~20
	 */
	public ArrayList<EventDTO> list(String SearchType,String SearchText,int PageNum,int lastNum) {
		ArrayList<EventDTO> list = new ArrayList<EventDTO>();
		
		String searchText = SearchText; // 실제 검색어
		String searchType = SearchType; // 검색 종류
		String whereSQL =""; // 실행할 쿼리문
		int listCount = 10; // 게시글 갯수 : 10개
		int pageNum = PageNum; // 페이지 갯수
		int last_Num = lastNum; // 마지막 페이지 번호
		int sv_num = (int) Math.ceil(last_Num/10.0); // 마지막 페이지 번호를 10으로 나누고 정수로 바꿔주기!
		int last_num = last_Num-(10*(pageNum-1)); // 마지막 페이지 번호 - ( 10 * (페이지번호 - 1));
		int first_num = last_num-9; // 첫번째 페이지 번호
		
		if(first_num <0){ // 작성된 게시글이 없다면 페이지 번호 부여 X
			first_num = 0;
		}
		System.out.println("페이지"+pageNum);
		System.out.println("처음"+first_num);
		System.out.println("마지막"+last_num);
		
		try {
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE event_title LIKE '%'||?||'%' OR event_author LIKE '%'||?||'%'";
				}else if(searchType.equals("event_title")){//제목검색일 경우
					whereSQL =" WHERE event_title LIKE '%'||?||'%'"; // 제목만 검색
				}else if(searchType.equals("event_author")){//작성자 검색일 경우
					whereSQL =" WHERE event_author LIKE '%'||?||'%'"; // 작성자만 검색
				}
			}
			
			String first="SELECT * FROM (select row_number() over (order by event_idx) num, A.* FROM event A ";
			String end = " ORDER BY event_idx DESC) where num between ? and ?";
			String query= first+ whereSQL+end;
			System.out.println(query);
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(query);
			
			if(!"".equals(whereSQL)){ //검색 쿼리가 있는 경우에만
				if(searchType.equals("ALL")){  //전체 검색일 경우 ? 다섯 개
					this.pstmt.setString(1,searchText);
					this.pstmt.setString(2,searchText);
					this.pstmt.setInt(3,listCount*(pageNum-1)+1);  //페이지의 시작 번호
					this.pstmt.setInt(4, listCount*pageNum);  //조회 갯수					
					System.out.println("all");
					
				}else{  //그외의 검색일 경우 ?가 세개
					this.pstmt.setString(1,searchText);
					this.pstmt.setInt(2,listCount*(first_num-1)+1);
					this.pstmt.setInt(3, listCount*pageNum);
					System.out.println("other");
				}
			}else{
				this.pstmt.setInt(1, first_num);
				this.pstmt.setInt(2, last_num);
				System.out.println("no");
			}
			
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				EventDTO data = new EventDTO();
				data.setEvent_idx(rs.getInt("event_idx")); // 글번호
				data.setEvent_author(rs.getString("event_author")); // 작성자
				data.setEvent_title(rs.getString("event_title")); // 글제목
				data.setEvent_content(rs.getString("event_content")); // 글내용
				data.setEvent_writeday(rs.getString("event_writeday")); // 글 작성일
				data.setEvent_hit(rs.getInt("event_hit")); // 조회수
				
				list.add(data);
			} // end while

		} catch (Exception e) {
			System.out.println("list ERR : " + e);
		} finally {
			this.close(rs, pstmt, con);
			
		}

		return list;	// ListActionController 로 반환
	} // list() END
	
	/* 글쓰기 메소드 : 글제목, 글쓴이, 글내용 */
	public void write(String event_title, String event_author, String event_content) {
		try {
			String sql = "INSERT INTO event (event_idx, event_title, event_author, event_content) VALUES (event_seq.NEXTVAL, ?, ?, ?)";	
			System.out.println(sql); // 한글 깨지는지 확인
			System.out.println("insert into 쿼리문 작동중 !!");
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			
			this.pstmt.setString(1, event_title); // 글제목 
			this.pstmt.setString(2, event_author); // 작성자
			this.pstmt.setString(3, event_content);  // 글내용
			this.pstmt.executeUpdate();
			
			System.out.println("write 작동 중!!");
			
			} catch (SQLException e) {

				System.out.println("WRITE ERR : " +  e.getMessage());
				
			} finally {
				this.close(null, pstmt, con);
			}

	} // write() END
	
	/* 글 상세보기 메소드 */
	public EventDTO retrieve(String event_idx) {
		EventDTO data = new EventDTO();
		
		try {
			// 1. 조회수 증가시키기
			String sql = "UPDATE event SET event_hit=event_hit+1 WHERE event_idx=?";
			this.con = ds.getConnection();
			this.pstmt  = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(event_idx));
			int update = this.pstmt.executeUpdate();
			System.out.println("eventDAO 에서 retreive 조회수 증가유무(update) ");
			
			// 2. 글 번호를 이용하여 선택된 글만 조회
			this.pstmt = null; // 한 개의 pstmt를 2번 (재사용) 쓸 수 있는 방법(null 한번 시켜서 초기화)
			sql = "SELECT * FROM event WHERE event_idx=?";
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(event_idx));
			this.rs = this.pstmt.executeQuery();
			
			if (rs.next()) {
				data.setEvent_idx(rs.getInt("event_idx"));
				data.setEvent_title(rs.getString("event_title"));
				data.setEvent_author(rs.getString("event_author"));
				data.setEvent_content(rs.getString("event_content"));
				
			} // if END

		} catch (SQLException e) {
			System.out.println("RETRIEVE ERR : " + e.getMessage());
			
		} finally {
			this.close(rs, pstmt, con);
		}
		
		return data;
		
	} // UpDTO retrieve() END
	
	/* 글 수정하기 메소드 */
	public void update(String event_idx, EventCommand data) {
		
		String sql = "UPDATE event SET event_title=?, event_content=? WHERE event_idx=?";
		System.out.println(sql);
		
		try {
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setString(1, data.getEvent_title());  // 글제목
			this.pstmt.setString(2, data.getEvent_content()); // 글내용
			this.pstmt.setInt(3, Integer.parseInt(event_idx) + 330000);  // 글번호
			this.pstmt.executeUpdate();

			
		} catch (SQLException e) {
			System.out.println("UPDATE ERR : " + e);
			
		} finally {
			this.close(null, pstmt, con);
		}
		
	} // update() END
	
	/* 글 삭제하기 */
	public void delete(String event_idx) {
		
		try {
			String sql = "DELETE event WHERE event_idx=?";
			System.out.println(sql);
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1,  Integer.parseInt(event_idx));
			this.pstmt.executeUpdate();
		
		} catch (NumberFormatException nfe) {
			System.out.println("DELETE NUMBER FORMAT ERR : " + nfe);
			
		} catch (SQLException e) {
			System.out.println("DELETE ERR : " + e);
			
		} finally {
			this.close(null, pstmt, con);
		}

	} // delete() END	
	
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("rs close() err : " + e.getMessage());
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("pstmt close() err : " + e.getMessage());
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("con close() err : " + e.getMessage());
			}
		}

	} // close () END	
	

} // Class END
