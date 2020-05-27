package com.bbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.bbs.command.BBSCommand;
import com.bbs.dao.BBSDAO;

/*
 *  BBS(공지사항 안내 게시판)
 *  게시글 쓰기 controller
 */

public class BBSWriteActionController extends AbstractCommandController {
	private BBSDAO dao;
	
	public void setdao(BBSDAO dao) {
		this.dao = dao;
		System.out.println("setDao() 호출됨(dao) : " + dao);
	
	}
	
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command, BindException error)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("BBS Write Action Controller 실행됨!");
		
		// BoardCommand 는 상속받은 부모의 Class (입력받아 올 데이터 : 글제목, 글쓴이, 글내용)
		BBSCommand data = (BBSCommand)command;
		String bbs_title = data.getBbs_title();
		String bbs_author = data.getBbs_author();
		String bbs_content = data.getBbs_content();
		
		// 글쓰기 메소드
		dao.write(bbs_title, bbs_author, bbs_content);

		// 글쓰기 화면에서 글 작성 후 저장하면 글목록으로 이동
		ModelAndView mav = new ModelAndView("redirect:/bbsList.do");

		return mav;
	}

}
