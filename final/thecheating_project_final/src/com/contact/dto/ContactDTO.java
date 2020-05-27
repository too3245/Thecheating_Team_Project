package com.contact.dto;

/**
 * 문의(contact) 게시판 DTO 클래스
 * @param contact_idx : 문의_글번호
 * @param contact_author : 문의_글쓴이
 * @param contact_title : 문의_글제목
 * @param contact_content : 문의_글내용
 * @param contact_writeday : 문의_작성날짜
 * @param contact_hit : 문의_조회수
 * @param re_ref : 참조 번호
 * @param re_lev : 참조 레벨
 * @param re_seq	: 참조 순서
 */

public class ContactDTO {
	private int contact_idx;	
	private String contact_author;	
	private String contact_title;
	private String contact_content;	
	private String contact_writeday;
	private int contact_hit;	
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
	public String getContact_writeday() {
		return contact_writeday;
	}
	public void setContact_writeday(String contact_writeday) {
		this.contact_writeday = contact_writeday;
	}
	public int getContact_hit() {
		return contact_hit;
	}
	public void setContact_hit(int contact_hit) {
		this.contact_hit = contact_hit;
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
	@Override
	public String toString() {
		return "ContactDTO [contact_idx=" + contact_idx + ", contact_author=" + contact_author + ", contact_title="
				+ contact_title + ", contact_content=" + contact_content + ", contact_writeday=" + contact_writeday
				+ ", contact_hit=" + contact_hit + ", re_ref=" + re_ref + ", re_lev=" + re_lev + ", re_seq=" + re_seq
				+ "]";
	}
	
	
	
	
}
