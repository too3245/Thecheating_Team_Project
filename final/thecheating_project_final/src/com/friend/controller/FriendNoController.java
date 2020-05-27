package com.friend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.friend.dao.FriendDAO;
import com.member.dao.MemberDAO;

public class FriendNoController implements Controller {
	
	
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
		
		//친구요청 받은 사람의 테이블명
		String mem_idx = (String) session.getAttribute("mem_idx");
		
		// 친구요청 받은 사람의 이메일
		String friend_request = (String) session.getAttribute("mem_email");
		
		// 친구요청 한 사람의 이메일
		String friend_response = request.getParameter("friend_email");
	
		
		
		
		//친구요청 한 사람의 테이블명
		String mem_idx2 = memdao.memberIDX(friend_response);
		

		// 친구요청 한 사람의 친구테이블 값 불러오기
		String friend_idx2 = fridao.friendResponseIDX(mem_idx, friend_response);
		

		//친구요청 받은 사람의 친구테이블 값 불러오기
		String friend_idx = fridao.friendRequestIDX(mem_idx2, friend_request);
		
		
		
		// 친구요청 받은 사람의 친구신청 데이서 삭제
		fridao.friendNO(mem_idx, friend_idx2);
		// 친구요청 한 사람의 친구신청 데이터 삭제
		fridao.friendNO2(mem_idx2, friend_idx);
		
		ModelAndView mav = new ModelAndView("redirect:/friendlist.do");
		
		return mav;
	}

}
