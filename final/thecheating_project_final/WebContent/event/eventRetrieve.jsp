<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.event.dto.*" %>

<%
	EventDTO data = (EventDTO) request.getAttribute("data");
	int event_idx = data.getEvent_idx();
	String event_title = data.getEvent_title();
	String event_author = (String) session.getAttribute("mem_nickname");
	String event_content = data.getEvent_content();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>이벤트 상세보기</title>
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
						<form action="eventRetrieve.do">
							<table align="center">
							<tbody>
							<br/><br/>
								<tr>
									<td></td>
									<td colspan="2" style="font-size:30px;" >
										상세보기 <br />
									</td>
								</tr>
								<tr>
									<td align="right" >
										NO. &nbsp;
									</td>
									<td>
										<input type="text" name="event_idx" value="<%= event_idx - 330000%>" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="event_author" value="<%= event_author %>" readonly="readonly" >
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td>
										<input type="text" name="event_title" value="<%= event_title %>" rows="5" cols="30" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<input type="text" name="event_content" value="<%= event_content %>" rows="5" cols="60" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
											<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='eventUpdateui.do?event_idx=<%= event_idx %>&event_author=<%= event_author %>&event_title=<%=event_title%>&event_content=<%=event_content%>'" value="수정" /> &nbsp; &nbsp;
											<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='eventList.do'" value="목록" />
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