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
				정보변경완료
			</td>
		</tr>
		<tr>
			<td>
				<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='main.do'">메인으로</button>
			</td>
		</tr>
	</table>

<footer>
	<jsp:include page="/bottom/bottom.jsp"></jsp:include>
</footer>
</body>
</html>