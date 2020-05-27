package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bbs.dao.BBSDAO;
import com.bbs.dto.BBSDTO;

/*
 *  BBS(공지사항 안내 게시판)
 *  글 수정 조회 controller
 */

public class BBSRetrieveActionController implements Controller {
	private BBSDAO dao;
	
	public void setdao(BBSDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("BBS Retrieve Action Controller 실행됨!");
		
		// 인덱스 번호 가져와서 상세보기
		String bbs_idx = request.getParameter("bbs_idx");
		System.out.println("idx : " + bbs_idx );
		
		// 레코드 한 개를 담을 객체 필요
		BBSDTO data = dao.retrieve(bbs_idx); // 검색
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("bbs/bbsRetrieve"); // 공지사항 게시판 상세보기 화면으로 이동!

		mav.addObject("data", data);
		
		return mav;
		
	}

}
