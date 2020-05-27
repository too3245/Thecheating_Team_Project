<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*, com.contact.dto.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>문의사항 게시판 목록</title>
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
                  		  <table class="table">
                    		<thead>
								<tr>
									<td style="border-bottom: hidden; border-top: hidden;" colspan="10" align="center">
										<h3>문의사항 게시판 목록</h3> &nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr align="center">
									<th scope="col" colspan="1">번호</th>
									<th scope="col" colspan="3">제목</th>
									<th scope="col" colspan="2">작성자</th>
									<th scope="col" colspan="3">작성일</th>
									<th scope="col" colspan="1">조회수</th>
								</tr>
							</thead>
						<tbody>
			
			<%
				ArrayList list = (ArrayList)request.getAttribute("lists"); // 리스트 정보
				ArrayList replyList = (ArrayList)request.getAttribute("replyList"); // 

				if(list != null) { // 리스트에 데이터가 존재한다면
					Iterator iter = list.iterator();
				
					while(iter.hasNext() == true) { // 꺼내올 데이터가 있다면 계속 꺼내오기
						
						ContactDTO data = (ContactDTO)iter.next();
					
						int contact_idx = data.getContact_idx();
						String contact_title = data.getContact_title();
						String contact_author = data.getContact_author();
						String contact_content = data.getContact_content();
						String contact_writeday = data.getContact_writeday();
						int contact_hit = data.getContact_hit();
						int re_lev = data.getRe_lev();
						int re_seq = data.getRe_seq();

						System.out.print("contact/contact_list");
			%>
			
			<tr align="center">
				<td colspan="1" >Q.<input type="hidden" <%= contact_idx - 340000%>></td>
				<td colspan="3" ><a href="contactRetrieve.do?contact_idx=<%= contact_idx %> "><%= contact_title %></a></td>
				<td colspan="2" ><%= contact_author %></td>
				<td colspan="3" ><%= contact_writeday.substring(0,10) %></td>
				<td colspan="1"><%= contact_hit %></td>				

			</tr>
			
			<%
			if(replyList!=null){
				int re_ref = 0;
				Iterator repIter = replyList.iterator();
				while(repIter.hasNext() == true){
					ContactDTO replyData = (ContactDTO)repIter.next();
					re_ref = replyData.getRe_ref();
					if(re_ref == contact_idx){
						String re_contact_title = replyData.getContact_title();	
						int re_contact_idx = replyData.getContact_idx();
						String re_contact_author = replyData.getContact_author();
						String re_contact_writeday = replyData.getContact_writeday();
						int re_contact_hit = replyData.getContact_hit();
						
						%>
						
						<tr align="center">
							<td colspan="1">ㄴ A. <!--  %= re_contact_idx - 340000%>--></td>
							<td colspan="3" ><a href="contactReplyRetrieve.do?contact_idx=<%= re_contact_idx %> "><%= re_contact_title %></a></td>								
							<td colspan="2" ><%= re_contact_author %></td>
							<td colspan="3" ><%= re_contact_writeday.substring(0,10) %></td>
							<td colspan="1"><%= re_contact_hit %></td>				
			
						</tr>
						<%
						
					}
				}
			}
			
			%>
				
				
			<%
					} // while END
					
				} // if END
			%>
			
							<!--  검색 기능 -->
							<tr>
								<td colspan="8" align="center">
									<form action="contactSearch.do">
										<select name="searchType" size="1">
											<option value="ALL">전체검색</option>
											<option value="contact_title">제목</option>
											<option value="contact_author">작성자</option>
										</select>
											<input type="text" name="searchText" />
										</td>
										<td colspan="2" align="left">
											<input type="submit" class="w3-button w3-black w3-round" style="font-size:1em;" value="검색" />

                                    		<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='contactWriteui.do'" value="글쓰기" />

										</form>
                                	</td>		
                                </tr>
								<tr>
									<td align="center" colspan="10"><c:out value="${pageNavigator}" escapeXml="false" /></td>
								</tr>
                    		</tbody>
                    	</table>
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