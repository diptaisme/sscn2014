package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.dao.DtVerifikasiNokDao;
import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.DtVerifikasiNok;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.services.VerfikasiService;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author efraim
 * 
 */
@Service(value = "verifikasiService")
public class VerfikasiServiceImpl implements VerfikasiService {

	@Inject
	private DtPendaftaranDao dtPendaftaranDao;

	@Inject
	private DtVerifikasiNokDao dtVerifikasiNokDao;

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Override
	@Transactional(readOnly = false)
	public boolean simpanHasilVerifikasi(DtPendaftaran pendaftaran,
			List<DtVerifikasiNok> verNoks) {
		try {
			if (verNoks.size() > 0) {
				// jika gagal
				pendaftaran.setStatus("0");
				pendaftaran.setTglValidate(new Date());
				dtPendaftaranDao.update(pendaftaran);
				Iterator<DtVerifikasiNok> iterator = verNoks.iterator();
				while (iterator.hasNext()) {
					DtVerifikasiNok dtVer = iterator.next();
					dtVerifikasiNokDao.insert(dtVer);
				}
			} else {
				String noPeserta = getNoUrutPeserta(pendaftaran);
				pendaftaran.setNoPeserta(noPeserta);
				pendaftaran.setStatus("1");
				pendaftaran.setTglValidate(new Date());
				dtPendaftaranDao.update(pendaftaran);
			}
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<DtVerifikasiNok> findVerifikasiNoksByPendaftar(
			DtPendaftaran pendaftar) {
		return dtVerifikasiNokDao.findByProperty("pendaftar", pendaftar, null);
	}

	@Override
	public String getNoUrutPeserta(DtPendaftaran pendaftar) {

		String instansi = pendaftar.getFormasi().getRefInstansi().getKode();
		RefPendidikan pend = refPendidikanDao.findById(pendaftar
				.getPendidikan());
		String pendidikan = pend.getTingkat();

		String stringDigit = instansi + pendidikan.substring(0, 1);
		String nourut = dtPendaftaranDao.getnoUrutPendaftaran(stringDigit);
		if (nourut.contentEquals("")) {
			if (pendidikan.substring(0, 1).contentEquals("2")){
				nourut = "20001";
			} else {
				nourut = "50001";
			}
			
		} else {
			int x = Integer.parseInt(nourut) + 1;
			nourut = String.format("%05d", x);
		}

		Integer varMod = 9 - (Integer.parseInt(nourut) % 8);
		return stringDigit + nourut + varMod;
	}

}
