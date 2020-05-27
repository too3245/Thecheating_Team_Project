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
	
	<form action = "profilemodify.do" >
		<table class="class" align="center">
			<tr>
				<td rowspan="10">
					프로필사진
				</td>
			</tr>
			<tr>
				<td align="right">
					이메일 &nbsp;
				</td>
				<td>
					<input type = "text" name = "mem_email" value = "${mem_email }"  readonly>
				</td>
			</tr>
			<tr>
				<td align="right">
					닉네임 &nbsp;
				</td>
				<td>
					<input type = "text" name = "mem_nickname" value = "${mem_nickname }" >
				</td>
			</tr>
			<tr>
				<td align="right">
					이름 &nbsp;
				</td>
				<td>
					<input type = "text" name = "mem_username" value = "${mem_username }" readonly>
				</td>
			</tr>
			<tr>
				<td align="right">
					성별 &nbsp;
				</td>
				<td>
					<input type = "text" name = "mem_sex" value = "${mem_sex }" readonly >
				</td>
			</tr>
			<tr>
				<td align="right">
					생년월일 &nbsp;
				</td>
				<td>
					<input type = "date" name = "mem_birthday" value = "${mem_birthday }" >
				</td>
			</tr>
			<tr>
				<td align="right">
					휴대폰번호 &nbsp;
				</td>
				<td>
					<input type="text" name = "mem_phone" value = "${mem_phone }" >
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="정보변경" class="w3-button w3-black w3-round" style="font-size:1em;">
				</td>
				<td>
					<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href=passwordchangeui.do'" value="비밀번호 변경">
			</tr>
		</table>
	</form>
			</div>
		</div>
	</article>
</section>	
	<footer>
		<jsp:include page="/bottom/bottom.jsp"></jsp:include>
	</footer>		
</body>
</html>