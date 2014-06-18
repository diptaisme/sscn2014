package id.go.bkn.sscn.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtPersyaratanDao;
import id.go.bkn.sscn.persistence.entities.DtPersyaratan;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dtPersyaratanDaoImpl")
public class DtPersyaratanDaoImpl extends CoreDaoImpl<DtPersyaratan> implements
        DtPersyaratanDao {

	/**
	 * @param sessionFactory
	 */
	@Autowired
	public DtPersyaratanDaoImpl(SessionFactory sessionFactory) {
		super(DtPersyaratan.class, sessionFactory);
	}
}
