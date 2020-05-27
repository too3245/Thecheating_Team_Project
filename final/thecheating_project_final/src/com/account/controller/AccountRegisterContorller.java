package com.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.account.dao.AccountDAO;
import com.member.dao.MemberDAO;

public class AccountRegisterContorller implements Controller {
	
	private AccountDAO accdao;
	private MemberDAO memdao;

	public void setAccdao(AccountDAO accdao) {
		this.accdao = accdao;
	}

	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;
	}


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String mem_email = (String) session.getAttribute("mem_email");
		String holder = (String) session.getAttribute("mem_username");
		String account_num = request.getParameter("account_num");
		int bank_name = Integer.parseInt(request.getParameter("bank_name"));
		String val_date = request.getParameter("val_date");
		
		
		String seq_num = accdao.AccountSeq_num(holder);
		
		String result = memdao.accountCheck(holder, account_num, bank_name, val_date);
		
		if (result == "일치하는 계좌가 없습니다.") {

			ModelAndView mav = new ModelAndView("redirect:/accountregisterui.do");
			mav.addObject("result", result);
			return mav;
		} else {
			
			accdao.AccountRegister(seq_num, mem_email);

			ModelAndView mav = new ModelAndView("redirect:/accountlist.do");
			
			return mav;
		} 
		
		
	}

}
