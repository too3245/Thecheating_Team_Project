<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.up.dto.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>업데이트 게시판 목록</title>
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
									<td style="border-bottom: hidden; border-top: hidden;" colspan="10" align="center"><h3>업데이트 게시판 목록</h3> &nbsp;&nbsp;&nbsp;
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
				ArrayList list = (ArrayList)request.getAttribute("lists");
				
				if(list != null) { // 리스트에 데이터가 존재한다면
					Iterator iter = list.iterator();
				
					while(iter.hasNext() == true) { // 꺼내올 데이터가 있다면 계속 꺼내오기
						
						UpDTO data = (UpDTO)iter.next();
						int up_idx = data.getUp_idx();
						String up_title = data.getUp_title();
						String up_author = data.getUp_author();
						String up_content = data.getUp_content();
						String up_writeday = data.getUp_writeday();
						int up_hit = data.getUp_hit();
						
						System.out.print("up/up_list");
			%>
			
			<tr align="center">
				<td colspan="1" ><%= up_idx - 320000%></td>
				<td colspan="3" ><a href="upRetrieve.do?up_idx=<%= up_idx %> "><%= up_title %></a></td>
				<td colspan="2" ><%= up_author %></td>
				<td colspan="3" ><%= up_writeday.substring(0,10) %></td>
				<td colspan="1" ><%= up_hit %></td>
				
			<%
					} // while END
					
				} // if END
			%>
			
							<!--  검색 기능 -->
							<tr>
								<td colspan="8" align="center">
									<form action="upSearch.do">
										<select name="searchType" size="1">
											<option value="ALL">전체검색</option>
											<option value="up_title">제목</option>
											<option value="up_author">작성자</option>
										</select>
											<input type="text" name="searchText" />
										</td>
										<td colspan="2" align="left">
											<input type="submit" class="w3-button w3-black w3-round" style="font-size:1em;" value="검색" />
											
										<%
											String up_id = (String) session.getAttribute("mem_idx");
											if(up_id == null) {
										%>
										<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='upRequestLoginui.do'" value="글쓰기" />
										<%
											} else {
										%>
										<input type="button" class="w3-button w3-black w3-round" style="font-size:1em;" onclick="location.href='upWriteui.do'" value="글쓰기" />
										<%		
											}
										%>
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