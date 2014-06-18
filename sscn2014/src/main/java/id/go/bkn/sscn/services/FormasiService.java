package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtFormasi;
import id.go.bkn.sscn.persistence.entities.MFormasi;

import java.util.Set;


public interface FormasiService {
	
	MFormasi insertFormasi(MFormasi mFormasi);
	
	MFormasi updateFormasi(MFormasi mFormasiOld, Set<DtFormasi> mFormasi);
	
	boolean deleteFormasi(int id);
}
