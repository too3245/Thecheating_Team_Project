package com.bbs.command;

/*
 *  공지사항 게시판(bbs)
 *  사용자로부터 순수 입력받는 값만 처리해주는 Class
 *  공지사항 게시판에서 작성글에 입력받을 항목 : 글제목, 글쓴이, 글내용
 */

public class BBSCommand {
	private String bbs_author, bbs_title, bbs_content;
	private int bbs_idx;

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

	

}
