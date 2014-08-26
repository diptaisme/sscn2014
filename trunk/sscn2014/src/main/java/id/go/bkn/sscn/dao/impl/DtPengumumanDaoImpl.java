package id.go.bkn.sscn.dao.impl;

import java.util.List;

import id.go.bkn.sscn.core.persistence.dao.impl.CoreDaoImpl;
import id.go.bkn.sscn.dao.DtPengumumanDao;
import id.go.bkn.sscn.persistence.entities.DtPengumuman;

import org.hibernate.Query;
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

	@Override
	public List<DtPengumuman> getPengumumanInstansiPusat() {
		String sqlQuery = "select dp from DtPengumuman as dp "				
				+ "join dp.refInstansi as instansi "
				+"WHERE instansi.kode like '1%' OR "
				+"instansi.kode like '2%' OR "
				+"instansi.kode like '3%' OR "
				+"instansi.kode like '4%' "
				+ "ORDER BY instansi.nama ";
		Query query = createQuery(sqlQuery);
		return doQuery(query, null);
	}
	
	@Override
	public List<DtPengumuman> getPengumumanInstansiPropinsi() {
		String sqlQuery = "select dp from DtPengumuman as dp "				
				+ "join dp.refInstansi as instansi "
				+"WHERE instansi.nama like '%Propinsi%' "
				+ "ORDER BY instansi.nama ";
		Query query = createQuery(sqlQuery);
		return doQuery(query, null);
	}
	
	@Override
	public List<DtPengumuman> getPengumumanInstansiKota() {
		String sqlQuery = "select dp from DtPengumuman as dp "				
				+ "join dp.refInstansi as instansi "
				+"WHERE instansi.nama like '% Kota %' "
				+ "ORDER BY instansi.nama ";
		Query query = createQuery(sqlQuery);
		return doQuery(query, null);
	}
	
	@Override
	public List<DtPengumuman> getPengumumanInstansiKabupaten() {
		String sqlQuery = "select dp from DtPengumuman as dp "				
				+ "join dp.refInstansi as instansi "
				+"WHERE instansi.nama like '% Kab.%' "
				+ "ORDER BY instansi.nama ";
		Query query = createQuery(sqlQuery);
		return doQuery(query, null);
	}
}
