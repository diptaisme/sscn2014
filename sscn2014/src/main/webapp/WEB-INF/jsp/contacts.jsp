<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="isLogin" value="${sessionScope.isLogin}" />

<!DOCTYPE html>
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
.style1 {color: #FFFFFF}
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
                    <li class="current"><a href="ActionServlet?page=contacts">KONTAK</a></li>
                    <c:choose>
						<c:when test="${isLogin == true}">
							<li><a href="ActionServlet?page=daftar">DAFTAR</a></li>
							<li><a href="LoginServlet?page=logout">LOGOUT</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="ActionServlet?page=login">LOGIN</a></li>
						</c:otherwise>
					</c:choose>
                </ul>
            </nav>
            <div class="clear"></div>
        </header>
        <!-- Slider -->
        <!-- Content -->
<section id="content"><div class="ic">June2014!</div>
            <div class="container_12">
            	<article class="a2">
                	<div class="wrapper">
                    	<div class="col-11">
                        	<h3>&nbsp;</h3>
                            <div class="map_container">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d1983.017315567877!2d106.86894686698115!3d-6.25916868703314!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sid!2s!4v1399020684035" width="600" height="450" frameborder="0" style="border:0"></iframe>
                            </div>
                            <div class="adress">
                            	<div class="dt">
                                	<p>BADAN KEPEGAWAIAN NEGARA</p>
                                	<p>JL. letjen sutoyo no. 12 - jakarta timur </p>
                            	</div>
                                <div class="extra-wrap">
                                	<div class="dd">E-mail: <a href="#">adminsscn@bkn.go.id</a></div>
                                    <div class="dd">twitter:  <a href="#">@skypename</a></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-12">
                        	<h3>hubungi kami </h3>
                            <form id="contact-form" method="post">
                              <fieldset>
                                <label class="name">
                                    <input type="text" value="Your Name" onFocus="if(this.value=='Your Name'){this.value=''}" onBlur="if(this.value==''){this.value='Your Name'}">
                                </label>
                                <label class="phone">
                                    <input type="text" value="Telephone" onFocus="if(this.value=='Telephone'){this.value=''}" onBlur="if(this.value==''){this.value='Telephone'}">
                                </label>
                                <label class="email">
                                  <input type="email" value="Email" onFocus="if(this.value=='Email'){this.value=''}" onBlur="if(this.value==''){this.value='Email'}">
                                </label>
                                <label class="message">
                                  <textarea onFocus="if(this.value=='Message'){this.value=''}" onBlur="if(this.value==''){this.value='Message'}">Message</textarea>
                                </label>
                                <div class="btns">
                                    <a class="button" onClick="document.getElementById('contact-form').reset()">Clear</a>
                                    <a class="button" onClick="document.getElementById('contact-form').submit()">Send</a> 
                                </div>
                              </fieldset>
                            </form>
                        </div>
                    </div>
                </article>
            </div>
      </section>
        <!-- Footer -->
        <footer>
            <div class="copyright"><span class="style1">Hak Cipta  �  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</span></a></div>
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