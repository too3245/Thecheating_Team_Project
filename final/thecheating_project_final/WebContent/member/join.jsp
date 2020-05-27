<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%
String emailCheck = request.getParameter("mem_email");
if(emailCheck == null) {
	emailCheck = "";
}
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-1.6.2.js" type="text/javascript" ></script>
		
	<!-- 비밀번호 확인 -->
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
        		} else {
        			$('#lblError').text(''); // 클리어
        			$('#lblError').html("<b>암호가 일치합니다.</b>"); // 레이어에 텍스트 출력
        		}	
        	});
 
        });
       
    </script>
    
    <!-- 필수입력 경고창 -->
   	<script language="javascript">

	function CheckForm() {	
		
		if(document.FrmLogin.mem_email.value == "") {
			alert("이메일을 입력하세요.");
			return false;
		}
		if(document.FrmLogin.mem_password.value == "") {
			alert("비밀번호를 입력하세요.");
			return false;
		}
		if(document.FrmLogin.mem_passwordck.value == "") {
			alert("비밀번호 확인을 입력하세요.");
			return false;
		}
		if(document.FrmLogin.mem_username.value == "") {
			alert("이름을 입력하세요.");
			return false;
		}
		if(document.FrmLogin.mem_nickname.value == "") {
			alert("닉네임을 입력하세요.");
			return false;
		}
		if(document.FrmLogin.mem_sex.value == "") {
			alert("성별을 선택하세요.");
			return false;
		}
		if(document.FrmLogin.mem_birthday.value == "") {
			alert("생년월일을 입력하세요.");
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
						<div class = "join" >
							<form action = "join.do" id = "FrmLogin" name = "FrmLogin" method = "post" onsubmit = "return CheckForm();" >
								<table class="class" align="center">
									<tr>
										<td></td>
										<td colspan="2" style="font-size:30px;" >
											회원가입 <br />
										</td>
									</tr>
									<tr>
										<td width="110" align="right" >
											EMAIL &nbsp;
										</td>
										<td>
											<input type = "email" name = "mem_email" id = "mem_email" value = "<%= emailCheck %>"  size="30" style="height:40px;" placeholder = "이메일을 입력하세요."/>
										</td>
										<td>
										 	<input type = "button" value = "이메일 중복 확인" class="w3-button w3-black w3-round" style="font-size:1em;"  onclick = "location.href = 'email.do?emailCheck='+mem_email.value" /> <br />
											${result }
										</td>
									</tr>
									<tr>
										<td align="right" >
											비밀번호 &nbsp;
										</td>
										<td>
											<input type = "password" id = "txtPassword" name = "mem_password" id = "mem_password" size="30" style="height:40px;" /> <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
											비밀번호 확인 &nbsp;
										</td>
										<td>
											<input type = "password" id = "txtPasswordConfirm" name = "mem_passwordck" id = "mem_passwordck" size="30" style="height:40px;" placeholder = "비밀번호 확인"/> 
										</td>
										<td>
											<div id="lblError"></div>
										</td>
									</tr>
									<tr>
										<td align="right" >
											이름 &nbsp;
										</td>
										<td>
											<input type = "text" name = "mem_username" id = "mem_username" size="30" style="height:40px;" /> <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
											닉네임 &nbsp;
										</td>
										<td>
											<input type = "text" name = "mem_nickname" id = "mem_nickname" size="30" style="height:40px;" /> <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
											성별 &nbsp;
										</td>
										<td>
											남&nbsp;<input type = "radio" name = "mem_sex" id = "mem_sex" value = "남자" />&nbsp;|&nbsp;
											&nbsp;여<input type = "radio" name = "mem_sex" id = "mem_sex" value = "여자" /> <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
											생년월일 &nbsp;
										</td>
										<td>
											<input type = "date" name = "mem_birthday" id = "mem_birthday" size="30" style="height:40px;" /> <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
											휴대폰번호 &nbsp;
										</td>
										<td>
											<input type = "tel" name = "mem_phone" id = "mem_phone" size="30" style="height:40px;" /> <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
								    		계좌번호 &nbsp;
								    	</td>
								    	<td>
								    		<input type = "text" name = "acc_seq" value = "${account_num }" size="30" style="height:40px;" /><br />
								    	</td>
								    	<td>
								    		<input type = "button" class="w3-button w3-black w3-round" style="font-size:1em;" value = "계좌등록하기" onclick = "window.open('accountcheck.jsp', 'account_insert', 'width=430, height=500, location = no, status = no, scrollbars = yes');" />
								    	</td>
							    	</tr>
							    	<tr >
							    		<td></td>
							    		<td colspan="2">
								    		<input type = "checkbox" class = "selectAllMembers" />전체 이용약관 동의<br />
								   	 		<input type = "checkbox" class = "memberChk" name = "mem_agree1" value = "Y"  required 
								    			oninvalid="this.setCustomValidity('필수 이용약관에 동의하세요.')"
								    			oninput = "setCustomValidity('')" />이용약관 동의 (필수) <br />
								    		<input type = "checkbox" class = "memberChk" name = "mem_agree2" id = "memberChk" value = "Y" required 
								    			oninvalid="this.setCustomValidity('필수 이용약관에 동의하세요.')"
								    			oninput = "setCustomValidity('')" />개인정보 수집 및 이용에 대한 동의 (필수) <br />
								    		<input type = "checkbox" class = "memberChk" name = "mem_agree3" value = "Y" />개인정보 수집 및 이용안내 (선택) <br />
								    		<input type = "checkbox" class = "memberChk" name = "mem_agree4" value = "Y" />개인정보 제 3자 제공에 대한 동의 (선택) <br />
								    	</td>
							    	</tr>
							    		<!-- 이용약관 동의 -->
								 	   <script>
								    		var selectAll = document.querySelector(".selectAllMembers");
								    		selectAll.addEventListener('click', function() {
								    			var objs = document.querySelectorAll(".memberChk");
								    			for (var i = 0; i < objs.length; i++) {
								    				objs[i].checked = selectAll.checked;
								   				};
								   			}, false);
								   			
								   			var objs = document.querySelectorAll(".memberChk");
								    		
								   	 		for(var i = 0; i < objs.length; i++) {
								   	 			objs[i].addEventListener('click', function() {
								   	 				var selectAll = document.querySelector(".selectAllMembers");
								   	 				for (var j = 0; j < objs.lenght; j++) {
								   	 					if (objs[j].checked === false) {
								   	 						selectAll.checked = false;
								    							return;
								    					};
								    				};
								    				selectAll.checked = true;
								    			}, false);
								    		}
								   		</script>
							   		<tr>
							   			<td></td>
								   		<td colspan="2">
											<input type = "submit" id = "submit"   class="w3-button w3-black w3-round"style="font-size:1em;"  value = "회원가입" />
											<input type = "button" value = "취소" class="w3-button w3-black w3-round" style="font-size:1em;" onclick = "location.href='loginui.do'" /> 
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