package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.RefPendidikan;

import java.util.List;


public interface PendidikanService {

	List<RefPendidikan> findAllPendidikan(int... idx);

	List<RefPendidikan> findPendidikanByLikeName(String name);
	
	RefPendidikan findById(String id);

}
