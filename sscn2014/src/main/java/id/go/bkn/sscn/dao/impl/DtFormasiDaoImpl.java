package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtFormasiDao;
import id.go.bkn.sscn.persistence.entities.DtFormasi;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DtFormasiDao")
public class DtFormasiDaoImpl extends CoreDaoImpl<DtFormasi> implements DtFormasiDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtFormasiDaoImpl(SessionFactory sessionFactory) {
		super(DtFormasi.class, sessionFactory);
	}
}
