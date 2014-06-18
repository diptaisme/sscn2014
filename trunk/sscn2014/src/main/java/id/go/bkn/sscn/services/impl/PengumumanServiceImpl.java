package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.DtPengumumanDao;
import id.go.bkn.sscn.dao.RefInstansiDao;
import id.go.bkn.sscn.persistence.entities.DtPengumuman;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.services.PengumumanService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("PengumumanService")
public class PengumumanServiceImpl implements PengumumanService {
	@Inject
	private DtPengumumanDao dtPengumumanDao;
	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtPengumuman> getPengumuman(int... idx) {
		return dtPengumumanDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefInstansi> getInstansi(int... idx) {
		return refInstansiDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updatePengumuman(DtPengumuman dtPengumuman) {
		dtPengumumanDao.updatePengumuman(dtPengumuman);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public void insertPengumuman(DtPengumuman dtPengumuman) {
		dtPengumumanDao.insert(dtPengumuman);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<DtPengumuman> findByProperty(String name, String value, int...idx){
		return dtPengumumanDao.findByProperty(name,value,idx);
	}
}
