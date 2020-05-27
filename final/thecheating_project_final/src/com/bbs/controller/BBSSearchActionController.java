package com.bbs.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.bbs.dao.BBSDAO;
import com.bbs.dto.BBSDTO;
import com.util.PageNavigator;

/*
 *  BBS(공지사항 안내 게시판)
 *  게시글 검색 controller
 */

public class BBSSearchActionController implements Controller {
	private BBSDAO dao;
	
	public void setdao(BBSDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("BBS Search Action Controller 실행됨!");
		
		String pageNum = request.getParameter("pageNum"); // 페이지번호
		String searchType = request.getParameter("searchType"); // 검색종류(전체검색/작성자/제목)
		String searchText = request.getParameter("searchText"); // 실제 검색어
		int list = 10; // 한 페이지 당 게시글 갯수 10개
		int pagePerBlock = 10;  // 페이지 번호 10개
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

		int count = dao.selectCount(searchType, searchText); // 글갯수로 게시글 검색하는 select 메소드 가져오기
		System.out.println(count);
		
		ArrayList<BBSDTO> lists = dao.list(searchType, searchText,Integer.parseInt(pageNum),count);
		
		PageNavigator pNavigator = new PageNavigator(); // 페이지 넘버 설정
		String p_navi = pNavigator.getPageNavigator(count, list,pagePerBlock, Integer.parseInt(pageNum), searchType, searchText);
		
		ModelAndView mav = new ModelAndView();
        mav.setViewName("bbs/bbsList"); 	// 공지사항 글목록으로 이동
        
        mav.addObject("lists", lists); 	
        mav.addObject("pageNavigator", p_navi);
        return mav;		// DispatcherServlet에게 전달
	}

}
