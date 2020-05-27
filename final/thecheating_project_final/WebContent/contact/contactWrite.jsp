<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String contact_author = (String) session.getAttribute("mem_nickname");	
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>문의사항 글쓰기 화면</title>
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
					<form action="contactWrite.do" >
						<table align="center">
						<tbody>
						<br/><br/>
							<tr>
								<td></td>
								<td colspan="2" style="font-size:30px;" >
									글쓰기 <br />
								</td>
							</tr>
							<tr>
								<td align="right" >
									TITLE &nbsp;
								</td>
								<td>
									<input type="text" name="contact_title"  value="문의드려요 :) " required="required" readonly="readonly" >
								</td>
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
									<textarea style="resize: none;" name="contact_content" rows="5" cols="30" required="required" ></textarea>
								</td>
							</tr>
							<tr>
								<td align="center" >
								<!-- 댓글여부 re_lev: 0(일반글), 1(댓글) -->
									<input type="hidden" name="re_lev" value="0"> 
									<input type="submit"  class="w3-button w3-black w3-round" style="font-size:1em; font-color: white;" value="저장" />
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