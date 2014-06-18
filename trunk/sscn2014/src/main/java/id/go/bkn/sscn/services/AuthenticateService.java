package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.DtUser;

public interface AuthenticateService {
	DtUser login(String username, String password);
}
