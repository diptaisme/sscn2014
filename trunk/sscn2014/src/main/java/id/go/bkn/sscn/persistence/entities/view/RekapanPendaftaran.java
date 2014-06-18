package id.go.bkn.sscn.persistence.entities.view;

public class RekapanPendaftaran {
	private String lokasi;
	private String jabatan;
	private String pendidikan;
	private String jumlahPendaftar;
	private String jumlahLulus;
	private String jumlahTidakLulus;
	private String jumlahBelumVerifikasi;

	public RekapanPendaftaran() {
	}

	public RekapanPendaftaran(String lokasi, String jabatan, String pendidikan,
			String jumlahPendaftar, String jumlahLulus,
			String jumlahTidakLulus, String jumlahBelumVerifikasi) {
		super();
		this.lokasi = lokasi;
		this.jabatan = jabatan;
		this.pendidikan = pendidikan;
		this.jumlahPendaftar = jumlahPendaftar;
		this.jumlahLulus = jumlahLulus;
		this.jumlahTidakLulus = jumlahTidakLulus;
		this.jumlahBelumVerifikasi = jumlahBelumVerifikasi;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getPendidikan() {
		return pendidikan;
	}

	public void setPendidikan(String pendidikan) {
		this.pendidikan = pendidikan;
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

	public String getJumlahBelumVerifikasi() {
		return jumlahBelumVerifikasi;
	}

	public void setJumlahBelumVerifikasi(String jumlahBelumVerifikasi) {
		this.jumlahBelumVerifikasi = jumlahBelumVerifikasi;
	}

}
