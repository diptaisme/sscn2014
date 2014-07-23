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
				$("#imgLoadingLokasi1").hide();
				$("#imgLoadingJabatan1").hide();
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
				
				$('#nilai_ipk').change(
						function() {
							var ipk = $(this).val();
							if (parseFloat(ipk) > 10.0) {
								$(this).val("");
								$(this).closest('.control-group').removeClass(
										'success').addClass('error');
							}
						});
				$('#dialogKonfirmasi').dialog({
					autoOpen : false,
					width : 600,
					width : 600,
					modal : true,
					resizable : false,
					buttons : {
						"Submit Form" : function() {
							if($("#formRegistrasi").valid()){
								document.formRegistrasi.submit();
							}else{
								alert("form tidak valid");
							}
							
						},
						"Cancel" : function() {
							$(this).dialog("close");
						}
					}
				});

				$('#tabs').tabs();
				getPendidikan();
			});

	function caps(element) {
		element.value = element.value.toUpperCase();
	}

	$(function() {
		//$("#datepickerTglLahir").datepicker();
		/*$("#datepickerTglLahir").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '1973:1995',
			dateFormat : "dd-mm-yy"
		});*/
	});

<!--jquery autocomplete-->

	$(function() {
		$('#lokasi_kerja1').change(function() {
			//clear jabatan
			var html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan1').html(html);
			$("#imgLoadingJabatan1").hide();
			getJabatan1();
		});
		$('#pendidikan').change(function() {
			//clear lokasi
			var html = '<option value="">Pilih Lokasi Kerja</option>';
			$('#lokasi_kerja1').html(html);
			$('#lokasi_kerja2').html(html);
			$('#lokasi_kerja3').html(html);
			$("#imgLoadingLokasi1").hide();
			$("#imgLoadingLokasi2").hide();
			$("#imgLoadingLokasi3").hide();
			//clear jabatan			
			html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan1').html(html);
			$('#jabatan2').html(html);
			$('#jabatan3').html(html);
			$("#imgLoadingJabatan1").hide();
			$("#imgLoadingJabatan2").hide();
			$("#imgLoadingJabatan3").hide();
			getLokasi();
		});
		/*$('#instansi').change(function() {
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
		});*/
	});

	function changeImage() {
		element = document.getElementById('cekbox')
		if (element.src.match("before")) {
			element.src = "/sscn2014/resources/images/after.gif";
			document.getElementById("btnKirimPendaftaran").disabled = false;
		} else {
			element.src = "/sscn2014/resources/images/before.gif";
			document.getElementById("btnKirimPendaftaran").disabled = true;
		}
	}
	function getLokasi() {
		if ($("#pendidikan option:selected").val() != ""){
			$("#imgLoadingLokasi1").show();
			$("#imgLoadingLokasi2").show();
			$("#imgLoadingLokasi3").show();
		}
		var param = $('#instansi').val();
		var param2 = $('#pendidikan').val();
		var url = 'cb_lokasi_by_instansi_pendidikan.html?instansi=' + param+ ' &pendidikan='+param2;
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
					$('#lokasi_kerja1').html(html);
					$('#lokasi_kerja2').html(html);
					$('#lokasi_kerja3').html(html);
					$("#imgLoadingLokasi1").hide();
					$("#imgLoadingLokasi2").hide();
					$("#imgLoadingLokasi3").hide();
					//clear jabatan
					html = '<option value="">Pilih Jabatan</option>';
					$('#jabatan1').html(html);
					$('#jabatan2').html(html);
					$('#jabatan3').html(html);
					$("#imgLoadingJabatan1").hide();
					$("#imgLoadingJabatan2").hide();
					$("#imgLoadingJabatan3").hide();
				});
			}
		});
	}
	function getPendidikan() {
		$("#imgLoadingPendidikan").show();
		var param = $('#instansi').val();
		var url = 'cb_pendidikan_by_instansi.html?instansi='
				+ param;

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

				html = '<option value="">Pilih Lokasi Kerja</option>';
				$('#lokasi_kerja1').html(html);
				$('#lokasi_kerja2').html(html);
				$('#lokasi_kerja3').html(html);				
				
				html = '<option value="">Pilih Jabatan</option>';
				$('#jabatan1').html(html);
				$('#jabatan2').html(html);
				$('#jabatan3').html(html);

				$("#imgLoadingPendidikan").hide();
				$("#imgLoadingLokasi1").hide();
				$("#imgLoadingLokasi2").hide();
				$("#imgLoadingLokasi3").hide();
				$("#imgLoadingJabatan1").hide();
				$("#imgLoadingJabatan2").hide();
				$("#imgLoadingJabatan3").hide();
			}
		});
	}

	function getJabatan1() {
		$("#imgLoadingJabatan1").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja1').val();
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
				$('#jabatan1').html(html);
				$("#imgLoadingJabatan1").hide();
			}
		});
	}
