package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtHasilDao;
import id.go.bkn.sscn.dao.DtPendaftaranDao;
import id.go.bkn.sscn.dao.InstansiThk2Dao;
import id.go.bkn.sscn.persistence.entities.DtHasil;
import id.go.bkn.sscn.persistence.entities.DtPendaftaran;
import id.go.bkn.sscn.persistence.entities.InstansiThk2;
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

@Repository("InstansiThk2Dao")
public class InstansiThk2DaoImpl extends CoreDaoImpl<InstansiThk2> implements
		InstansiThk2Dao {
	/**
	 * Default constructor.
	 * 
	 * @param sessionFactory
	 *            the session factory
	 */
	@Autowired
	public InstansiThk2DaoImpl(SessionFactory sessionFactory) {
		super(InstansiThk2.class, sessionFactory);
	}
}
