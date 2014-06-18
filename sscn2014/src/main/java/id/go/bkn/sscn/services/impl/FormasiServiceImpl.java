package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.DtFormasiDao;
import id.go.bkn.sscn.dao.MFormasiDao;
import id.go.bkn.sscn.persistence.entities.DtFormasi;
import id.go.bkn.sscn.persistence.entities.MFormasi;
import id.go.bkn.sscn.services.FormasiService;

import java.util.Iterator;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("FormasiService")
public class FormasiServiceImpl implements FormasiService {

	@Inject
	private MFormasiDao formasiDao;
	@Inject
	private DtFormasiDao dtFormasiDao;

	@Override
	@Transactional(readOnly = false)
	public boolean deleteFormasi(int id) {
		try {
			MFormasi instance = formasiDao.findUniqueByProperty("id", id, null, null);
			Iterator<DtFormasi> iterator = instance.getDtFormasis().iterator();
			while (iterator.hasNext()) {
				DtFormasi detFormasi = iterator.next();
				dtFormasiDao.remove(detFormasi);
			}
			formasiDao.remove(instance);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	
	@Override
	@Transactional(readOnly = false)
	public MFormasi insertFormasi(MFormasi formasi) {
		try {
			formasiDao.insert(formasi);			
			Iterator<DtFormasi> iterator = formasi.getDtFormasis().iterator();
			while (iterator.hasNext()) {
				DtFormasi detFormasi = iterator.next();				
				dtFormasiDao.insert(detFormasi);
			}			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return formasi;
	}
	
	@Override
	@Transactional(readOnly = false, propagation= Propagation.SUPPORTS)
	public MFormasi updateFormasi(MFormasi formasi, Set<DtFormasi> dtformasi) {
		try {
						
			// delete all dtFormasis
			Iterator<DtFormasi> iteratOld = formasi.getDtFormasis().iterator();
			while (iteratOld.hasNext()) {
				DtFormasi detFormasiOld = iteratOld.next();
				dtFormasiDao.remove(detFormasiOld);
			}
			
			Iterator<DtFormasi> iterator = dtformasi.iterator();
			formasi.setDtFormasis(dtformasi);
			while (iterator.hasNext()) {
				DtFormasi detFormasi = iterator.next();
				dtFormasiDao.insert(detFormasi);
			}			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return formasi;
	}
}
