<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>시작화면</title>

</head>
<body>
<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
</header>
	
	<div class = "login container" style="height: 800px;">
		<div class="col-xl-12" align="center">
			<table class="class" align="center">
				<tbody>
					<tr  >
						<td colspan="2" style="font-size:30px;" align="center">
							내 계좌 목록
						</td>
					</tr>
						<c:forEach var = "accountlist" items = "${list }" >
							<tr>
								<td align="right" >
									계좌번호 &nbsp;
								</td>
								<td>
								 	<input type="text" value="${accountlist.account_num }" size="30" style="height:40px;" />
								</td>
							</tr>
							
							<tr>
								<td align="right" >
									은행  &nbsp;
								</td>
								<td>
									<c:if test="${accountlist.bank_name eq '1' }" >
								 		<input type="text" value="국민은행" size="30" style="height:40px;" />
									</c:if>
								</td>
								<td>
									<c:if test="${accountlist.bank_name eq '2' }" >
										<input type="text" value="기업은행" size="30" style="height:40px;" />
									</c:if>
								</td>
								<td>
									<c:if test="${accountlist.bank_name eq '3' }" >
								 		<input type="text" value="신한은행" size="30" style="height:40px;" />
									</c:if>
								</td>
								<td>
									<c:if test="${accountlist.bank_name eq '4' }" >
								 		<input type="text" value="우리은행" size="30" style="height:40px;" />
									</c:if>
								</td>
							</tr>
							<tr>
								<td align="right" >
									이름  &nbsp;
								</td>
								<td> 
									<input type="text" value="${accountlist.holder }" size="30" style="height:40px;" />
								</td>
							</tr> 
							<tr>
								<td align="right" >
									유효기간  &nbsp;
								</td>
								<td>
									<input type="text" value="${accountlist.val_date }" size="30" style="height:40px;" />
								 </td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<input type = "button" class="w3-button w3-black w3-round"style="font-size:1em;" value = "계좌 삭제" onclick = "location.href='accountdelete.do'" />
								</td>
							</tr>
						</c:forEach>
					<tr></tr>
					<tr>
						<td colspan="2" align="center">
							<input type = "button"  class="w3-button w3-black w3-round"style="font-size:1em;" value = "계좌등록하기" onclick = "location.href='accountregisterui.do'"/> <br />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
<footer>
	<jsp:include page="/bottom/bottom.jsp"></jsp:include>
</footer>
</body>
</html>