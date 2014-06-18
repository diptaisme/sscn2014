package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.RefJabatanDao;
import id.go.bkn.sscn.persistence.entities.RefJabatan;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RefJabatanDao")
public class RefJabatanDaoImpl extends CoreDaoImpl<RefJabatan> implements
		RefJabatanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefJabatanDaoImpl(SessionFactory sessionFactory) {
		super(RefJabatan.class, sessionFactory);
	}
}
