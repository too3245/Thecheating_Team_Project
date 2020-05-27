package com.hy.Chat.handle;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hy.Chat.DAO.ChatDAO;
import com.hy.Chat.DTO.MoneyDTO;


public class sendMoneyui extends AbstractController {
	
private ChatDAO dao = null;
	
	public void setDao(ChatDAO dao){
		this.dao = dao;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String room = request.getParameter("room");
		
		ArrayList<MoneyDTO> moneys = dao.money_choose(room);
		ModelAndView mav = new ModelAndView();
		mav.addObject("moneys",moneys);
		mav.setViewName("money/sendMoney");
		return mav;
	}

}
