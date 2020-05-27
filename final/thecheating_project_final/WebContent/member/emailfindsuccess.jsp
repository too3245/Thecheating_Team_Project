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
    <section> 
        <article>
            <div class="container">
                <div class="row mainArticle">
                    <div class="col-xl-12 p-5">
					<form action = "emailfind.do" id = "FrmLogin" name = "FrmLogin" method = "post" onsubmit = "return CheckForm();" >
						<table class="class">
						<tr>
							<td align="center" style="border-bottom: hidden; border-top: hidden;">
								${mem_username  } 님의 아이디는 :
								${result }  입니다.
							</td>
						</tr>
							<tr>
								<td align="center" style="border-bottom: hidden; border-top: hidden;">
 									<input type = "button" class="w3-button w3-black w3-round" style="font-size:1em;" value = "로그인하러 가기" onclick = "location.href = 'loginui.do'" />
 									<input type = "button" class="w3-button w3-black w3-round" style="font-size:1em;" value = "비밀번호 찾기" onclick = "location.href = 'passwordfindui.do'" />
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