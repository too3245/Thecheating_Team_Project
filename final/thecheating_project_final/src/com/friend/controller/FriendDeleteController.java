package com.friend.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.friend.dao.FriendDAO;
import com.member.dao.MemberDAO;

public class FriendDeleteController implements Controller {
	

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
		// 자신의 테이블 친구 삭제
		String mem_idx = (String) session.getAttribute("mem_idx"); 				// 자신의 멤버고유값 
		String friend_email = request.getParameter("friendlist_email"); 		// 친구 이메일
		String friend_idx = fridao.friendFriendIDX(mem_idx, friend_email);			// 자신의 친구테이블고유값
		
		// 친구의 테이블 친구삭제
		String mem_idx2 = memdao.memberIDX(friend_email);						// 친구의 멤버고유값
		String mem_email = (String) session.getAttribute("mem_email");			// 자신의 이메일
		String friend_idx2 = fridao.friendFriendIDX(mem_idx2, mem_email);	// 친구의 친구테이블고유값
		
		// 자신의 테이블 친구 삭제
		fridao.friendRequestDelete(mem_idx, friend_idx);
		
		// 친구의 테이블 친구 삭제
		fridao.friendResponseDelete(mem_idx2, friend_idx2);
		
		ModelAndView mav = new ModelAndView("redirect:/friendlist.do");
		
		return mav;
	}

}
