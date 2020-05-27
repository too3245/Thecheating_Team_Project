package com.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.contact.command.ContactCommand;
import com.contact.dao.ContactDAO;

public class ContactReplyWriteActionController extends AbstractCommandController {
	private ContactDAO dao;
	
	public void setdao(ContactDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Contact Reply Write Action Controller");
		
		// BoardCommand 는 상속받은 부모의 Class (입력받아 올 데이터 : 글제목, 글쓴이, 글내용)
		ContactCommand data = (ContactCommand)command;
		
        //게시글 읽기에서 답변글쓰기를 클릭하면 넘겨주는 데이터들을 받아줌
        int contact_idx = Integer.parseInt(request.getParameter("contact_idx")); // 게시글 번호
        int re_ref = Integer.parseInt(request.getParameter("re_ref")); //  게시글 참조 번호
        int re_seq = Integer.parseInt(request.getParameter("re_seq")); // 게시글 참조 순서
        int re_lev = Integer.parseInt(request.getParameter("re_lev")); // 게시글 참조 레벨(depth)
        											// but 문의사항 게시판에서 1개의 질문 게시글에 1개의 답글만 사용할 것!
        
        // 로그인시 사용자의 닉네임 값 세션 가져오기
        HttpSession session = request.getSession();
		String re_contact_author = (String) session.getAttribute("mem_nickname");
		
		// 답글쓰기 메소드
		dao.replyWrite(data);
		
		ModelAndView mav = new ModelAndView();
		
		// 글쓰기 화면에서 글 작성 후 저장하면 글목록으로 이동
		mav.setViewName("redirect:/contactList.do"); 
		mav.addObject("re_ref", contact_idx); 
		mav.addObject("re_seq", re_seq);
		mav.addObject("re_lev", re_lev);
		
		return mav;
		
	}

}
