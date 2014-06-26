package id.go.bkn.sscn.services;

import id.go.bkn.sscn.persistence.entities.TabelPendaftar;

public interface AuthenticateService {
	TabelPendaftar login(String username, String password);
}
