package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.DtPengumuman;


public interface DtPengumumanDao extends CoreDao<DtPengumuman> {
	void updatePengumuman(DtPengumuman dtPengumuman);
}
