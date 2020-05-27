package com.up.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.up.command.UpCommand;
import com.up.dto.UpDTO;


/**
 *  UP - 업데이트 게시판 DAO 클래스
 */

public class UpDAO {
	DataSource ds;

	private Connection con = null; // 사용할 객체 초기화
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/* JDBC Connection */	
	public UpDAO() {
		try {
			Context ctx = new InitialContext();
			
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds : " + ds);
	
		} catch (Exception e) {
			System.out.println("JDBC ERR : " + e.getMessage());

		}
	} // UpDAO() END
	
	/**
	 *  검색 메소드 & 글 갯수 카운트 메소드
	 * 
	 *  총 게시글 수를 조회하여 전체검색일 경우 글 제목과 작성자를 모두 검색
	 *  제목 검색일 경우에는 제목만 검색
	 *  작성자 검색일 경우에는 작성자만 검색
	 */
	public int selectCount(String SearchType,String SearchText){
		
		int totalCount = 0;  // 게시글 전체 갯수
		String searchType = SearchType;  // 검색종류(전체검색/작성자/제목)
		String searchText = SearchText;  // SearchText : 실제 검색어
		String whereSQL =""; // 실행할 쿼리문 변수 지정

		try {
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE up_title LIKE '%'||?||'%' OR up_author LIKE '%'||?||'%'"; // 글 제목과 작성자 모두 검색하는 쿼리문
				}else if(searchType.equals("up_title")){//제목검색일 경우
					whereSQL =" WHERE up_title LIKE '%'||?||'%'"; // 제목 게시글 중에서 검색어로 입력 받아 검색
				}else if(searchType.equals("up_author")){//작성자 검색일 경우
					whereSQL =" WHERE up_author LIKE '%'||?||'%'"; // 작성자 게시글 중에서 검색어로 입력 받아 검색
				}
			}
			
			String query = "SELECT COUNT(up_idx) AS TOTAL FROM up"+whereSQL;
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(query);
			
			if(!"".equals(whereSQL)){//검색어가 있는 경우에만
				if("ALL".equals(searchType)){ // 전체 검색일 경우 - 검색 받는 검색어 2가지(글제목, 작성자에서 동시 검색)
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
		}
		
		return totalCount;//결과 값 반환 / BoardListServlet의 doGet()에게
	}// selectCount()END

	/**
	 *  글목록 조회 & 페이지 넘버 설정해주는 메소드
	 *  
	 *  한 페이지당 게시글 갯수 : 10개
	 *  페이지 갯수 : 10개  ex) 1~10, 11~20
	 */
	public ArrayList<UpDTO> list(String SearchType,String SearchText,int PageNum,int lastNum) {
		ArrayList<UpDTO> list = new ArrayList<UpDTO>();
		String searchText = SearchText; // 실제 검색어
		String searchType = SearchType; // 검색 종류
		String whereSQL =""; // 실행할 쿼리문
		int listCount = 10;  // 게시글 갯수 : 10개
		int pageNum = PageNum; // 페이지 갯수
		int last_Num = lastNum; // 마지막 페이지 번호
		int sv_num = (int) Math.ceil(last_Num/10.0); // 마지막 페이지 번호를 10으로 나누고 정수로 바꿔주기!
		int last_num = last_Num-(10*(pageNum-1)); // 마지막 페이지 번호 - ( 10 * (페이지번호 - 1));
		int first_num = last_num-9;  // 첫번째 페이지 번호
		
		if(first_num <0){ // 작성된 게시글이 없다면 페이지 번호 부여 X
			first_num = 0;
		}
		System.out.println("페이지"+pageNum);
		System.out.println("처음"+first_num);
		System.out.println("마지막"+last_num);
		
		try {
			
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE up_title LIKE '%'||?||'%' OR up_author LIKE '%'||?||'%'"; // 게시글 제목과 작성자 모두 검색
				}else if(searchType.equals("up_title")){//제목검색일 경우
					whereSQL =" WHERE up_title LIKE '%'||?||'%'"; // 제목만 검색
				}else if(searchType.equals("up_author")){//작성자 검색일 경우
					whereSQL =" WHERE up_author LIKE '%'||?||'%'"; // 작성자만 검색
				}
			}
			
			String first="SELECT * FROM (select row_number() over (order by up_idx) num, A.* FROM up A ";
			String end = " ORDER BY up_idx DESC) where num between ? and ?";
			String query= first+ whereSQL+end;
			System.out.println(query);
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(query);
			
			if(!"".equals(whereSQL)){//검색 쿼리가 있는 경우에만
				if(searchType.equals("ALL")){//전체 검색일 경우 ? 다섯 개
					this.pstmt.setString(1,searchText);
					this.pstmt.setString(2,searchText);
					this.pstmt.setInt(3,listCount*(pageNum-1)+1);//페이지의 시작 번호
					this.pstmt.setInt(4, listCount*pageNum);//조회 갯수					
					System.out.println("all");
					
				}else{//그외의 검색일 경우 ?가 세개
					this.pstmt.setString(1,searchText);
					this.pstmt.setInt(2,listCount*(first_num-1)+1);
					this.pstmt.setInt(3, listCount*pageNum);
					System.out.println("other");
				}
				
			}else{
				this.pstmt.setInt(1, first_num);
				this.pstmt.setInt(2, last_num);
				System.out.println("no");
				System.out.println("no");
			}
			
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				UpDTO data = new UpDTO();
				data.setUp_idx(rs.getInt("up_idx")); // 글번호
				data.setUp_author(rs.getString("up_author")); // 작성자
				data.setUp_title(rs.getString("up_title")); // 글제목
				data.setUp_content(rs.getString("up_content")); // 글내용
				data.setUp_writeday(rs.getString("up_writeday")); // 글 작성일
				data.setUp_hit(rs.getInt("up_hit")); // 조회수
				
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
	public void write(String up_title, String up_author, String up_content) {
		try {
			String sql = "INSERT INTO up (up_idx, up_title, up_author, up_content) VALUES (up_seq.NEXTVAL, ?, ?, ?)";	
			System.out.println(sql); // 한글 깨지는지 확인
			System.out.println("insert into 쿼리문 작동중 !!");
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			
			this.pstmt.setString(1, up_title); // 글제목
			this.pstmt.setString(2, up_author); // 작성자
			this.pstmt.setString(3, up_content);  // 글내용
			this.pstmt.executeUpdate();
			
			System.out.println("write 작동 중!!");
			
			} catch (SQLException e) {

				System.out.println("WRITE ERR : " +  e.getMessage());
				
			} finally {
				this.close(null, pstmt, con);
			}

	} // write() END
	
	/* 글 상세보기 메소드 */
	public UpDTO retrieve(String up_idx) {
		UpDTO data = new UpDTO();
		
		try {
			// 1. 조회수 증가시키기
			String sql = "UPDATE up SET up_hit=up_hit+1 WHERE up_idx=?";
			this.con = ds.getConnection();
			this.pstmt  = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(up_idx));
			int update = this.pstmt.executeUpdate();
			System.out.println("UpDAO 에서 retreive 조회수 증가유무(update) ");
			
			// 2. 글 번호를 이용하여 선택된 글만 조회
			this.pstmt = null; // 한 개의 pstmt를 2번 (재사용) 쓸 수 있는 방법(null 한번 시켜서 초기화)
			sql = "SELECT * FROM up WHERE up_idx=?";
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(up_idx));
			this.rs = this.pstmt.executeQuery();
			
			if (rs.next()) {
				data.setUp_idx(rs.getInt("up_idx"));
				data.setUp_title(rs.getString("up_title"));
				data.setUp_author(rs.getString("up_author"));
				data.setUp_content(rs.getString("up_content"));
				
			} // if END

		} catch (SQLException e) {
			System.out.println("RETRIEVE ERR : " + e.getMessage());
			
		} finally {
			this.close(rs, pstmt, con);
		}
		
		return data;
		
	} // UpDTO retrieve() END
	
	/* 글 수정하기 메소드 */
	public void update(String up_idx, UpCommand data) {
		
		String sql = "UPDATE up SET up_title=?, up_content=? WHERE up_idx=?";
		System.out.println(sql);
		
		try {
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setString(1, data.getUp_title());
			this.pstmt.setString(2, data.getUp_content());
			this.pstmt.setInt(3, Integer.parseInt(up_idx) + 320000);
			this.pstmt.executeUpdate();

			
		} catch (SQLException e) {
			System.out.println("UPDATE ERR : " + e);
			
		} finally {
			this.close(null, pstmt, con);
		}
		
	} // update() END

	/* 글 삭제하기 */
	public void delete(String up_idx) {
		
		try {
			String sql = "DELETE up WHERE up_idx=?";
			System.out.println(sql);
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1,  Integer.parseInt(up_idx));
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

} // class END
