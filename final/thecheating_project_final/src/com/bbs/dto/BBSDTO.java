package com.bbs.dto;

/**
 * 공지사항(bbs) 게시판 DTO 클래스
 * @param bbs_idx : 글번호
 * @param bbs_author : 글쓴이
 * @param bbs_title : 글제목
 * @param bbs_content : 글내용
 * @param bbs_writeday : 글작성날짜
 * @param bbs_hit : 조회수
 */

public class BBSDTO {
	private int bbs_idx; 
	private String bbs_author, bbs_title, bbs_content, bbs_writeday; 
	private int bbs_hit; 
	
	public int getBbs_idx() {
		return bbs_idx;
	}
	public void setBbs_idx(int bbs_idx) {
		this.bbs_idx = bbs_idx;
	}
	public String getBbs_author() {
		return bbs_author;
	}
	public void setBbs_author(String bbs_author) {
		this.bbs_author = bbs_author;
	}
	public String getBbs_title() {
		return bbs_title;
	}
	public void setBbs_title(String bbs_title) {
		this.bbs_title = bbs_title;
	}
	public String getBbs_content() {
		return bbs_content;
	}
	public void setBbs_content(String bbs_content) {
		this.bbs_content = bbs_content;
	}
	public String getBbs_writeday() {
		return bbs_writeday;
	}
	public void setBbs_writeday(String bbs_writeday) {
		this.bbs_writeday = bbs_writeday;
	}
	public int getBbs_hit() {
		return bbs_hit;
	}
	public void setBbs_hit(int bbs_hit) {
		this.bbs_hit = bbs_hit;
	}

	
}
