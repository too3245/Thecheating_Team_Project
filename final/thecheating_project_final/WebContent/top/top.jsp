<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
 <!-- 네이버 로그인 -->
				 <%
				    String clientId = "5VuRhEnf1uN3D5aUrKBs";//애플리케이션 클라이언트 아이디값";
				    String redirectURI = URLEncoder.encode("http://localhost:3307/thecheating_Final_One/callback.do", "UTF-8");
				    SecureRandom random = new SecureRandom();
				    String state = new BigInteger(130, random).toString();
				    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
				    apiURL += "&client_id=" + clientId;
				    apiURL += "&redirect_uri=" + redirectURI;
				    apiURL += "&state=" + state;
				    session.setAttribute("state", state);
				 %>


<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
 <header>
 	<div class="container" >
    	<div class="row header">
    	
            <img src="./image/logo.jpg" width="200" onclick="location.href='main.do'" align="left"/>
         	<div class="btn pull-right col-xl-8" align="right"  style="display: inline-flex;  margin-top: 32px;  justify-content: flex-end;">
         	
         		<c:if test="${mem_username eq null}">
         		<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;     height: 50px;" onclick="location.href='./loginui.do'">로그인</button>
         		
         		<a href="<%=apiURL%>"><img height="50" src="./image/Naver_Green_Icon.PNG"/></a>
         		<!------------------구글 로그인----------------------------------------------------->
				  <div class="g-signin2" data-onsuccess="onSignIn" align="right" data-width="50" data-height="50" data-longtitle="true" style="margin: 0 !important;"></div>
				  	<form action="google.do" id="glogin" method="get">
				 		<input type="hidden" id="id" name="id" value="">
						<input type="hidden" id="name" name="name" value="">
					   <input type="hidden" id="email" name="email" value="">
				  	</form>
				  <script>
				
				    function onSignIn(googleUser) {
				
				      var profile = googleUser.getBasicProfile();
				      var id_token = googleUser.getAuthResponse().id_token;
				      var auth2 = gapi.auth2.getAuthInstance();
					   auth2.disconnect();
				      $("#id").val(profile.getId());
				      $("#name").val(profile.getName());
				      $("#email").val(profile.getEmail());
					
				      $("#glogin").submit();
				    }
				    
				  </script>
         		</c:if>
         		<c:if test="${mem_username ne null}">
         		<p style="font-size:30px; color:black; top:50%;" align="right">${mem_username } 님 환영합니다. /
         		<input type="button" value="로그아웃" class="w3-button w3-black w3-round" style="font-size:0.1em;" onclick="location.href='logout.do'"/>
         		</p>
         		</c:if>
         	</div>
    	</div>
   </div>
</header>
        <div class="container" >
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark col-xl-12">
            <a class="navbar-brand" href="main.do">THE CHEATING</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            
            <div class="collapse navbar-collapse" id="navbarNav" >
              <ul class="navbar-nav" >
                <li class="nav-item active">
                  <a class="nav-link" href="main.do">HOME<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown" >
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      ABOUT
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="dutchpayui.do">더치페이 소개</a>
                      <a class="dropdown-item" href="memberui.do">MEMBER</a>
                    </div>
                </li>
                <li class="nav-item active">
                  <a class="nav-link" href="chatList.do">DUCTH PAY</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      MY PAGE
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="profile.do">MY PAGE</a>
                      <a class="dropdown-item" href="friendlist.do">FRIEND</a>
                      <a class="dropdown-item" href="accountlist.do">ACCOUNT</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      INFO
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="bbsList.do">NOTICE</a>
                      <a class="dropdown-item" href="upList.do">UPDATE</a>
                      <a class="dropdown-item" href="eventList.do">EVENT</a>
                      <a class="dropdown-item" href="contactList.do">1:1 CONTACT</a>
                    </div>
                </li>
              </ul>
            </div>
          </nav>
        </div>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="409696082026-jfi6bsfi2t93qeqerdo43vjvgtudfmif.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>