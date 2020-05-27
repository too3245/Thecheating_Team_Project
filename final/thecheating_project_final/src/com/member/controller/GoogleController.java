package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class GoogleController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String mem_idx = request.getParameter("id");
		String mem_username = request.getParameter("name");
		String mem_email = request.getParameter("email");
		HttpSession session = request.getSession();
		session.setAttribute("mem_idx", mem_idx);
		session.setAttribute("mem_username", mem_username);
		session.setAttribute("mem_email", mem_email);
		request.setAttribute("session", session);
		
		mav.addObject("session", session);
		mav.setViewName("/main");
		return mav;
	}

}
