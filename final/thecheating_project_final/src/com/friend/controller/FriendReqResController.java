package com.friend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.friend.dao.FriendDAO;
import com.member.dao.MemberDAO;

public class FriendReqResController implements Controller {
	
	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;

	}
	
	private FriendDAO fridao;
	
	public void setFridao(FriendDAO fridao) {
		this.fridao = fridao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//친구요청 하는사람
		HttpSession session = request.getSession();
		String mem_idx = (String) session.getAttribute("mem_idx");
		String mem_email = (String) session.getAttribute("mem_email");
		
		
		//친구요청 받는사람
		String mem_email2 = request.getParameter("mem_email_friend");
		String mem_idx2 = memdao.memberIDX(mem_email2);
		
		// 친구요청 하는사람
		fridao.friendRequest(mem_idx, mem_email2);
		
		// 친구요청 받는사람	
		fridao.friendResponse(mem_idx2, mem_email);
		
		ModelAndView mav = new ModelAndView("redirect:/friendlist.do");
		
		return mav;
	}

}
