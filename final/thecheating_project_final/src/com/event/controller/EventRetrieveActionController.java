package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.event.dao.EventDAO;
import com.event.dto.EventDTO;


public class EventRetrieveActionController implements Controller {
	private EventDAO dao;
	
	public void setdao(EventDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("EVENT Retrieve Action Controller 실행됨!");
		
		// 인덱스 번호 가져와서 상세보기
		String event_idx = request.getParameter("event_idx");
		System.out.println("idx : " + event_idx );
		
		// 레코드 한 개를 담을 객체 필요
		EventDTO data = dao.retrieve(event_idx); // 검색
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("event/eventRetrieve"); // 이벤트 게시판 상세보기 화면으로 이동!

		mav.addObject("data", data);
		
		return mav;
	}

}
