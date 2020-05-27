<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>Insert title here</title>
</head>
<body>
	<form action="clearMoney.do" method="post">
		<table>
			<tr>
				<td>은행 결제</td>
				<td>
					<select name ="idx" id="idx">
						<option value="">방을 선택하시오.</option>
						<c:forEach items="${moneys }" var="money" >
							<option id="money" value="${money.moneyIdx }" >${money.moneyTitle }</option>
				 		</c:forEach>
				 	</select> </td>
			</tr>
				<input type="hidden" name="room"/>
				<input type="hidden" name="member"/>
			<tr>
				<td colspan="2"><input type="submit" value="보내기"/></td>
			</tr>
		</table>
	</form>
</body>
</html>