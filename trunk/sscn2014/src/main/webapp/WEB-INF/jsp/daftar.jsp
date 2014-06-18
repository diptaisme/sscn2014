<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Seleksi CPNS 2014</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/styles/form.css?v3.1.1187" />
<link href="css/calendarview.css?v3.1.1187" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.8.16/jquery-1.6.2.js"></script>
<!--jquery autocomplete-->
<link rel="stylesheet" href="js/jquery-ui-1.8.16/themes/base/jquery-ui.css" type="text/css" />
<script type="text/javascript" src="js/jquery-ui-1.8.16/ui/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.16/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.16/ui/jquery.ui.position.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.16/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>

<style>
	#instansi {color: blue;}
	#lokasi_kerja {color: blue;}
	#jabatan {color: blue;}
	#pendidikan {color: blue;} 
</style>

<style>
fieldset {
	width: 570px;
	border:1px solid black; 
	padding:0 1.4em 1.4em 1.4em; 
	margin:0 0 1.5em 0;
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
  _gaq.push(['_setAccount', 'UA-44204367-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<script>
	$(document).ready(
			function() {
				function validate(value) {
					var valid = ".0123456789";
					
					var ok = true;
					var temp;
					for (var i=0; i<value.length; i++) {
						temp = "" + value.substring(i, i+1);
						if (valid.indexOf(temp) == "-1"){
							ok = false;
						} 
					}				
						
					return ok;
				}
				$("#imgLoadingLokasi").hide();
				$("#imgLoadingJabatan").hide();
				$("#imgLoadingPendidikan").hide();
				jQuery.validator.addMethod("lettersonly", function(value, element) {
					  return this.optional(element) || /^[a-z- ]+$/i.test(value);
					}, "Field tidak valid");
				jQuery.validator.addMethod("numericsonly", function(value, element) {
					  return this.optional(element) || /^[0-9]+$/i.test(value);
					}, "Field tidak valid");
				jQuery.validator.addMethod("ipkcheck", function (value, element) {
				        return validate(value);
				    }, 'Field tidak valid');
				$('#formRegistrasi').validate(
						{
							rules : {
								no_nik : {
									minlength : 14,
									maxlength: 18,  
									required : true
								},
								nama : {
									required : true,
									lettersonly: true
								},
								tempat_lahir : {
									required : true,
									lettersonly: true
								},
								datepickerTglLahir : {
									required : true
								},
								alamat : {
									required : true
								},
								kota : {
									required : true,
									lettersonly: true
								},
								propinsi : {
									required : true,
									lettersonly: true
								},
								kode_pos : {
									required : true
								},
								telpon : {
									minlength : 5,
									maxlength: 12,
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
									lettersonly: true
								},
								nilai_ipk : {
									required : true,
									ipkcheck : true
								},
								jenis_kelamin:{
									required : true
								},
								instansi:{
									required : true
									//lettersonly: true
								},
								lokasi_kerja:{
									required : true
								},
								jabatan:{
									required : true
								},
								pendidikan:{
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
							submitHandler: function(form) {
							    window.location = "ActionServlet?page=home";
			                    //window.location = "/SSCN/ActionServlet?page=home";	
								form.submit();			                    
			                }
						});

					$('#nilai_ipk').change(function(){
						var ipk = $(this).val();
						if (parseFloat(ipk) > 10.0){
							$(this).val("");
							$(this).closest('.control-group')
							.removeClass('success').addClass(
									'error');
						}
					});
			});
</script>
<script>
	function caps(element){
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
			//clear jabatan
			var html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan').html(html);
			//clear pendidikan
			html = '<option value="">Pilih Pendidikan</option>';			
			$('#pendidikan').html(html);
			$("#imgLoadingJabatan").hide();
			$("#imgLoadingPendidikan").hide();
			getJabatan();
		});
		$('#jabatan').change(function() {
			//clear pendidikan
			var html = '<option value="">Pilih Pendidikan</option>';			
			$('#pendidikan').html(html);
			$("#imgLoadingPendidikan").hide();
			getPendidikan();
		});
		$('#instansi').change(function(){
			//clear lokasi
			var html = '<option value="">Pilih Lokasi Kerja</option>';			
			$('#lokasi_kerja').html(html);
			//clear jabatan
			html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan').html(html);
			//clear pendidikan
			html = '<option value="">Pilih Pendidikan</option>';			
			$('#pendidikan').html(html);
			$("#imgLoadingLokasi").hide();
			$("#imgLoadingJabatan").hide();
			$("#imgLoadingPendidikan").hide();
			getLokasi();
		});

	/*	$.ajax({
			url : '/sscnServer/cb_instansi.do',
			dataType : "jsonp",
			data : {
				featureClass : "P"
			},
			success : function(data) {
				var html = '<option value="">Pilih Instansi</option>';
				$.map(data.instansis, function(item) {
					html += '<option value="' + item.kode + '">'
							+ item.nama + '</option>';
				})
				html += '</option>';
				$('#instansi').html(html);
			}
		});*/ 
	/*	$("#instansi").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "/sscnServer/ac_instansi.do",
					dataType : "jsonp",
					data : {
						featureClass : "P",
						maxRows : 10,
						startsWith : request.term
					},
					success : function(data) {
						response($.map(data.instansis, function(item) {
							return {
								code : item.kode,
								label : item.nama,
								value : item.nama,
							}
						}));
					}
				});
			},
			minLength : 5,
			select : function(event, ui) {
				$('#instansiValue').val(ui.item.code);
				//clear lokasi kerja
				var html = '<option value="">Pilih Lokasi Kerja</option>';				
				$('#lokasi_kerja').html(html);
				//clear jabatan
				html = '<option value="">Pilih Jabatan</option>';
				$('#jabatan').html(html);
				//clear pendidikan
				html = '<option value="">Pilih Pendidikan</option>';			
				$('#pendidikan').html(html);
				$("lokasi_kerja").focus();
				getLokasi();
			},
			open : function() {
				$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
			},
			close : function() {
				$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
				//clear lokasi kerja
				var html = '<option value="">Pilih Lokasi Kerja</option>';				
				$('#lokasi_kerja').html(html);
				//clear jabatan
				html = '<option value="">Pilih Jabatan</option>';
				$('#jabatan').html(html);
				//clear pendidikan
				html = '<option value="">Pilih Pendidikan</option>';			
				$('#pendidikan').html(html);
				$("lokasi_kerja").focus();
				//getLokasi();
			}
		}); */

	});

	function changeImage() {
		element=document.getElementById('cekbox')
		if (element.src.match("before"))
		 {
			element.src="images/after.gif"; document.getElementById("btnKirimPendaftaran").disabled = false;
		} else 
		 {
			element.src="images/before.gif";	document.getElementById("btnKirimPendaftaran").disabled = true;
		}
	}
	function getLokasi() {
		$("#imgLoadingLokasi").show();
		var param = $('#instansi').val();
		var url = '/sscnServer/cb_lokasi_by_instansi.do?instansi=' + param;
		/*$.get(url, function(data) {
			var html = '<option value="">Pilih Lokasi Kerja</option>';
			$.map(data.lokasis, function(item) {
				html += '<option value="' + item.kode + '">' + item.nama
						+ '</option>';
			})
			html += '</option>';
			$('#lokasi_kerja').html(html);
			//clear jabatan
			html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan').html(html);
			//clear pendidikan
			html = '<option value="">Pilih Pendidikan</option>';			
			$('#pendidikan').html(html);
		});*/

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
						html += '<option value="' + item.kode + '">' + item.nama
								+ '</option>';
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
	function getJabatan() {
		$("#imgLoadingJabatan").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja').val();
		var url = '/sscnServer/cb_jabatan_by_instansi_lokasi.do?instansi='
				+ param + '&lokasi=' + param2;
	/*	$.get(url, function(data) {
			var html = '<option value="">Pilih Jabatan</option>';
			$.map(data.jabatans, function(item) {
				html += '<option value="' + item.kode + '">' + item.nama
						+ '</option>';
			})
			html += '</option>';
			$('#jabatan').html(html);			
			//clear pendidikan
			html = '<option value="">Pilih Pendidikan</option>';			
			$('#pendidikan').html(html);
		}); */
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
				//clear pendidikan
				html = '<option value="">Pilih Pendidikan</option>';			
				$('#pendidikan').html(html);
				$("#imgLoadingJabatan").hide();
			}
		});
	}
	function getPendidikan() {
		$("#imgLoadingPendidikan").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja').val();
		var param3 = $('#jabatan').val();
		var url = '/sscnServer/cb_pendidikan_by_instansi_lokasi_jabatan.do?instansi='
				+ param + '&lokasi=' + param2 + '&jabatan=' + param3;
		/*$.get(url, function(data) {
			var html = '<option value="">Pilih Pendidikan</option>';
			$.map(data.pendidikans, function(item) {
				html += '<option value="' + item.kode + '">' + item.nama
						+ '</option>';
			})
			html += '</option>';
			$('#pendidikan').html(html);
		});*/
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
				$("#imgLoadingPendidikan").hide();
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
<!--[if lt IE 7]>
		<script type="text/javascript" src="js/ie_png.js"></script>
		<script type="text/javascript">ie_png.fix('.png, .carousel-box .next img, .carousel-box .prev img');</script>
		<link href="css/ie6.css" rel="stylesheet" type="text/css" />
		<![endif]-->
<style type="text/css">
<!--
.style1 {color: #000000}
.style3 {
	color: #FF0000;
	font-weight: bold;
}
-->
</style>

</head>
<body id="page1">
<div class="tail-top">
  <div id="header">
    <div class="center"><a href="#"><img src="images/header-bg2.png" alt="" width="972" height="205" border="0" /></a></div>
    <div class="left"></div>
    <!--</div> -->
    <div class="right"> </div>
    <!--       <ul>
         <li><a href="contact-us.jsp"><img src="images/hubungi.png" alt="Hubungi Kami" width="43" height="45"  style="float:right;margin:0 5px 0 0;"/><div class="extra-wrap"><span>Hubungi Kami</span></div></a>
 
         <li><a href="index.jsp" class="active"><img src="images/home.png" alt="" width="43" height="45" style="float:right;margin:100 5px 0 0;"/> </a></li>
        </ul>-->
    <table width="100%" border="0">
      <center>
        <tr>
          <td width="87%"><marquee>
          Untuk tampilan terbaik diharapkan menggunakan browser <b>Mozilla Firefox 3, Safari, Google Chrome</b> atau diatasnya.&nbsp; |&nbsp; Situs ini akan resmi dibuka pada tanggal ...... 2014 </marquee></td>
          <td width="7%"><img src="images/home.png" width="30" height="30"></td>
          <td width="6%"><img src="images/hubungi.png" width="30" height="30"></td>
        </tr>
        <tr>
          <td width="87%">&nbsp;</td>
          <td width="7%"><a href="ActionServlet?page=home">
            <div class="inside">Beranda</div>
            </a></td>
          <td width="6%"><a href="ActionServlet?page=contact-us">
            <div class="extra-wrap">Kontak</div>
            </a></td>
        </tr>
      </center>
    </table>
  </div>
  <div id="content">
    <div class="row-1">
      <div class="inside">
        <div class="container">
          <div class="aside">
            <h3>Menu Utama </h3>
            <ul>
              <li> <a href="ActionServlet?page=informasi-umum"><img src="images/general.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Informasi Umum</strong></span>Informasi Terkait Pendaftaran CPNS 2014<a href="#"></a> </div>
                </a> </li>
              <li> <a href="ActionServlet?page=pengumuman"><img src="images/pengumuman.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Pengumuman</strong></span>Pengumuman Pendaftaran di Instansi Pemerintah<a href="#"></a> </div>
                </a> </li>
              <li> <a href="ActionServlet?page=peraturan"><img src="images/peraturan.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Peraturan</strong></span>Kumpulan Peraturan Seleksi CPNS Nasional 2014<a href="#"></a> </div>
                </a> </li>
              <li> <a href="ActionServlet?page=jadwal"><img src="images/jadwal.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Jadwal Pelaksanaan</strong></span>Jadwal Pelaksanaan Seleksi CPNS Nasional 2014<a href="#"></a> </div>
                </a> </li>
              <li> <a href="ActionServlet?page=alur"><img src="images/alur.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Alur</strong></span>Prosedur Seleksi CPNS Nasional<a href="#"></a> </div>
              </li>
              <li> <a href="ActionServlet?page=petunjuk"><img src="images/general.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Petunjuk Pendaftaran CPNS</strong></span>Petunjuk Pendaftaran CPNS Nasional<a href="#"></a> </div>
              </li>
		<li> <a href="ActionServlet?page=daftar"><img src="images/daftar.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Pendaftaran CPNS</strong></span>Pendaftaran CPNS Nasional<a href="#"></a> </div>
              </li>
              <li> <a href="ActionServlet?page=hasil"><img src="images/hasil.png" alt="" width="43" height="41" border="0" />
                <div class="extra-wrap"> <span><strong>Hasil Seleksi</strong></span>Hasil Pengolahan TKD<a href="#"></a> </div>
              </li>
            </ul>
            <div class="wrapper"></div>
          </div>
          <div class="content">
            <div class="box" >
			
              <h3 class="style1">Form Pendaftaran</h3>
            </div>

			<!-- Codes by HTML.am scrolling box-->
				<form class="jotform-form"
								action="/sscnServer/RegistrasiServlet" method="post"
								name="formRegistrasi" id="formRegistrasi" accept-charset="utf-8">
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
											<li class="form-line" style="background-color:#E6E6E6" id="id_4"><label
												class="form-label-left" id="label_4" for="input_4">
													No Induk Kependudukan (NIK) / No Passport</label>
												<div id="cid_4" class="form-input">
													<input type="number" class=" form-textbox" maxlength="18"
														data-type="input-textbox" id="no_nik" name="no_nik" title="NIK / Passport"
														size="20" />
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_3"><label
												class="form-label-left" id="label_3" for="input_3">
													Nama Lengkap (tanpa gelar) </label>
												<div id="cid_3" class="form-input">
													<input type="text" class=" form-textbox" title="Nama Lengkap"
														data-type="input-textbox" id="nama" name="nama" size="35" onKeyUp="caps(this)"/>
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6"><label class="form-label-left">
													Jenis Kelamin </label>
												<div class="form-input">
													<select id="jenis_kelamin" name="jenis_kelamin" title="Jenis Kelamin">
														<option value="">Pilih Jenis Kelamin</option>

														<option value="P">Pria</option>
														<option value="W">Wanita</option>
													</select>
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_5"><label
												class="form-label-left" id="label_5" for="input_5">
													Tempat Lahir </label>
												<div id="cid_5" class="form-input">
													<input type="text" class=" form-textbox" title="Tempat Lahir"
														data-type="input-textbox" id="tempat_lahir"
														name="tempat_lahir" size="40" onKeyUp="caps(this)" />
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_7"><label
												class="form-label-left" id="label_7" for="input_7">
													Tanggal Lahir </label>
												<div id="cid_7" class="form-input">
													<input type="text" name="datepickerTglLahir" title="Tanggal Lahir" size="10"
														id="datepickerTglLahir" readonly="readonly" /> <label>
														dd-mm-yyyy</label>
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_8"><label
												class="form-label-left" id="label_8" for="input_8">
													Alamat </label>
												<div id="cid_8" class="form-input">
													<table summary="" class="form-address-table" border="0"
														cellpadding="0" cellspacing="0">
														<tr>
															<td colspan="2"><span
																class="form-sub-label-container"> <input title="Alamat" size="50"
																	class="form-textbox form-address-line" type="text"
																	name="alamat" id="alamat" onKeyUp="caps(this)" /> <label
																	class="form-sub-label" for="input_8_addr_line1">
																		&nbsp;&nbsp;&nbsp; </label></span></td>
														</tr>
														<tr>
															<td width="50%"><span
																class="form-sub-label-container"> <input title="Kota"
																	class="form-textbox form-address-city" type="text"
																	name="kota" id="input_8_city" size="21" onKeyUp="caps(this)" /> <label
																	class="form-sub-label" for="input_8_city" id="kota">
																		Kota </label></span></td>
															<td><span class="form-sub-label-container"> <input title="Propinsi"
																	class="form-textbox form-address-state" type="text"
																	name="propinsi" id="input_8_state" size="22" onKeyUp="caps(this)" /> <label
																	class="form-sub-label" for="input_8_state"
																	id="propinsi"> Propinsi </label></span></td>
														</tr>
														<tr>
															<td width="50%"><span
																class="form-sub-label-container"> <input title="Kode Pos"
																	class="form-textbox form-address-postal" type="number"
																	name="kode_pos" id="input_8_postal" size="5" maxlength="5"/> <label
																	class="form-sub-label" for="input_8_postal"
																	id="kode_pos"> Kode Pos </label></span></td>
															<td><span class="form-sub-label-container"></td>
														</tr>
													</table>
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_9"><label
												class="form-label-left" id="label_9" for="input_9">
													No Telp </label>
												<div id="cid_9" class="form-input">
													<span class="form-sub-label-container"> <input title="No Telp"
														class="form-textbox" type="number" name="telpon" id="telpon" maxlength="12"
														size="12"> </div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_10"><label
												class="form-label-left" id="label_10" for="input_10">
													E-mail </label>
												<div id="cid_10" class="form-input">
													<input type="email" class=" form-textbox validate[Email]" title="Email"
														id="email" name="email" size="35" />
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6"><label class="form-label-left">
													Asal Institusi Pendidikan</label>
												<div class="form-input">
													<input type="text" class=" form-textbox" title="Institusi Pendidikan"
														data-type="input-textbox" id="universitas"
														name="universitas" size="50" onKeyUp="caps(this)" />
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_16"><label
												class="form-label-left" id="label_16" for="input_16">
													No Ijazah </label>
												<div id="cid_16" class="form-input">
													<input type="text" class=" form-textbox" title="No Ijazah"
														data-type="input-textbox" id="no_ijazah" name="no_ijazah"
														size="30" maxlength="30"/>
												</div></li>

											<li class="form-line" style="background-color:#E6E6E6"><label class="form-label-left">
													Akreditas </label>
												<div id=class="form-input">
												<input type="text" class=" form-textbox" title="Akreditas"
														data-type="input-textbox" id="akreditasi" maxlength="1"
														name="akreditasi" size="1" onKeyUp="caps(this)" /> 
												<em>contoh A / B / - (untuk Universitas Negeri)</em> </div></li>
											<li class="form-line" style="background-color:#E6E6E6"><label class="form-label-left">
													Nilai IPK </label>
												<div id=class="form-input">
												<input type="text" class=" form-textbox" title="Nilai IPK/Ijazah (SLTA)" maxlength="4"
														data-type="input-textbox" id="nilai_ipk" name="nilai_ipk"
														size="1" /> 
												<em>dalam skala 4, contoh 3.50. untuk SLTA skala 10, contoh 7.5</em> </div> </li>
											<li class="form-line" style="background-color:#E6E6E6">
												<div id=class="form-input"></div>
											</li>
										</fieldset>

										<fieldset id="fsFormasi">
											<legend>Formasi Jabatan yang dilamar</legend>
											<li class="form-line" style="background-color:#E6E6E6" id="id_11"><label
												class="form-label-left" id="label_11" for="input_11">
													Instansi Pemerintah (K/L/D/I)<span class="style3">*</span></label>
												<div id="cid_11" class="form-input">
													<!--  <input type="hidden" name="instansiValue"
														id="instansiValue" /> <input id="instansi" size="50" type="text" title="Instansi"
														name="instansi"/> -->
													<!--  <select class="form-dropdown" style="width: 200px" title="Instansi"
														id="instansi" name="instansi">
														<option value="">Pilih instansi</option>
													</select>-->														
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_12"><label
												class="form-label-left" id="label_12" for="input_12">
													Lokasi Kerja </label>
												<div id="cid_12" class="form-input">
													<select class="form-dropdown" style="width: 250px" title="Lokasi Kerja"
														id="lokasi_kerja" name="lokasi_kerja">
														<option value="">Pilih lokasi kerja</option>
													</select>
													<img id="imgLoadingLokasi" src="images/loading.png"  />
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6" id="id_13"><label
												class="form-label-left" id="label_13" for="input_13">
													Jabatan yang dilamar</label>
												<div id="cid_13" class="form-input">
													<select class="form-dropdown" style="width: 300px" title="Jabatan"
														id="jabatan" name="jabatan">
														<option value="">Pilih jabatan</option>
													</select>
													<img id="imgLoadingJabatan" src="images/loading.png"  />
												</div></li>

											<li class="form-line" style="background-color:#E6E6E6" id="id_15"><label
												class="form-label-left" id="label_15" for="input_15">
													Kualifikasi Pendidikan </label>
												<div id="cid_15" class="form-input">
													<select class="form-dropdown" style="width: 300px" title="Kualifikasi Pendidikan"
														id="pendidikan" name="pendidikan">
														<option value="">Pilih Pendidikan</option>
													</select>
													<img id="imgLoadingPendidikan" src="images/loading.png"  />
												</div></li>
											<li class="form-line" style="background-color:#E6E6E6">
												<div id=class="form-input"></div>
											</li>
											<li class="form-line" style="background-color:#E6E6E6">
												<div id=class="form-input"><span class="style3">*</span>Pilih instansi pada dropdown list</div>
											</li>
										</fieldset>
										<li class="form-line">
										  <table width="570" border="2">
                                            <tr>
                                              <th width="70" height="76" valign="middle" scope="col"><div align="center">
                                                <image id="cekbox" data-type="input-textbox" 
												onClick="changeImage()" img src="images/before.gif" width="67" height="58" name="cekbox" />
                                              </div></th>
                                              <th width="490" valign="middle" scope="col"><div align="justify" class="form-line">
                                                <p><em>Demikian data pribadi saya buat dengan sebenarnya dan bila ternyata isian yang dibuat tidak benar, saya bersedia menanggung akibat hukum yang ditimbulkannya</em></p>
                                              </div></th>
                                            </tr>
                                          </table>
										  <label></label></li>
										<li class="form-line" id="id_2">
											<div id="cid_2" class="form-input-wide">
												<div style="margin-left: 156px" class="form-buttons-wrapper">
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
			<!--untuk footer -->
			
            <div class="wrapper"></div>
          </div>
          <div class="clear"></div>
        </div>
      </div>
    </div>
  </div>


  <div id="footer">
    <div class="footerlink">
      <p class="lf">Hak Cipta  &copy;  2013 Badan Kepegawaian Negara. Semua Hak Dilindungi.</p>
      <p class="rf">Website : <a href="http://www.bkn.go.id/">www.bkn.go.id</a> </p>
      <div style="clear:both;"></div>
    </div>
  </div>
</div>

</body>
</html>
