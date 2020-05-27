package com.event.command;

/*
 *  이벤트 안내 게시판(event)
 *  사용자로부터 순수 입력받는 값만 처리해주는 Class
 *  이벤트 게시판에서 작성글에 입력받을 항목 : 글제목, 글쓴이, 글내용
 */
public class EventCommand {
	private String event_author, event_title, event_content;
	private int event_idx;
	
	
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
	public int getEvent_idx() {
		return event_idx;
	}
	public void setEvent_idx(int event_idx) {
		this.event_idx = event_idx;
	}
	
	
	
}
