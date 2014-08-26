package id.go.bkn.sscn.dao;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.persistence.entities.DtPengumuman;

import java.util.List;

public interface DtPengumumanDao extends CoreDao<DtPengumuman> {
	void updatePengumuman(DtPengumuman dtPengumuman);

	List<DtPengumuman> getPengumumanInstansiPusat();
	
	List<DtPengumuman> getPengumumanInstansiPropinsi();	
	
	List<DtPengumuman> getPengumumanInstansiKota() ;
	
	List<DtPengumuman> getPengumumanInstansiKabupaten();
}
