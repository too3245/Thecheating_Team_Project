<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.mySlides {
   display: none;
}
.mySlides2 {
   display: none;
}
</style>
<title>시작화면</title>
</head>
<body>
   <header>
      <jsp:include page="/top/top.jsp"></jsp:include>
   </header>
   <br />
   <div class="container">
      <div class="col-xl-12" style="font-size: 100px;">
	    	<p>&nbsp;&nbsp;더취할래?</p>
	    	<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;더치할래!</p>
      </div>
      
      <div class="login container" style="margin: 0px; padding: 0px;">
         <div class="col-xl-12">
            <div class="w3-content w3-display-container">
               <img class="mySlides" src="./image/main.jpg" style="width: 100%; height: 400px;" onclick="location.href='dutchpayui.do'">
               <img class="mySlides" src="./image/pay.png" style="width: 100%; height: 400px;" onclick="location.href='dutchpayui.do'">
               <img class="mySlides" src="./image/street.jpg" style="width: 100%; height: 400px;" onclick="location.href='dutchpayui.do'">

               <button class="w3-button w3-black w3-display-left"
                  onclick="plusDivs(-1)">&#10094;</button>
               <button class="w3-button w3-black w3-display-right"
                  onclick="plusDivs(1)">&#10095;</button>
            </div>

            <script>
               var slideIndex = 1;
               showDivs(slideIndex);

               function plusDivs(n) {
                  showDivs(slideIndex += n);
               }

               function showDivs(n) {
                  var i;
                  var x = document.getElementsByClassName("mySlides");
                  if (n > x.length) {
                     slideIndex = 1
                  }
                  if (n < 1) {
                     slideIndex = x.length
                  }
                  for (i = 0; i < x.length; i++) {
                     x[i].style.display = "none";
                  }
                  x[slideIndex - 1].style.display = "block";
               }
            </script>
         </div>
         
<!--          <br /><br /> -->
<!--          <div class="col-xl-12" style="font-size: 50px;"> -->
<!--             <p>아직도 못받고 그러니? 써봐! 더치팅!</p> -->
<!--             <p> -->
<!--          </div> -->
         
         <br /> <br />
         <div class="col-xl-12" style="font-size: 50px;">
            <p style="font: bold;">&nbsp;&nbsp; ABOUT TEAM MEMBERS OF THE CHEATING</p>
         </div>

         <div class="col-xl-12">
            <div class="w3-content w3-section">
               <img class="mySlides2" src="./image/member1.jpg" style="width: 100%; height: 600px;" onclick="location.href='memberui.do'" >
               <img class="mySlides2" src="./image/member2.jpg" style="width: 100%; height: 600px;" onclick="location.href='memberui.do'" >
               <img class="mySlides2" src="./image/member3.jpg" style="width: 100%; height: 600px;" onclick="location.href='memberui.do'" >
               <img class="mySlides2" src="./image/member4.jpg" style="width: 100%; height: 600px;" onclick="location.href='memberui.do'" >
            </div>


            <script>
               var myIndex = 0;
               carousel();

               function carousel() {
                  var i;
                  var x = document.getElementsByClassName("mySlides2");
                  for (i = 0; i < x.length; i++) {
                     x[i].style.display = "none";
                  }
                  myIndex++;
                  if (myIndex > x.length) {
                     myIndex = 1
                  }
                  x[myIndex - 1].style.display = "block";
                  setTimeout(carousel, 2000); // Change image every 2 seconds
               }
            </script>

         </div>

      </div>
   </div>
   <br/><br/>
   <footer>
      <jsp:include page="/bottom/bottom.jsp"></jsp:include>
   </footer>
</body>
</html>