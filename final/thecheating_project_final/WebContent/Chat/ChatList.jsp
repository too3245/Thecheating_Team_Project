<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<script>
window.addEventListener('load',function(){
    const secretRoom = document.querySelectorAll('.room');
    const password = document.querySelectorAll('.password');
    const room_num = document.querySelectorAll('.room_num');
    for(let i =0; i<secretRoom.length; i++){
        secretRoom[i].onclick = function(){
            if(password[i].value ==''){
            	location.href='chatMain.do?num='+room_num[i].value;
            }else{
                const inserPassword = prompt('비밀번호를 입력하시오.');               
                if(password[i].value == inserPassword){
                	location.href='chatMain.do?num='+room_num[i].value;
                }else{
                    alert("비밀번호가 틀렸습니다.")
                }
            }
        }
    }
    
    //secretRoom.addEventListener('click',roomEnter);
    //location.href='chatMain.do?num=${list.chatIdx }'
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
                    	<form action="chatList.do" method="post">
                        <table class="table">
                            <thead>
                                <th scope="col">
                                    Number
                                </th>
                                <th scope="col">
                                    방이름
                                </th>
                                <th scope="col">
                                    방장
                                </th>
                                <th scope="col">
                                    인원
                                </th>
                                <th scope="col">
                                    비밀방
                                </th>
                            </thead>                         
                            <tbody>
                            	<c:forEach items="${lists }" var="list" >
                                <tr>
                                  <input type="hidden" value="${list.chatIdx }"class ='room_num'/>
                                  <th scope="row">${list.chatIdx-19999}</th>
                                  <td>${list.chatName }</td>
                                  <td>${list.chatLeader }</td>
                                  <td>${list.chatHead }</td>
                                 <c:set var="secret" value=""></c:set>
                                  <c:if test="${list.chatSecret ==1 }" >
                                  	<c:set var="secret" value="checked"></c:set>
                                  	
                                  </c:if>
                                  <td><input type="checkbox" ${secret} readonly="readonly" onclick="return false;"/></td>
                                  <td>                                                              
                                  	<input type="hidden" value="${list.chatPassword}" class='password'/>                                 
                                  	<input type="button" class="w3-button w3-black w3-round" value="방 입장" class='room'>
                                  </td>
                                </tr>
                                </c:forEach>
                                
                                <tr>
    								<td align="center" colspan="5"><c:out value="${pageNavigator}" escapeXml="false" /></td>
   								</tr>
   								
                                <tr>
                                    <td colspan="2" align="right">
                                        <select name="searchType">
                                        	<option value="ALL">
                                        		전체
                                        	</option>
                                            <option value="NAME">
                                                제목
                                            </option>
                                            <option value="LEADER">
                                                이름
                                            </option>
                                        </select>
                                        <input type="text" name="searchText"/>
                                    </td>
                                    <td>
                                        <input type="submit" class="w3-button w3-black w3-round" value="방검색"/>
                                    </td>
                                    <td>
                                       <input type="button" class="w3-button w3-black w3-round" value="방만들기" onclick="location.href='chatCreateui.do'">
                                    </td>
                                </tr>
                              </tbody>
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