package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtVerifikasiNokDao;
import id.go.bkn.sscn.persistence.entities.DtVerifikasiNok;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("DtVerifikasiNokDao")
public class DtVerifikasiNokDaoImpl extends CoreDaoImpl<DtVerifikasiNok> implements DtVerifikasiNokDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public DtVerifikasiNokDaoImpl(SessionFactory sessionFactory) {
		super(DtVerifikasiNok.class, sessionFactory);
	}
}
