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
}