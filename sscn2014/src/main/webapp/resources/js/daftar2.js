	$(document).ready(
			function() {
				$("#imgLoadingLokasi2").hide();
				$("#imgLoadingJabatan2").hide();
				//$("#imgLoadingPendidikan").hide();											
			});

	$(function() {
		$('#lokasi_kerja2').change(function() {			
			//clear jabatan
			var html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan2').html(html);			
			$("#imgLoadingJabatan2").hide();
			getJabatan2();
		});
		/*$('#pendidikan').change(function() {
			//clear jabatan
			var html = '<option value="">Pilih Jabatan</option>';
			$('#jabatan2').html(html);
			$("#imgLoadingJabatan2").hide();
			getJabatan2();
		});*/
	});	
	
	/*function getPendidikan2() {
		$("#imgLoadingPendidikan2").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja2').val();
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
				$('#pendidikan2').html(html);

				html = '<option value="">Pilih Jabatan</option>';
				$('#jabatan2').html(html);

				$("#imgLoadingPendidikan2").hide();
				$("#imgLoadingJabatan2").hide();
			}
		});
	}
*/
	function getJabatan2() {
		$("#imgLoadingJabatan2").show();
		var param = $('#instansi').val();
		var param2 = $('#lokasi_kerja2').val();
		var param3 = $('#pendidikan').val();

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
				$('#jabatan2').html(html);
				$("#imgLoadingJabatan2").hide();
			}
		});
	}
