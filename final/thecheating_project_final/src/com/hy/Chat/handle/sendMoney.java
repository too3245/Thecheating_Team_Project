package com.hy.Chat.handle;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.hy.Chat.DAO.ChatDAO;


public class sendMoney extends AbstractController {
	
private ChatDAO dao = null;
	
	public void setDao(ChatDAO dao){
		this.dao = dao;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String room = request.getParameter("room");
		String title =request.getParameter("title");
		String member = request.getParameter("member");
		String money_c = request.getParameter("money");
		int money = Integer.parseInt(money_c);
		String idxs = request.getParameter("idx");
		System.out.println(idxs);
		String[] middle = idxs.split("\\+");
		String idx = middle[0];
		int leftmoney = Integer.parseInt(middle[1]);

		if((leftmoney-money) < 0){
			ModelAndView mav = new ModelAndView("");
			mav.setViewName("Close/Close");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('남은돈 이상을 입금하는 것은 불가능합니다.');</script>");
			out.flush();
			return mav;
		}
		dao.sendMoney(leftmoney-money, idx, room, member, money_c);
		ModelAndView mav = new ModelAndView("");
		mav.setViewName("Close/Close");
		return mav;
	}

}
