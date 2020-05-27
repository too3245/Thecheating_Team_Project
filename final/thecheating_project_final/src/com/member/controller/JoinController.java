package com.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.member.command.MemberCommand;
import com.member.dao.MemberDAO;

public class JoinController extends AbstractCommandController {

	private MemberDAO memdao;
	
	public void setMemdao(MemberDAO memdao) {
		this.memdao = memdao;

	}
	
	private String getIp(HttpServletRequest request) {
		
	    String ip = request.getHeader("X-Forwarded-For");
	    logger.info(">>>> X-FORWARDED-FOR : " + ip);
	    if (ip == null) {
	        ip = request.getHeader("Proxy-Client-IP");
	        logger.info(">>>> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
	        logger.info(">>>> WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_CLIENT_IP");
	        logger.info(">>>> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	        logger.info(">>>> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = request.getRemoteAddr();
	    }
	        
	    logger.info(">>>> Result : IP Address : "+ip);
	 
	    return ip;
	 
	}

	/**
	 * AbstractCommandController 가 가지고 있는 객체
	 * 
	 * 1. HttpServletRequest : 요청객체 2. HttpServletResponse : 응답객체 3. Object : 입력받은
	 * 값을 저장하는 객체 4. BindException : 사용자로부터 값을 입력 시, 에러가 발생하면 처리해주는 class
	 */
	@Override
	protected ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException error) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		String mem_email_check = request.getParameter("mem_email");
		
		/** spring 방식 */
		// BoardCommand는 상속받은 부모의 claa
		String result = memdao.emailCheck(mem_email_check);
		
		if(result == "이미 존재하는 이메일입니다.") {
			result = "이미 존재하는 이메일입니다. 이메일을 변경하세요.";
			ModelAndView mav = new ModelAndView("join");
			mav.addObject("result", result);
			return mav;
		} else {
			
		
		MemberCommand data = (MemberCommand) command;
		String mem_email = data.getMem_email();
		String mem_password = data.getMem_password();
		String mem_username = data.getMem_username();
		String mem_nickname = data.getMem_nickname();
		String mem_birthday = data.getMem_birthday();
		String mem_sex = data.getMem_sex();
		String mem_phone = data.getMem_phone();
		String mem_joinip = this.getIp(request);			
		System.out.println("이름:"+mem_username);
		
		memdao.memberJoin(mem_email, mem_password, mem_username, mem_nickname, mem_sex, mem_birthday, mem_phone, mem_joinip);
		
		// 친구 테이블명 생성을 위한 멤버테이블 idx값 가져오기
		String mem_idx = memdao.memberIDX(mem_email);
		
		// 친구테이블 생성
		memdao.firendInsert(mem_idx);
		
		// 친구테이블 시퀀스 생성
		memdao.friendSeq(mem_idx);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/login");
		/**
		 * response.sendRedirect("list.jsp"); <= Model2 방식 (Servlet)
		 * 
		 * ModelAndView mav = new ModelAndView(); mav.setViewName("list");
		 * 
		 * 위에 ModelAndView() 안에 넣음으로서 생략이 가능하다. ModelAndView mav = new
		 * ModelAndView("list");
		 * 
		 * return mav;
		 * 
		 */
		// 또는
		return mav; // 위의 주석 문을 한줄로 처리
		}
	}

}
