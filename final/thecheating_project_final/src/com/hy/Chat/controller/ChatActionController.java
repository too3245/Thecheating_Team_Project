package com.hy.Chat.controller;

import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.hy.Chat.DAO.ChatDAO;

public class ChatActionController implements Controller {

	private ChatDAO dao = null;
	
	public void setdao(ChatDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String num = request.getParameter("num");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mem_username");
		ArrayList<String> member = dao.memberList(num);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Chat/chatMain");
		mav.addObject("board",num);
		mav.addObject("members",member);
		int join = dao.searchID(num, id);
		System.out.println();
		if(join == 0){
			dao.insertRoom(num, id);
		}
		
		return mav;
	}

}
