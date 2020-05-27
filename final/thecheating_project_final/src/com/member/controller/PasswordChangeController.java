package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;

public class PasswordChangeController implements Controller {
	
	private MemberDAO memdao;
	

	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String mem_email = (String) session.getAttribute("mem_email");
		String mem_username = (String) session.getAttribute("mem_username");
		String mem_birthday = (String) session.getAttribute("mem_birthday");
		String mem_phone = (String) session.getAttribute("mem_phone");
		
		
		String mem_password = request.getParameter("mem_password");
		
		memdao.passwordChange(mem_password, mem_email, mem_username, mem_birthday, mem_phone);
		
		ModelAndView mav = new ModelAndView("/member/passwordchangesuccess");
		
		return mav;
	}

}
