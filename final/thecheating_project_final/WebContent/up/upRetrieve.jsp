<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.up.dto.*" %>

<%
	UpDTO data = (UpDTO) request.getAttribute("data");
	int up_idx = data.getUp_idx();
	String up_title = data.getUp_title();
	String up_author = (String) session.getAttribute("mem_nickname");
	String up_content = data.getUp_content();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>업데이트 상세보기</title>
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
						<form action="upRetrieve.do">
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
										<input type="text" name="up_idx" value="<%= up_idx - 320000%>" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="up_author" value="<%= up_author %>" readonly="readonly" >
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td>
										<input type="text" name="up_title" value="<%= up_title %>" rows="5" cols="30" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<input type="text" name="up_content" value="<%= up_content %>" rows="5" cols="60" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
											<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='upUpdateui.do?up_idx=<%= up_idx %>&up_author=<%= up_author %>&up_title=<%=up_title%>&up_content=<%=up_content%>'"  value="수정" />  &nbsp; &nbsp;
											<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='upList.do'" value="목록" />
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
