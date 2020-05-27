package com.account.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.account.dao.AccountDAO;

public class AccountListController implements Controller {
	
	private AccountDAO accdao;
	
	public void setAccdao(AccountDAO accdao) {
		this.accdao = accdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("계좌리스트실행");
		HttpSession session = request.getSession();
		
		String seq_num = (String) session.getAttribute("seq_num");
		System.out.println(seq_num);
		ArrayList list = accdao.AccountList(seq_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/account/accountpage");
		mav.addObject("list", list);
		
		return mav;
	}

}
