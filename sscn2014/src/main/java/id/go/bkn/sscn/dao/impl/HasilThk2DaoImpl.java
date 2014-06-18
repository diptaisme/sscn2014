package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtHasilDao;
import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.dao.HasilThk2Dao;
import id.go.bkn.sscn.persistence.entities.DtHasil;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.HasilThk2;
import id.go.bkn.sscn.persistence.entities.RefInstansi;
import id.go.bkn.sscn.persistence.entities.view.DataPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.RekapanPendaftaran;
import id.go.bkn.sscn.persistence.entities.view.StatInstansi;
import id.go.bkn.sscn.persistence.entities.view.StatInstansiJabatan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("HasilThk2Dao")
public class HasilThk2DaoImpl extends CoreDaoImpl<HasilThk2> implements
		HasilThk2Dao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public HasilThk2DaoImpl(SessionFactory sessionFactory) {
		super(HasilThk2.class, sessionFactory);
	}
}
