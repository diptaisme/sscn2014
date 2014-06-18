package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.view.StatInstansi;
import id.go.bkn.sscn.persistence.entities.view.StatInstansiJabatan;

import java.util.List;


public interface StatistikService {

	List<StatInstansi> getStatistikPendaftaranInstansi();
	
	List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(String kodeInstansi);
	
	List<StatInstansi> getStatistikPendaftaranInstansi(String kodeInstansi);
}
