<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript">

	function CheckForm() {	
		if(document.FrmLogin.holder.value == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		
		if(document.FrmLogin.account_num.value == "") {
			alert("계좌번호를 입력하세요.");
			return false;
		}
		
		if(document.FrmLogin.bank_name.value == "") {
			alert("은행을 선택하세요.");
			return false;
		}
		
		if(document.FrmLogin.val_date.value == "") {
			alert("유효기간을 입력하세요.");
			return false;
		}
		
	}
</script>

</head>
<body>
<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
</header>
	<div id = "account">
		<form action = "account.do" id = "FrmLogin" name = "FrmLogin" method = "post" onsubmit = "return CheckForm();" >
		<br/><br/>
			<h3 align="center">계좌등록</h3>
				<table class="table">
					<tr>
						<td align="center" style="border-bottom: hidden; border-top: hidden;">
							이름: <input type = "text" name = "holder" id = "holder" >
						</td>
					</tr>
					<tr>
						<td align="center" style="border-bottom: hidden; border-top: hidden;">
							계좌번호: <input type = "text" name = "account_num" id = "account_num" >
						</td>
					</tr>
					<tr>
						<td align="center" style="border-bottom: hidden; border-top: hidden;">
							은행: <select name='bank_name' id = "bank_name" >
  								<option value='' selected>-- 선택 --</option>
 								<option value='1'>국민</option>
  								<option value='2'>기업</option>
 								<option value='3'>신한</option>
 								<option value='4'>우리</option>
							</select>
						</td>
					</tr>
						<td align="center" style="border-bottom: hidden; border-top: hidden;">
						유효기간: <input type = "date" name = "val_date" id = "val_date" ></td>
		
						${result }
					</tr>
					<tr>
						<td align="center" style="border-bottom: hidden; border-top: hidden;">
							<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='#'">계좌등록</button>
<!-- 						<input type = "submit" value = "계좌등록"> -->

							<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='#'">취소</button>
<!-- 						<input type = "button" value = "취소" onclick = "window.close()"> -->
						</td>
					</tr>
				</table>
				<br/><br/>
			</form>
		</div>
    <footer>
		<jsp:include page="/bottom/bottom.jsp"></jsp:include>
	</footer>
</body>
</html>