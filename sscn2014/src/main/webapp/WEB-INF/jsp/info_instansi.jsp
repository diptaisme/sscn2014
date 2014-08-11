<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

	<script type="text/javascript">
		$(document).ready(	
			function() {
				$(this).bind('contextmenu',function(e){
							e.preventDefault();
						});
				});
	</script>		

<style>
#instansi {
	color: blue;
}

#lokasi_kerja {
	color: blue;
}

#jabatan {
	color: blue;
}

#pendidikan {
	color: blue;
}
</style>

<style>
fieldset {
	width: 570px;
	border: 1px solid black;
	padding: 0 1.4em 1.4em 1.4em;
	margin: 0 0 1.5em 0;
}

legend {
	padding: 0.5em 0.5em;
	border: 1px solid black;
	color: black;
	font-size: 100%;
	text-align: right;
}

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

<script>
	$(document).ready(
			function() {				
				$("#imgLoadingLokasi").hide();
				$("#imgLoadingJabatan").hide();
				$("#imgLoadingPendidikan").hide();
				
				$('#formInfoInstansi').validate(		
					{
							rules : {								
								instansi : {
									required : true
								},
								lokasi_kerja : {
									required : true
								},
								jabatan : {
									required : true
								},
								pendidikan : {
									required : true
								}
							},
							highlight : function(element) {
								$(element).closest('.control-group')
										.removeClass('success').addClass(
												'error');
							},
							success : function(element) {
								element.text('OK!').addClass('valid').closest(
										'.control-group').removeClass('error')
										.addClass('success');
							},
							submitHandler : function(form) {
								form.submit();								
							}
						});
			});
</script>

<script>
	$(function() {
		$('#lokasi_kerja').change(function() {
			//clear pendidikan
			var html = '<option value="">Pilih Pendidikan</option>';
			$('#pendidikan').html(html);
			//clear jabatan
			html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan').html(html);
			$("#imgLoadingPendidikan").hide();
			$("#imgLoadingJabatan").hide();
			getPendidikan();
		});
		$('#pendidikan').change(function() {
			//clear jabatan
			var html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan').html(html);
			$("#imgLoadingJabatan").hide();
			getJabatan();
		});
		$('#instansi').change(function() {
			//clear lokasi
			var html = '<option value="">Pilih Lokasi Kerja</option>';
			$('#lokasi_kerja').html(html);
			//clear pendidikan
			html = '<option value="">Pilih Pendidikan</option>';
			$('#pendidikan').html(html);
			//clear jabatan
			html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan').html(html);

			$("#imgLoadingLokasi").hide();
			$("#imgLoadingPendidikan").hide();
			$("#imgLoadingJabatan").hide();
			getLokasi();
		});
	});
	
	function getLokasi() {
		$("#imgLoadingLokasi").show();
		var param = $('#instansi').val();
		var url = 'cb_lokasi_by_instansi.html?instansi=' + param;
		$.ajax({
			url : url,
			dataType : "jsonp",
			data : {
				featureClass : "P"
			},
			success : function(data) {
				$.map(data.lokasis, function(item) {
					var html = '<option value="">Pilih Lokasi Kerja</option>';
					$.map(data.lokasis, function(item) {
						html += '<option value="' + item.kode + '">'
								+ item.nama + '</option>';
					});
					html += '</option>';
					$('#lokasi_kerja').html(html);
					//clear jabatan
					html = '<option value="">Pilih Jabatan</option>';
					$('#jabatan').html(html);
					//clear pendidikan
					html = '<option value="">Pilih Pendidikan</option>';
					$('#pendidikan').html(html);
					$("#imgLoadingLokasi").hide();
				});
			}
		});
	}
	function getPendidikan() {
		$("#imgLoadingPendidikan").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja').val();
		var url = 'cb_pendidikan_by_instansi_lokasi.html?instansi='
				+ param + '&lokasi=' + param2;

		$.ajax({
			url : url,
			dataType : "jsonp",
			data : {
				featureClass : "P"
			},
			success : function(data) {
				var html = '<option value="">Pilih Pendidikan</option>';
				$.map(data.pendidikans, function(item) {
					html += '<option value="' + item.kode + '">' + item.nama
							+ '</option>';
				})
				html += '</option>';
				$('#pendidikan').html(html);

				html = '<option value="">Pilih Jabatan</option>';
				$('#jabatan').html(html);

				$("#imgLoadingPendidikan").hide();
				$("#imgLoadingJabatan").hide();
			}
		});
	}

	function getJabatan() {
		$("#imgLoadingJabatan").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja').val();
		var param3 = $('#pendidikan').val();

		var url = 'cb_jabatan_by_instansi_lokasi_pendidikan.html?instansi='
				+ param + '&lokasi=' + param2 + '&pendidikan=' + param3;
		$.ajax({
			url : url,
			dataType : "jsonp",
			data : {
				featureClass : "P"
			},
			success : function(data) {
				var html = '<option value="">Pilih Jabatan</option>';
				$.map(data.jabatans, function(item) {
					html += '<option value="' + item.kode + '">' + item.nama
							+ '</option>';
				})
				html += '</option>';
				$('#jabatan').html(html);
				$("#imgLoadingJabatan").hide();
			}
		});
	}
</script>


<style type="text/css">
.form-label {
	width: 150px !important;
}

.form-label-left {
	width: 150px !important;
}

.form-line {
	padding-top: 12px;
	padding-bottom: 12px;
}

.form-label-right {
	width: 150px !important;
}

.form-all {
	width: 690px;
	color: #555 !important;
	font-family: 'Lucida Grande';
	font-size: 14px;
}

.style2 {
	font-weight: bold;
	font-size: 16px;
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
						<li><a href="index.html">BERANDA</a></li>                    
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
								<li><a href="#">PETUNJUK PENDAFTARAN</a></li>
							</ul>

						</div>
						<div class="col-9">
							<h3>FORM PENDAFTARAN</h3>
							<p>
								<form class="jotform-form" action="get_info_formasi.html"
								method="post" name="formInfoInstansi" id="formInfoInstansi"
								accept-charset="utf-8">								
								<div class="form-all"
									style="font-family: Arial, Helvetica, sans-serif; font-size: 100%; line-height: 1em;">
									<ul class="form-section">
										<li id="cid_1" class="form-input-wide"></li>
										<li class="form-line">
											<div id=class="form-input"></div>
										</li>
										<fieldset id="fsFormasi">
											<legend>Formasi Jabatan yang dilamar</legend>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_11"><label class="form-label-left" id="label_11"
												for="input_11"> Instansi Pemerintah (K/L/D/I)<span
													class="style3">*</span></label>
												<div id="cid_11" class="form-input">													
													<jsp:include page="listInstansi.jsp" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_12"><label class="form-label-left" id="label_12"
												for="input_12"> Lokasi Kerja </label>
												<div id="cid_12" class="form-input">
													<select class="form-dropdown" style="width: 250px"
														title="Lokasi Kerja" id="lokasi_kerja" name="lokasi_kerja">
														<option value="">Pilih lokasi kerja</option>
													</select> <img id="imgLoadingLokasi" src="/sscn2014/resources/images/loading.png" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_15"><label class="form-label-left" id="label_15"
												for="input_15"> Kualifikasi Pendidikan </label>
												<div id="cid_15" class="form-input">
													<select class="form-dropdown" style="width: 300px"
														title="Kualifikasi Pendidikan" id="pendidikan"
														name="pendidikan">
														<option value="">Pilih Pendidikan</option>
													</select> <img id="imgLoadingPendidikan" src="/sscn2014/resources/images/loading.png" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_13"><label class="form-label-left" id="label_13"
												for="input_13"> Jabatan yang dilamar</label>
												<div id="cid_13" class="form-input">
													<select class="form-dropdown" style="width: 300px"
														title="Jabatan" id="jabatan" name="jabatan">
														<option value="">Pilih jabatan</option>
													</select> <img id="imgLoadingJabatan" src="/sscn2014/resources/images/loading.png" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6">
												<div id=class="form-input">
												countAvailableFormasi : ${countAvailableFormasi}
												<BR>
												countFormasiInPendaftaran : ${countFormasiInPendaftaran}
												</div>
											</li>
											<li class="form-line" style="background-color: #E6E6E6">
												<div id=class="form-input">
													<span class="style3">*</span>Pilih instansi pada dropdown
													list
												</div>
											</li>
										</fieldset>										
										<li class="form-line" id="id_2">
											<div id="cid_2" class="form-input-wide">
												<div style="margin-left: 156px" class="form-buttons-wrapper">											
													<button type="submit"
														class="form-submit-button style2" id="btnSubmit">SUBMIT</button>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</form>							
						</div>
					</div>
				</article>
			</div>
			<!-- Footer -->
			<footer>
				<div class="copyright style2">Hak Cipta © 2014 Badan
					Kepegawaian Negara. Semua Hak Dilindungi.</div>
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