package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.RefPendidikanDao;
import id.go.bkn.sscn.persistence.entities.RefPendidikan;

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
}
