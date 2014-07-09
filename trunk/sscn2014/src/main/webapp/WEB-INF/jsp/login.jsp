<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userLogin" value="${sessionScope.userLogin}" />
<c:choose>
	<c:when test="${userLogin != null}">		
		<%
		    String redirectURL = "ActionServlet?page=index";
		    response.sendRedirect(redirectURL);
		%>
	</c:when>
</c:choose>
<html lang="en">
<head>
  	<title>Seleksi CPNS 2014</title>
  	<meta charset="utf-8">
    <meta name="description" content="sscn,bkn">
    <meta name="keywords" content="seleksi,cpns,2014">
    <meta name="author" content="HerieSharkfins">
    <link rel="stylesheet" href="/sscn2014/resources/css/style.css">
    <script src="/sscn2014/resources/js/jquery-1.7.1.min.js"></script>
    <script src="/sscn2014/resources/js/superfish.js"></script>
    <script src="/sscn2014/resources/js/jquery.easing.1.3.js"></script>
    <script src="/sscn2014/resources/js/tms-0.4.1.js"></script>
    <script src="/sscn2014/resources/js/slider.js"></script>
	<script type="text/javascript">
		$(document).ready(	
			function() {
				$(this).bind('contextmenu',function(e){
							e.preventDefault();
						});
				});
	</script>		
<!--[if lt IE 8]>
   <div style=' clear: both; text-align:center; position: relative;'>
     <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
       <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
    </a>
  </div>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
	<link rel="stylesheet" href="css/ie.css"> 
<![endif]-->
<style type="text/css">
<!--
.style1 {color: #CC0000}
.style2 {color: #FFFFFF}
-->
</style>
</head>
<body>
<div class="main-indents">
    <div class="main">
        <!-- Header -->
        <header>
            <h1 class="logo"><a href="index.html">SSCN 2014</a></h1>
			<marquee>Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla Firefox 3, Safari, Google Chrome</b> atau diatasnya</marquee>
            <nav>
                <ul class="sf-menu">                    
                    <li><a href="ActionServlet?page=index">BERANDA</a></li>                    
                    <li><a href="ActionServlet?page=contacts">KONTAK</a></li>
		      <li class="current"><a href="ActionServlet?page=login">LOGIN</a></li>					
                </ul>
            </nav>
            <div class="clear"></div>
        </header>
        <!-- Slider -->
        <!-- Content -->
      <div class="container_12">
            	<article class="a2">
                	<div class="wrapper">                    	
					  <div class="col-9">
					  	<form action="processLogin.do" method="post">
								<p align="center">Username: <input type="text" name="username" id="username"></p>
								<p align="center">Password: <input type="password" name="password" id="password"></p>
								<p align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" value="reset" /> <input type="submit" value="submit" /></p>        
        				</form>
                      </div>
				  </div>
                </article>
          <article class="content-box">
            <ul class="list-1"><li><div align="justify" class="style1">Badan Kepegawaian Negara (BKN) tidak bekerja   sama dengan pihak Bimbingan Belajar/Bimbingan Tes/Lembaga sejenis   manapun yang menjanjikan kemudahan untuk dapat diterima sebagai CPNS.   Jangan percaya pada pihak manapun yang menawarkan dan menjanjikan dapat   meluluskan dengan membayar sejumlah uang atau imbalan</div>
                           	  </li>
            </ul>
		  </article>
        </div>
      </section>
        <!-- Footer -->
        <footer>
            <div class="copyright style2">Hak Cipta  ©  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</a></div>
            <ul class="social-list">
            	<li><a href="https://twitter.com/BKN_RI"><img src="/sscn2014/resources/images/soc-icon-1.png" alt=""></a></li>
              <li><a href="https://www.facebook.com/pages/Badan-Kepegawaian-Negara-BKN-Republik-Indonesia/383767665088202"><img src="/sscn2014/resources/images/soc-icon-2.png" alt=""></a></li>
                <li><a href="#"><img src="/sscn2014/resources/images/soc-icon-3.png" alt=""></a></li>
            </ul>
        </footer>
    </div>
</div>
</body>
</html>