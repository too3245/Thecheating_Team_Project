package com.hy.Chat.controller;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.hy.Chat.DAO.ChatDAO;
import com.hy.Chat.DTO.ChatDTO;
import com.hy.util.PageNavigator;

public class ListActionController implements Controller {

	private ChatDAO dao = null;
	
	public void setdao(ChatDAO dao)
	{
		this.dao = dao;
	}
	
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("list게시판");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("mem_idx");
		if(id == null){
			ModelAndView mav = new ModelAndView("");
			mav.setViewName("/main");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('올바르지 않은 접근입니다.');</script>");
			out.flush();
			return mav;
		}
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		int list = 10;
		int pagePerBlock = 10;
		if(pageNum == null){
			pageNum= "1";//전달된 페이지 번호가 없으면 1
		}
		if(searchType == null){
			searchType= ""; //전달된 검색어 종류가 없으면 ""로 초기화
		}
		if(searchText == null){
			searchText= ""; //전달된 검색어 종류가 없으면 ""로 초기화
			searchType= ""; //전달된 검색어가 없으면 ""로 초기화
		}
		
		
		
		
		int count = dao.selectCount(searchType, searchText);
		System.out.println(count);
		ArrayList<ChatDTO> lists = dao.list(searchType, searchText,Integer.parseInt(pageNum),count);
		PageNavigator pNavigator = new PageNavigator();
		String p_navi = pNavigator.getPageNavigator(count, list,pagePerBlock, Integer.parseInt(pageNum), searchType, searchText);
		ModelAndView mav = new ModelAndView();
        mav.setViewName("Chat/ChatList"); 	//	list.jsp

        // ViewResolver 에서 .jsp 를 추가 (board-servlet.xml 맨 마지막)
        
        // 서블릿 클래스의 request.setAttribute("list",list); 와 동일한 역할
        mav.addObject("lists", lists);
 
        mav.addObject("pageNavigator", p_navi);
        return mav;		// DispatcherServlet에게 전달
	}

}
