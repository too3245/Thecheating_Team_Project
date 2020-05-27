package com.friend.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.friend.dao.FriendDAO;

public class FriendListController implements Controller {
	private FriendDAO fridao;
	
	public void setFridao(FriendDAO fridao) {
		this.fridao = fridao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		HttpSession session = request.getSession();
		String mem_idx = (String) session.getAttribute("mem_idx");
		String mem_email = (String) session.getAttribute("mem_email");
		
		
		// 친구 전체목록
		ArrayList list = fridao.friendList(mem_idx);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/friend/friendpage");
		mav.addObject("friendlist", list);
		
		// 친구검색리스트
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		ArrayList list2 = fridao.friendsearch(searchName, searchValue, mem_email, mem_idx);
		mav.addObject("list", list2);
		
		// 친구신청온 목록
		ArrayList list3 = fridao.friendRequestList(mem_idx);
		mav.addObject("requestlist", list3);
		
		// 친구신청한 목록
		ArrayList list4 = fridao.friendResponseList(mem_idx);
		mav.addObject("responselist", list4);
		
		return mav;
		
	}

}
