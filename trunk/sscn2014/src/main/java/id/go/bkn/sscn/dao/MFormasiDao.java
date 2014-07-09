package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.MFormasi;

import java.util.List;


public interface MFormasiDao extends CoreDao<MFormasi> {
	List<MFormasi> findByInstansiLokasiPendidikan(String instansi,
			String lokasi, String pendidikan);
	
	List<MFormasi> findByInstansiLokasiJabatan(String instansi,
			String lokasi, String jabatan);
}
