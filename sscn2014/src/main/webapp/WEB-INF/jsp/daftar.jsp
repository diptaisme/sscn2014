<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="userLogin" value="${sessionScope.userLogin}" />
<c:choose>
    <c:when test="${sessionScope.userLogin == null}">
        <c:redirect url="page?page=login" />
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
<link rel="stylesheet" href="/sscn2014/resources/css/tabs.css">
<link rel="stylesheet" href="/sscn2014/resources/css/default.css">

<script src="/sscn2014/resources/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="/sscn2014/resources/js/jquery-ui-1.8.16/jquery-1.6.2.js"></script>

<!--jquery autocomplete-->
<link rel="stylesheet"
	href="/sscn2014/resources/js/jquery-ui-1.8.16/themes/base/jquery-ui.css" type="text/css" />
<script type="text/javascript" src="/sscn2014/resources/js/jquery-ui-1.8.16/ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="/sscn2014/resources/js/jquery-ui-1.8.16/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="/sscn2014/resources/js/jquery-ui-1.8.16/ui/jquery.ui.position.js"></script>
<script type="text/javascript"
	src="/sscn2014/resources/js/jquery-ui-1.8.16/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/sscn2014/resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="/sscn2014/resources/js/daftar1.js"></script>
<script type="text/javascript" src="/sscn2014/resources/js/daftar2.js"></script>
<script type="text/javascript" src="/sscn2014/resources/js/daftar3.js"></script>

<script type="text/javascript">
$(document).ready(	
	function() {
		showRecaptcha('recaptcha_div');
		$('#alamat').focus();
		$('#formRegistrasi').validate(
						{
							rules : {
								no_nik : {
									minlength : 14,
									maxlength : 18,
									required : true
								},
								nama : {
									required : true,
									lettersonly : true
								},
								tempat_lahir : {
									required : true,
									lettersonly : true
								},
								datepickerTglLahir : {
									required : true
								},
								alamat : {
									required : true
								},
								kota : {
									required : true,
									lettersonly : true
								},
								propinsi : {
									required : true,
									lettersonly : true
								},
								kode_pos : {
									required : true
								},
								telpon : {
									minlength : 5,
									maxlength : 12,
									required : true
								},
								email : {
								//required : true
								},
								universitas : {
									required : true
								},
								tahun_lulus: {
									required : true
								},
								no_ijazah : {
									required : true
								},
								akreditasi : {
									required : true,
									lettersonly : true
								},
								nilai_ipk : {
									required : true,
									ipkcheck : true
								},
								jenis_kelamin : {
									required : true
								},
								/*instansi1 : {
									required : true
								//lettersonly: true
								},*/
								lokasi_kerja1 : {
									required : true
								},
								jabatan1 : {
									required : true
								},
								pendidikan : {
									required : true
								},
								lokasi_test : {
									required : true
								}
							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							},
							success : function(element) {
								element.text('').addClass('valid').closest(
										'.control-group').removeClass('error')
										.addClass('success');
							},
							submitHandler : function(form) {
								//check jabatan must be unique
								var unique = "false";
								if ($("#jabatan2 option:selected").val() != ""){
									var j1 = $("#jabatan1").val();
									var j2 = $("#jabatan2").val();
									if(j1 == j2){
										unique = "true";
										alert('Jabatan pilihan 1 dan 2 sama');											
									}
									if ($("#jabatan3 option:selected").val() != ""){
										var j3 = $("#jabatan3").val();
										if(j2 == j3){
											unique = "true";
											alert('Jabatan pilihan 2 dan 3 sama');
										}	
									}
								}
								if ($("#jabatan3 option:selected").val() != ""){
									var j1 = $("#jabatan1").val();
									var j3 = $("#jabatan3").val();
									if(j1 == j3){
										unique = "true";
										alert('Jabatan pilihan 1 dan 3 sama');
									}
								}
							
								if(unique == "false"){
									var data = "NIK : " + $("input#no_nik").val();
									$("div#dialog-nik").html(data);
									data = "Nama : " + $("input#nama").val() + " (" + $("#jenis_kelamin option:selected").text() + ")";
									$("div#dialog-nama").html(data);
									data = "TTL : " + $("input#tempat_lahir").val() + " , "+$("input#datepickerTglLahir").val();
									$("div#dialog-ttl").html(data);
									data = "Telpon : " + $("input#telpon").val()+ " , Email : " + $("input#email").val();			
									$("div#dialog-email").html(data);								
									data = "Instansi : ${userLogin.refInstansi.nama} <BR>Lokasi Test : "+ $("#lokasi_test option:selected").text()+" <BR><BR> "+								
									"Pilihan 1 Lokasi : " + $("#lokasi_kerja1 option:selected").text()
									 + " , Pendidikan : " + $("#pendidikan option:selected").text() + " , Jabatan : " + $("#jabatan1 option:selected").text();
									$("div#dialog-formasi1").html(data);								
									
									$("div#dialog-formasi2").html("");
									if ($("#lokasi_kerja2 option:selected").val() != ""){
											if ($("#pendidikan option:selected").val() != ""){
												if ($("#jabatan2 option:selected").val() != ""){
													data = "<BR>Pilihan 2 Lokasi : " + $("#lokasi_kerja2 option:selected").text()
									 + " , Pendidikan : " + $("#pendidikan option:selected").text() + " , Jabatan : " + $("#jabatan2 option:selected").text();
									$("div#dialog-formasi2").html(data);
												}
											}
									}								
									
									$("div#dialog-formasi3").html("");
									if ($("#lokasi_kerja3 option:selected").val() != ""){
											if ($("#pendidikan option:selected").val() != ""){
												if ($("#jabatan3 option:selected").val() != ""){
													data = "<BR>Pilihan 3 Lokasi : " + $("#lokasi_kerja3 option:selected").text()
									 + " , Pendidikan : " + $("#pendidikan option:selected").text() + " , Jabatan : " + $("#jabatan3 option:selected").text();
									$("div#dialog-formasi3").html(data);
												}
											}
									}								
									$('#dialogKonfirmasi').dialog('open');
								}
								$(this).bind('contextmenu',function(e){
									e.preventDefault();
								});
							}
						});
		});
</script>

<script type="text/javascript" src="http://www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
      <!-- Wrapping the Recaptcha create method in a javascript function -->
      <script type="text/javascript">
         function showRecaptcha(element) {
           Recaptcha.create("6LcqAfUSAAAAAJSKj6Z98v7_T4lBwGOHLpNgrPzQ", element, {
             theme: "clean",
             callback: Recaptcha.focus_response_field});
         }
		 <!-- Recaptcha is not focus when form is loaded -->
		 Recaptcha.focus_response_field = function(){return false;};
</script>
	  
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
<style>
/*css validation*/
label.valid {
	width: 24px;
	height: 24px;
	/*background: url(/sscn2014/resources/assets/img/valid.png) center center no-repeat;*/
	display: inline-block;
	text-indent: -9999px;
}

label.error {
	font-weight: bold;
	color: red;
	padding: 2px 8px;
	margin-top: 2px;
}
</style>
</head>
<body>
	<div class="main-indents">
	<div class="main">
			<!-- Header -->
			<header>
				<h1 class="logo">
					<a href="index.html">SSCN 2014</a>
				</h1>
				<marquee>
					Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla
						Firefox 3, Safari, Google Chrome</b> atau diatasnya
				</marquee>
				<nav>
					<ul class="sf-menu">
						<li class="current"><a href="index.html">BERANDA</a></li>
						<li><a href="contacts.html">KONTAK</a></li>
						<c:choose>
							<c:when test="${userLogin != null}">
								<c:choose>
									<c:when test="${userLogin.jumlahDaftar == 0}">
									<li><a href="daftar.html">DAFTAR</a></li>
								</c:when>
								<c:otherwise>									
									<li><a href="cetak.html">CETAK</a></li>
								</c:otherwise>
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
							<h3>MENU UTAMA</h3>
							<ul class="list-2">
							<li><a href="informasi_umum.html"><img src="/sscn2014/resources/images/info.jpg" width="243" height="37" border="0" align="left"></a></img></li>
							<li><a href="pengumuman_instansi.html"><img src="/sscn2014/resources/images/pengumuman.jpg" width="243" height="37" border="0" align="left"></a></img></li>																							
                            <li><a href="#"><img src="/sscn2014/resources/images/petunjuk.jpg" width="243" height="37" border="0" align="left"></a></img></li>
							</ul>
						</div>
			<div class="col-9">
							<form action="registrasi" method="post" name="formRegistrasi" id="formRegistrasi"
							accept-charset="utf-8" class="register">
							<input type="hidden" name="formID" value="32063786011852" /> 
							<input type="hidden" name="typeReport" value="rptRegistrasi" />
            <h1>Form Pendaftaran </h1>
            <fieldset class="row1">
                <legend>Informasi Pelamar 
                </legend>
                <p>
                    <label>NIK
                    </label>
                    <input type="number" class="form-input" maxlength="18" style="width: 150px"
														data-type="input-textbox" id="no_nik" name="no_nik" value="${userLogin.nik}"
														title="NIK / Passport" size="18" readonly/>
                <p>
				<label>Nama
                  </label>
                    <input type="text" class=" form-textbox" readonly value="${userLogin.nama}" style="width: 200px"
														title="Nama Lengkap" data-type="input-textbox" id="nama"
														name="nama" size="35" onKeyUp="caps(this)" />
		  <p>		<label>Jenis Kelamin 
                  </label>
                    <select id="jenis_kelamin" name="jenis_kelamin"
														title="Jenis Kelamin">														
														<c:choose>
														    <c:when test="${userLogin.jenisKelamin == 'P'}">
																<option value="P" selected>Pria</option>
														    </c:when>
															<c:otherwise>
																<option value="W" selected>Wanita</option>
														    </c:otherwise>
														</c:choose>														
				  </select>

                </p>
                <p>
                    <label>Tgl.Lahir 
                    </label>
                    <input type="text" name="datepickerTglLahir" value="<fmt:formatDate pattern='dd-MM-yyyy' value='${userLogin.tglLahir}' />"
														title="Tanggal Lahir" size="10" id="datepickerTglLahir"
														readonly="readonly" /> 
		  <p>
		      <label>Tempat Lahir 
                    </label>
                    <input type="text" class=" form-textbox" readonly value="${userLogin.tempatLahir}"
														title="Tempat Lahir" data-type="input-textbox" style="width: 280px"
														id="tempat_lahir" name="tempat_lahir" size="40"
														onKeyUp="caps(this)" />
                    			
                </p>
				<p>
                    <label>Alamat
                    </label>
                    <input
																	title="Alamat" size="50" style="width: 400px"
																	class="form-textbox form-address-line" type="text"
																	name="alamat" id="alamat" onKeyUp="caps(this)" />
                </p>
				<p>
                    <label>Kota
                    </label>
                    <input
																	title="Kota" class="form-textbox form-address-city" style="width: 390px"
																	type="text" name="kota" id="input_8_city" size="21"
																	onKeyUp="caps(this)" />
                </p>
				<p>
                    <label>Propinsi
                    </label>
                    <input
																	title="Propinsi" style="width: 390px"
																	class="form-textbox form-address-state" type="text"
																	name="propinsi" id="input_8_state" size="22"
																	onKeyUp="caps(this)" />
                </p>
				<p>
                    <label>Kode Pos
                    </label>
                    <input
																	title="Kode Pos"
																	class="form-textbox form-address-postal" type="number"
																	name="kode_pos" id="input_8_postal" size="5"
																	maxlength="5" />
                </p>
				<p>
                    <label>No.Telp
                    </label>
                    <input title="No Telp" class="form-textbox" type="number" name="telpon" id="telpon" maxlength="12" size="12"/>
                </p>
				<p>
                    <label>Email
                    </label>
                    <input type="email" class=" form-textbox validate[Email]" title="Email" id="email" name="email" size="35" 
					style="width: 350px"/>
                </p>
				<p>
                    <label>Asal Institusi Pend. 
                    </label>
                    <input type="text" class=" form-textbox" title="Institusi Pendidikan" data-type="input-textbox" id="universitas" name="universitas"
style="width: 390px" size="50" onKeyUp="caps(this)" />
                </p>
				<p>
                    <label>Tahun Lulus
                    </label>
				<input
																	title="Tahun Lulus"
																	class="form-textbox form-address-postal" type="number"
																	name="tahun_lulus" id="tahun_lulus" size="4"
																	maxlength="4" />
				</p>
				<p>
                    <label>No.Ijazah
                    </label>
                    <input type="text" class=" form-textbox" title="No Ijazah" style="width: 280px"
														data-type="input-textbox" id="no_ijazah" name="no_ijazah"
														size="30" maxlength="30" />
                </p>
				<p>
                    <label>Akreditasi                    </label>
                    <input type="text" class=" form-textbox" title="Akreditas"
														data-type="input-textbox" id="akreditasi" maxlength="1"
														name="akreditasi" size="1" onKeyUp="caps(this)" /> <em>contoh
														A / B / - (untuk Universitas Negeri)</em>
                </p>
				<p>
                    <label>Nilai IPK 
                    </label>
                    <input type="text" class=" form-textbox"
														title="Nilai IPK/Ijazah (SLTA)" maxlength="4"
														data-type="input-textbox" id="nilai_ipk" name="nilai_ipk"
														size="1" /> <em>dalam skala 4, contoh 3.50. untuk
														SLTA skala 10, contoh 7.5</em>
                </p>
            </fieldset>

										<fieldset class="row2">
                <legend>Formasi Jabatan yang dilamar 
                </legend>
				<p>
                    Instansi : <B>${userLogin.refInstansi.nama} </B>
													<input type="hidden" id="instansi" name="instansi" value="${userLogin.refInstansi.kode}"/> 
                </p>
                <p>
                    <label>Pendidikan
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Kualifikasi Pendidikan" id="pendidikan"
														name="pendidikan">
														<option value="">Pilih Pendidikan</option>
				  </select> <img id="imgLoadingPendidikan" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>Pilihan 1  *
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Lokasi Kerja" id="lokasi_kerja1" name="lokasi_kerja1">
														<option value="">Pilih lokasi kerja</option>
				  </select> <img id="imgLoadingLokasi1" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Jabatan" id="jabatan1" name="jabatan1">
														<option value="">Pilih jabatan</option>
				  </select> <img id="imgLoadingJabatan1" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>Pilihan 2  
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Lokasi Kerja" id="lokasi_kerja2" name="lokasi_kerja2">
														<option value="">Pilih lokasi kerja</option>
				  </select> <img id="imgLoadingLokasi2" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Jabatan" id="jabatan2" name="jabatan2">
														<option value="">Pilih jabatan</option>
				  </select> <img id="imgLoadingJabatan2" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>Pilihan 3  
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Lokasi Kerja" id="lokasi_kerja3" name="lokasi_kerja3">
														<option value="">Pilih lokasi kerja</option>
				  </select> <img id="imgLoadingLokasi3" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>
                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Jabatan" id="jabatan3" name="jabatan3">
														<option value="">Pilih jabatan</option>
				  </select> <img id="imgLoadingJabatan3" src="/sscn2014/resources/images/loading.png" />
                </p>
				<p>
                    <label>Lokasi Test                    </label>
                    <select class="form-dropdown" style="width: 500px"
														title="Lokasi Test" id="lokasi_test" name="lokasi_test">
														<option value="">Pilih lokasi test</option>
				  </select> <em>Pilih untuk lokasi test yang diharapkan</em>
                </p>
				</fieldset>
										
												
											


											<table width="570" border="2">
											<tr>
												<th width="70" height="76" valign="middle" scope="col">
												<div align="center">
															<image id="cekbox" data-type="input-textbox"
																onClick="changeImage()" img src="/sscn2014/resources/images/before.gif"
																width="67" height="58" name="cekbox" />
												</div></th>
												<th width="490" valign="middle" scope="col">
												<div align="justify" class="form-line">
												<p>
												<em>Demikian data pribadi saya buat dengan sebenarnya dan bila ternyata isian yang dibuat tidak
												    benar, saya bersedia menanggung akibat hukum yang ditimbulkannya</em>												</p>
												</div></th>
											</tr>
											<tr>
											  <th height="76" colspan="2" align="center" scope="col"><div id="recaptcha_div"></div></th>
											  </tr>
											<tr>
											  <th height="76" colspan="2" align="center" scope="col"><div>
												
													 													
													<button type="submit" disabled="disabled"
														class="button" id="btnKirimPendaftaran">DAFTAR</button>
												
											</div></th>
											  </tr>
											</table> 
												<label></label>												
								
								<input type="hidden" name="csrfPreventionSalt" value="<c:out value='${csrfPreventionSalt}'/>"/>
								<input type="hidden" id="simple_spc" name="simple_spc"
									value="32063786011852" />
								<script type="text/javascript">
									document.getElementById("si" + "mple"
											+ "_spc").value = "32063786011852-32063786011852";
								</script>
							
							</form>
				
		</div>
			</div>	
			</section>
			<!-- Footer -->
			<footer>
				<div class="copyright style2">Hak Cipta © 2014 Badan
					Kepegawaian Negara. Semua Hak Dilindungi.
				</div>
				<ul class="social-list">
					<li><a href="https://twitter.com/BKN_RI"><img src="/sscn2014/resources/images/soc-icon-1.png" alt=""></a></li>
              <li><a href="https://www.facebook.com/pages/Badan-Kepegawaian-Negara-BKN-Republik-Indonesia/383767665088202"><img src="/sscn2014/resources/images/soc-icon-2.png" alt=""></a></li>
              <li><a href="#"><img src="/sscn2014/resources/images/soc-icon-3.png" alt=""></a></li>
			  <li><a href="http://www.quick-counter.net/" title="HTML hit counter - Quick-counter.net"><img src="http://www.quick-counter.net/aip.php?tp=bb&tz=Asia%2FJakarta" alt="HTML hit counter - Quick-counter.net" border="0" /></a></li>	
				</ul>
			</footer>
		
			<div id="dialogKonfirmasi" title="Konfirmasi Form Pendaftaran">
				<p>
			<span class="ui-icon ui-icon-alert"
				style="float:left; margin:0 7px 0 0;">
					</span> 
					Silahkan periksa kembali data anda</p>
					<div id="dialog-nik"></div>
					<div id="dialog-nama"></div>
					<div id="dialog-ttl"></div>
					<div id="dialog-email"></div>
					<div id="dialog-formasi1"></div>
					<div id="dialog-formasi2"></div>
					<div id="dialog-formasi3"></div>					
					<br>
					<p>Klik tombol Submit Form jika data anda sudah benar</p>
					<p>Untuk kembali klik Cancel
				<p>
			</div>
	</div>
	</div>
</body>
</html>