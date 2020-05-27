<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.event.dto.*" %>

<%
	int event_idx = Integer.parseInt(request.getParameter("event_idx"));
	String event_title = request.getParameter("event_title");
	String event_author = (String) session.getAttribute("mem_nickname");
	String event_content = request.getParameter("event_content");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이벤트 상세보기 - 수정, 삭제</title>
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
						<form action="eventUpdate.do">
							<table align="center">
							<tbody>
							<br/><br/>
								<tr>
									<td></td>
									<td colspan="2" style="font-size:30px;">
										수정하기 <br />
									</td>
								</tr>
								<tr>
									<td align="right" >
										NO. &nbsp;
									</td>
									<td>
										<input type="text" name="event_idx" value="<%= event_idx - 310000%>" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="event_author" value="<%= event_author %>" readonly="readonly" />
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td >
										<textarea style="resize: none;" rows="5" cols="30"><%=event_title %></textarea>
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<textarea style="resize: none;" name="event_content" rows="5" cols="30"><%=event_content %></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" >
										<input type="submit" class="w3-button w3-black w3-round"  value = "수정완료" style="font-size:1em; font-color: white;">
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" onclick="location.href='eventDelete.do?event_idx=<%= event_idx %>'">삭제</button>
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='eventList.do'">목록</button>	
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