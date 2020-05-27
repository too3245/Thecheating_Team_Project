<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
	</header>
	<br/><br/>
	<table class="table">
		<tr>
			<td align="center" style="border-bottom: hidden; border-top: hidden;">
				비밀번호 변경 완료
			</td>
		</tr>
		<tr>
			<td align="center" style="border-bottom: hidden; border-top: hidden;">
				<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='loginui.do'">로그인하기</button>
			</td>
		</tr>
	</table>
	<footer>
		<jsp:include page="/bottom/bottom.jsp"></jsp:include>
	</footer>	
</body>
</html>