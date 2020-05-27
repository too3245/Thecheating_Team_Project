package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bbs.dao.BBSDAO;

/*
 *  BBS(공지사항 안내 게시판)
 *  글 삭제 controller
 */

public class BBSDeleteActionController implements Controller {
	private BBSDAO dao;
	
	public void setdao(BBSDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("BBS Delete Action Controller 실행됨!");
		
		// 글 번호를 받아와서 delete 메소드 실행하여 해당 게시글 지우기
		String bbs_idx = request.getParameter("bbs_idx");
		
		// 게시판 dao의 delete 쿼리문
		this.dao.delete(bbs_idx);
		
		// 게시글을 지우고 나면, 글목록으로 돌아가기
		return new ModelAndView("redirect:/bbsList.do");
		
	}

}
