<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type = "text/css">
div#wrap {
	​ width: 900px;
	​ margin: 0 auto;
	​ background: #ddd;
	​
}
    header#header {
	​ background: #fdd;
	​
}
    nav#nav {
	​ background: #dfd;​
}
    section#container {
	​ background: #ddf;​
}
    div.content {
	​ height: 250px;
	​ background: #eee;​
}
    aside#aside {
	​ height: 250px;
	​ background: #fef;​
}
    footer#footer {
	​ background: #ffe;​
}
    div#warp, 
    header#header, ​
    nav#nav, ​
    section#container, ​
    div.content, ​
    aside#aside, ​
    footer#footer {
	​ padding: 10px;​
}
    nav#nav ul {
	​ margin: 0;
	​ padding: 0;
	​ list-style: none;​
}
   nav#nav ul li {
	​ background: #eee;
	​ padding: 10px;
	​ display: inline-block;​
}

​ /* ---------- */ 
    div.content {
	​ width: 600px;
	​ float: left;​
}
    aside#aside {
	​ width: 220px;
	​ float: right;​
}
    section#container::after {
	​ content: "";
	​ display: block;
	​ clear: both;​
}
</style>

</head>
<body>
	<div id="wrap">
		​
		<header id="header">
			​
			<h1>더치팅</h1>
			​​

		</header>
		<nav id="mav">
			<ul>
				<li>소개 &nbsp;|&nbsp;</li>
				<li>더치팅 시작하기 &nbsp;|&nbsp;</li>
				<li>고객센터 &nbsp;|&nbsp;</li>
				<li>공지사항 &nbsp;</li>
			</ul>

		</nav>
		​
		<section id="container">
			​
			<div class="content">
				​
				<article>​ # 본문​</article>
				​
			</div>
			​
			<aside id="aside">​ # 사이드바​</aside>
			​
		</section>
		​
		<footer id="footer">​ # 푸터​ </footer>
		​
	</div>
</body>
</html>