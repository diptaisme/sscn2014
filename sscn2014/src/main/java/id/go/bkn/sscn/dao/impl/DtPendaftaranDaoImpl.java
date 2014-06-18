package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.view.DataPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.RekapanPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.StatInstansi;
import id.go.bkn.sscn.persistence.entities.view.StatInstansiJabatan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DtPendaftaranDao")
public class DtPendaftaranDaoImpl extends CoreDaoImpl<DtPendaftaran> implements
		DtPendaftaranDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtPendaftaranDaoImpl(SessionFactory sessionFactory) {
		super(DtPendaftaran.class, sessionFactory);
	}

	public List<DtPendaftaran> findByInstansi(RefInstansi instansi,
			int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());

		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.formasi.refInstansi.kode = :refInstansiId) ");
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase);

		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", instansi.getKode());
		return doQuery(query, idxAndCount);
	}

	public String getnoUrutPendaftaran(String limaDigitPertama) {
		StringBuilder sqlText = new StringBuilder(
				"select max(convert(substr(NO_PESERTA,6,5),unsigned integer)) from dt_pendaftaran where NO_PESERTA LIKE '"
						+ limaDigitPertama + "%'");

		SQLQuery query = createSqlQuery(sqlText);
		Object myResult = query.uniqueResult();

		if (myResult == null) {
			return "";
		} else {
			String result = String.valueOf(myResult);
			return result;
		}
	}

	public Integer countByInstansi(RefInstansi refInstansi) {
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.formasi.refInstansi.kode = :refInstansiId ");
		sbFind.append(wherePhrase);
		Query query = createQuery(sbFind);

		query.setParameter("refInstansiId", refInstansi.getKode());
		return Integer.valueOf(query.uniqueResult().toString());
	}

	public List<DtPendaftaran> findByInstansiAndMap(RefInstansi instansi,
			Map<String, Object> map, int... idxAndCount) {
		StringBuilder sbFind = new StringBuilder(getSelectFindQuery());
		final String innerJoinFetchPhrase = createLeftJoinFetchPhrase("model.formasi");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE (model.formasi.refInstansi.kode = :refInstansiId) ");
		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :"
					+ entry.getKey();
		}
		sbFind.append(innerJoinFetchPhrase).append(wherePhrase)
				.append(" AND " + whereMap);
		System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", instansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return doQuery(query, idxAndCount);
	}

	public Integer countByInstansiAndMap(RefInstansi refInstansi,
			Map<String, Object> map) {
		StringBuilder sbFind = new StringBuilder(
				"SELECT COUNT(model.id) FROM DtPendaftaran model ");
		StringBuilder wherePhrase = new StringBuilder(
				" WHERE model.formasi.refInstansi.kode = :refInstansiId ");

		String whereMap = "";
		Iterator<Map.Entry<String, Object>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, Object> entry = entries.next();
			whereMap = whereMap + "model." + entry.getKey() + " LIKE :"
					+ entry.getKey();
		}
		sbFind.append(wherePhrase).append(" AND " + whereMap);
		// System.out.println("Query : " + sbFind);
		Query query = createQuery(sbFind);
		query.setParameter("refInstansiId", refInstansi.getKode());

		Iterator<Map.Entry<String, Object>> entries2 = map.entrySet()
				.iterator();
		while (entries2.hasNext()) {
			Map.Entry<String, Object> entry = entries2.next();
			query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	public List<DataPendaftaran> findDataPendaftaran(String kodeInstansi) {
		List<DataPendaftaran> result = new ArrayList<DataPendaftaran>();
		final String sql = "SELECT NO_NIK, NO_REGISTER, NO_PESERTA, NAMA, TMP_LAHIR, DATE_FORMAT(TGL_LAHIR, '%d-%m-%y'), JNS_KELAMIN, ALAMAT, KODE_POS, PROPINSI, KOTA, TELPON, EMAIL, LEMBAGA, NO_IJAZAH, AKREDITASI, NILAI_IPK, INSTANSI, NAMA_INSTANSI as NI, LOKASI, NAMA_LOKASI as NL, JABATAN, NAMA_JABATAN as NJ, PENDIDIKAN, NAMA_PENDIDIKAN as NP, DATE_FORMAT(TGL_CREATED, '%d-%m-%y'), STATUS FROM data_01 "
				+ "WHERE INSTANSI= '"
				+ kodeInstansi
				+ "' order by TGL_CREATED, NO_PESERTA, NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					DataPendaftaran dataPendaftaran = new DataPendaftaran();
					int index = 0;
					dataPendaftaran.setNoNik(String.valueOf(obj[index++]));
					dataPendaftaran.setNoRegister(String.valueOf(obj[index++]));
					dataPendaftaran.setNoPeserta(String.valueOf(obj[index++]));
					dataPendaftaran.setNama(String.valueOf(obj[index++]));
					dataPendaftaran.setTmpLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setTglLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setJnsKelamin(String.valueOf(obj[index++]));
					dataPendaftaran.setAlamat(String.valueOf(obj[index++]));
					dataPendaftaran.setKodePos(String.valueOf(obj[index++]));
					dataPendaftaran.setPropinsi(String.valueOf(obj[index++]));
					dataPendaftaran.setKota(String.valueOf(obj[index++]));
					dataPendaftaran.setTelpon(String.valueOf(obj[index++]));
					dataPendaftaran.setEmail(String.valueOf(obj[index++]));
					dataPendaftaran.setAsalInstitusiPendidikan(String
							.valueOf(obj[index++]));
					dataPendaftaran.setNoIjazah(String.valueOf(obj[index++]));
					dataPendaftaran.setAkreditasi(String.valueOf(obj[index++]));
					dataPendaftaran.setNilaiIpk(String.valueOf(obj[index++]));
					dataPendaftaran.setInstansiKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setInstansiNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiKode(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiNama(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanKode(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanNama(String.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setTglCreated(String.valueOf(obj[index++]));
					dataPendaftaran.setStatus(String.valueOf(obj[index++]));

					result.add(dataPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public List<DataPendaftaran> findDataPesertaTest(String kodeInstansi) {
		List<DataPendaftaran> result = new ArrayList<DataPendaftaran>();
		final String sql = "SELECT NO_NIK, NO_REGISTER, NO_PESERTA, NAMA, TMP_LAHIR, DATE_FORMAT(TGL_LAHIR, '%d-%m-%y'), JNS_KELAMIN, ALAMAT, KODE_POS, PROPINSI, KOTA, TELPON, EMAIL, LEMBAGA, NO_IJAZAH, AKREDITASI, NILAI_IPK, INSTANSI, NAMA_INSTANSI as NI, LOKASI, NAMA_LOKASI as NL, JABATAN, NAMA_JABATAN as NJ, PENDIDIKAN, NAMA_PENDIDIKAN as NP, DATE_FORMAT(TGL_CREATED, '%d-%m-%y'), STATUS FROM data_01 "
				+ "WHERE INSTANSI= '"
				+ kodeInstansi
				+ "' and STATUS = '1' order by NO_PESERTA, NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					DataPendaftaran dataPendaftaran = new DataPendaftaran();
					int index = 0;
					dataPendaftaran.setNoNik(String.valueOf(obj[index++]));
					dataPendaftaran.setNoRegister(String.valueOf(obj[index++]));
					dataPendaftaran.setNoPeserta(String.valueOf(obj[index++]));
					dataPendaftaran.setNama(String.valueOf(obj[index++]));
					dataPendaftaran.setTmpLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setTglLahir(String.valueOf(obj[index++]));
					dataPendaftaran.setJnsKelamin(String.valueOf(obj[index++]));
					dataPendaftaran.setAlamat(String.valueOf(obj[index++]));
					dataPendaftaran.setKodePos(String.valueOf(obj[index++]));
					dataPendaftaran.setPropinsi(String.valueOf(obj[index++]));
					dataPendaftaran.setKota(String.valueOf(obj[index++]));
					dataPendaftaran.setTelpon(String.valueOf(obj[index++]));
					dataPendaftaran.setEmail(String.valueOf(obj[index++]));
					dataPendaftaran.setAsalInstitusiPendidikan(String
							.valueOf(obj[index++]));
					dataPendaftaran.setNoIjazah(String.valueOf(obj[index++]));
					dataPendaftaran.setAkreditasi(String.valueOf(obj[index++]));
					dataPendaftaran.setNilaiIpk(String.valueOf(obj[index++]));
					dataPendaftaran.setInstansiKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setInstansiNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setLokasiKode(String.valueOf(obj[index++]));
					dataPendaftaran.setLokasiNama(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanKode(String.valueOf(obj[index++]));
					dataPendaftaran
							.setJabatanNama(String.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanKode(String
							.valueOf(obj[index++]));
					dataPendaftaran.setPendidikanNama(String
							.valueOf(obj[index++]));
					dataPendaftaran.setTglCreated(String.valueOf(obj[index++]));
					dataPendaftaran.setStatus(String.valueOf(obj[index++]));

					result.add(dataPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public List<StatInstansi> getStatistikPendaftaranInstansi() {
		List<StatInstansi> result = new ArrayList<StatInstansi>();
		final String sql = "SELECT INSTANSI, Jml_Pendaftar, Jml_Lulus, Jml_tdkLulus from tab_stat_01 order by INSTANSI";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					StatInstansi statInstansi = new StatInstansi();
					int index = 0;
					statInstansi.setInstansi(String.valueOf(obj[index++]));
					statInstansi.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					statInstansi.setJumlahLulus(String.valueOf(obj[index++]));
					statInstansi.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					result.add(statInstansi);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public List<StatInstansiJabatan> getStatistikJabatanPendaftaranInstansi(
			String kodeInstansi) {
		List<StatInstansiJabatan> result = new ArrayList<StatInstansiJabatan>();
		final String sql = "SELECT INSTANSI, NAMA, Jml_Pendaftar, Jml_Lulus, Jml_tdkLulus from tab_stat_02 where INSTANSI='"
				+ kodeInstansi + "' order by NAMA";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					StatInstansiJabatan statInstansi = new StatInstansiJabatan();
					int index = 0;
					statInstansi.setInstansi(String.valueOf(obj[index++]));
					statInstansi.setJabatan(String.valueOf(obj[index++]));
					statInstansi.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					statInstansi.setJumlahLulus(String.valueOf(obj[index++]));
					statInstansi.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					result.add(statInstansi);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public List<StatInstansi> getStatistikPendaftaranInstansi(
			String kodeInstansi) {
		List<StatInstansi> result = new ArrayList<StatInstansi>();
		final String sql = "SELECT INSTANSI, Jml_Pendaftar, Jml_Lulus, Jml_tdkLulus from tab_stat_01 where INSTANSI='"
				+ kodeInstansi + "'";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			for (Object[] obj : res) {
				if (res != null) {
					StatInstansi statInstansi = new StatInstansi();
					int index = 0;
					statInstansi.setInstansi(String.valueOf(obj[index++]));
					statInstansi.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					statInstansi.setJumlahLulus(String.valueOf(obj[index++]));
					statInstansi.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					result.add(statInstansi);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public List<RekapanPendaftaran> getRekapanPendaftaranInstansi(
			String kodeInstansi) {
		List<RekapanPendaftaran> result = new ArrayList<RekapanPendaftaran>();
		final String sql = "SELECT lokasi, jabatan, pendidikan, TOTAL_PENDAFTAR, LULUS, TIDAK_LULUS, BELUM_VERIFIKASI from rekap_lok_jab_pend where kode='"
				+ kodeInstansi + "'";
		try {
			SQLQuery query = createSqlQuery(sql);
			List<Object[]> res = query.list();
			String lokasi = "";
			String jabatan = "";
			for (Object[] obj : res) {
				if (res != null) {
					RekapanPendaftaran rekapanPendaftaran = new RekapanPendaftaran();
					int index = 0;
					String lokasiTemp = String.valueOf(obj[index++]);
					String jabatanTemp = String.valueOf(obj[index++]);
					if (lokasi.equals(lokasiTemp)) {
						rekapanPendaftaran.setLokasi("");
					} else if (lokasi.equals("")) {
						rekapanPendaftaran.setLokasi(lokasiTemp);
						lokasi = lokasiTemp;
					} else {
						rekapanPendaftaran.setLokasi(lokasiTemp);
						lokasi = lokasiTemp;
					}
					if (jabatan.equals(jabatanTemp)) {
						rekapanPendaftaran.setJabatan("");
					} else if (jabatan.equals("")) {
						rekapanPendaftaran.setJabatan(jabatanTemp);
						jabatan = jabatanTemp;
					} else {
						rekapanPendaftaran.setJabatan(jabatanTemp);
						jabatan = jabatanTemp;
					}
					rekapanPendaftaran.setPendidikan(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahPendaftar(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahLulus(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahTidakLulus(String
							.valueOf(obj[index++]));
					rekapanPendaftaran.setJumlahBelumVerifikasi(String
							.valueOf(obj[index++]));
					result.add(rekapanPendaftaran);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
