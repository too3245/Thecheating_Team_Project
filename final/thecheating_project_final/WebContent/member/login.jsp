<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>

<!DOCTYPE html>
<html>
<head>
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id" content="409696082026-jfi6bsfi2t93qeqerdo43vjvgtudfmif.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script language="javascript" type="text/javascript">

	function CheckForm() {	
		var mem_email = window.document.FrmLogin.mem_email
		if(mem_email.value == "") {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		if(document.FrmLogin.mem_password.value == "") {
			alert("암호를 입력하세요.");
			return false;
		}
	}
</script>
<title>Insert title here</title>
<meta charset="UTF-8">

</head>
<body>
	<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
	</header>
	<div class = "login container">


	<div class = "login" align="center">
	<form action = "login.do" id = "FrmLogin" name = "FrmLogin" method = "post" onsubmit = "return CheckForm();" >
	<table class="class">
		<tr>
			<td colspan="2" style="font-size:30px;" align="center">
				로그인 
			</td>
		</tr>
		<tr>
			<td align="right">
				eamil  &nbsp;
			</td>
			<td>
				<input type = "email" name = "mem_email" id = "mem_email" size="20" style="height:30px;" /> <br />
			</td>
		</tr>
		<tr>
			<td align="right">
				비밀번호  &nbsp;
			</td>
			<td>
				<input type = "password" name = "mem_password" id = "mem_password" size="20" style="height:30px;" /> <br />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type = "submit" value = "로그인" /> |
				<input type = "button" value = "회원가입" onclick = "location.href='joinui.do'"/> <br />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<a href = "emailfindui.do">이메일 찾기 </a>|
			<a href = "passwordfindui.do">비밀번호 찾기</a><br />
			${result }
			</td>
		</tr>
	
	
		<tr>
			<td colspan="2" align="center">
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
  				<a href="<%=apiURL%>"><img height="50" src="./image/Naver_Green.PNG"/></a>
		
		
		
				</td>
			</tr>
		</table>
		</form>
		</div>
	</div>
	
		<!------------------구글 로그인----------------------------------------------------->
		  <div class="g-signin2" data-onsuccess="onSignIn" align="center" data-width="231" data-height="50" data-longtitle="true" ></div>
		  	<form action="google.do" id="glogin" method="get">
		 		 <table class="class">
		 			 <tr>
		 			 	<td>
		 				   <input type="hidden" id="id" name="id" value="">
						   <input type="hidden" id="name" name="name" value="">
						   <input type="hidden" id="email" name="email" value="">
		   				</td>
		   			</tr>
		  		</table>
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
		
	<footer>
		<jsp:include page="/bottom/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>