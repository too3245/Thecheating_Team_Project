<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int re_contact_idx = Integer.parseInt(request.getParameter("contact_idx"));
	String re_contact_title = request.getParameter("contact_title");
	String re_contact_author = (String) session.getAttribute("mem_nickname");
	String re_contact_content = request.getParameter("contact_content");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의사항 답글쓰기 화면</title>
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
							<form action="contactReplyWrite.do"  >
								<table align="center">
								<tbody>
								<br/><br/>
									<tr>
										<td></td>
										<td colspan="2" style="font-size:30px;" >
											답글쓰기 <br />
										</td>
									</tr>
									<tr>
										<td align="right" >
											TITLE &nbsp;
										</td>
										<td>
										<input type="text" name="contact_title"  value="답변드려요 :)"  required="required"  readonly="readonly"  /></td>
									</tr>
									<tr>
										<td align="right" >
											WRITER &nbsp;
										</td>
										<td>									
											<input type="text"  name="contact_author" rows="5" cols="30" required="required" >
										</td>
									</tr>
									<tr>
										<td align="right" >
											CONTENT &nbsp;
										</td>
										<td>
											<textarea style="resize: none;" name="contact_content" rows="5" cols="60" required="required" ></textarea>
										</td>
									</tr>
									<tr>
										<td colspan="3">
						        			<input type="hidden" name="re_ref" value="<%= re_contact_idx %>">
						        			<input type="hidden" name="contact_idx" value="<%= re_contact_idx %>">
						             		<input type="hidden" name="re_seq" value="1"> 
						             		<!-- 댓글여부 re_lev: 0(일반글), 1(댓글) -->
						            		<input type="hidden" name="re_lev" value="1"> 
						            		<input type="submit" class="w3-button w3-black w3-round" value="답글 등록"> &nbsp; &nbsp; 
						            		<input type="button" class="w3-button w3-black w3-round" onclick="location.href='contactList.do'" value="목록보기"></td>            								
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