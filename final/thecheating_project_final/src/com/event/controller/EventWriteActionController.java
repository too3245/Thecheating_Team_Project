package com.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.event.command.EventCommand;
import com.event.dao.EventDAO;

public class EventWriteActionController extends AbstractCommandController {
	private EventDAO dao;
	
	public void setdao(EventDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("EVENT Write Action Controller 실행됨!");
		
		// BoardCommand 는 상속받은 부모의 Class (입력받아 올 데이터 : 글제목, 글쓴이, 글내용)
		EventCommand data = (EventCommand)command;
		String event_title = data.getEvent_title();
		String event_author = data.getEvent_author();
		String event_content = data.getEvent_content();
		
		// 글쓰기 메소드
		dao.write(event_title, event_author, event_content);
		
		// 글쓰기 화면에서 글 작성 후 저장하면 글목록으로 이동
		ModelAndView mav = new ModelAndView("redirect:/eventList.do");

		return mav;
	}

}
