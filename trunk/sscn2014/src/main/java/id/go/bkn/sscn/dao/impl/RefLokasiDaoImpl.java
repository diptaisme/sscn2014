package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.RefLokasiDao;
import id.go.bkn.sscn.persistence.entities.RefLokasi;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("RefLokasiDao")
public class RefLokasiDaoImpl extends CoreDaoImpl<RefLokasi> implements
		RefLokasiDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public RefLokasiDaoImpl(SessionFactory sessionFactory) {
		super(RefLokasi.class, sessionFactory);
	}
}
