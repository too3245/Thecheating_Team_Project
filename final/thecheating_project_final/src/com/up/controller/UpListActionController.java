package com.up.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.up.dao.UpDAO;
import com.up.dto.UpDTO;
import com.util.PageNavigator;


public class UpListActionController implements Controller {
	private UpDAO dao;
	
	public void setdao(UpDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("UP List Action Controller 실행됨!");

		String pageNum = request.getParameter("pageNum"); // 페이지 번호
		String searchType = request.getParameter("searchType"); // 검색종류(전체검색/작성자/제목)
		String searchText = request.getParameter("searchText");  // 실제 검색어
		
		int listCount = 10; // 한 페이지당 게시글 갯수 10개
		int pagePerBlock = 10;  // 페이지번호 나타낼 갯수 10개
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
		
		ArrayList<UpDTO> lists = dao.list(searchType, searchText,Integer.parseInt(pageNum),count);
		PageNavigator upNavigator = new PageNavigator();
		String up_navi = upNavigator.getPageNavigator(count, listCount, pagePerBlock, Integer.parseInt(pageNum), searchType, searchText);

		ModelAndView mav = new ModelAndView(); 
        mav.setViewName("up/upList"); 	//	업데이트 게시글 목록으로 

        mav.addObject("lists", lists); 	// 업데이트 게시글 리스트 조회
        mav.addObject("pageNavigator", up_navi); // 페이지 넘버 설정
		
		return mav;
	}

}
