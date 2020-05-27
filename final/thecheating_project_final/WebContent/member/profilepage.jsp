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
		<div class="col-xl-12">
			<br /><br />
				<table class="class">
					<tbody>
						<tr >
							<td rowspan="10">프로필사진 <input type= />
							</td>
						</tr>
						<tr>
							<td align="right" >
								이메일 &nbsp;
							</td>
							<td>
								<input type="text" name="mem_email" value="${mem_email }" size="30" style="height:40px;" readonly />
							</td>
						</tr>
						
						<tr>
							<td align="right">
								닉네임 &nbsp;
							</td> 
							<td>
								<input type="text" name="mem_nickname" value="${mem_nickname }" size="30" style="height:40px;" />
							</td>
						</tr>
						<tr>
							<td align="right">
								이름  &nbsp;
							</td>
							<td>
								<input type="text" name="mem_username" value="${mem_username }" size="30" style="height:40px;" readonly />
							</td>
						</tr>
						<tr>
							<td align="right">
								성별  &nbsp;
							</td>
							<td>
								<input type="text" name="mem_sex" 
									<c:if test="${mem_sex eq 'M'}">
										value="남자"
									</c:if>
									<c:if test="${mem_sex eq 'F'}">
										value="여자"
									</c:if>
									<c:if test="${mem_sex eq '남자'}">
										value="남자"
									</c:if>
									<c:if test="${mem_sex eq '여자'}">
										value="여자"
									</c:if>
									size="30" style="height:40px;" readonly />
							</td>
						</tr>
						<tr>
							<td align="right">
								생년월일  &nbsp;
							</td>
							<td>
								<input type="text" name="mem_birthday" value="${mem_birthday }" size="30" style="height:40px;" />
							</td>
						</tr>
						<tr>
							<td align="right">
								휴대폰번호  &nbsp;
							</td>
							<td>
								<input type="text" name="mem_phone" value="${mem_phone }" size="30" style="height:40px;" />
							</td>
						</tr>
						<tr>
							<td>
								<input type="button" class="w3-button w3-black w3-round"  value="프로필수정" onclick="location.href='profilemodifyui.do'" />
							</td>
							<td>
								<input type="button" class="w3-button w3-black w3-round"  value="비밀번호변경" onclick="location.href='passwordchangeui.do'">
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