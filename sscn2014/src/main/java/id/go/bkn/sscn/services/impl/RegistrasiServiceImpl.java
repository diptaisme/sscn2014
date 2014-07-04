package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.dao.MFormasiDao;
import id.go.bkn.sscn.dao.RefInstansiDao;
import id.go.bkn.sscn.dao.RefLokasiDao;
import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.persistence.entities.DtFormasi;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.MFormasi;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.RefJabatan;
import id.go.bkn.sscn.persistence.entities.RefLokasi;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.RegistrasiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("RegistrasiService")
public class RegistrasiServiceImpl implements RegistrasiService {
	@Inject
	private DtPendaftaranDao dtPendaftaranDao;
	@Inject
	private RefInstansiDao refInstansiDao;
	@Inject
	private RefLokasiDao refLokasiDao;
	@Inject
	private MFormasiDao mFormasiDao;

	@Inject
	private RefPendidikanDao refPendidikanDao;

	@Transactional(readOnly = false)
	public DtPendaftaran insertNewRegistrasi(DtPendaftaran dtPendaftaran) {
		dtPendaftaranDao.insert(dtPendaftaran);
		return dtPendaftaran;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefInstansi> getInstansi(int maxRows, String startWith) {
		// return refInstansiDao.findLikeProperty("nama", startWith, new int[] {
		// 0, maxRows });
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("nama", startWith);
		properties.put("status", "1");
		// return refInstansiDao.findLikeMapOfProperties(properties, new int[] {
		// 0, maxRows });
		return refInstansiDao.findPrefixLikeMapOfProperties(properties, null,
				null, new int[] { 0, maxRows });
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefLokasi> getLokasi(String instansi) {
		List<MFormasi> listFormasi = mFormasiDao.findByProperty(
				"refInstansi.kode", instansi, null);
		List<RefLokasi> listLokasi = new ArrayList<RefLokasi>();

		for (MFormasi formasi : listFormasi) {
			listLokasi.add(formasi.getRefLokasi());
		}
		return listLokasi;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> getJabatan(String instansi, String lokasi) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("refInstansi.kode", instansi);
		map.put("refLokasi.kode", lokasi);

		List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(map,
				null, null);

		List<RefJabatan> listJabatan = new ArrayList<RefJabatan>();

		for (MFormasi formasi : listFormasi) {
			listJabatan.add(formasi.getRefJabatan());
		}
		return listJabatan;

	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> getPendidikan(String instansi, String lokasi,
			String jabatan) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("refInstansi.kode", instansi);
		map.put("refLokasi.kode", lokasi);
		map.put("refJabatan.kode", jabatan);

		List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(map,
				null, null);

		List<RefPendidikan> listPendidikan = new ArrayList<RefPendidikan>();

		MFormasi formasi = listFormasi.get(0);

		Iterator<DtFormasi> iterator = formasi.getDtFormasis().iterator();
		while (iterator.hasNext()) {
			DtFormasi dtFormasi = iterator.next();
			listPendidikan.add(dtFormasi.getPendidikan());
		}

		return listPendidikan;

	}

	@Override
	@Transactional(readOnly = false)
	public DtPendaftaran insertPendaftaran(HttpServletRequest request,
			TabelPendaftar pendaftar) {
		try {

			String noNik = request.getParameter("no_nik");

			String nama = request.getParameter("nama");
			String tempatLahir = request.getParameter("tempat_lahir");
			String strTglLahir = request.getParameter("datepickerTglLahir");
			Date tglLahir = new SimpleDateFormat("dd-MM-yyyy")
					.parse(strTglLahir);

			String alamat = request.getParameter("alamat");
			String kota = request.getParameter("kota");
			String propinsi = request.getParameter("propinsi");
			String kodePos = request.getParameter("kode_pos");
			String telpon = request.getParameter("telpon");
			String email = request.getParameter("email");

			String instansi = request.getParameter("instansi");
			String jabatan1 = request.getParameter("jabatan1");
			String lokasiKerja1 = request.getParameter("lokasi_kerja1");
			String pendidikan1 = request.getParameter("pendidikan1"); // kode yg
																		// disimpan
			// String pendidikan = refPendidikanDao
			// .findByProperty("kode", request.getParameter("pendidikan"),
			// null).get(0).getNama();

			String jabatan2 = request.getParameter("jabatan2");
			String lokasiKerja2 = request.getParameter("lokasi_kerja2");
			String pendidikan2 = request.getParameter("pendidikan2");
			if (lokasiKerja2 != null && !lokasiKerja2.equals("") && pendidikan2 != null && !pendidikan2.equals("") 
					&& jabatan2 != null && !jabatan2.equals("")) {

			}
			String jabatan3 = request.getParameter("jabatan3");
			String lokasiKerja3 = request.getParameter("lokasi_kerja3");
			String pendidikan3 = request.getParameter("pendidikan3");
			if (lokasiKerja3 != null && !lokasiKerja3.equals("") && pendidikan3 != null && !pendidikan3.equals("") 
					&& jabatan3 != null && !jabatan3.equals("")) {

			}
			MFormasi mFormasi1 = null; // formasi nanti, sudah
			HashMap<String, String> propertiesMap = new HashMap<String, String>();
			propertiesMap.put("refInstansi.kode", instansi);
			propertiesMap.put("refLokasi.kode", lokasiKerja1);
			propertiesMap.put("refJabatan.kode", jabatan1);
			List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(
					propertiesMap, null, null);
			if (listFormasi == null || listFormasi.size() == 0) {
				return null;
			} else {
				mFormasi1 = listFormasi.get(0);
			}

			String noIjazah = request.getParameter("no_ijazah");
			String jnsKelamin = request.getParameter("jenis_kelamin");
			String status = ""; // status
			String regStatus = ""; // regStatus

			String lembaga = request.getParameter("universitas"); // lembaga =
																	// universitas
			String akreditasi = request.getParameter("akreditasi");
			String nilaiIPK = request.getParameter("nilai_ipk");
			// memang tidak diisi
			Date tglTest = new Date();
			String lokasiTest = "";
			Date tglCreated = new Date();
			Date tglUpdated = new Date();
			String userValidate = "";
			Date tglValidate = new Date();
			String keterangan = "";

			String noPeserta = null; // noPeserta dibuatkan null

			String noRegister = generateNoRegistrasi();

			DtPendaftaran pendaftaran = new DtPendaftaran(mFormasi1, noNik,
					noRegister, nama, tempatLahir, tglLahir, jnsKelamin,
					alamat, kodePos, propinsi, kota, telpon, email,
					pendidikan1, lembaga, noIjazah, status, regStatus,
					noPeserta, tglTest, lokasiTest, tglCreated, tglUpdated,
					userValidate, tglValidate, keterangan, akreditasi, nilaiIPK);
			if (pendaftar != null) {
				pendaftaran.setTabelPendaftar(pendaftar);
			} else {
				return null;
			}
			pendaftaran = dtPendaftaranDao.insert(pendaftaran);
			return pendaftaran;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public DtPendaftaran getPendaftaranByNoRegistrasi(String noRegister) {
		List<DtPendaftaran> listPendaftarans = dtPendaftaranDao.findByProperty(
				"noRegister", noRegister, null);
		if (listPendaftarans.size() > 0) {
			return listPendaftarans.get(0);
		} else {
			return null;
		}
	}

	// tanpa tingkat pendidikan dulu
	// private String generateNoRegistrasi(String kodeInstansi, String
	// tingkatPendidikan, String kodeJabatan){
	private String generateNoRegistrasi() {
		Random r = new Random(System.nanoTime());
		long resut = 1000000000 + r.nextInt(2000000000);
		if (resut < 0) {
			resut = resut * -1;
		}
		Long finalResult = Long.valueOf(resut);
		return finalResult.toString();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefPendidikan> getPendidikan(String instansi, String lokasi) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("refInstansi.kode", instansi);
		map.put("refLokasi.kode", lokasi);

		List<MFormasi> listFormasi = mFormasiDao.findByMapOfProperties(map,
				null, null);
		HashMap<String, RefPendidikan> mapPendidikan = new HashMap<String, RefPendidikan>();

		for (MFormasi mFormasi : listFormasi) {
			Iterator<DtFormasi> iterator = mFormasi.getDtFormasis().iterator();
			while (iterator.hasNext()) {
				DtFormasi dtFormasi = iterator.next();
				mapPendidikan.put(dtFormasi.getPendidikan().getKode(),
						dtFormasi.getPendidikan());
			}
		}
		return new ArrayList<RefPendidikan>(mapPendidikan.values());
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<RefJabatan> getJabatan(String instansi, String lokasi,
			String pendidikan) {

		List<MFormasi> listFormasi = mFormasiDao
				.findByInstansiLokasiPendidikan(instansi, lokasi, pendidikan);

		List<RefJabatan> listJabatan = new ArrayList<RefJabatan>();

		for (MFormasi formasi : listFormasi) {
			listJabatan.add(formasi.getRefJabatan());
		}
		return listJabatan;
	}

}
