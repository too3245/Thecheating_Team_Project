<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int up_idx = Integer.parseInt(request.getParameter("up_idx"));
	String up_title = request.getParameter("up_title");
	String up_author = (String) session.getAttribute("mem_nickname");
	String up_content = request.getParameter("up_content");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>업데이트 상세보기 - 수정, 삭제</title>
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
						<form action="upUpdate.do">
							<table class="table">
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
										<input type="text" name="up_idx" value="<%= up_idx - 320000%>" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="up_author" value="<%= up_author %>" readonly="readonly" />
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td>
										<textarea style="resize: none;" name="up_title" rows="5" cols="30"><%=up_title %></textarea>
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<textarea style="resize: none;" name="up_content" rows="5" cols="60"><%=up_content %></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" >
										<input type="submit" class="w3-button w3-black w3-round"  value = "수정완료" style="font-size:1em; font-color: white;">
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" onclick="location.href='upDelete.do?up_idx=<%= up_idx %>'">삭제</button>
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='upList.do'">목록</button>		
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