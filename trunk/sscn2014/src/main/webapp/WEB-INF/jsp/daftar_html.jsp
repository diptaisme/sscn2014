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
<!--taruh scripbaru disini-->
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
                    <li class="current"><a href="index.html">BERANDA</a></li>
                    <li><a href="calendar.html">DAFTAR</a></li>
                    <li><a href="contacts.html">KONTAK</a></li>
                </ul>
            </nav>
            <div class="clear"></div>
        </header>
        <!-- Slider -->
        <!-- Content -->
      <div class="container_12">            	
                	<div class="wrapper">
                	  <div class="col-13">
                        	<h3>PELAKSANAAN SELEKSI CPNS TAHUN 2014 </h3>
                      <p>
                            	Dalam rangka mewujudkan Pegawai Negeri Sipil yang profesional, berkualitas, dan bertanggung jawab, diperlukan Pegawai Negeri Sipil yang kompeten melalui sistem pengadaan yang transparan dan akuntabel serta bebas dari korupsi, kolusi dan nepotisme. Untuk mewujudkan hal tersebut maka pemerintah akan melaksanakan pendaftaran CPNS secara serentak dan terintegrasi melalui sistem pendaftaran (registration) online. Sistem pendaftaran CPNS online ini diperuntukkan bagi pelamar CPNS yang akan mengisi formasi umum di instansi pusat maupun daerah.                        </p>
                        </div>
				          
				  <p>
				  <form action="/sscnServer/RegistrasiServlet" method="post"
								name="formRegistrasi" id="formRegistrasi" accept-charset="utf-8">
								<input type="hidden" name="formID" value="32063786011852" /> 
								<input type="hidden" name="typeReport" value="rptRegistrasi" />
								<div 
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
        </div>        
        <!-- Footer -->
        <footer>
            <div class="copyright style2">Hak Cipta  ©  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</div>
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