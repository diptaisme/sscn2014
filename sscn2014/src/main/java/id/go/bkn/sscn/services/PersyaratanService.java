package id.go.bkn.sscn.services;

import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.persistence.entities.DtPersyaratan;

import java.util.List;


/**
 * @author efraim
 * 
 */
public interface PersyaratanService {
	/**
	 * @param persyaratan
	 * @return DtPersyaratan
	 */
	DtPersyaratan simpanPersyaratan(DtPersyaratan persyaratan);
	
	List<DtPersyaratan> findByProperty(String name, Object value,List<QueryOrder> orders, int...idx);
	
	boolean delete(DtPersyaratan persyaratan);
}
