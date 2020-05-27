package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.event.dao.EventDAO;


public class EventDeleteActionController implements Controller {
	private EventDAO dao;
	
	public void setdao(EventDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("EVENT Delete Action Controller 실행됨!");
		
		// 글 번호를 받아와서 delete 메소드 실행하여 해당 게시글 지우기
		String up_idx = request.getParameter("event_idx");
		
		// 게시판 dao의 delete 쿼리문
		this.dao.delete(up_idx);
		
		// 게시글을 지우고 나면, 글목록으로 돌아가기
		return new ModelAndView("redirect:/eventList.do");
	}

}
