package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.MFormasiDao;
import id.go.bkn.sscn.persistence.entities.MFormasi;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("MFormasiDao")
public class MFormasiDaoImpl extends CoreDaoImpl<MFormasi> implements
		MFormasiDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public MFormasiDaoImpl(SessionFactory sessionFactory) {
		super(MFormasi.class, sessionFactory);
	}

	public List<MFormasi> findByInstansiLokasiPendidikan(String instansi,
			String lokasi, String pendidikan) {
		/*
		 * SELECT m.jabatan FROM m_formasi m INNER JOIN dt_formasi dt ON
		 * dt.formasi=m.id WHERE m.instansi = '4011' AND m.lokasi = '1234' AND
		 * dt.pendidikan = '2110303';
		 */

		StringBuilder sbFind = new StringBuilder(
				"SELECT model FROM MFormasi as model, DtFormasi as detail ");
		sbFind.append("WHERE model.id = detail.formasi.id ");
		sbFind.append("AND model.refInstansi.kode = :instansi ");
		sbFind.append("AND model.refLokasi.kode = :lokasi ");
		sbFind.append("AND detail.pendidikan.kode = :pendidikan ");

		Query query = createQuery(sbFind);
		query.setParameter("instansi", instansi);
		query.setParameter("lokasi", lokasi);
		query.setParameter("pendidikan", pendidikan);

		return doQuery(query, null);
	}
	
	public List<MFormasi> findByInstansiLokasiJabatan(String instansi,
			String lokasi, String jabatan) {
		/*
		 * SELECT m.jabatan FROM m_formasi m 
		 * WHERE m.instansi = '4011' AND m.lokasi = '1234' AND
		 * m.jabatan = '2110303';
		 */

		StringBuilder sbFind = new StringBuilder(
				"SELECT model FROM MFormasi as model ");
		sbFind.append("WHERE ");
		sbFind.append("model.refInstansi.kode = :instansi ");
		sbFind.append("AND model.refLokasi.kode = :lokasi ");
		sbFind.append("AND model.refJabatan.kode = :jabatan ");

		Query query = createQuery(sbFind);
		query.setParameter("instansi", instansi);
		query.setParameter("lokasi", lokasi);
		query.setParameter("jabatan", jabatan);

		return doQuery(query, null);
	}
	
	public List<MFormasi> findByInstansiPendidikan(String instansi,
			String pendidikan) {
		/*
		SELECT LOKASI FROM M_FORMASI f INNER JOIN DT_FORMASI df
		ON df.formasi = f.id
		WHERE df.pendidikan = '2110303' AND f.instansi = '4011';
		 */

		StringBuilder sbFind = new StringBuilder(
				"SELECT model FROM MFormasi as model, DtFormasi as detail ");
		sbFind.append("WHERE model.id = detail.formasi.id ");
		sbFind.append("AND model.refInstansi.kode = :instansi ");		
		sbFind.append("AND detail.pendidikan.kode = :pendidikan ");
		sbFind.append("ORDER BY model.refLokasi.nama ");
		
		Query query = createQuery(sbFind);
		query.setParameter("instansi", instansi);
		query.setParameter("pendidikan", pendidikan);

		return doQuery(query, null);
	}
}
