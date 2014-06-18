package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.dao.DtPersyaratanDao;
import id.go.bkn.sscn.persistence.entities.DtPersyaratan;
import id.go.bkn.sscn.services.PersyaratanService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author efraim
 * 
 */
@Service(value = "persyaratanServiceImpl")
public class PersyaratanServiceImpl implements PersyaratanService {

	@Inject
	private DtPersyaratanDao persyaratanDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sscn.services.PersyaratanService#simpanPersyaratan(org.sscn.persistence
	 * .entities.DtPersyaratan)
	 */
	@Override
	@Transactional(readOnly = false)
	public DtPersyaratan simpanPersyaratan(DtPersyaratan persyaratan) {
		DtPersyaratan newInstance = null;
		if (persyaratan != null) {
			if (persyaratan.getId() == null) {
				newInstance = persyaratanDao.insert(persyaratan);
			} else {
				newInstance = persyaratanDao.update(persyaratan);
			}
		}
		return newInstance;
	}

	@Override
	@Transactional(readOnly = false)
	public boolean delete(DtPersyaratan syarat) {
		try {
			persyaratanDao.remove(syarat);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<DtPersyaratan> findByProperty(String name, Object value,
			List<QueryOrder> orders, int... idx) {
		return persyaratanDao.findByProperty(name, value, orders, idx);
	}
}
