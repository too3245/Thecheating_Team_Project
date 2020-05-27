package com.up.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.up.command.UpCommand;
import com.up.dao.UpDAO;
import com.up.dto.UpDTO;

public class UpUpdateActionController extends AbstractCommandController {
	private UpDAO dao;
	
	public void setdao(UpDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}

	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("UP Update Action Controller 실행됨!");
		
		// 인덱스로 가져와서 선택한 인덱스의 글 수정하기
		String up_idx = request.getParameter("up_idx");
		System.out.println("idx : " + up_idx);
		
		// 글 상세보기 메소드 (상세보기 화면에서 글수정 가능)
		UpDTO dto = dao.retrieve(up_idx);
		
		// 글 수정시 입력받는 데이터 값 (글쓴이, 제목, 내용)
		UpCommand data = (UpCommand)command;
		String up_author = data.getUp_author();
		String up_title = data.getUp_title();
		String up_content = data.getUp_content();
		
		System.out.println(up_title);
		
		// 글 수정 메소드
		this.dao.update(up_idx, data); 

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/upList.do"); // 글 수정 끝나면 글목록으로 이동
		
		mav.addObject("data", dto);
		
		return mav;

	}
}
