package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.persistence.entities.view.DataPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.RekapanPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.StatInstansi;
import id.go.bkn.sscn.persistence.entities.view.StatInstansiJabatan;

import java.util.List;
import java.util.Map;


public interface DtPendaftaranDao extends CoreDao<DtPendaftaran> {

	String getnoUrutPendaftaran(String sebelasDigitPertama);

	List<DtPendaftaran> findByInstansi(RefInstansi instansi,
			final int... idxAndCount);
	
	List<DtPendaftaran> findByInstansiAndMap(RefInstansi instansi, Map<String, Object> map,
			final int... idxAndCount);

	Integer countByInstansi(RefInstansi refInstansi);
	
	Integer countByInstansiAndMap(RefInstansi refInstansi, Map<String, Object> map);

	List<DataPendaftaran> findDataPendaftaran(String kodeInstansi);
	
	List<DataPendaftaran> findDataPesertaTest(String kodeInstansi);
	
	List<StatInstansi> getStatistikPendaftaranInstansi();
	
	List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(String kodeInstansi) ;
	
	List<StatInstansi> getStatistikPendaftaranInstansi(
			String kodeInstansi) ;
	
	List<RekapanPendaftaran> getRekapanPendaftaranInstansi(
			String kodeInstansi) ;

}
