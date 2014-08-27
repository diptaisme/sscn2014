package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.PeriodeDaftarDao;
import id.go.bkn.sscn.persistence.entities.PeriodeDaftar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("PeriodeDaftarDao")
public class PeriodeDaftarDaoImpl extends CoreDaoImpl<PeriodeDaftar> implements PeriodeDaftarDao{
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public PeriodeDaftarDaoImpl(SessionFactory sessionFactory) {
		super(PeriodeDaftar.class, sessionFactory);
	}
}
