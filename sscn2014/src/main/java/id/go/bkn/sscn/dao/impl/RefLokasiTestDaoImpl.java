package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.RefLokasiTestDao;
import id.go.bkn.sscn.persistence.entities.RefLokasiTest;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RefLokasiTestDao")
public class RefLokasiTestDaoImpl extends CoreDaoImpl<RefLokasiTest> implements
		RefLokasiTestDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefLokasiTestDaoImpl(SessionFactory sessionFactory) {
		super(RefLokasiTest.class, sessionFactory);
	}

	public List<RefLokasiTest> findLokasiTestByInstansi(String instansi) {
		String sqlQuery = "select lokasiTest from RefLokasiTest lokasiTest where lokasiTest.instansi.kode = :instansi "
				+ "ORDER BY lokasiTest.nama";
		Query query = createQuery(sqlQuery);
		query.setString("instansi", instansi);
		return doQuery(query, null);
	}
}
