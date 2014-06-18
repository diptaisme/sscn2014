package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtUser;

import java.util.List;


public interface UserService {
	
	List<DtUser> getAllUser(int... idx);
	
	Integer countAllUser();
	
	List<DtUser> getAllUserByInstansi(String kodeInstansi, int... idx);
	
	Integer countAllUserByInstansi(String kodeInstansi);

	boolean editUser(DtUser user, String kodeInstansi);
	
	boolean addUser(DtUser user, String kodeInstansi);
	
	boolean deleteUserByUsername(String username);
	
	//
	DtUser insertUser(DtUser user);
	
	List<DtUser> findByProperty(String name, String value, int...idx);
	
	boolean changePassword(DtUser user, String password);
	
	boolean isSamePassword(String password, String currentPassword);
}
