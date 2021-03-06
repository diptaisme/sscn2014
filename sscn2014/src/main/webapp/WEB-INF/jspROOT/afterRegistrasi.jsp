
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userLogin" value="${sessionScope.userLogin}" />
<c:choose>
    <c:when test="${sessionScope.userLogin == null}">
        <c:redirect url="page?page=login" />
    </c:when>
</c:choose>	
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Seleksi CPNS 2014</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

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
<script src="/resources/js/jquery-1.7.1.min.js"></script>    
	<script type="text/javascript">
		$(document).ready(	
			function() {
				$(this).bind('contextmenu',function(e){
							e.preventDefault();
						});
				});
	</script>		
<script>
function kembali(){
	window.location.href = 'http://sscn.bkn.go.id';
}
</script>
<style type="text/css">
<!--
body {
	background-image: url(/resources/images/bg.png);
	background-repeat: repeat;
}
-->
</style></head>
<body id="page1">
<header>   
  <h1 class="logo"><a href="index.html">SSCN 2014</a></h1>
</header>
<p align="center">
		Anda sudah mendaftarkan untuk mengikuti Seleksi CPNS di posisi yang
		anda lamar. <br> Silahkan melakukan Cetak Nomor Pendaftaran pada
		tombol dibawah ini.	</p>
	<p align="center">
	<form action="report" method="post" target="_blank"
		name="formCetakRegistrasi" id="formCetakRegistrasi">
		<div align="center">
		  <input type="hidden" name="idRegistrasi" value="${idRegistrasi}" /> 
		  <input
			type="hidden" name="formID" value="32063786011852" /> 
		  <input
			type="hidden" name="typeReport" value="rptRegistrasi" /> 
		  <input
			type="submit" value="Cetak Nomor Pendaftaran"
			class="form-submit-button style2" title = "Cetak Nomor Pendaftaran"/> 
		  <input type="button" onClick="kembali()"
			name="btnKembali" value="Kembali" class="form-submit-button style2" />
</form>  
<div>		
		<p align="center" class="lf">&nbsp;</p>
		<p align="center" class="lf">Hak Cipta &copy; 2014 Badan Kepegawaian Negara.
		  Semua Hak Dilindungi.</p>
		<p align="center" class="rf">
			Website : <a href="http://www.bkn.go.id/">www.bkn.go.id</a>		</p>
</div>
</body>
</html>
