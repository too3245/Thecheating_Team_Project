package com.event.dto;


/**
 * 이벤트(EVENT) 게시판 DTO 클래스
 * @param event_idx : 글번호
 * @param event_author : 글쓴이
 * @param event_content : 글내용
 * @param event_writeday : 글작성날짜
 * @param event_hit : 조회수
 */


public class EventDTO {
	
	private int event_idx; 
	private String event_author, event_title, event_content, event_writeday;
	private int event_hit;
	private int event_pageTotal;
	
	public int getEvent_idx() {
		return event_idx;
	}
	public void setEvent_idx(int event_idx) {
		this.event_idx = event_idx;
	}
	public String getEvent_author() {
		return event_author;
	}
	public void setEvent_author(String event_author) {
		this.event_author = event_author;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public String getEvent_writeday() {
		return event_writeday;
	}
	public void setEvent_writeday(String event_writeday) {
		this.event_writeday = event_writeday;
	}
	public int getEvent_hit() {
		return event_hit;
	}
	public void setEvent_hit(int event_hit) {
		this.event_hit = event_hit;
	}
	public int getEvent_pageTotal() {
		return event_pageTotal;
	}
	public void setEvent_pageTotal(int event_pageTotal) {
		this.event_pageTotal = event_pageTotal;
	}

	
	
}
