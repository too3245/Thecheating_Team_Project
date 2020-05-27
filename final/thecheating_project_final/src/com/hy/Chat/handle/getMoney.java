package com.hy.Chat.handle;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hy.Chat.DAO.ChatDAO;



public class getMoney extends AbstractController {
	
	private ChatDAO dao = null;
	
	public void setDao(ChatDAO dao){
		this.dao = dao;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String room = request.getParameter("room");
		String title =request.getParameter("title");
		String money = request.getParameter("money");
		
		dao.getMoney(room, title, money);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("Close/Close");
		return mav;
	}

}
