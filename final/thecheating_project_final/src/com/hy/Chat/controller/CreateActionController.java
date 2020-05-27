package com.hy.Chat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.hy.Chat.ChatCommand;
import com.hy.Chat.DAO.ChatDAO;

public class CreateActionController extends AbstractCommandController {

	private ChatDAO dao = null;
	
	public void setdao(ChatDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		ChatCommand data = (ChatCommand)command;
		System.out.println(data.getChatName()+"\t"+data.getChatPassword()+"\t"+data.getChatHuman()+"\t"+data.getChatSecret());
		dao.create(data);
		ModelAndView mav = new ModelAndView("redirect:/chatList.do");
		return mav;
	}

}
