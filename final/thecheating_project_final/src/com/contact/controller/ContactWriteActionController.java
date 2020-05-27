package com.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.contact.command.ContactCommand;
import com.contact.dao.ContactDAO;

public class ContactWriteActionController extends AbstractCommandController {
	private ContactDAO dao;
	
	public void setdao(ContactDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Contact Write Action Controller");
		
		// BoardCommand 는 상속받은 부모의 Class (입력받아 올 데이터 : 글제목, 글쓴이, 글내용)
		ContactCommand data = (ContactCommand)command;		
		String contact_title = data.getContact_title(); 
		String contact_author = data.getContact_author(); 
		String contact_content = data.getContact_content();
		
		int re_lev = data.getRe_lev(); // 게시글 참조 레벨
		int re_ref = 0; // 게시글 참조 번호
		int re_seq = 0; // 게시글 참조 순서
		
		// re_lev 댓글 여부 0:(문의글)  1:(댓글)
		if(re_lev !=0 ){ 
			re_ref = data.getRe_ref(); 
			re_seq = data.getRe_seq();
		}
		
		// 글쓰기 메소드
		dao.write(contact_title, contact_author, contact_content,re_ref,re_lev,re_seq);
		
		// 글쓰기 화면에서 글 작성 후 저장하면 글목록으로 이동
		ModelAndView mav = new ModelAndView("redirect:/contactList.do");
		
		return mav;
		
	}

}
