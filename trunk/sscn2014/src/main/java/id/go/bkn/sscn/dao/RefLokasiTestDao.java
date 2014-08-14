package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.RefLokasiTest;

import java.util.List;

public interface RefLokasiTestDao extends CoreDao<RefLokasiTest> {
	List<RefLokasiTest> findLokasiTestByInstansi(String instansi);
}
