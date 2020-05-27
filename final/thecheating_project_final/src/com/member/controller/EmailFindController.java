package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;

public class EmailFindController implements Controller {

	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mem_username = request.getParameter("mem_username");
		String mem_birthday = request.getParameter("mem_birthday");
		String mem_phone = request.getParameter("mem_phone");
		
		
		String result = memdao.emailFind(mem_username, mem_birthday, mem_phone);
		ModelAndView mav = new ModelAndView();
		
		if(result == "정확히 입력하세요.") {
			mav.addObject("result", result);
			mav.setViewName("/member/emailfind");
			return mav;
		} else {
			mav.addObject("result", result);
			mav.addObject("mem_username", mem_username);
			mav.setViewName("/member/emailfindsuccess");
			return mav;
		}
	}

}
