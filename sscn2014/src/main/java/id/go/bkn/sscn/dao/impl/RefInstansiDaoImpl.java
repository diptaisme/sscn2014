package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.RefInstansiDao;
import id.go.bkn.sscn.persistence.entities.RefInstansi;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RefInstansiDao")
public class RefInstansiDaoImpl extends CoreDaoImpl<RefInstansi> implements RefInstansiDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefInstansiDaoImpl(SessionFactory sessionFactory) {
		super(RefInstansi.class, sessionFactory);
	}
}
