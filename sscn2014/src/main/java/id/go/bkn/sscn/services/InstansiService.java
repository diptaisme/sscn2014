package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.RefInstansi;

import java.util.List;


public interface InstansiService {
	List<RefInstansi> findAllInstansi(int... idx);

	List<RefInstansi> findInstansiByLikeName(String name);
}
