package id.go.bkn.sscn.dao.impl;

import java.util.List;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RefPendidikanDao")
public class RefPendidikanDaoImpl extends CoreDaoImpl<RefPendidikan> implements
		RefPendidikanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefPendidikanDaoImpl(SessionFactory sessionFactory) {
		super(RefPendidikan.class, sessionFactory);
	}
	public List<RefPendidikan> findPendidikanByInstansi(String instansi) {
		String sqlQuery = "select df.pendidikan from DtFormasi df where df.formasi.refInstansi.kode = :instansi " 
						+"ORDER BY df.pendidikan.nama";
		Query query = createQuery(sqlQuery);
		query.setString("instansi", instansi);
		return doQuery(query, null);
	}
}
