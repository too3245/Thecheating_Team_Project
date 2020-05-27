<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.contact.dto.*" %>

<%
	ContactDTO data = (ContactDTO) request.getAttribute("data");
	int re_contact_idx = data.getContact_idx();
	String re_contact_title = data.getContact_title();
	String re_contact_author = (String) session.getAttribute("mem_nickname");
	String re_contact_content = data.getContact_content();
	int re_lev = data.getRe_lev();
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>문의사항 답글 상세보기</title>
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
						<form action="contactReplyRetrieve.do">
							<table align="center">
							<tbody>
							<br/><br/>
								<tr>
									<td></td>
									<td colspan="2" style="font-size:30px;" >
										답글 상세보기 <br />
									</td>
								</tr>
								<tr>
									<td align="right" >
										NO. &nbsp;
									</td>
									<td>
										<input type="text" name="contact_idx" value="<%= re_contact_idx - 340000%>" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										WRITER &nbsp;
									</td>
									<td>
										<input type="text" name="contact_author" value="<%= re_contact_author %>" readonly="readonly" >
									</td>
								</tr>			
								<tr>
									<td align="right" >
										TITLE &nbsp;
									</td>
									<td>
										<input type="text" name="contact_title" value="<%= re_contact_title %>" rows="5" cols="30" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td align="right" >
										CONTENT &nbsp;
									</td>
									<td>
										<input type="text" name="contact_content" value="<%= re_contact_content %>" rows="5" cols="30" readonly="readonly" >
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="submit" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" onclick="location.href='contactReplyUpdateui.do?re_contact_idx=<%= re_contact_idx %>&re_contact_author=<%= re_contact_author %>&re_contact_title=<%= re_contact_title%>&re_contact_content=<%=re_contact_content%>'" value="수정"> &nbsp; &nbsp;
										<input type="button" class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" onclick="location.href='contactList.do'" value="목록" />
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