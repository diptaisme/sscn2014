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
    <link rel="stylesheet" href="css/style.css">
    
    <script src="js/jquery-1.7.1.min.js"></script>
    <script src="js/superfish.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/tms-0.4.1.js"></script>
    <script src="js/slider.js"></script>    
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
                    <li class="current"><a href="ActionServlet?page=index">BERANDA</a></li>                    
                    <li><a href="ActionServlet?page=contacts">KONTAK</a></li>                    
                    <c:choose>
						<c:when test="${isLogin != null}">
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
      <div class="container_12">
            	<article class="a2">
                	<div class="wrapper">
                    	<div class="col-10">
                        	<h3>MENU UTAMA  </h3>
                            <ul class="list-2">
                            	<li><a href="ActionServlet?page=informasi_umum">INFORMASI UMUM</a></li>
								<li><a href="#">PENGUMUMAN INSTANSI</a></li>												
                                <li><a href="#">PETUNJUK PENDAFTARAN</a></li>
                            </ul>
                            
                        </div>
					  <div class="col-9">
						  <h3><span class="welcome">seleksi CPNS 2014 </span></h3>
	                        <h3>TRANSPARAN, AKUNTABEL DAN OBJEKTIF</h3>
	                      <p>Dalam rangka mewujudkan Pegawai Negeri Sipil yang profesional, berkualitas, dan bertanggung jawab, diperlukan Pegawai Negeri Sipil yang kompeten melalui sistem pengadaan yang transparan dan akuntabel serta bebas dari korupsi, kolusi dan nepotisme. Untuk mewujudkan hal tersebut maka pemerintah akan melaksanakan pendaftaran CPNS secara serentak dan terintegrasi melalui sistem pendaftaran (registration) online. Sistem pendaftaran CPNS online ini diperuntukkan bagi pelamar CPNS yang akan mengisi formasi umum di instansi pusat maupun daerah.                        </p>
                      </div>
				  </div>
                </article>
	          <article class="content-box">
	            <ul class="list-1"><li><div align="justify" class="style1">Badan Kepegawaian Negara (BKN) tidak bekerja   sama dengan pihak Bimbingan Belajar/Bimbingan Tes/Lembaga sejenis   manapun yang menjanjikan kemudahan untuk dapat diterima sebagai CPNS.   Jangan percaya pada pihak manapun yang menawarkan dan menjanjikan dapat   meluluskan dengan membayar sejumlah uang atau imbalan</div>
	                           	  </li>
	            </ul>
			  </article>
        </div>      
        <!-- Footer -->
        <footer>
            <div class="copyright style2">Hak Cipta  Â©  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</div>
            <ul class="social-list">
            	<li><a href="#"><img src="images/soc-icon-1.png" alt=""></a></li>
                <li><a href="#"><img src="images/soc-icon-2.png" alt=""></a></li>
                <li><a href="#"><img src="images/soc-icon-3.png" alt=""></a></li>
            </ul>
        </footer>
    </div>
</div>
</body>
</html>