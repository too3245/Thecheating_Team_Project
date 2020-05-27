package com.friend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.friend.dao.FriendDAO;
import com.member.dao.MemberDAO;

public class FriendYesController implements Controller {
	
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
		
		HttpSession session = request.getSession();
		// 친구요청 받은사람의 테이블명
		String mem_idx = (String) session.getAttribute("mem_idx");
		// 친구요청 받은사람의 이메일
		String mem_email = (String) session.getAttribute("mem_email");
		// -------------------------
		// 친구요청 한사람의 이메일
		String mem_email2 = request.getParameter("friend_email");
		
		// 친구요청 한사람의 테이블명
		String mem_idx2 = memdao.memberIDX(mem_email2);
		
		// 친구요청 한사람의 friend_idx
		String friend_idx2 = fridao.friendRequestIDX(mem_idx2, mem_email);
		
		// 친구요청 한사람의 friend_idx
		String friend_idx = fridao.friendResponseIDX(mem_idx, mem_email2);
		
		// 친구신청 받은사람이 친구 수락시 자신의 친구테이블 수정
		fridao.friendYES(mem_idx, mem_email2, friend_idx);
		fridao.friendYES2(mem_idx2, mem_email, friend_idx2);
		
		ModelAndView mav = new ModelAndView("redirect:/friendlist.do");
		return mav;
		
	}

}
