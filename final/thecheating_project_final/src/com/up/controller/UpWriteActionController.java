package com.up.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.up.command.UpCommand;
import com.up.dao.UpDAO;

public class UpWriteActionController extends AbstractCommandController {
	private UpDAO dao;
	
	public void setdao(UpDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("UP Write Action Controller 실행됨!");
		
		// BoardCommand 는 상속받은 부모의 Class (입력받아올 데이터 : 글제목, 글쓴이, 글내용)
		UpCommand data = (UpCommand)command;
		String up_title = data.getUp_title();
		String up_author = data.getUp_author();
		String up_content = data.getUp_content();
		
		// 글쓰기 메소드
		dao.write(up_title, up_author, up_content);
		
		// 글쓰기 화면에서 글 작성 후 저장하면 글목록으로 이동
		ModelAndView mav = new ModelAndView("redirect:/upList.do");

		return mav;
	}

}
