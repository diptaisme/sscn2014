	$(document).ready(
			function() {
				$("#imgLoadingLokasi3").hide();
				$("#imgLoadingJabatan3").hide();
				$("#imgLoadingPendidikan3").hide();							
			});

	$(function() {
		$('#lokasi_kerja3').change(function() {
			//clear pendidikan
			var html = '<option value="">Pilih Pendidikan</option>';
			$('#pendidikan3').html(html);
			//clear jabatan
			html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan3').html(html);
			$("#imgLoadingPendidikan3").hide();
			$("#imgLoadingJabatan3").hide();
			getPendidikan3();
		});
		$('#pendidikan3').change(function() {
			//clear jabatan
			var html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan3').html(html);
			$("#imgLoadingJabatan3").hide();
			getJabatan3();
		});
	});
	
	function getPendidikan3() {
		$("#imgLoadingPendidikan3").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja3').val();
		var url = 'cb_pendidikan_by_instansi_lokasi.do?instansi='
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
				$('#pendidikan3').html(html);

				html = '<option value="">Pilih Jabatan</option>';
				$('#jabatan3').html(html);

				$("#imgLoadingPendidikan3").hide();
				$("#imgLoadingJabatan3").hide();
			}
		});
	}

	function getJabatan3() {
		$("#imgLoadingJabatan3").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja3').val();
		var param3 = $('#pendidikan3').val();

		var url = 'cb_jabatan_by_instansi_lokasi_pendidikan.do?instansi='
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
				$('#jabatan3').html(html);
				$("#imgLoadingJabatan3").hide();
			}
		});
	}
