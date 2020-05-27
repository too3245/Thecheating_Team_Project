package com.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.account.dao.AccountDAO;

public class AccountDeleteController implements Controller {
	
	private AccountDAO accdao;

	public void setAccdao(AccountDAO accdao) {
		this.accdao = accdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String mem_email = (String) session.getAttribute("mem_email");
		
		accdao.AccountDelete(mem_email);
		
		ModelAndView mav = new ModelAndView("redirect:/account/accountlist.do");
		
		
		return mav;
	}

}
