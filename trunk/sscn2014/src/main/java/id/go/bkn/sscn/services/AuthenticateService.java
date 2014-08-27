package id.go.bkn.sscn.services;

import java.util.Map;

public interface AuthenticateService {
	Map<String, Object> login(String username, String password) ;
}
