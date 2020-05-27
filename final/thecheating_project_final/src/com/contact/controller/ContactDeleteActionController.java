package com.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.contact.dao.ContactDAO;

public class ContactDeleteActionController implements Controller {
	private ContactDAO dao;
	
	public void setdao(ContactDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Contact Delete Action Controller 실행됨!");
		
		// 글 번호를 받아와서 delete 메소드 실행하여 해당 게시글 지우기
		String contact_idx = request.getParameter("contact_idx");
		
		// 게시판 dao의 delete 쿼리문
		this.dao.delete(contact_idx);
		
		// 게시글 지우고, 글 목록으로
		return new ModelAndView("redirect:/contactList.do");

	}

}
