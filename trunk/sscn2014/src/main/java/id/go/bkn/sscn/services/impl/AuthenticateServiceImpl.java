package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.core.util.PasswordUtil;
import id.go.bkn.sscn.dao.TabelPendaftarDao;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.AuthenticateService;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("AuthenticateService")
public class AuthenticateServiceImpl implements AuthenticateService {

	private String SALT = "casn2014";
	@Inject
	private TabelPendaftarDao tabelPendaftarDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TabelPendaftar login(String username, String password) {
		TabelPendaftar result = new TabelPendaftar();
		Boolean isValid = false;
		if (username == null || password == null) {
			// throw new IllegalArgumentException("Gagal Login");
			System.out.println("Gagal Login Illegal Parameter: " + username);
			return null;
		}
		List<TabelPendaftar> listUsers = tabelPendaftarDao.findByProperty(
				"idLogin", username, null);
		if (listUsers.isEmpty()) {
			// throw new IllegalArgumentException("User tidak ditemukan");
			System.out.println("User tidak ditemukan : " + username);
			return null;
		}
		result = listUsers.get(0);

		PasswordUtil passwordUtil = new PasswordUtil();
		try {
			isValid = passwordUtil.isPasswordEqualUsingMessageDigest(password + SALT,
					result.getPassword());

		} catch (Exception e) {
			System.out.println("Exception Login = " + e.getMessage());
			return null;
		}

		if (!isValid) {
			return null;
		} else {
			return result;
		}

	}
}
