package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;

public class LoginCheckController implements Controller {
	
	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;

	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		String mem_email = request.getParameter("emailCheck");
		
		String result = memdao.emailCheck(mem_email);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("email_check", mem_email);
		mav.addObject("result", result);
		mav.setViewName("/member/join");
		
		return mav;
	}

}
