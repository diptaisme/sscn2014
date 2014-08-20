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
                        	<h3>INFORMASI UMUM </h3>
                            <ul class="list-2">
					<li><a href="peraturan.html"><img src="/sscn2014/resources/images/peraturan.jpg" width="243" height="37" border="0" align="left"></a></img></li>
					<li><a href="alur.html"><img src="/sscn2014/resources/images/alur.jpg" width="243" height="37" border="0" align="left"></a></img></li>								
                    <li><a href="calendar.html"><img src="/sscn2014/resources/images/jadwal.jpg" width="243" height="37" border="0" align="left"></a></img></li>
                            </ul>                            
                        </div>
			   		<div class="col-9">
                        	<h3>Proses PENDAFTARAN </h3>
                            <p align="justify">Tahun 2014 ini, Pemerintah melakukan rekrutmen CPNS melalui jalur pelamar umum yang dibuka melalui formasi umum dan formasi khusus. Formasi umum dibuka berdasarkan analisis kebutuhan setiap instansi yang diajukan ke BKN dan Kementerian PAN dan RB untuk dianalisa dan dikeluarkan formasi yang disetujui untuk diadakan rekrutmen. Sedangkan formasi khusus dibuka untuk Putra-putri Papua, Para Atlet Berprestasi, Lulusan Perguruan Tinggi Negeri dengan Prestasi Terbaik, Instruktur (Sarjana Mengajar daerah Terpencil, Terbelakang, Terluar (SM3T)), Penyandang Cacat, Mahasiswa Tugas Belajar terbaik yang dibiayai Pemerintah Daerah.</p>
                            <p align="justify">Untuk pelamar umum tersebut dilakukan seleksi Tes Kompetensi Dasar (TKD) dengan menggunakan sistem seleksi Computer Assisted Test (CAT). Namun beberapa instansi menerapkan saringan kedua dengan melakukan Tes Kompetensi Bidang (TKB) baik dengan tes psikologi, tes wawancara maupun tes tertulis.</p>
                            <p align="justify">Pelaksanaan tes pelamar umum tidak dipungut biaya apapun dari mulai pendaftaran online, verifikasi berkas, pelaksanaan ujian hingga pengumuman kelulusan.</p>
              <article class="content-box">
            	<div style="width:850px;height:550px;line-height:3em;overflow:scroll;padding:0px; background-color:#E6E6E6">			
		<ol style="list-style-type: decimal;padding-left: 20px;	line-height: 1.54em; font-size:13px">
		<h3>1. PERSYARATAN PENDAFTARAN</h3>
              <p>Kementerian/Lembaga non Kementerian, Pemerintah Daerah Propinsi/Kabupaten/Kota tertentu, membuka kesempatan penerimaan Calon Pegawai Negeri Sipil (CPNS) Tahun 2014 untuk mengisi lowongan formasi dengan kualifikasi pendidikan tertentu. Seluruh calon peserta test CPNS 2014 diharapkan melakukan pendaftaran secara online dengan ketentuan sebagai berikut;</p>
              <p><b>UMUM</b></p>
              <ul style="list-style-type: decimal;padding-left: 20px;	line-height: 1.54em;">
                <li> Warga Negara Indonesia yang memiliki kualifikasi pendidikan (jenjang dan jurusan) sesuai dengan formasi yang dibutuhkan;</li>
                <li>Pria dan Wanita dengan usia minimal 18 (delapan belas) tahun dan maksimal 35 (tiga puluh lima) tahun pada tanggal 01 September 2014. Catt : usia maksimal secara umum adalah 35 tahun, namun setiap instansi mempunyai kewenangan untuk menetapkan batasan usia maksimal yang akan diterima;</li>
                <li>3.	Berijazah, lulusan Perguruan Tinggi Negeri atau Perguruan Tinggi Swasta yang telah terakredirasi oleh Badan Akreditasi Nasional Perguruan Tinggi (BAN-PT) minimal C atau Perguruan Tinggi Luar Negeri yang telah mendapat pengesahan dari Kementerian Pendidikan dan Kebudayaan. Surat Keterangan Lulus / Ijazah sementara tidak berlaku;</li>
                <li>Sehat jasmani dan rohani; </li>
                <li>Tidak terikat hubungan kerja/ikatan dinas dengan Instansi Pemerintah atau Badan Swasta lainnya;</li>
                <li>Tidak terlibat langsung atau tidak langsung dalam kegiatan organisasi yang bertentangan dengan Pancasila, Undang-Undang Dasar 1945, Negara dan Pemerintah (PUNP);</li>
                <li>Tidak pernah tersangkut perkara pidana atau kasus narkoba;</li>
                <li>Tidak pernah diberhentikan dengan hormat tidak atas permintaan sendiri atau tidak dengan hormat sebagai Pegawai Negeri Sipil atau diberhentikan tidak dengan hormat sebagai pegawai swasta;</li>
                <li>Calon pelamar di seluruh Indonesia dapat melakukan pendaftaran secara online ke alamat website Panselnas di: <b>https://panselnas.menpan.go.id</b> untuk mendapatkan username dan password yang kemudian digunakan untuk log in ke SSCN di: <b>http://sscn.bkn.go.id/</b>;</li>
                <li>
                Setelah mengisi form registrasi melalui website pada SSCN maka pelamar dapat mencetak tanda bukti pendaftaran.
                <ul style="list-style-type: lower-alpha;padding-left: 20px;	line-height: 1.54em;">
                  <li>Calon Pelamar Instansi Pemerintah Pusat Kementerian/Lembaga/Badan:</li>
                  <ul style="list-style-type: lower-roman;padding-left: 20px;	line-height: 1.54em;">
                    <li><i>Calon Pelamar Formasi Pusat:</i></li>
                    <ul style="list-style-type: square;padding-left: 20px;	line-height: 1.54em;">
                      <li>Tanda Bukti pendaftaran dan dokumen lamaran lengkap dikirim ke panitia penerimaan CPNS pada Biro Kepegawaian kantor pusat instansi yang dilamar;</li>
                    </ul>
                    <li><i>Calon Pelamar Formasi Kantor Wilayah</i></li>
                    <ul style="list-style-type: square;padding-left: 20px;	line-height: 1.54em;">
                      <li>Tanda Bukti pendaftaran dan dokumen lamaran lengkap dikirim ke panitia penerimaan CPNS pada Bagian Kepegawaian kantor wilayah instansi yang dilamar</li>
                    </ul>
                  </ul>
                  <li>Calon Pelamar Instansi Pemerintah Daerah Propinsi/Kota/Kabupaten</li>
                  <ul style="list-style-type: square;padding-left: 20px;	line-height: 1.54em;">
                    <li>Tanda Bukti pendaftaran dan dokumen lamaran lengkap dikirim ke panitia penerimaan CPNS pada Badan Kepegawaian Daerah instansi Pemerintah Daerah yang dilamar</li>
                  </ul>
                  </li>
                </ul>
                <li>Pendaftaran secara online dimulai pada tanggal 20 Agustus - 3 September 2014 jam 24.00 waktu setempat.</li>
		  <li>Verifikasi berkas lamaran lengkap di masing-masing instansi dimulai pada tanggal 23 Agustus - 5 September 2014, jam 08.00 - 16.00 waktu setempat.</li>
	         <li>Pelamar hanya dapat mendaftar pada 1(satu) instansi.</li>	
                <li>Lowongan Formasi dan kualifikasi pendidikan serta persyaratan tambahan lainnya dapat dilihat pada panduan pendaftaran masing-masing jalur penerimaan setiap instansi pada link <b>Pengumuman Pendaftaran.</b></li>
              </ul>
			  <br/>
              <p><b>KHUSUS :</b></p>
              <ul style="list-style-type: decimal;padding-left: 20px;	line-height: 1.54em;">
              <li>Membuat surat lamaran yang dibuat dengan tulisan tangan sendiri, tinta hitam dengan melampirkan :</li>
              <ul style="list-style-type: lower-alpha;padding-left: 20px;	line-height: 1.54em;">
                <li>Daftar Riwayat Hidup (DRH) meliputi pendidikan formal dan informal serta pengalaman. Khusus pendidikan formal harus dituliskan dari mulai Sekolah Dasar sampai dengan pendidikan terakhir, nama lembaga pendidikan, tahun lulus pendidikan dan nilai rata-rata ijazah atau IPK;</li>
                <li>Fotocopy Ijazah / STTB  terakhir yang disahkan dan dilegalisir oleh pejabat yang berwenang : Rektor/Dekan/Ketua/Direktur dengan stempel basah (bukan stempel foto copy);</li>
                <li>Transkrip nilai akademik yang dilegalisir oleh Dekan Fakultas bagi Perguruan Tinggi Swasta sesuai peraturan pemerintah;</li>
                <li>Fotocopy Kartu Tanda Penduduk (KTP) yang masih berlaku pada saat pendaftaran 1 (satu) lembar. </li>
		  <li>Pas foto berwarna 4x6 : 3 lembar</li>
              </ul>
			  </ul>

			 <br/>
			<h3>2. PANDUAN PENDAFTARAN</h3>
			<p>Proses pendaftaran CALON PEGAWAI NEGERI SIPIL (CPNS) Tahun Anggaran 2014 terdiri dari :</p>
			<ul style="list-style-type: decimal;padding-left: 20px;	line-height: 1.54em;">
				<li>Masuk ke portal Panselnas di <b>http://panselnas.menpan.go.id</b> untuk melakukan pendaftaran dengan memasukkan Nomor Induk Kependudukan (NIK), tanggal lahir, instansi yang dipilih dan e-mail.</li>
				<li>Log in ke http://sscn.bkn.go.id dengan mengisi username & password yang sudah dikirim via e-mail.</li> 
					<ul style="list-style-type: lower-alpha;padding-left: 20px;	line-height: 1.54em;">
					<li>Klik DAFTAR untuk mengisi formulir pendaftaran.</li>
					<li>Isi formulir registrasi yang muncul.</li>
					<li>Pastikan isian data pribadi pada form registrasi sesuai dengan KTP.</li>
					<li>Pastikan isian jabatan sesuai dengan kualifikasi pendidikan.</li>
					<li>Klik check box mengenai kebenaran data yang telah dimasukkan</li>
					<li>Masukkan kode captcha yang tertera.</li>
					<li>Klik tombol Daftar untuk memproses pendaftaran dan mendapatkan nomor registrasi.</li>
					<li>Cetak tanda bukti pendaftaran.</li>
					<ul style="list-style-type: square;padding-left: 20px;	line-height: 1.54em;">
						<li>Tanda bukti pendaftaran berupa file dalam format PDF yang dapat disimpan di flashdisk dan dapat dicetak ditempat lain.</li>
						<li>Tanda bukti pendaftaran yang sudah dicetak agar dibawa pada saat verifikasi dokumen lamaran di panitia penerimaan CPNS masing-masing instansi.</li>
					</ul>
				</ul>
				<li>Melakukan <b>verifikasi dokumen</b></li>
				<ul style="list-style-type: square;padding-left: 20px;	line-height: 1.54em;">
					<li>Verifikasi dokumen dilakukan oleh Panitia Penerimaan CPNS yang ada pada masing-masing instansi yang dilamar.</li>
					<li>Instansi menentukan apakah dokumen lamaran dibawa oleh pelamar yang bersangkutan atau dikirim melalui Pos Indonesia.</li> 
					<li>Verifikasi dokumen tersebut untuk memastikan kesesuaian data pelamar pada isian formulir pendaftaran dan pilihan formasi serta pendidikan apakah telah terisi dengan data yang benar.</li>
				</ul>
				<li>Mengikuti ujian seleksi pada waktu yang telah ditentukan.</li>
				<li>Setelah mengikuti ujian seleksi masuk, Anda dapat melihat hasil seleksi pada tanggal pengumuman.</li>
			</ul>
			<br/>
			<h3>3. PELAKSANAAN UJIAN</h3>
				<ul style="list-style-type: decimal;padding-left: 20px;	line-height: 1.54em;">
					<li>Test dengan menggunakan sistem Computer Assisted Test (CAT)</li>
					<ul style="list-style-type: lower-alpha;padding-left: 20px;	line-height: 1.54em;">
						<li>Pra Test</li>
						<ul style="list-style-type: lower-roman;padding-left: 20px;	line-height: 1.54em;">
						    <li>Peserta ujian agar sudah mengetahui kapan jadwal (tanggal dan jam) serta lokasi ujiannya masing-masing.</li>
							<li>Peserta ujian agar datang minimal 30 menit sebelum ujian dimulai.</li>
						</ul>
						<li>Pelaksanaan Test:</li>
						<ul style="list-style-type: lower-roman;padding-left: 20px;	line-height: 1.54em;">
							<li>5 menit persiapan peserta memasuki ruangan test dengan posisi duduk yang tidak ditentukan oleh panitia.</li>
							<li>15 menit pengarahan tata cara ujian menggunakan sistem CAT oleh panitia pelaksanaan ujian.</li>
							<li>90 menit pelaksanaan ujian.</li>
							<li>Dilarang membawa peralatan elektronis ke dalam ruangan test.</li>
							<li>Peserta hanya diperbolehkan membawa kartu peserta ujian ke dalam ruangan ujian.</li>
							<li>Jika ada peserta yang diketahui melakukan tindakan curang yang merugikan peserta lain maka akan di diskualifikasikan dan dinyatakan gugur sebagai peserta ujian.</li>
						</ul>
					</ul>						
					</ul>
				</ul>
			<br/>
			<h3>4. MATERI UJIAN</h3>
			<ul style="list-style-type: decimal;padding-left: 20px;	line-height: 1.54em;">
			<li>Tes Kompetensi Dasar (TKD)</li>
				<ul style="list-style-type: lower-alpha;padding-left: 20px;	line-height: 1.54em;">
					<li>Tes Wawasan Kebangsaan</li>
					<li>Tes Intelegensia Umum</li>
					<li>Tes Karakteristik Pribadi</li>
				</ul>
			<li>Tes Kompetensi Bidang (TKB)</li>
				<p>Bagi peserta yang memenuhi passing grade dan dinyatakan lulus Tes Kompetensi Dasar (TKD)  selanjutnya berhak mengikuti Tes Kompetensi Bidang (TKB). Namun ada beberapa instansi dan jurusan tertentu yang tidak memerlukan tes TKB, artinya setelah lulus TKD maka berarti lulus ujian CPNS.</p>
			</ul>
					
			</ol>
		  </article>
			   </div>
				  </div>
          	
        </div>
      </section>
        <!-- Footer -->
        <footer>
            <div class="copyright style2">Hak Cipta  ©  2014 Badan Kepegawaian Negara. Semua Hak Dilindungi.</a></div>
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