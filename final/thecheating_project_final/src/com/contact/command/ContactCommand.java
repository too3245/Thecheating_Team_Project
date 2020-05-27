package com.contact.command;

/*
 *  1:1 문의사항 게시판(contact)
 *  사용자로부터 순수 입력받는 값만 처리해주는 Class
 *  문의사항 게시판에서 작성글에 입력받을 항목 : 글제목, 글쓴이, 글내용, 게시글 참조번호, 게시글 참조레벨, 게시글 참조순서
 */
public class ContactCommand {
	private int contact_idx;	
	private String contact_author;	
	private String contact_title;
	private String contact_content;	
	private int re_ref;
	private int re_lev;
	private int re_seq;
	public int getContact_idx() {
		return contact_idx;
	}
	public void setContact_idx(int contact_idx) {
		this.contact_idx = contact_idx;
	}
	public String getContact_author() {
		return contact_author;
	}
	public void setContact_author(String contact_author) {
		this.contact_author = contact_author;
	}
	public String getContact_title() {
		return contact_title;
	}
	public void setContact_title(String contact_title) {
		this.contact_title = contact_title;
	}
	public String getContact_content() {
		return contact_content;
	}
	public void setContact_content(String contact_content) {
		this.contact_content = contact_content;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	

	
}
