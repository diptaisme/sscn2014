package id.go.bkn.sscn.services.impl;

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

	@Inject
	private TabelPendaftarDao tabelPendaftarDao;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public TabelPendaftar login(String username, String password) {
		TabelPendaftar result = new TabelPendaftar();
		Boolean isValid = false;
		if (username == null || password == null) {
			throw new IllegalArgumentException("Gagal Login");
		}
		List<TabelPendaftar> listUsers = tabelPendaftarDao.findByProperty(
				"idLogin", username, null);
		if (listUsers.isEmpty()) {
			//throw exception untuk pendaftar yang tidak ditemukan
			throw new IllegalArgumentException("User tidak ditemukan");
		}
		result = listUsers.get(0);

		// PasswordUtil passwordUtil = new PasswordUtil();
		// try {
		isValid = password.equals(result.getPassword());
		//
		// } catch (IOException e) {
		// System.out.println("IO exception " + e.getMessage());
		// return null;
		// } catch (DecoderException e) {
		// System.out.println("Decoder exception = " + e.getMessage());
		// return null;
		// } catch (Exception e) {
		// System.out.println("Exception = " + e.getMessage());
		// return null;
		// }

		if (!isValid) {
			return null;
		} else {
			return result;
		}

	}
}
