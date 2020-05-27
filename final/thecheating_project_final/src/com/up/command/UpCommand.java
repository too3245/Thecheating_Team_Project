package com.up.command;

/*
 *  업데이트 안내 게시판(up)
 *  사용자로부터 순수 입력받는 값만 처리해주는 Class
 *  업데이트 게시판에서 작성글에 입력받을 항목 : 글제목, 글쓴이, 글내용
 */
public class UpCommand {
	private String up_author, up_title, up_content;
	private int up_idx;
	
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
	public int getUp_idx() {
		return up_idx;
	}
	public void setUp_idx(int up_idx) {
		this.up_idx = up_idx;
	}
	
}
