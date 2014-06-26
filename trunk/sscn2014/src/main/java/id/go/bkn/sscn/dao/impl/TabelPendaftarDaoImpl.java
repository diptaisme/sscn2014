package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.TabelPendaftarDao;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("TabelPendaftarDao")
public class TabelPendaftarDaoImpl extends CoreDaoImpl<TabelPendaftar>
		implements TabelPendaftarDao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public TabelPendaftarDaoImpl(SessionFactory sessionFactory) {
		super(TabelPendaftar.class, sessionFactory);
	}

}
