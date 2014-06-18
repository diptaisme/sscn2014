package id.go.bkn.sscn.util.json;

import id.go.bkn.sscn.model.json.UserJson;
import id.go.bkn.sscn.persistence.entities.DtUser;

import java.util.ArrayList;
import java.util.List;


public class ObjectConverterJson {
	public static UserJson convertFromDtUser(DtUser dtUser) {
		UserJson userJson = new UserJson();
		userJson.setKewenangan(dtUser.getKewenangan());
		userJson.setKodeInstansi(dtUser.getRefInstansi().getKode());
		userJson.setNamaInstansi(dtUser.getRefInstansi().getNama());
		userJson.setNama(dtUser.getNama());
		userJson.setNip(dtUser.getNip());
		userJson.setNipAdmin(dtUser.getNipAdmin());
		userJson.setTglCreated(dtUser.getTglCreated());
		userJson.setTglUpdated(dtUser.getTglUpdated());
		return userJson;
	}

	public static List<UserJson> convertFromListDtUser(List<DtUser> listDtUser) {
		List<UserJson> listUserJson = new ArrayList<UserJson>();
		for (DtUser dtUser : listDtUser) {
			UserJson userJson = new UserJson();
			userJson.setKewenangan(dtUser.getKewenangan());
			userJson.setKodeInstansi(dtUser.getRefInstansi().getKode());
			userJson.setNamaInstansi(dtUser.getRefInstansi().getNama());
			userJson.setNama(dtUser.getNama());
			userJson.setNip(dtUser.getNip());
			userJson.setNipAdmin(dtUser.getNipAdmin());
			userJson.setTglCreated(dtUser.getTglCreated());
			userJson.setTglUpdated(dtUser.getTglUpdated());
			listUserJson.add(userJson);
		}
		return listUserJson;
	}
}
