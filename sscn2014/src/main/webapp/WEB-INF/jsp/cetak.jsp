<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="userLogin" value="${sessionScope.userLogin}" />
<c:choose>
    <c:when test="${sessionScope.userLogin == null}">
        <c:redirect url="ActionServlet?page=login" />
    </c:when>
</c:choose>	
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
	<link rel="stylesheet" href="/sscn2014/resources/css/ie.css"> 
<![endif]-->
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
            <h1 class="logo"><a href="http://sscn.bkn.go.id">SSCN 2014</a></h1>
			<marquee>Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla Firefox 3, Safari, Google Chrome</b> atau diatasnya</marquee>
            <nav>
                <ul class="sf-menu">
                    <li><a href="index.do">BERANDA</a></li>                    
                    <li><a href="contacts.do">KONTAK</a></li>
                   <c:choose>
						<c:when test="${userLogin != null}">
							<c:choose>
								<c:when test="${userLogin.jumlahDaftar == 0}">
									<li><a href="daftar_new.do">DAFTAR</a></li>
								</c:when>
								<c:when test="${userLogin.jumlahDaftar > 2}">
									<li><a href="cetak.do">CETAK</a></li>
								</c:when>								
								<c:otherwise>									
									<li><a href="daftar_new.do">DAFTAR</a></li>
									<li><a href="cetak.do">CETAK</a></li>
								</c:otherwise>
							</c:choose>									
							<li><a href="logout.do">LOGOUT</a></li>
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
                        	<h3>INFORMASI UMUM </h3>
                            <ul class="list-2">
                                <li><a href="peraturan.do"><img src="/sscn2014/resources/images/peraturan.jpg" width="243" height="37" border="0" align="left"></a></img></li>
					<li><a href="alur.do"><img src="/sscn2014/resources/images/alur.jpg" width="243" height="37" border="0" align="left"></a></img></li>								
                                	<li><a href="calendar.do"><img src="/sscn2014/resources/images/jadwal.jpg" width="243" height="37" border="0" align="left"></a></img></li>
                            </ul>
                            
                        </div>
					  <div class="col-9">
                        	<h3>CETAK PENDAFTARAN</h3>
                           <p align="center">
							Anda sudah mendaftarkan untuk mengikuti Seleksi CPNS di posisi yang
							anda lamar. <br> Silahkan melakukan Cetak Nomor Pendaftaran pada
							tombol dibawah ini.	</p>
							<p align="center">
							<c:choose>
							    <c:when test="${idRegistrasi1 != null}">
										<form action="ReportServlet" method="post" target="_blank"
											name="formCetakRegistrasi1" id="formCetakRegistrasi1">
											<div align="center">
											  <input type="hidden" name="idRegistrasi" value="${idRegistrasi1}" /> 
											  <input
												type="hidden" name="formID" value="32063786011851" /> 
											  <input
												type="hidden" name="typeReport" value="rptRegistrasi" /> 
											  <input
												type="submit" value="Cetak Nomor Pendaftaran"
												class="form-submit-button style2" /> 
											  <input type="button" onClick="kembali()"
												name="btnKembali" value="Kembali" class="form-submit-button style2" />
										</form>  
							    </c:when>
							</c:choose>	
	
							<c:choose>
							    <c:when test="${idRegistrasi2 != null}">
									<p align="center">
										<form action="ReportServlet" method="post" target="_blank"
											name="formCetakRegistrasi2" id="formCetakRegistrasi2">
											<div align="center">
											  <input type="hidden" name="idRegistrasi" value="${idRegistrasi2}" /> 
											  <input
												type="hidden" name="formID" value="32063786011852" /> 
											  <input
												type="hidden" name="typeReport" value="rptRegistrasi" /> 
											  <input
												type="submit" value="Cetak Nomor Pendaftaran"
												class="form-submit-button style2" /> 
											  <input type="button" onClick="kembali()"
												name="btnKembali" value="Kembali" class="form-submit-button style2" />
										</form>  
							    </c:when>
							</c:choose>		
							<c:choose>
							    <c:when test="${idRegistrasi3 != null}">
									<p align="center">
										<form action="ReportServlet" method="post" target="_blank"
											name="formCetakRegistrasi3" id="formCetakRegistrasi3">
											<div align="center">
											  <input type="hidden" name="idRegistrasi" value="${idRegistrasi3}" /> 
											  <input
												type="hidden" name="formID" value="32063786011853" /> 
											  <input
												type="hidden" name="typeReport" value="rptRegistrasi" /> 
											  <input
												type="submit" value="Cetak Nomor Pendaftaran"
												class="form-submit-button style2" /> 
											  <input type="button" onClick="kembali()"
												name="btnKembali" value="Kembali" class="form-submit-button style2" />
										</form>  
							    </c:when>
							</c:choose>	
                      </div>
				  </div>
                </article>
        <article class="content-box"></article>
      </div>
      </section>
        <!-- Footer -->
        <footer>
            <div class="copyright"><span class="style2">Hak Cipta  �  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</span></a></div>
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