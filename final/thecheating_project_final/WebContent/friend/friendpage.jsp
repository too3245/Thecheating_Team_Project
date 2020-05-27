<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>시작화면</title>

</head>
<body>
<header>
	<jsp:include page="/top/top.jsp"></jsp:include>
</header>
	
	<div class = "login container" style="height: 800px;">
		<div class="col-xl-12">
			<table class="class" align="center">
				<tr>
					<td colspan="2" align="center" style="font-size:30px;">
						친구목록 
					</td>
				</tr>	
				<tr>	
					<c:forEach var = "friendlist" items = "${friendlist }">
					<tr>
						<td align="right">	
							이메일 &nbsp;
						</td>
						<td>
							<input type="text" value="${friendlist.mem_email }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td align="right">
							닉네임 &nbsp;
						</td>
						<td> 
							<input type="text" value="${friendlist.mem_nickname }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td align="right">	
							이름 &nbsp;
						</td>
						<td>
							<input type="text" value="${friendlist.mem_username }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type = "button" class="w3-button w3-black w3-round" style="font-size:1em;" value = "친구삭제" onclick = "location.href='frienddelete.do?friendlist_email=${friendlist.mem_email}'" />	<br />
						</td>
					</tr>
						</c:forEach>
				</tr>
			</table>
			<br /><br />
			
			<!-- 친구신청하기 테이블 -->
			<table class="class" align="center">
				<tr>
					<td colspan="2" align="center" style="font-size:30px;">
						친구신청하기
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<form action="friendlist.do">
							<select name="searchName">
								<option value="mem_email">이메일</option>
								<option value="mem_nickname">닉네임</option>
							</select> <input type="text" name="searchValue" />
 							<input type="submit" class="w3-button w3-black w3-round" style="font-size:1em;" value="검색" /> 
 						</form>
					</td>
				</tr>
				<tr>
					<c:forEach var="friend" items="${list }">
				<tr>
					<td align="right">
							이메일 &nbsp;
					</td>
					<td >
						<input type="text" value="${friend.mem_email }"  size="30" style="height:30px;" readonly />						 
					</td>
				</tr>
				<tr>
					<td align="right">
						닉네임 &nbsp;
					</td>
					<td>
						<input type="text" value="${friend.mem_nickname }"  size="30" style="height:30px;" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						이름 &nbsp;
					</td>
					<td>
						<input type="text" value="${friend.mem_username }"  size="30" style="height:30px;" readonly />
					</td>
				</tr>
				<tr>
					<td colspan="2" align = "center">
						<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;"  value="친구신청" onclick="location.href='friendrequest.do?mem_email_friend=${friend.mem_email}'" />
					</td>
				</tr>
					</c:forEach>
			</table>
			<br /><br />
			
			<!-- 친구신청온 테이블 -->
			<table class="class" align="center">
					<tr>
						<td colspan="2" align="center" style="font-size:30px;"> 
							친구신청온 목록
						</td>
							<c:forEach var = "requestList" items = "${requestlist }">
					<tr>
						<td align="right" >
							이메일  &nbsp;
						</td>
						<td>
							<input type="text" value="${requestList.mem_email }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td align="right" >
							닉네임  &nbsp;
						</td>
						<td>
							<input type="text" value="${requestList.mem_nickname }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td align="right" >
							이름  &nbsp;
						</td>
						<td>
							<input type="text" value="${requestList.mem_username }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td align="right" >
							받은시간  &nbsp;
						</td>
						<td>
							<input type="text" value="${requestList.mem_regdate }"  size="30" style="height:30px;" readonly />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type = "button" value = "수락" class="w3-button w3-black w3-round" style="font-size:1em;" onclick = "location.href='friendyes.do?friend_email=${requestList.mem_email }'" />&nbsp; | &nbsp;
							<input type = "button" value = "거절" class="w3-button w3-black w3-round" style="font-size:1em;" onclick = "location.href='friendno.do?friend_email=${requestList.mem_email }'"/>
						</td>
					</tr>
							</c:forEach>
					</tr>
				</table>
				<br /><br />
				
				<!-- 친구신청한 목록 테이블 -->
			<table class="class" align="center">
				<tr>
					<td colspan="2" align="center" style="font-size:30px;" >
						친구신청한 목록
					</td>
					<c:forEach var = "responseList" items = "${responselist }">
				<tr>
					<td align="right">
						이메일  &nbsp;
					</td>
					<td>
						<input type="text" value="${responseList.mem_email }"  size="30" style="height:30px;" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						닉네임  &nbsp;
					</td>
					<td>
						<input type="text" value="${responseList.mem_nickname }"  size="30" style="height:30px;" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						이름  &nbsp;
					</td>
					<td>
						<input type="text" value="${responseList.mem_username }"  size="30" style="height:30px;" readonly />
					</td>
				</tr>
				<tr>
					<td align="right">
						신청시간 &nbsp;
					</td>
					<td>
						<input type="text" value="${responseList.mem_regdate }"  size="30" style="height:30px;" readonly />
						<br /> ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
					</td>
				</tr>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
<footer>
	<jsp:include page="/bottom/bottom.jsp"></jsp:include>
</footer>
</body>
</html>