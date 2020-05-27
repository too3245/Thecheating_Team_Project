package com.contact.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.bbs.dto.BBSDTO;
import com.contact.command.ContactCommand;
import com.contact.dto.ContactDTO;

/**
 *  Contact - 1:1 문의사항 게시판 DAO 클래스 (게시글+답글)
 */

public class ContactDAO {
	DataSource ds;
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/* JDBC Connection */
	public ContactDAO() {
		try {
			Context  ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds : " + ds);
			
		} catch (NamingException e) {
			System.out.println("JDBC ERR : " + e.getMessage());
			
		}
	} // ContactDAO() END
	
	/* 답글 쓰기 메소드 */
	public ContactCommand replyWrite(ContactCommand data){
		int re_ref = data.getRe_ref(); // 게시글 참조번호
		int re_lev = data.getRe_lev(); // 게시글 참조레벨
		int re_seq = data.getRe_seq(); // 게시글 참조순서
		
		try {
			String levelsql = "update contact set re_lev= re_lev+1 where re_ref=? and re_lev> ?";
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(levelsql);
			
			this.pstmt.setInt(1, re_ref);
			this.pstmt.setInt(2, re_lev);
			this.pstmt.executeUpdate();
			
			// 답변 글 저장
			String sql = "insert into contact (contact_idx, contact_title, contact_author, contact_content, contact_writeday, contact_hit, re_ref, re_lev, re_seq) values(contact_seq.NEXTVAL, ?, ?, ?, sysdate, 0, ?, ?, ?)";
			this.pstmt = this.con.prepareStatement(sql);
			
			// ?에 값을 매핑		
			this.pstmt.setString(1, data.getContact_title());
			this.pstmt.setString(2, data.getContact_author());
			this.pstmt.setString(3, data.getContact_content());
			this.pstmt.setInt(4, re_ref);
			this.pstmt.setInt(5, re_seq + 1); 
			this.pstmt.setInt(6, re_lev + 1);
			
			this.pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("replyWrite err : " + e.getMessage());
		} finally {
			this.close(null, pstmt, con);
		}
		return data;
		
	} // replyWrite END
	
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
		String whereSQL =""; // 실행할 쿼리문 변수 지정

		try {
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE contact_title LIKE '%'||?||'%' OR contact_author LIKE '%'||?||'%'";  // 글 제목과 작성자 모두 검색하는 쿼리문
				}else if(searchType.equals("contact_title")){//제목검색일 경우
					whereSQL =" WHERE contact_title LIKE '%'||?||'%'"; // 제목 게시글 중에서 검색어로 입력 받아 검색
				}else if(searchType.equals("contact_author")){//작성자 검색일 경우
					whereSQL =" WHERE contact_author LIKE '%'||?||'%'"; // 작성자 게시글 중에서 검색어로 입력 받아 검색
				}
			}
			
			String query = "SELECT COUNT(contact_idx) AS TOTAL FROM contact"+whereSQL;
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(query);
			
			if(!"".equals(whereSQL)){//검색어가 있는 경우에만
				if("ALL".equals(searchType)){ // 전체 검색일 경우 - 검색 받는 검색어 2가지(글제목, 작성자에서 동시 검색)
					this.pstmt.setString(1,searchText);
					this.pstmt.setString(2,searchText);
				}else{   // 그 외 검색일 경우 - 작성자 검색 / 제목 검색
					this.pstmt.setString(1,searchText);
				}
			}
			this.rs = this.pstmt.executeQuery();
			if(rs.next()){
				totalCount = this.rs.getInt("TOTAL");
			}
			
		} catch (SQLException e) {
			System.out.println("selectCount ERR : " + e);
		} finally {
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
	public ArrayList<ContactDTO> list(String SearchType,String SearchText,int PageNum,int lastNum) {
		ArrayList<ContactDTO> list = new ArrayList<ContactDTO>();
		String searchText = SearchText; // 실제 검색어
		String searchType = SearchType;  // 검색 종류
		String whereSQL =""; // 실행할 쿼리문
		int listCount = 10;  // 게시글 갯수 : 10개
		int pageNum = PageNum;  // 페이지 갯수
		int last_Num = lastNum;  // 마지막 페이지 번호
		int sv_num = (int) Math.ceil(last_Num/10.0); // 마지막 페이지 번호를 10으로 나누고 정수로 바꿔주기!
		int last_num = last_Num-(10*(pageNum-1)); // 마지막 페이지 번호 - ( 10 * (페이지번호 - 1));
		int first_num = last_num-9;// 첫번째 페이지 번호
		
		if(first_num <0){ // 작성된 게시글이 없다면 페이지 번호 부여 X
			first_num = 0; 
		}
		System.out.println("페이지"+pageNum);
		System.out.println("처음"+first_num);
		System.out.println("마지막"+last_num);

		
		try {
			if(!"".equals(searchText)){
				if(searchType.equals("ALL")){//전체 검색일 경우
					whereSQL =" WHERE contact_title LIKE '%'||?||'%' OR contact_author LIKE '%'||?||'%'"; // 게시글 제목과 작성자 모두 검색
				}else if(searchType.equals("contact_title")){//제목검색일 경우
					whereSQL =" WHERE contact_title LIKE '%'||?||'%'";  // 제목만 검색
				}else if(searchType.equals("contact_author")){//작성자 검색일 경우
					whereSQL =" WHERE contact_author LIKE '%'||?||'%'"; // 작성자만 검색
				}
			}
			
			String first="SELECT * FROM (select row_number() over (order by contact_idx) num, A.* FROM contact A ";
			String end = " ORDER BY contact_idx DESC) where re_ref =0 and  num between ? and ?";
			String query= first+ whereSQL+end;
			System.out.println(query);
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(query);
			
			if(!"".equals(whereSQL)){//검색 쿼리가 있는 경우에만
				if(searchType.equals("ALL")){//전체 검색일 경우 ? 다섯 개
					this.pstmt.setString(1,searchText);
					this.pstmt.setString(2,searchText);
					this.pstmt.setInt(3,listCount*(pageNum-1)+1);  //페이지의 시작 번호
					this.pstmt.setInt(4, listCount*pageNum);  //조회 갯수					
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
			}
			this.rs = this.pstmt.executeQuery();

			while (rs.next()) {
				ContactDTO data = new ContactDTO();
				data.setContact_idx(rs.getInt("contact_idx")); // 글번호
				data.setContact_author(rs.getString("contact_author")); // 작성자
				data.setContact_title(rs.getString("contact_title")); // 글제목
				data.setContact_content(rs.getString("contact_content")); // 글내용
				data.setContact_writeday(rs.getString("contact_writeday")); // 글 작성일
				data.setContact_hit(rs.getInt("contact_hit")); // 조회수
				
				list.add(data);
			} // end while

		} catch (Exception e) {
			System.out.println("list ERR : " + e);
		} finally {
			this.close(rs, pstmt, con);
		}

		return list;	// ListActionController 로 반환
	} // list() END
	
	/* 답글 목록 조회 메소드 */
	public ArrayList<ContactDTO> replyList(ArrayList<ContactDTO>  list){
		
		ArrayList<ContactDTO> replyList = new ArrayList<ContactDTO>();
			
		for(int i=0; i<list.size(); i++){
	
			int contact_idx = list.get(i).getContact_idx();
			String sql = "SELECT * FROM contact  WHERE re_ref != 0   AND re_ref = "+contact_idx;
			try {
				this.con = ds.getConnection();
				this.pstmt = this.con.prepareStatement(sql);
				this.rs = this.pstmt.executeQuery();
				
				while(rs.next()) {
					ContactDTO replyData = new ContactDTO();
					
					replyData.setContact_idx(rs.getInt("contact_idx")); // 글번호
					replyData.setContact_author(rs.getString("contact_author")); // 글작성자
					replyData.setContact_title(rs.getString("contact_title")); // 글제목
					replyData.setContact_content(rs.getString("contact_content")); // 글내용
					replyData.setContact_writeday(rs.getString("contact_writeday")); // 글작성일
					replyData.setContact_hit(rs.getInt("contact_hit")); // 조회수
					replyData.setRe_ref(rs.getInt("re_ref"));
					replyData.setRe_lev(rs.getInt("re_lev"));
	
					replyList.add(replyData);
					
				} // while() EN
			} catch (Exception e) {
				System.out.println("replyList ERR : " + e.getMessage());
			} finally {
				this.close(rs, pstmt, con);
				
			}
			
		}
		return replyList;
	}
	
	/* 글쓰기 메소드 : 글제목, 글쓴이, 글내용 */
	public void write(String contact_title, String contact_author, String contact_content, int re_ref, int re_lev, int re_seq) {
		
		try {
			String sql = "INSERT INTO contact (contact_idx, contact_title, contact_author, contact_content, re_ref, re_lev, re_seq) VALUES (contact_seq.NEXTVAL, ?, ?, ?,?,?,?)";
			System.out.println(sql);
			System.out.println("contact insert into 쿼리문 작동 중!");
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			
			this.pstmt.setString(1, contact_title); // 글제목
			this.pstmt.setString(2, contact_author); // 글쓴이
			this.pstmt.setString(3, contact_content); // 글내용
			this.pstmt.setInt(4, re_ref); // 답글 번호
			this.pstmt.setInt(5, re_lev); // 답글 레벨
			this.pstmt.setInt(6, re_seq); // 답글 참조순서
			
			this.pstmt.executeUpdate();
			
			System.out.println("contact write 쿼리문 작동 중!");
		
		} catch (SQLException e) {
			System.out.println("write err : " + e.getMessage());
		} finally {
			this.close(null, pstmt, con);
		}
		
	} // write() END

	/* 글 상세보기 메소드 */
	public ContactDTO retrieve(String contact_idx) {
		ContactDTO data = new ContactDTO();
		String sql = "UPDATE contact SET contact_hit=contact_hit+1 WHERE contact_idx=?";
		try {
			// 1. 조회수 증가
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(contact_idx));
			int update = this.pstmt.executeUpdate();
			System.out.println("ContactDAO - retrieve 조회수 증가유무(update)");
			
			// 2. 글번호 이용해서 선택된 글만 조회
			this.pstmt = null; // 한 개의 pstmt를 2번 (재사용) 쓸 수 있는 방법(null 한번 시켜서 초기화)
			sql = "SELECT * FROM contact WHERE contact_idx=?";
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(contact_idx));
			this.rs = this.pstmt.executeQuery();
			
			if(rs.next()) {
				data.setContact_idx(rs.getInt("contact_idx"));
				data.setContact_title(rs.getString("contact_title"));
				data.setContact_author(rs.getString("contact_author"));
				data.setContact_content(rs.getString("contact_content"));
				data.setRe_lev(rs.getInt("re_lev"));
				
				
			} // if() END
			
		} catch (SQLException e) {
			System.out.println("retrieve err : " + e.getMessage());
		} finally {
			this.close(rs, pstmt, con);
			
		}
		return data;
		
	} // retrieve END
	
	/* 글 수정하기 메소드 */
	public void update(String contact_idx, ContactCommand data) {
		String sql = "UPDATE contact SET contact_title=?, contact_content=? WHERE contact_idx=?";
		System.out.println(sql);
		System.out.println("update 쿼리문 작동중!");
		
		try {
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setString(1, data.getContact_title()); // 글제목
			this.pstmt.setString(2, data.getContact_content());  // 글내용
			this.pstmt.setInt(3, Integer.parseInt(contact_idx) + 340000); // 글번호
			this.pstmt.executeUpdate();
			
			System.out.println("update 확인 !");
			
		} catch (SQLException e) {
			System.out.println("update err : " +e.getMessage() );
			
		} finally {
			this.close(null, pstmt, con);;
			
		}
		
	} // update() END
	
	
	
	/* 글 삭제하기 */
	public void delete(String contact_idx) {
		
		try {
			String sql = "DELETE contact WHERE contact_idx=?";
			System.out.println(sql);
			
			this.con = ds.getConnection();
			this.pstmt = this.con.prepareStatement(sql);
			this.pstmt.setInt(1, Integer.parseInt(contact_idx));
			this.pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("DELETE () ERR : " + e.getMessage());
			
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
	} // close() END
} // class END