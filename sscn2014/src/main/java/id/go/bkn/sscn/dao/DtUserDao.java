package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.DtUser;

import java.util.List;


public interface DtUserDao extends CoreDao<DtUser> {
	List<DtUser> findByInstansi(String instansi, int... idxAndCount);
	Integer countByInstansi(String refInstansi);
}
