package com.up.dto;

/**
 * 업데이트(Up) 게시판 DTO 클래스
 * @param up_idx : 글번호
 * @param up_author : 글쓴이
 * @param up_title : 글제목
 * @param up_content : 글내용
 * @param up_writeday : 글작성날짜
 * @param up_hit : 조회수
 */

public class UpDTO {
	
		private int up_idx; 
		private String up_author, up_title, up_content, up_writeday;
		private int up_hit;
		
		
		public int getUp_idx() {
			return up_idx;
		}
		public void setUp_idx(int up_idx) {
			this.up_idx = up_idx;
		}
		public String getUp_author() {
			return up_author;
		}
		public void setUp_author(String up_author) {
			this.up_author = up_author;
		}
		public String getUp_title() {
			return up_title;
		}
		public void setUp_title(String up_title) {
			this.up_title = up_title;
		}
		public String getUp_content() {
			return up_content;
		}
		public void setUp_content(String up_content) {
			this.up_content = up_content;
		}
		public String getUp_writeday() {
			return up_writeday;
		}
		public void setUp_writeday(String up_writeday) {
			this.up_writeday = up_writeday;
		}
		public int getUp_hit() {
			return up_hit;
		}
		public void setUp_hit(int up_hit) {
			this.up_hit = up_hit;
		}


}
