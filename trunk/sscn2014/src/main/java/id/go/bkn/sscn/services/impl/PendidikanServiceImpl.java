package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.services.PendidikanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("PendidikanService")
public class PendidikanServiceImpl implements PendidikanService {

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> findAllPendidikan(int... idx) {
		return refPendidikanDao.findAll(idx);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> findPendidikanByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefPendidikan> pendidikans = refPendidikanDao
				.findPrefixLikeMapOfProperties(map, null, null, null);
		return pendidikans;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public RefPendidikan findById(String id) {
		return refPendidikanDao.findById(id);
	}
}
