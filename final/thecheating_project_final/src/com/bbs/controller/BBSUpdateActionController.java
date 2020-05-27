package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.bbs.command.BBSCommand;
import com.bbs.dao.BBSDAO;
import com.bbs.dto.BBSDTO;

/*
 *  BBS(공지사항 안내 게시판)
 *  게시글 수정 controller
 */

public class BBSUpdateActionController extends AbstractCommandController {
	private BBSDAO dao;
	
	public void setdao(BBSDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("BBS Update Action Controller 실행됨!");
		
		// 인덱스로 가져와서 선택한 인덱스의 글 수정하기
		String bbs_idx = request.getParameter("bbs_idx");
		System.out.println("idx : " + bbs_idx );
		
		// 글 상세보기 메소드 (상세보기 화면에서 글수정 가능)
		BBSDTO dto = dao.retrieve(bbs_idx);
		
		// 글 수정시 입력받는 데이터 값 (글쓴이, 제목, 내용)
		BBSCommand data = (BBSCommand)command;
		String bbs_author = data.getBbs_author();
		String bbs_title = data.getBbs_title();
		String bbs_content = data.getBbs_content();
		
		System.out.println(bbs_title);
		
		// 글 수정 메소드
		this.dao.update(bbs_idx, data); 
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/bbsList.do"); // 글 수정 끝나면 글목록으로 이동
		
		mav.addObject("data", dto);
		
		return mav;

	}

}
