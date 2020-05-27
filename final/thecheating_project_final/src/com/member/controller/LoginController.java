package com.member.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;
import com.member.dto.MemberDTO;
import com.member.util.SHA256Util;

public class LoginController implements Controller {
	
	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;

	}
	


	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		String mem_email = request.getParameter("mem_email");
		String mem_password = request.getParameter("mem_password");
		
		String salt = memdao.memberSalt(mem_email);
		mem_password = SHA256Util.getEncrypt(mem_password, salt);
		
		
		String result = memdao.memberLogin(mem_email, mem_password);
		ModelAndView mav = new ModelAndView();
		if (result == "1") {
			MemberDTO memberDTO = memdao.loginSession(mem_email, mem_password);
			String mem_idx = memberDTO.getMem_idx();
			String mem_username = memberDTO.getMem_username();
			String mem_nickname = memberDTO.getMem_nickname();
			String mem_birthday = memberDTO.getMem_birthday();
			String mem_sex = memberDTO.getMem_sex();
			String mem_phone = memberDTO.getMem_phone();
			String mem_loginip = memberDTO.getMem_loginip();
			String seq_num = memberDTO.getSeq_num();
			
			HttpSession session = request.getSession();
			session.setAttribute("mem_idx", mem_idx);
			session.setAttribute("mem_email", mem_email);
			session.setAttribute("mem_password", mem_password);
			session.setAttribute("mem_username", mem_username);
			session.setAttribute("mem_nickname", mem_nickname);
			session.setAttribute("mem_birthday", mem_birthday);
			session.setAttribute("mem_sex", mem_sex);
			session.setAttribute("mem_phone", mem_phone);
			session.setAttribute("mem_loginip", mem_loginip);
			session.setAttribute("seq_num", seq_num);
			
			request.setAttribute("session", session);
			mav.addObject("data", session);
			mav.setViewName("/main");
			System.out.println("로그인 성공");
			return mav;
		} else {
			mav.addObject("result", result);
			mav.setViewName("/member/login");
			return mav;
		}
		
		
	}

}
