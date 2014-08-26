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
                    <li class="current"><a href="index.html">BERANDA</a></li>                    
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
                        	<h3>MENU UTAMA  </h3>
                            <ul class="list-2">
                            	<li><a href="informasi_umum.html"><img src="/sscn2014/resources/images/info.jpg" width="243" height="37" border="0" align="left"></a></img></li>
					<li><a href="pengumuman_instansi_home.html"><img src="/sscn2014/resources/images/pengumuman.jpg" width="243" height="37" border="0" align="left"></a></img></li>												
                                	<li><a href="#"><img src="/sscn2014/resources/images/petunjuk.jpg" width="243" height="37" border="0" align="left"></a></img></li>
                            </ul>
                            
                        </div>
					  <div class="col-9">
						  <h3>Petunjuk Pengisian Formulir Pendaftaran</h3>
	                        <table width="650" border="0">
                              <tr>
                                <th colspan="3" scope="col"><div align="left"><strong>Informasi Data Pribadi</strong></div></th>
                              </tr>
                              <tr>
                                <td width="156">No. Induk Kependudukan</td>
                                <td width="20"><div align="center">:</div></td>
                                <td width="460"> Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td>Nama</td>
                                <td><div align="center">:</div></td>
                                <td>Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td>Jenis Kelamin</td>
                                <td><div align="center">:</div></td>
                                <td> Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td>Tempat Lahir </td>
                                <td><div align="center">:</div></td>
                                <td> Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td>Tanggal Lahir</td>
                                <td><div align="center">:</div></td>
                                <td>Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td>Alamat</td>
                                <td><div align="center">:</div></td>
                                <td> Isikan alamat korespondensi surat menyurat, dan dilengkapi dengan kode pos yang jelas.</td>
                              </tr>
                              <tr>
                                <td>Nomor Telepon</td>
                                <td><div align="center">:</div></td>
                                <td>No. telpon rumah atau mobile phone (handphone) yang bisa dihubungi </td>
                              </tr>
                              <tr>
                                <td>Email</td>
                                <td><div align="center">:</div></td>
                                <td>Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td>Asal Institusi Pendidikan</td>
                                <td><div align="center">:</div></td>
                                <td> Isikan nama institusi pendidikan dengan lengkap, contoh : Universitas Indonesia, Institut Teknologi Sepuluh November dsb.</td>
                              </tr>
                              <tr>
                                <td>Tahun Lulus </td>
                                <td><div align="center">:</div></td>
                                <td>Cukup Jelas </td>
                              </tr>
                              <tr>
                                <td>No Ijazah</td>
                                <td><div align="center">:</div></td>
                                <td>Cukup jelas.</td>
                              </tr>
                              <tr>
                                <td>Akreditasi</td>
                                <td><div align="center">:</div></td>
                                <td>Isikan dengan A atau B sesuai dengan Akreditasi Universitas/Perguruan Tinggi atau tanda (-) untuk Universitas/Perguruan Tinggi Negeri</td>
                              </tr>
                              <tr>
                                <td>IPK</td>
                                <td><div align="center">:</div></td>
                                <td> isikan IPK dengan cara menggunakan titik (.) decimal contoh 3.45</td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td colspan="3"><strong>Formasi Jabatan yang dilamar : </strong></td>
                              </tr>
                              <tr>
                                <td height="42">Instansi</td>
                                <td align="center" valign="middle"><div align="center">:</div></td>
                                <td valign="middle">Terisi otomatis sesuai dengan data yang sudah diisi di portal Panselnas (http://panselnas.menpan.go.id)</td>
                              </tr>
                              <tr>
                                <td height="42">Pendidikan </td>
                                <td align="center" valign="middle"><div align="center">:</div></td>
                                <td valign="middle">Pilih sesuai dengan pendidikan Anda </td>
                              </tr>
                              <tr>
                                <td height="42">Pilihan 1 </td>
                                <td align="center" valign="middle"><div align="center">:</div></td>
                                <td valign="middle">Pilih Lokasi Kerja sesuai dengan yang tersedia di instansi<br>
                                Pilih jabatan yang sesuai dengan pendidikan Anda</td>
                              </tr>
                              <tr>
                                <td height="42">Lokasi Test </td>
                                <td align="center" valign="middle"><div align="center">:</div></td>
                                <td valign="middle">Lokasi test Anda untuk mengikuti ujian CAT </td>
                              </tr>
                              
                              <tr>
                                <td height="30" colspan="3">Klik pernyataan pada check box. </td>
                              </tr>
                              <tr>
                                <td height="30" colspan="3">Masukkan kode captcha yang tertera. </td>
                              </tr>
                              
                              <tr>
                                <td height="28" colspan="3">Selanjutnya klik DAFTAR jika sudah yakin dengan isian, kemudian klik tombol CETAK untuk mencetak form registrasi. </td>
                              </tr>
                            </table>
                          <p>&nbsp;</p>
                      </div>
				  </div>
                  <article class="content-box">
	            <ul class="list-1"><li><div align="justify" class="style1">Badan Kepegawaian Negara (BKN) tidak bekerja   sama dengan pihak Bimbingan Belajar/Bimbingan Tes/Lembaga sejenis   manapun yang menjanjikan kemudahan untuk dapat diterima sebagai CPNS.   Jangan percaya pada pihak manapun yang menawarkan dan menjanjikan dapat   meluluskan dengan membayar sejumlah uang atau imbalan</div>
	                           	  </li>
	            </ul>
		    </article>
        </div>      
        <!-- Footer -->
        <footer>
            <div class="copyright style2">Hak Cipta  ©  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</div>
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