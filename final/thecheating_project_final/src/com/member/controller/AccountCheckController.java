package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;

public class AccountCheckController implements Controller {
	
	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;

	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String holder = request.getParameter("holder");
		String account_num = request.getParameter("account_num");
		int bank_name = Integer.parseInt(request.getParameter("bank_name"));
		String val_date = request.getParameter("val_date");
		
		String result = memdao.accountCheck(holder, account_num, bank_name, val_date);
		ModelAndView mav = new ModelAndView();

		if (result == "1") {
			mav.addObject("account_num", account_num);
			mav.setViewName("/member/join");
			return mav;
		} else {
			mav.addObject("result", result);
			mav.setViewName("/member/accountcheck");
			return mav;
		} 
		
		
	}

}
