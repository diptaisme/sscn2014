package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.RefInstansiDao;
import id.go.bkn.sscn.dao.RefLokasiDao;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefLokasi;
import id.go.bkn.sscn.services.LokasiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("LokasiService")
public class LokasiServiceImpl implements LokasiService {

	@Inject
	private RefLokasiDao refLokasiDao;

	@Inject
	private RefInstansiDao refInstansiDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasi> findAllLokasi(int... idx) {
		return refLokasiDao.findAll(idx);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasi> findAllLokasiByInstansi(String kodeInstansi,int... idx) {
		return refLokasiDao.findByProperty("refInstansi.kode", kodeInstansi, null, idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasi> findLokasiByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefLokasi> lokasis = refLokasiDao.findLikeMapOfProperties(map,
				null);
		return lokasis;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasi> findLokasiByLikeNameInstansi(String name, String instansiKd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		RefInstansi pinstansi = refInstansiDao.findById(instansiKd);
		map.put("refInstansi", pinstansi);
		List<RefLokasi> lokasis = refLokasiDao.findPrefixLikeMapOfProperties(map,
				null,null,null);
		return lokasis;
	}

	@Override
	@Transactional(readOnly = false)
	public RefLokasi save(String kode, String name, String instansiKd) {
		RefLokasi lokasi = null;
		try {
			RefInstansi instansi = refInstansiDao.findById(instansiKd);
			lokasi = new RefLokasi();
			lokasi.setKode(instansiKd+kode);
			lokasi.setNama(name);
			lokasi.setStatus("1");			
			lokasi.setRefInstansi(instansi);
			lokasi = refLokasiDao.insert(lokasi);
		} catch (Exception e) {
			e.printStackTrace();
			lokasi = null;
		}
		return lokasi;
	}

	@Override
	@Transactional(readOnly = false)
	public RefLokasi update(String kode, String name) {
		RefLokasi lokasi = null;
		try {
			lokasi = refLokasiDao.findByProperty("kode", kode, null).get(0);
			// lokasi.setKode(newKode);
			lokasi.setNama(name);
			// RefInstansi instansi = refInstansiDao.findById(instansiKd);
			// lokasi.setRefInstansi(instansi);
			lokasi = refLokasiDao.update(lokasi);
		} catch (Exception e) {
			e.printStackTrace();
			lokasi = null;
		}
		return lokasi;
	}

	@Override
	@Transactional(readOnly = false)
	public RefLokasi delete(String kode) {
		RefLokasi lokasi = null;
		try {
			lokasi = refLokasiDao.findByProperty("kode", kode, null).get(0);
			if (!refLokasiDao.remove(lokasi)) {
				lokasi = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			lokasi = null;
		}
		return lokasi;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RefLokasi findLokasiById(String kode) {
		RefLokasi refLokasi = null;
		try {
			refLokasi = refLokasiDao.findById(kode);
		} catch (Exception ex) {
			ex.printStackTrace();
			refLokasi = null;
		}
		return refLokasi;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Integer countFindAllLokasiByInstansi(String kodeInstansi) {
		// TODO Auto-generated method stub
		return refLokasiDao.countByProperty("refInstansi.kode", kodeInstansi);
	}

}
