package id.go.bkn.sscn.model.json;

import java.util.Date;

public class UserJson {
	private String nip;
	private String kodeInstansi;
	private String namaInstansi;
	private String nama;
	private String password;
	private String kewenangan;
	private Date tglCreated;
	private Date tglUpdated;
	private String nipAdmin;

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getKodeInstansi() {
		return kodeInstansi;
	}

	public void setKodeInstansi(String kodeInstansi) {
		this.kodeInstansi = kodeInstansi;
	}

	public String getNamaInstansi() {
		return namaInstansi;
	}

	public void setNamaInstansi(String namaInstansi) {
		this.namaInstansi = namaInstansi;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKewenangan() {
		return kewenangan;
	}

	public void setKewenangan(String kewenangan) {
		this.kewenangan = kewenangan;
	}

	public Date getTglCreated() {
		return tglCreated;
	}

	public void setTglCreated(Date tglCreated) {
		this.tglCreated = tglCreated;
	}

	public Date getTglUpdated() {
		return tglUpdated;
	}

	public void setTglUpdated(Date tglUpdated) {
		this.tglUpdated = tglUpdated;
	}

	public String getNipAdmin() {
		return nipAdmin;
	}

	public void setNipAdmin(String nipAdmin) {
		this.nipAdmin = nipAdmin;
	}

}
