<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">

	function CheckForm() {	
		var mem_email = window.document.FrmLogin.mem_email
		if(mem_email.value == "") {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		if(document.FrmLogin.mem_username.value == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		
		if(document.FrmLogin.mem_birthday.value == "") {
			alert("생일을 입력하세요.");
			return false;
		}
		
		if(document.FrmLogin.mem_phone.value == "") {
			alert("핸드폰번호를 입력하세요.");
			return false;
		}
		
	}
</script>
</head>
<body>
<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
</header>

<section> 
	<article>
		<div class="container">
			<div class="row mainArticle">
				<div class="col-xl-12 p-5">
					<form action ="passwordfind.do" id = "FrmLogin" name = "FrmLogin" method = "post" onsubmit = "return CheckForm();" >
						<table class="class">
							<tr>
								<td align="right">
									이메일 &nbsp;
								</td>
								<td>	
									<input type = "text" name = "mem_email" id = "mem_email" size="16" >
								</td>
							</tr>
							<tr>
								<td align="right">
									이름 &nbsp;
								</td>
								<td>
									<input type = "text" name = "mem_username" id = "mem_username" size="16" >
								</td>
							</tr>
							<tr>
								<td align="right" >
									생년월일 &nbsp;
								</td>
								<td>
									<input type = "date" name = "mem_birthday" id = "mem_birthday" size="16">
								</td>
							</tr>
							<tr>
								<td align="right" >
									핸드폰번호 &nbsp;
								</td>
								<td>
									<input type = "text" name = "mem_phone" id = "mem_phone" size="16">
									${result }
								</td>
							</tr>
							<tr>
								<td colspan="2" >
				 					<input type = "submit" class="w3-button w3-black w3-round" style="font-size:1em;" value = "비밀번호 찾기" >
				 					<input type = "button" class="w3-button w3-black w3-round" style="font-size:1em;" value = "로그인하기" onclick = "location.href='loginui.do'" > 
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</article>
</section>		

<footer>
	<jsp:include page="/bottom/bottom.jsp"></jsp:include>
</footer>	
</body>
</html>