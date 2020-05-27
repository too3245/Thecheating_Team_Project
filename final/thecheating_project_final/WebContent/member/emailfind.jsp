<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">

	function CheckForm() {	
		
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
					<form action = "emailfind.do" id = "FrmLogin" name = "FrmLogin" method = "post" onsubmit = "return CheckForm();" >
						<table class="class">
						<tr>
							<td align="right" style="border-bottom: hidden; border-top: hidden;">
								이름 &nbsp;
							</td>
							<td>
								<input type = "text" name = "mem_username" size="16px">
							</td>
						</tr>
						<tr>
							<td align="right" style="border-bottom: hidden; border-top: hidden;">
								생년월일 &nbsp;
							</td>
							<td>
								<input type = "date" name = "mem_birthday" size="16px" />
							</td>
						</tr>
						<tr>
							<td align="right" style="border-bottom: hidden; border-top: hidden;">
								핸드폰번호 &nbsp;
							</td>
							<td>
								<input type = "text" name = "mem_phone" size="16px" />
							</td>
						</tr>
						<tr align="center" >	
							<td align="right" style="border-bottom: hidden; border-top: hidden;">
							</td>
							<td>
								<input type = "submit" class="w3-button w3-black w3-round" style="font-size:1em;" value = "아이디 찾기"> 
			 					<input type = "button" class="w3-button w3-black w3-round" style="font-size:1em;" value = "취소" onclick = "location.href = 'loginui.do'">
								${result }
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