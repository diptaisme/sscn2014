package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;

import java.util.List;


public interface RefPendidikanDao extends CoreDao<RefPendidikan> {
	List<RefPendidikan> findPendidikanByInstansi(String instansi);	
}
