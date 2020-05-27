package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;

public class PasswordFindController implements Controller {
	
	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mem_email = request.getParameter("mem_email");
		String mem_username = request.getParameter("mem_username");
		String mem_birthday = request.getParameter("mem_birthday");
		String mem_phone = request.getParameter("mem_phone");
		
		String result = memdao.passwordFind(mem_email, mem_username, mem_birthday, mem_phone);
		
		ModelAndView mav = new ModelAndView();
		
		
		if(result == "2") {
			result = "정확히 입력하세요.";
			mav.addObject("result", result);
			mav.setViewName("/member/passwordfind");
			return mav;
		} else {
			HttpSession session = request.getSession();
			
			session.setAttribute("mem_email", mem_email);
			session.setAttribute("mem_username", mem_username);
			session.setAttribute("mem_birthday", mem_birthday);
			session.setAttribute("mem_phone", mem_phone);
			mav.addObject("session", session);
			mav.setViewName("/member/passwordchange");
			return mav;
		}
		
	}

}
