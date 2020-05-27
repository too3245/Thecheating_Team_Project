<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.event.dto.*, com.event.dao.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>이벤트 게시판 목록</title>
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
                    		<thead>
								<tr>
									<td style="border-bottom: hidden; border-top: hidden;" colspan="10" align="center"><h3>이벤트 게시판 목록</h3> &nbsp;&nbsp;&nbsp;
								</tr>
								<tr  align="center">
									<th scope="col" colspan="1">번호</th>
									<th scope="col" colspan="3">제목</th>
									<th scope="col" colspan="2">작성자</th>
									<th scope="col" colspan="3">작성일</th>
									<th scope="col" colspan="1">조회수</th>
								</tr>
							</thead>
						<tbody>
			
			<%
				ArrayList list = (ArrayList)request.getAttribute("lists");
				
				if(list != null) { // 리스트에 데이터가 존재한다면
					Iterator iter = list.iterator();
				
					while(iter.hasNext() == true) { // 꺼내올 데이터가 있다면 계속 꺼내오기
						
						EventDTO data = (EventDTO)iter.next();
						int event_idx = data.getEvent_idx();
						String event_author = data.getEvent_author();
						String event_title = data.getEvent_title();
						String event_content = data.getEvent_content();
						String event_writeday = data.getEvent_writeday();
						int event_hit = data.getEvent_hit();
						int event_pageTotal = data.getEvent_pageTotal();
						
						System.out.print("event/event_list");
			%>
			
			<tr align="center">
				<td colspan="1" ><%= event_idx - 330000%></td>
				<td colspan="3" ><a href="eventRetrieve.do?event_idx=<%= event_idx %> "><%= event_title %></a></td>
				<td colspan="2" ><%= event_author %></td>
				<td colspan="3" ><%= event_writeday.substring(0,10) %></td>
				<td colspan="1" ><%= event_hit %></td>
				
			<%
					} // while END
					
				} // if END
			%>
			
							<!--  검색 기능 -->
							<tr>
								<td colspan="8" align="center">
									<form action="eventSearch.do">
										<select name="searchType" size="1">
											<option value="ALL">전체검색</option>
											<option value="event_title">제목</option>
											<option value="event_author">작성자</option>
										</select>
											<input type="text" name="searchText" />
										</td>
										<td colspan="2" align="left">
											<input type="submit" class="w3-button w3-black w3-round" style="font-size:1em;" value="검색" />
										<%
											String event_id = (String) session.getAttribute("mem_idx");
											if(event_id == null) {
										%>
											<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='eventRequestLoginui.do'" value="글쓰기" />
										<%
											} else {
										%>
                                    		<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='eventWriteui.do'" value="글쓰기" />
										<%		
											}
										%>
										</form>
                                	</td>								
								</tr>
								<tr>
									<td align="center" colspan="10"><c:out value="${pageNavigator}" escapeXml="false" /></td>
								</tr>
                    		</tbody>
                    	</table>
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