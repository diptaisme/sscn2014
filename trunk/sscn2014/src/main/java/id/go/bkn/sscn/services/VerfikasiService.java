package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.DtVerifikasiNok;

import java.util.List;


/**
 * @author efraim
 * 
 */
public interface VerfikasiService {

	boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran, List<DtVerifikasiNok> verNok);

	List<DtVerifikasiNok> findVerifikasiNoksByPendaftar(DtPendaftaran pendaftarId);

	String getNoUrutPeserta(DtPendaftaran pendaftar);
}
