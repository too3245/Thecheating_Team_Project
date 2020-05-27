<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="//code.jquery.com/jquery-1.6.2.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function() {
        	// [1] lblError 레이어 클리어
        	$('#txtPassword').keyup(function() {
        		//$('#lblError').remove(); // 제거
        		$('#lblError').text(''); // 제거가 아니라 클리어
        	});
        	
        	// [2] 암호 확인 기능 구현
        	$('#txtPasswordConfirm').keyup(function() {
        		if($('#txtPassword').val() != $('#txtPasswordConfirm').val()) {
        			$('#lblError').text(''); // 클리어
        			$('#lblError').html("<b>암호가 틀립니다.</b>"); // 레이어에 HTML 출력
        			$('#button_joinus').attr('disabled', true); // 회원가입버튼 off
        		} else {
        			$('#lblError').text(''); // 클리어
        			$('#lblError').html("<b>암호가 일치합니다.</b>"); // 레이어에 텍스트 출력
        			$('#button_joinus').attr('disabled', false); // 회원가입버튼 on
        		}	
        	});
 
        });
    </script>
   
</head>
<body>
	<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
	</header>
	<br/><br/>
	
	<form action = "passwordchange.do"  >
		<table class="class" align="center">
			<tr>
				<td align="right">
					변경할 비밀번호 &nbsp;
				</td>
				<td>
					<input type = "password" id = "txtPassword" name = "mem_password" >
				</td>
			</tr>
			<tr>
				<td align="right">
					비밀번호 확인 &nbsp;
				</td>	
				<td>	
					<input type = "password" id = "txtPasswordConfirm" name = "mem_password" >
				</td>
			</tr>
			<tr>
				<td align="center" style="border-bottom: hidden; border-top: hidden;">
					<div id="lblError" ></div>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type = "submit" class="w3-button w3-black w3-round" style="font-size:1em;"  id = "button_joinus" value = "비밀번호 변경" disabled = "" >
					<input type = "reset" class="w3-button w3-black w3-round" style="font-size:1em;" value = "초기화">
				</td>
			</tr>
		</table>
	</form>
	<br/><br/>
	<footer>
		<jsp:include page="/bottom/bottom.jsp"></jsp:include>
	</footer>	
</body>
</html>