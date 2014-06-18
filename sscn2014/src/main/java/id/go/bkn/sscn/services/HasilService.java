package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtHasil;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.DtPeserta;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.persistence.entities.RefLokasi;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


public interface HasilService {
	DtHasil getDtHasil(DtPeserta peserta);
	
	DtPeserta getDtPesertaByNoPeserta(String noPeserta);
	
	DtPendaftaran insertNewRegistrasi(DtPendaftaran dtPendaftaran);

	List<RefInstansi> getInstansi(int maxRows, String startWith);

	List<RefLokasi> getLokasi(String instansi);

	List<RefJabatan> getJabatan(String instansi, String lokasi);

	List<RefPendidikan> getPendidikan(String instansi, String lokasi,
			String jabatan);

	DtPendaftaran insertPendaftaran(HttpServletRequest request);
	
	DtPendaftaran getPendaftaranByNoRegistrasi(String noRegister);
}
