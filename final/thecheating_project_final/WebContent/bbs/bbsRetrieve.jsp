<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bbs.dto.*" %>

<%
	BBSDTO data = (BBSDTO) request.getAttribute("data");
	int bbs_idx = data.getBbs_idx();
	String bbs_title = data.getBbs_title();
	String bbs_author = (String) session.getAttribute("mem_nickname");
	String bbs_content = data.getBbs_content();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>공지사항 상세보기</title>
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
						<form action="bbsRetrieve.do">
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
										<input type="text" name="bbs_idx" value="<%= bbs_idx - 310000%>" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="bbs_author" value="<%= bbs_author %>" readonly="readonly" >
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td>
										<input type="text" name="bbs_title" value="<%= bbs_title %>" rows="30" cols="30" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<input type="text" name="bbs_content" value="<%= bbs_content %>" rows="30" cols="30" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='bbsUpdateui.do?bbs_idx=<%= bbs_idx %>&bbs_author=<%= bbs_author %>&bbs_title=<%=bbs_title%>&bbs_content=<%=bbs_content%>'" value="수정" /> &nbsp; &nbsp;
										<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='bbsList.do'" value="목록" />
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