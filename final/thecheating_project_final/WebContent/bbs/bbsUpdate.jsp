<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.bbs.dto.*" %>

<%
	int bbs_idx = Integer.parseInt(request.getParameter("bbs_idx"));
	String bbs_title = request.getParameter("bbs_title");
	String bbs_author = (String) session.getAttribute("mem_nickname");
	String bbs_content = request.getParameter("bbs_content");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 상세보기 - 수정, 삭제</title>
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
						<form action="bbsUpdate.do">
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
										<textarea style="resize: none;" name="bbs_title" rows="5" cols="30"><%=bbs_title %></textarea>
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<textarea style="resize: none;" name="bbs_content" rows="5" cols="30"><%=bbs_content %></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" >
										<input type="submit" class="w3-button w3-black w3-round"  value = "수정완료" style="font-size:1em; font-color: white;">
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" onclick="location.href='bbsDelete.do?bbs_idx=<%= bbs_idx %>'">삭제</button>
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='bbsList.do'">목록</button>										
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