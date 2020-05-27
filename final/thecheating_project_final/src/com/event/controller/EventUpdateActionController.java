package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.event.command.EventCommand;
import com.event.dao.EventDAO;
import com.event.dto.EventDTO;

public class EventUpdateActionController extends AbstractCommandController {
	private EventDAO dao;
	
	public void setdao(EventDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("EVENT Update Action Controller 실행됨!");
		
		// 인덱스로 가져와서 선택한 인덱스의 글 수정하기
		String event_idx = request.getParameter("event_idx");
		System.out.println("idx : " + event_idx );
		
		// 글 상세보기 메소드 (상세보기 화면에서 글수정 가능)
		EventDTO dto = dao.retrieve(event_idx);
		
		// 글 수정시 입력받는 데이터 값 (글쓴이, 제목, 내용)
		EventCommand data = (EventCommand)command;
		String event_author = data.getEvent_author();
		String event_title = data.getEvent_title();
		String event_content = data.getEvent_content();
		
		System.out.println(event_title);
		
		// 글 수정 메소드
		this.dao.update(event_idx, data); // 게시물의 내용을 DAO 통해 가져오기
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/eventList.do"); // 글 수정 끝나면 글목록으로 이동
		
		mav.addObject("data", dto);
		
		return mav;
	}

}
