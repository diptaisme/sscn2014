package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.persistence.entities.RefLokasi;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface RegistrasiService {
	DtPendaftaran insertNewRegistrasi(DtPendaftaran dtPendaftaran);

	List<RefInstansi> getInstansi(int maxRows, String startWith);

	List<RefLokasi> getLokasi(String instansi);

	List<RefJabatan> getJabatan(String instansi, String lokasi);

	List<RefPendidikan> getPendidikan(String instansi, String lokasi,
			String jabatan);

	DtPendaftaran insertPendaftaran(HttpServletRequest request,
			TabelPendaftar pendaftar);

	//mungkin tidak akan terpakai lagi
	DtPendaftaran getPendaftaranByNoRegistrasi(String noRegister);

	List<RefPendidikan> getPendidikan(String instansi, String lokasi);

	List<RefJabatan> getJabatan(String instansi, String lokasi,
			String pendidikan);
	
	List<DtPendaftaran>  insertPendaftarans(HttpServletRequest request,
			TabelPendaftar pendaftar);
	
	DtPendaftaran getPendaftaranById(String id);
	
	List<DtPendaftaran> getPendaftaransByNoRegistrasi(String noRegister);
	
	boolean deletePendaftarans(List<DtPendaftaran> pendaftarans);
	
	TabelPendaftar updatePendaftar(TabelPendaftar pendaftar);
	
	List<DtPendaftaran> getPendaftaransByUser(Integer idUser);
	
	RefInstansi getInstansibyId(String id);
}
