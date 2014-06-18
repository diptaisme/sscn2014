package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtPengumumanDao;
import id.go.bkn.sscn.persistence.entities.DtPengumuman;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DtPengumumanDao")
public class DtPengumumanDaoImpl extends CoreDaoImpl<DtPengumuman> implements
        DtPengumumanDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtPengumumanDaoImpl(SessionFactory sessionFactory) {
		super(DtPengumuman.class, sessionFactory);
	}

	@Override
	public void updatePengumuman(DtPengumuman dtPengumuman) {
		update(dtPengumuman);
	}
}
