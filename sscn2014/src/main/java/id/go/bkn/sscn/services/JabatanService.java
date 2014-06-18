package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.RefJabatan;

import java.util.List;


public interface JabatanService {
	List<RefJabatan> findAllJabatan(int... idx);
	
	List<RefJabatan> findJabatanByLikeName(String name) ;
}
