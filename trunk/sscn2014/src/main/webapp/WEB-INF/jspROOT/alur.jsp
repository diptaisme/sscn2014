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
    <link rel="stylesheet" href="/resources/css/style.css">
    <script src="/resources/js/jquery-1.7.1.min.js"></script>
    <script src="/resources/js/superfish.js"></script>
    <script src="/resources/js/jquery.easing.1.3.js"></script>
    <script src="/resources/js/tms-0.4.1.js"></script>
    <script src="/resources/js/slider.js"></script>
<!--[if lt IE 8]>
   <div style=' clear: both; text-align:center; position: relative;'>
     <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
       <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
    </a>
  </div>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/html5.js"></script>
	<link rel="stylesheet" href="/resources/css/ie.css"> 
<![endif]-->
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-44204367-1' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
<style type="text/css">
<!--
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
            	<article class="a2">
                	<div class="wrapper">
                    	<div class="col-10">
                        	<h3>INFORMASI UMUM </h3>
                            <ul class="list-2">
                                <li><a href="peraturan.html"><img src="/resources/images/peraturan.jpg" width="243" height="37" border="0" align="left"></a></img></li>
					<li><a href="alur.html"><img src="/resources/images/alur.jpg" width="243" height="37" border="0" align="left"></a></img></li>								
                                	<li><a href="calendar.html"><img src="/resources/images/jadwal.jpg" width="243" height="37" border="0" align="left"></a></img></li>
                            </ul>
                            
                        </div>
					  <div class="col-9">
                        	<h3>ALUR MEKANISME PENDAFTARAN CPNS 2014 </h3>
                            <p align="justify"><img src="/resources/images/alur1.jpg" width="800" height="599"></p>
				<p align="justify"><img src="/resources/images/alur2.jpg" width="800" height="599"></p>
                      </div>
				  </div>
                </article>
        <article class="content-box"></article>
      </div>
      </section>
        <!-- Footer -->
        <footer>
            <div class="copyright"><span class="style2">Hak Cipta  ©  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</span></a></div>
            <ul class="social-list">
            	<li><a href="https://twitter.com/BKN_RI"><img src="/resources/images/soc-icon-1.png" alt=""></a></li>
              <li><a href="https://www.facebook.com/pages/Badan-Kepegawaian-Negara-BKN-Republik-Indonesia/383767665088202"><img src="/resources/images/soc-icon-2.png" alt=""></a></li>
              <li><a href="#"><img src="/resources/images/soc-icon-3.png" alt=""></a></li>
			  <li><a href="http://www.quick-counter.net/" title="HTML hit counter - Quick-counter.net"><img src="http://www.quick-counter.net/aip.php?tp=bb&tz=Asia%2FJakarta" alt="HTML hit counter - Quick-counter.net" border="0" /></a></li>	
            </ul>
        </footer>
    </div>
</div>
</body>

</html>