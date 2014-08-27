package id.go.bkn.sscn.services.impl;

import id.go.bkn.sscn.core.util.PasswordUtil;
import id.go.bkn.sscn.dao.TabelPendaftarDao;
import id.go.bkn.sscn.persistence.entities.TabelPendaftar;
import id.go.bkn.sscn.services.AuthenticateService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("AuthenticateService")
public class AuthenticateServiceImpl implements AuthenticateService {

	private String PESAN = "PESAN";
	private String RESULT = "RESULT";
	private String SALT = "casn2014";
	@Inject
	private TabelPendaftarDao tabelPendaftarDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Map<String, Object> login(String username, String password) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		TabelPendaftar result = new TabelPendaftar();
		Boolean isValid = false;
		if (username == null || password == null) {
			mapResult.put(PESAN, "Gagal Login Illegal Parameter: " + username);
			mapResult.put(RESULT, null);
		} else {
			List<TabelPendaftar> listUsers = tabelPendaftarDao.findByProperty(
					"idLogin", username, null);
			if (listUsers.isEmpty()) {
				mapResult.put(PESAN, "User tidak ditemukan : " + username);
				mapResult.put(RESULT, null);
			} else {
				result = listUsers.get(0);
				boolean isException = false;
				PasswordUtil passwordUtil = new PasswordUtil();
				try {
					isValid = passwordUtil.isPasswordEqualUsingMessageDigest(
							password + SALT, result.getPassword());

				} catch (Exception e) {
					mapResult
							.put(PESAN, "Terjadi kesalahan: " + e.getMessage());
					mapResult.put(RESULT, null);
					isException = true;
				}

				if (!isValid) {
					if (!isException) {
						mapResult.put(PESAN, "Password login salah");
						mapResult.put(RESULT, null);
					}
				} else {
					mapResult.put(PESAN, "Login berhasil");
					mapResult.put(RESULT, result);
				}
			}
		}
		return mapResult;
	}
}
