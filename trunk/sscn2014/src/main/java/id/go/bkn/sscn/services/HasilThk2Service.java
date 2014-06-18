package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.HasilThk2;
import id.go.bkn.sscn.persistence.entities.InstansiThk2;
import id.go.bkn.sscn.persistence.entities.PropThk2;
import id.go.bkn.sscn.persistence.entities.RefJabatan;

import java.util.List;


public interface HasilThk2Service {
	List<PropThk2> findProvinsiByJenis(String jenis);
	List<InstansiThk2> findInstansiKerjaThk2ByProvinsi(String provinsi);
	List<HasilThk2> findHasilThk2ByInstansi(String instansi, String order, final int... rowStartIdxAndCount);
	Integer countHasilThk2ByInstansi(String instansi);
	List<HasilThk2> findHasilThk2ByNoTest(String noTest);
	List<HasilThk2> findHasilThk2ByInstansiNoTest(String noTest, String instansiId);
}
