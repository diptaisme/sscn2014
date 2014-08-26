<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="userLogin" value="${sessionScope.userLogin}" />

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
    <script src="/sscn2014/resources/js/fullcalendar.min.js"></script>
    <script src="/sscn2014/resources/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script src="/sscn2014/resources/js/calendar-events.js"></script>

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
            <h1 class="logo"><a href="http://sscn.bkn.go.id">SSCN 2014</a></h1>
            <nav>
                <ul class="sf-menu">
                    <li><a href="index.html">BERANDA</a></li>                    
                    <li><a href="contacts.html">KONTAK</a></li>
                    <c:choose>
							<c:when test="${userLogin != null}">
								<c:choose>
									<c:when test="${userLogin.jumlahDaftar == 0 && userLogin.refInstansi.status == '1'}">
										<li><a href="daftar.html">DAFTAR</a></li>
									</c:when>
									<c:when test="${userLogin.jumlahDaftar != 0}">
										<li><a href="cetak.html">CETAK</a></li>
									</c:when>
								</c:choose>									
								<li><a href="logout.html">LOGOUT</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="login.html">LOGIN</a></li>
							</c:otherwise>
						</c:choose>
                </ul>
            </nav>
            <div class="clear"></div>
        </header>
        <!-- Slider -->
        <!-- Content -->
            <div class="container_12">
            	<div class="a2">
                	<div class="wrapper">
                    	<div class="col-7">
                        	<h3 class="hp-1">JADWAL PELAKSANAAN</h3>
                            <h5><span>31</span> june</h5>
                            <p class="p3">
                            	Nam liber tempor cumuod soluta nobis eleifend option congue imperdiet doming id quod mazim placerat.
                            </p>
                            <h5><span>28</span> june</h5>
                            <p class="p3">
                            	Facer possim assum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod.
                            </p>
                            <h5><span>21</span> june</h5>
                            <p class="p3">
                            	Ut wisi enim ad minim veniam, qnostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex.
                            </p>
                            <h5><span>12</span> june</h5>
                            <p class="p3">
                            	At vero eos et accusam et justo duo dolores et ea rebum. Stet clita gubergren no sea takimata sanctus.
                            </p>
                            <h5><span>05</span> june</h5>
                            <p class="p3">
                            	Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt.
                            </p>
                            <a href="#" class="button">More</a>
                        </div>
                        <div class="col-8">
                        	<h3 class="hp-1">KALENDER </h3>
                            <div id="calendar"></div>
                        </div>
                    </div>
                </div>
            </div>
      </section>
        <!-- Footer -->
        <footer>
            <div class="copyright style1">
                Hak Cipta  �  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</a>            </div>
            <ul class="social-list">
            	<li><a href="https://twitter.com/BKN_RI"><img src="/sscn2014/resources/images/soc-icon-1.png" alt=""></a></li>
              <li><a href="https://www.facebook.com/pages/Badan-Kepegawaian-Negara-BKN-Republik-Indonesia/383767665088202"><img src="/sscn2014/resources/images/soc-icon-2.png" alt=""></a></li>
              <li><a href="#"><img src="/sscn2014/resources/images/soc-icon-3.png" alt=""></a></li>
			  <li><a href="http://www.quick-counter.net/" title="HTML hit counter - Quick-counter.net"><img src="http://www.quick-counter.net/aip.php?tp=bb&tz=Asia%2FJakarta" alt="HTML hit counter - Quick-counter.net" border="0" /></a></li>	
            </ul>
        </footer>
    </div>
</div>
</body>
</html>