package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.RefJabatanDao;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.services.JabatanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("JabatanService")
public class JabatanServiceImpl implements JabatanService {

	@Inject
	private RefJabatanDao refJabatanDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> findAllJabatan(int... idx) {
		return refJabatanDao.findAll(idx);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> findJabatanByLikeName(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nama", name);
		List<RefJabatan> jabatans = refJabatanDao.findPrefixLikeMapOfProperties(
				map, null, null, null);
		return jabatans;
	}
}
