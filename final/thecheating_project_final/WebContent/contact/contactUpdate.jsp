<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.contact.dto.*" %>

<%
	int contact_idx = Integer.parseInt(request.getParameter("contact_idx"));
	String contact_title = request.getParameter("contact_title");
	String contact_author = (String) session.getAttribute("mem_nickname");
	String contact_content = request.getParameter("contact_content");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의사항 상세보기 - 수정, 삭제</title>
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
						<form action="contactUpdate.do">
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
										<input type="text" name="contact_idx" value="<%= contact_idx - 340000%>" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="contact_author" value="<%= contact_author %>" readonly="readonly" >
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td>
										<input type="text" name="contact_title" value="<%=contact_title %>" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<textarea style="resize: none;" name="contact_content" rows="5" cols="60"><%=contact_content %></textarea>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center" >
										<input type="submit" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" value="수정완료" />
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" onclick="location.href='contactDelete.do?contact_idx=<%= contact_idx %>'">삭제</button>
										<button type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='contactList.do'">목록</button>	
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