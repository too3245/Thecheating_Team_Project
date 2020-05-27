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
					<div class = "accountregister">
						<form action = "accountregister.do" method = "post" >	
							<table class="class" align="center">
								<tr>
									<td colspan="2" align="center" style="font-size:30px;">
										계좌등록
									</td>
								</tr>
							
								<tr>
									<td align="right">
										이름 &nbsp;
									</td>
									<td>
										<input type = "text" name = "holder"  size="30" style="height:40px;">
									</td>
								</tr>
								<tr>
									<td align="right">
										계좌번호 &nbsp;
									</td>
									<td>
										<input type = "text" name = "account_num"  size="30" style="height:40px;" >
									</td>
								</tr>
								<tr>
									<td align="right">
										은행 &nbsp;
									</td>
									<td>
											<select name='bank_name' >
							  					<option value='' selected>-- 선택 --</option>
							 					<option value='1'>국민</option>
							  					<option value='2'>기업</option>
							 					<option value='3'>신한</option>
							 					<option value='4'>우리</option>
											</select>
									</td>
								</tr>
								<tr>
									<td align="right">
										유효기간 &nbsp;
									</td>
									<td>
										<input type = "date" name = "val_date"  size="30" style="height:40px;" >
									</td>
								</tr>
								<tr>
									${result }
									<td colspan="2" align="center">
										<input type = "submit" class="w3-button w3-black w3-round"style="font-size:1em;" value = "계좌등록" >
										<input type = "button" class="w3-button w3-black w3-round"style="font-size:1em;" value = "취소" onclick = "location.href='accountpageui.do'">
									</td>	
								</tr>
							</table>
						</form>	
					</div>
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