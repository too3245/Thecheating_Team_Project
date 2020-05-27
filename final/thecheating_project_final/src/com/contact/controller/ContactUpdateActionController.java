package com.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.contact.command.ContactCommand;
import com.contact.dao.ContactDAO;
import com.contact.dto.ContactDTO;

public class ContactUpdateActionController extends AbstractCommandController {
	private ContactDAO dao;
	
	public void setdao(ContactDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {		
		request.setCharacterEncoding("UTF-8");
		System.out.println("Contact Update Action Controller 실행됨!");
		
		// 인덱스로 가져와서 선택한 인덱스의 글 수정하기
		String contact_idx = request.getParameter("contact_idx");
		System.out.println("contact_idx : " + contact_idx);
		
		// 글 상세보기 메소드 (상세보기 화면에서 글수정 가능)
		ContactDTO dto = dao.retrieve(contact_idx);
		
		// 글 수정시 입력받는 데이터 값 (글쓴이, 제목, 내용)
		ContactCommand data = (ContactCommand)command;
		String contact_title = data.getContact_content();
		String contact_author = data.getContact_author();
		String contact_content = data.getContact_content();
		
		System.out.println("contact_title");
		
		// 글 수정 메소드
		this.dao.update(contact_idx, data);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/contactList.do"); // 글 수정 끝나면 글목록으로 이동
		
		mav.addObject("data", dto);

		return mav;
	}

}
