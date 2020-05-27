<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<meta charset=UTF-8>
<title>Insert title here</title>
<script>
	window.addEventListener('load',function(){
		const selectElement = document.querySelector('#idx');
		const result = document.querySelector('.result');
		selectElement.addEventListener('change', (event) => {
		  result.textContent = event.target.value
		});
		
	});
	
</script>
</head>
<body>
	<form action="sendMoney.do" method="post">
		<table>
			<tr>
				<td>제목</td>
				<td>
					
					<select name ="idx" id="idx">
						<option value="">방을 선택하시오.</option>
						<c:forEach items="${moneys }" var="money" >
							<option id="money" value="${money.moneyIdx }+${money.leftMoney }" >${money.moneyTitle }</option>
				 		</c:forEach>
				 	</select> 
				 </td>
				 <td><div class="result"></div> </td>
			</tr>
			<tr>
				<td >보내는 돈</td>
				<td colspan="2"><input type="text" name="money"/></td>
			</tr>
				<input type="hidden" name="room" value=${param.room } />
				<input type="hidden" name="member" value=${mem_username }/>
			<tr>
				<td colspan="3"><input type="submit" value="보내기"/></td>
			</tr>
		</table>
	</form>
</body>
</html>