package id.go.bkn.sscn.persistence.entities.view;

public class StatInstansiJabatan {
	private String instansi;
	private String jabatan;
	private String jumlahPendaftar;
	private String jumlahLulus;
	private String jumlahTidakLulus;

	public StatInstansiJabatan() {
	}

	public StatInstansiJabatan(String instansi, String jabatan,
			String jumlahPendaftar, String jumlahLulus, String jumlahTidakLulus) {
		super();
		this.instansi = instansi;
		this.jabatan = jabatan;
		this.jumlahPendaftar = jumlahPendaftar;
		this.jumlahLulus = jumlahLulus;
		this.jumlahTidakLulus = jumlahTidakLulus;
	}

	public String getInstansi() {
		return instansi;
	}

	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getJumlahPendaftar() {
		return jumlahPendaftar;
	}

	public void setJumlahPendaftar(String jumlahPendaftar) {
		this.jumlahPendaftar = jumlahPendaftar;
	}

	public String getJumlahLulus() {
		return jumlahLulus;
	}

	public void setJumlahLulus(String jumlahLulus) {
		this.jumlahLulus = jumlahLulus;
	}

	public String getJumlahTidakLulus() {
		return jumlahTidakLulus;
	}

	public void setJumlahTidakLulus(String jumlahTidakLulus) {
		this.jumlahTidakLulus = jumlahTidakLulus;
	}

}
