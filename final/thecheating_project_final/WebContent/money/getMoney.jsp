<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Insert title here</title>
</head>
<body>
	<form action="getMoney.do" method="post">
		<table>
			<tr>
				<td>제목</td>
				<td><input tpye="text" name="title" /></td>
			</tr>
			<tr>
				<td>모으는 돈</td>
				<td><input type="text" name="money"/></td>
			</tr>
				<input type="hidden" name="room" value=${param.room } />
				<input type="hidden" name="member"/>
			<tr>
				<td colspan="2"><input type="submit" value="모으기"/></td>
			</tr>
		</table>
	</form>
</body>
</html>