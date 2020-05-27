package com.up.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.up.dto.UpDTO;
import com.up.dao.UpDAO;

public class UpRetrieveActionController implements Controller {
	private UpDAO dao;
	
	public void setdao(UpDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("UP Retrieve Action Controller 실행됨!");
		
		// 인덱스 번호 가져와서 상세보기
		String up_idx = request.getParameter("up_idx");
		System.out.println("idx : " + up_idx );
		
		// 레코드 한 개를 담을 객체 필요
		UpDTO data = dao.retrieve(up_idx); // 검색
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("up/upRetrieve"); // 업데이트 게시판 상세보기 화면으로 이동!
		
		mav.addObject("data", data);
		
		return mav;
	}

}
