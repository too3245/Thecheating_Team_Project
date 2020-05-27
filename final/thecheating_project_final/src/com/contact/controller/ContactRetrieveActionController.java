package com.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.contact.dao.ContactDAO;
import com.contact.dto.ContactDTO;

public class ContactRetrieveActionController implements Controller {
	private ContactDAO dao;
	
	public void setdao(ContactDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Contact Retrieve Action Controller 실행됨!");
		
		// 인덱스 번호 가져와서 상세보기
		String contact_idx = request.getParameter("contact_idx");
		System.out.println("contact_idx : " + contact_idx);
		
		// 레코드 한 개를 담을 객체
		ContactDTO data = dao.retrieve(contact_idx); // 검색
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("contact/contactRetrieve"); // 문의사항 게시판 상세보기 화면으로 이동!
				
		mav.addObject("data", data);
		
		return mav;
	}

}
