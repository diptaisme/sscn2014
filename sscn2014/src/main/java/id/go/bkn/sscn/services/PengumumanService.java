package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtPengumuman;
import id.go.bkn.sscn.persistence.entities.RefInstansi;

import java.util.List;

public interface PengumumanService {
	List<DtPengumuman> getPengumuman(int... idx);

	List<RefInstansi> getInstansi(int... idx);

	void updatePengumuman(DtPengumuman dtPengumuman);

	void insertPengumuman(DtPengumuman dtPengumuman);

	List<DtPengumuman> findByProperty(String name, String value, int... idx);

	// get pengumuman instansi pusat
	List<DtPengumuman> getPengumumanInstansiPusat();

	// get pengumuman instansi propinsi
	List<DtPengumuman> getPengumumanInstansiPropinsi();

	// get pengumuman instansi kota
	List<DtPengumuman> getPengumumanInstansiKota();

	// get pengumuman instansi pusat
	List<DtPengumuman> getPengumumanInstansiKabupaten();
}
