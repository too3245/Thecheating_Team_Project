<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.contact.dto.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>비로그인 시 글쓰기 클릭했을 때 로그인 요청 화면</title>
</head>
<body>
<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
</header>
<br/><br/>
<br/><br/>
<br/><br/>
	<h3 align="center">로그인 후 이용해주세요.</h3>
	<form action="bbsRequestLoginui.do">
	<table class="table">
	<tbody>
		<tr>
			<td align="center" style="border-bottom: hidden; border-top: hidden;">
				<input type="button" class="w3-button w3-black w3-round" onclick="location.href='bbsList.do'" value="목록으로 돌아가기">
			</td>
		</tr>

	</tbody>
	</table>
	</form>
	<br/><br/>
	<br/><br/>
	<br/><br/>
	<footer>
    	<jsp:include page="/bottom/bottom.jsp"></jsp:include>
    </footer>
</body>
</html>