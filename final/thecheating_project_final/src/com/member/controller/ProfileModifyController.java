package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.member.dao.MemberDAO;
import com.member.dto.MemberDTO;

public class ProfileModifyController implements Controller {
	
	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String mem_nickname = request.getParameter("mem_nickname");
		String mem_birthday = request.getParameter("mem_birthday");
		String mem_phone = request.getParameter("mem_phone");
		String mem_email = request.getParameter("mem_email");
		String mem_username = request.getParameter("mem_username");
		
		
		memdao.profileModify(mem_nickname, mem_birthday, mem_phone, mem_email, mem_username);
		
		ModelAndView mav = new ModelAndView("/member/profilemodifysuccess");
		
		
		return mav;
	}

}
