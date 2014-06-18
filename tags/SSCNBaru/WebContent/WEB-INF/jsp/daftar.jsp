<%@page language="java" session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
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

<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.16/jquery-1.6.2.js"></script>

<!--jquery autocomplete-->
<link rel="stylesheet"
	href="js/jquery-ui-1.8.16/themes/base/jquery-ui.css" type="text/css" />
<script type="text/javascript" src="js/jquery-ui-1.8.16/ui/jquery-ui.js"></script>
<script type="text/javascript"
	src="js/jquery-ui-1.8.16/ui/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="js/jquery-ui-1.8.16/ui/jquery.ui.position.js"></script>
<script type="text/javascript"
	src="js/jquery-ui-1.8.16/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>

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
	/*background: url(assets/img/valid.png) center center no-repeat;*/
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
				function validate(value) {
					var valid = ".0123456789";

					var ok = true;
					var temp;
					for ( var i = 0; i < value.length; i++) {
						temp = "" + value.substring(i, i + 1);
						if (valid.indexOf(temp) == "-1") {
							ok = false;
						}
					}

					return ok;
				}
				$("#imgLoadingLokasi").hide();
				$("#imgLoadingJabatan").hide();
				$("#imgLoadingPendidikan").hide();
				jQuery.validator.addMethod("lettersonly", function(value,
						element) {
					return this.optional(element) || /^[a-z- ]+$/i.test(value);
				}, "Field tidak valid");
				jQuery.validator.addMethod("numericsonly", function(value,
						element) {
					return this.optional(element) || /^[0-9]+$/i.test(value);
				}, "Field tidak valid");
				jQuery.validator.addMethod("ipkcheck",
						function(value, element) {
							return validate(value);
						}, 'Field tidak valid');
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
								instansi : {
									required : true
								//lettersonly: true
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
								window.location = "ActionServlet?page=home";
								//window.location = "/SSCN/ActionServlet?page=home";	
								form.submit();
							}
						});

				$('#nilai_ipk').change(
						function() {
							var ipk = $(this).val();
							if (parseFloat(ipk) > 10.0) {
								$(this).val("");
								$(this).closest('.control-group').removeClass(
										'success').addClass('error');
							}
						});
			});
</script>
<script>
	function caps(element) {
		element.value = element.value.toUpperCase();
	}
</script>
<script>
	$(function() {
		//$("#datepickerTglLahir").datepicker();
		$("#datepickerTglLahir").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1973:1995',
			dateFormat : "dd-mm-yy"
		});
	});
</script>
<!--jquery autocomplete-->
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

	function changeImage() {
		element = document.getElementById('cekbox')
		if (element.src.match("before")) {
			element.src = "images/after.gif";
			document.getElementById("btnKirimPendaftaran").disabled = false;
		} else {
			element.src = "images/before.gif";
			document.getElementById("btnKirimPendaftaran").disabled = true;
		}
	}
	function getLokasi() {
		$("#imgLoadingLokasi").show();
		var param = $('#instansi').val();
		var url = '/sscnServer/cb_lokasi_by_instansi.do?instansi=' + param;
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
		var url = '/sscnServer/cb_pendidikan_by_instansi_lokasi.do?instansi='
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

		var url = '/sscnServer/cb_jabatan_by_instansi_lokasi_pendidikan.do?instansi='
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
							<h3>MENU UTAMA</h3>
							<ul class="list-2">
								<li><a href="ActionServlet?page=informasi_umum">INFORMASI
										UMUM</a></li>
								<li><a href="#">PENGUMUMAN INSTANSI</a></li>
								<li><a href="#">PETUNJUK PENDAFTARAN</a></li>
							</ul>

						</div>
						<div class="col-9">
							<h3>FORM PENDAFTARAN</h3>
							<p>
								<form class="jotform-form" action="/sscnServer/RegistrasiServlet"
								method="post" name="formRegistrasi" id="formRegistrasi"
								accept-charset="utf-8">
								<input type="hidden" name="formID" value="32063786011852" /> <input
									type="hidden" name="typeReport" value="rptRegistrasi" />
								<div class="form-all"
									style="font-family: Arial, Helvetica, sans-serif; font-size: 100%; line-height: 1em;">
									<ul class="form-section">
										<li id="cid_1" class="form-input-wide"></li>
										<li class="form-line">
											<div id=class="form-input"></div>
										</li>
										<fieldset id="fsInformasiPelamar">
											<legend>Informasi Pelamar</legend>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_4"><label class="form-label-left" id="label_4"
												for="input_4"> No Induk Kependudukan (NIK) / No
													Passport</label>
												<div id="cid_4" class="form-input">
													<input type="number" class=" form-textbox" maxlength="18"
														data-type="input-textbox" id="no_nik" name="no_nik"
														title="NIK / Passport" size="20" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_3"><label class="form-label-left" id="label_3"
												for="input_3"> Nama Lengkap (tanpa gelar) </label>
												<div id="cid_3" class="form-input">
													<input type="text" class=" form-textbox"
														title="Nama Lengkap" data-type="input-textbox" id="nama"
														name="nama" size="35" onKeyUp="caps(this)" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"><label
												class="form-label-left"> Jenis Kelamin </label>
												<div class="form-input">
													<select id="jenis_kelamin" name="jenis_kelamin"
														title="Jenis Kelamin">
														<option value="">Pilih Jenis Kelamin</option>

														<option value="P">Pria</option>
														<option value="W">Wanita</option>
													</select>
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_5"><label class="form-label-left" id="label_5"
												for="input_5"> Tempat Lahir </label>
												<div id="cid_5" class="form-input">
													<input type="text" class=" form-textbox"
														title="Tempat Lahir" data-type="input-textbox"
														id="tempat_lahir" name="tempat_lahir" size="40"
														onKeyUp="caps(this)" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_7"><label class="form-label-left" id="label_7"
												for="input_7"> Tanggal Lahir </label>
												<div id="cid_7" class="form-input">
													<input type="text" name="datepickerTglLahir"
														title="Tanggal Lahir" size="10" id="datepickerTglLahir"
														readonly="readonly" /> <label> dd-mm-yyyy</label>
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_8"><label class="form-label-left" id="label_8"
												for="input_8"> Alamat </label>
												<div id="cid_8" class="form-input">
													<table summary="" class="form-address-table" border="0"
														cellpadding="0" cellspacing="0">
														<tr>
															<td colspan="2"><span
																class="form-sub-label-container"> <input
																	title="Alamat" size="50"
																	class="form-textbox form-address-line" type="text"
																	name="alamat" id="alamat" onKeyUp="caps(this)" /> <label
																	class="form-sub-label" for="input_8_addr_line1">
																		&nbsp;&nbsp;&nbsp; </label></span></td>
														</tr>
														<tr>
															<td width="50%"><span
																class="form-sub-label-container"> <input
																	title="Kota" class="form-textbox form-address-city"
																	type="text" name="kota" id="input_8_city" size="21"
																	onKeyUp="caps(this)" /> <label class="form-sub-label"
																	for="input_8_city" id="kota"> Kota </label></span></td>
															<td><span class="form-sub-label-container"> <input
																	title="Propinsi"
																	class="form-textbox form-address-state" type="text"
																	name="propinsi" id="input_8_state" size="22"
																	onKeyUp="caps(this)" /> <label class="form-sub-label"
																	for="input_8_state" id="propinsi"> Propinsi </label></span></td>
														</tr>
														<tr>
															<td width="50%"><span
																class="form-sub-label-container"> <input
																	title="Kode Pos"
																	class="form-textbox form-address-postal" type="number"
																	name="kode_pos" id="input_8_postal" size="5"
																	maxlength="5" /> <label class="form-sub-label"
																	for="input_8_postal" id="kode_pos"> Kode Pos </label></span></td>
															<td><span class="form-sub-label-container"></td>
														</tr>
													</table>
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_9"><label class="form-label-left" id="label_9"
												for="input_9"> No Telp </label>
												<div id="cid_9" class="form-input">
													<span class="form-sub-label-container"> <input
														title="No Telp" class="form-textbox" type="number"
														name="telpon" id="telpon" maxlength="12" size="12">
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_10"><label class="form-label-left" id="label_10"
												for="input_10"> E-mail </label>
												<div id="cid_10" class="form-input">
													<input type="email" class=" form-textbox validate[Email]"
														title="Email" id="email" name="email" size="35" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"><label
												class="form-label-left"> Asal Institusi Pendidikan</label>
												<div class="form-input">
													<input type="text" class=" form-textbox"
														title="Institusi Pendidikan" data-type="input-textbox"
														id="universitas" name="universitas" size="50"
														onKeyUp="caps(this)" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_16"><label class="form-label-left" id="label_16"
												for="input_16"> No Ijazah </label>
												<div id="cid_16" class="form-input">
													<input type="text" class=" form-textbox" title="No Ijazah"
														data-type="input-textbox" id="no_ijazah" name="no_ijazah"
														size="30" maxlength="30" />
												</div></li>

											<li class="form-line" style="background-color: #E6E6E6"><label
												class="form-label-left"> Akreditas </label>
												<div id=class="form-input">
													<input type="text" class=" form-textbox" title="Akreditas"
														data-type="input-textbox" id="akreditasi" maxlength="1"
														name="akreditasi" size="1" onKeyUp="caps(this)" /> <em>contoh
														A / B / - (untuk Universitas Negeri)</em>
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"><label
												class="form-label-left"> Nilai IPK </label>
												<div id=class="form-input">
													<input type="text" class=" form-textbox"
														title="Nilai IPK/Ijazah (SLTA)" maxlength="4"
														data-type="input-textbox" id="nilai_ipk" name="nilai_ipk"
														size="1" /> <em>dalam skala 4, contoh 3.50. untuk
														SLTA skala 10, contoh 7.5</em>
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6">
												<div id=class="form-input"></div>
											</li>
										</fieldset>

										<fieldset id="fsFormasi">
											<legend>Formasi Jabatan yang dilamar</legend>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_11"><label class="form-label-left" id="label_11"
												for="input_11"> Instansi Pemerintah (K/L/D/I)<span
													class="style3">*</span></label>
												<div id="cid_11" class="form-input">
													<!--  <input type="hidden" name="instansiValue"
														id="instansiValue" /> <input id="instansi" size="50" type="text" title="Instansi"
														name="instansi"/> -->
													<!--  <select class="form-dropdown" style="width: 200px" title="Instansi"
														id="instansi" name="instansi">
														<option value="">Pilih instansi</option>
													</select>-->
													<jsp:include page="listInstansi.jsp" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_12"><label class="form-label-left" id="label_12"
												for="input_12"> Lokasi Kerja </label>
												<div id="cid_12" class="form-input">
													<select class="form-dropdown" style="width: 250px"
														title="Lokasi Kerja" id="lokasi_kerja" name="lokasi_kerja">
														<option value="">Pilih lokasi kerja</option>
													</select> <img id="imgLoadingLokasi" src="images/loading.png" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_15"><label class="form-label-left" id="label_15"
												for="input_15"> Kualifikasi Pendidikan </label>
												<div id="cid_15" class="form-input">
													<select class="form-dropdown" style="width: 300px"
														title="Kualifikasi Pendidikan" id="pendidikan"
														name="pendidikan">
														<option value="">Pilih Pendidikan</option>
													</select> <img id="imgLoadingPendidikan" src="images/loading.png" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6"
												id="id_13"><label class="form-label-left" id="label_13"
												for="input_13"> Jabatan yang dilamar</label>
												<div id="cid_13" class="form-input">
													<select class="form-dropdown" style="width: 300px"
														title="Jabatan" id="jabatan" name="jabatan">
														<option value="">Pilih jabatan</option>
													</select> <img id="imgLoadingJabatan" src="images/loading.png" />
												</div></li>
											<li class="form-line" style="background-color: #E6E6E6">
												<div id=class="form-input"></div>
											</li>
											<li class="form-line" style="background-color: #E6E6E6">
												<div id=class="form-input">
													<span class="style3">*</span>Pilih instansi pada dropdown
													list
												</div>
											</li>
										</fieldset>
										<li class="form-line">
											<table width="570" border="2">
												<tr>
													<th width="70" height="76" valign="middle" scope="col"><div
															align="center">
															<image id="cekbox" data-type="input-textbox"
																onClick="changeImage()" img src="images/before.gif"
																width="67" height="58" name="cekbox" />
														</div></th>
													<th width="490" valign="middle" scope="col"><div
															align="justify" class="form-line">
															<p>
																<em>Demikian data pribadi saya buat dengan
																	sebenarnya dan bila ternyata isian yang dibuat tidak
																	benar, saya bersedia menanggung akibat hukum yang
																	ditimbulkannya</em>
															</p>
														</div></th>
												</tr>
											</table> <label></label>
										</li>
										<li class="form-line" id="id_2">
											<div id="cid_2" class="form-input-wide">
												<div style="margin-left: 156px" class="form-buttons-wrapper">
													<br>
													<%
														ReCaptcha c = ReCaptchaFactory.newReCaptcha(
																"6LdlHOsSAAAAAM8ypy8W2KXvgMtY2dFsiQT3HVq-",
																"6LdlHOsSAAAAACe2WYaGCjU2sc95EZqCI9wLcLXY", false);
														out.print(c.createRecaptchaHtml(null, null));
													%>
													<br>
													<button type="submit" disabled="disabled"
														class="form-submit-button style2" id="btnKirimPendaftaran">DAFTAR</button>
												</div>
											</div>
										</li>
										<li style="display: none">Should be Empty: <input
											type="text" name="website" value="" />
										</li>
									</ul>
								</div>
								<input type="hidden" id="simple_spc" name="simple_spc"
									value="32063786011852" />
								<script type="text/javascript">
									document.getElementById("si" + "mple"
											+ "_spc").value = "32063786011852-32063786011852";
								</script>
							</form>
							
						</div>
					</div>
				</article>
			</div>
			<!-- Footer -->
			<footer>
				<div class="copyright style2">Hak Cipta Â© 2014 Badan
					Kepegawaian Negara. Semua Hak Dilindungi.</div>
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