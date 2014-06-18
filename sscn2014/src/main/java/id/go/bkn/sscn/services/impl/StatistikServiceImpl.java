package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.persistence.entities.view.StatInstansi;
import id.go.bkn.sscn.persistence.entities.view.StatInstansiJabatan;
import id.go.bkn.sscn.services.StatistikService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("StatistikService")
public class StatistikServiceImpl implements StatistikService {
	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansi> getStatistikPendaftaranInstansi() {		
		return dtPendaftaranDao.getStatistikPendaftaranInstansi();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(
			String kodeInstansi) {
		return dtPendaftaranDao.getStatistikJabatanPendaftaranInstansi(kodeInstansi);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<StatInstansi> getStatistikPendaftaranInstansi(
			String kodeInstansi) {
		return dtPendaftaranDao.getStatistikPendaftaranInstansi(kodeInstansi);
	}

}
