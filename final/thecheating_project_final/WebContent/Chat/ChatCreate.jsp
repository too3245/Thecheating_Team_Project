<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src= "./index.js"></script>
<script>
window.addEventListener('load',function(){
    document.querySelector("#form1").addEventListener('submit',submitCheck);
    function submitCheck(){
    const name = document.querySelector('#chatName');
    const passwordCheck = document.querySelector('#chatSecret');
    const password = document.querySelector('#chatPassword');
    	if(name.value==''){
              alert("아이디를 입력해주세요.");
              event.preventDefault();
              return false;
              
    	    }
    	if(passwordCheck.checked){
    	    if(password.value==''){
                console.log("비밀번호 입력!");
                alert("비밀번호 입력!");
                event.preventDefault();
    	        return false;
    	      }
            }else{
            	if(password.value!=''){
                    console.log("비밀번호 체크!");
                    alert("비밀번호 체크!");
                    event.preventDefault();
        	        return false;
        	      }
            }
        return true; 
    }
});
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
                    <form id="form1" action="chatCreate.do" method="post">
                        <table class="table">
                        	<tr>
                        		<td>
                        			방 제목
                        		</td>
                        		<td colspan="2">
                        			<input type="text" name="chatName" id="chatName">
                        		</td>
                        	</tr>
                        	<tr>
                        		<td>
                        			비밀번호
                        		</td>
                        		<td>
                        			<input type="password" name="chatPassword" id="chatPassword" >
                        		</td>
                        		<td>
                        			비밀방 여부<input type="checkbox" name="chatSecret" id="chatSecret" value="1">
                        		</td>
                        	</tr>
                        		<input type="hidden" name="chatHuman" value='${mem_username}'>                    
                        	<tr>
                        		<td>
                        		 	
                        		</td>
                        		<td>
                        			<input type="submit" class="w3-button w3-black w3-round" value="방 생성" />
                        		</td>
                        		<td>
                        			<input type="button" class="w3-button w3-black w3-round" value="리스트로 돌아가기"/>
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