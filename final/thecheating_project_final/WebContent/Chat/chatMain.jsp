<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	
    


<style type="text/css">
	#chatArea {
    width: 200px; height: 100px; overflow-y: auto; border: 1px solid black;
}
</style>
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
                        <table class="table">                                       
                        		<td colspan="2">
                        			<div id="chatArea"><div id="chatMessageArea"></div></div>
                        		</td>
                        		<td>
                        	   		아이디 목록<br>
                        	   		<c:forEach items="${members }" var="member" >
       								${member }<br>
                        	   		</c:forEach>
                        	   		
                        	   	</td>
                        	</tr> 
                        	<tr>	
                        			<c:if test="${members[0] eq mem_username}">
                        				<td>
                        					<input type="button" class="w3-button w3-black w3-round" value="돈 모으기" id="getMoney"/>
                        				</td>
                        			</c:if> 
                        			<td>
                        				<input type="button" class="w3-button w3-black w3-round" value="돈 보내기" id="sendMoney"/>
                        			</td>
                        	   		<td>
                        	   			<input type="text" id="message"/>
                        	   		</td>
                        	   		<td>
                        	   			<input type="button"  class="w3-button w3-black w3-round" id ="sendBtn" value="전송"/>
                        	   		</td> 
                        	   		<c:if test="${members[0] eq mem_username}">
                        				<td>
                        					<input type="button" class="w3-button w3-black w3-round" value="결제" id="clearMoney"/>
                        				</td>
                        			</c:if> 
                        	   </tr>
                        	                  
                        </table>
                    </div>
                </div>
              </div>
        </article>
    </section>
<footer>
	<jsp:include page="/bottom/bottom.jsp"></jsp:include>
</footer>
<script type="text/javascript">
	
	
	var wsocket;
	wsocket = new WebSocket("ws://localhost:3307/thecheating_Final_One/BoardChat/${board}");
	wsocket.onopen = onOpen;
	wsocket.onmessage = onMessage;
	wsocket.onclose = onClose;
	function onOpen(evt){
		appendMessage('${mem_username}'+"연결되었습니다.");
	}
	function onMessage(evt){
		var data = evt.data;
		if(data.substring(0,4)=="msg:"){
			appendMessage(data.substring(4));
		}
	}
	function onClose(evt){
		appendMessage("연결 끊김");
	}
	function disconnect(){
		wsocket.close();
	}
	function send(){
		var nickname = '${mem_username}';
		var msg = $("#message").val();
		wsocket.send("msg:"+nickname+":"+msg);
		$("#message").val("");
	}
	function appendMessage(msg){
		$("#chatMessageArea").append(msg+"<br>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
		
		$("#chatArea").scrollTop(maxScroll);
	}
	$(document).ready(function(){
		
		$("#message").keypress(function(event){
				var keycode = (event.keyCode ? event.keyCode : event.which);
				
				if(keycode=='13'){
					send();
				}
				event.stopPropagation();
			});
				$('#sendBtn').click(function(){send();});
				$('#enterBtn').click(function(){connect();});
				$('#exitBtn').click(function(){disconnect();});
				
		});
	window.addEventListener('load',function(){
        var sendMoney = document.querySelector('#sendMoney');
        var getMoney = document.querySelector('#getMoney');
        var clearMoney = document.querySelector('#clearMoney');
        if('${members[0] }'==='${mem_username }'){
	        getMoney.addEventListener('click',function(){
	            var url = 'getMoneyui.do?room='+'${board}';
	            window.open(url,"","width=400,height=400,left=600");
	        });
        }
        sendMoney.addEventListener('click',function(){
        	var url = 'sendMoneyui.do?room='+'${board}';
        	window.open(url,"","width=400,height=400,left=600");
        });
        clearMoney.addEventListener('click',function(){
        	var url = 'clearMoneyui.do?room='+'${board}';
        	window.open(url,"","width=400,height=400,left=600");
        });
    });
</script>
</body>
</html>