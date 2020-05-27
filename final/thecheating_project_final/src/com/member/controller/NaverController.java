package com.member.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


public class NaverController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		
		ModelAndView mav = new ModelAndView();
		String clientId = "5VuRhEnf1uN3D5aUrKBs";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "h38CT5Kw9b";//애플리케이션 클라이언트 시크릿값";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("http://localhost:3307/thecheating_Final_One/callback.do", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;

		// access_token 요청
		try {
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		BufferedReader br;
		
		if(responseCode==200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {  // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();

		//성공시에 
		if(responseCode==200) {
			System.out.println(res.toString());
			//토큰 받은거......... json 파싱..........
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(res.toString());
			System.out.println("access_token="+jsonObj.get("access_token"));

			// 이제.. 회원 정보를 요청한다............
			String token = jsonObj.get("access_token").toString(); // 네이버 로그인 접근 토큰;
			String header = "Bearer " + token; // Bearer 다음에 공백 추가
			String apiURL2 = "https://openapi.naver.com/v1/nid/me";
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("Authorization", header);
			String responseBody = getAPI(apiURL2,requestHeaders,"GET");
		

			// 회원정보 받은거 제이슨파싱... 
			JSONParser jsonParser1 = new JSONParser();
			JSONObject jsonObj1 = (JSONObject) jsonParser1.parse(responseBody);
			System.err.println(responseBody);
			String resultcode = jsonObj1.get("resultcode").toString();
			JSONObject memberArray = (JSONObject) jsonObj1.get("response");
			if(resultcode.equals("00")){
				String naver_id = memberArray.get("id").toString();
				String mem_email = memberArray.get("email").toString();
				String mem_username = memberArray.get("name").toString();
				String mem_nickname = memberArray.get("nickname").toString();
				String mem_sex = memberArray.get("gender").toString();
				String mem_birthday = memberArray.get("birthday").toString();
				HttpSession session = request.getSession();
				session.setAttribute("mem_idx", naver_id);
				session.setAttribute("mem_email", mem_email);
				session.setAttribute("mem_username", mem_username);
				session.setAttribute("mem_nickname", mem_nickname);
				session.setAttribute("mem_sex", mem_sex);
				session.setAttribute("mem_birthday", mem_birthday);
				
				request.setAttribute("session", session);
				mav.addObject("session", session);
			}else{
				mav.addObject("name", "실패~");
			}
			
		}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		mav.setViewName("/main");
		return mav;
	}
	
	//api 호출
			private static String getAPI(String apiUrl, Map<String, String> requestHeaders, String reqType){
			
				HttpURLConnection con = connect(apiUrl);
				try {
					con.setRequestMethod(reqType);
					for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
						con.setRequestProperty(header.getKey(), header.getValue());
					}
					int responseCode = con.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
						return readBody(con.getInputStream());
					} else { // 에러 발생
						return readBody(con.getErrorStream());
					}
				} catch (IOException e) {
					throw new RuntimeException("API 요청과 응답 실패", e);
				} finally {
					con.disconnect();
				}
			}
			//http 커넥션
			private static HttpURLConnection connect(String apiUrl){
				try {
					URL url = new URL(apiUrl);
					return (HttpURLConnection)url.openConnection();
				} catch (MalformedURLException e) {
					throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
				} catch (IOException e) {
					throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
				}
			}
		
			private static String readBody(InputStream body){
				InputStreamReader streamReader = new InputStreamReader(body);
		
				try (BufferedReader lineReader = new BufferedReader(streamReader)) {
					StringBuilder responseBody = new StringBuilder();
		
					String line;
					while ((line = lineReader.readLine()) != null) {
						responseBody.append(line);
					}
		
					return responseBody.toString();
				} catch (IOException e) {
					throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
				}
			}

}
