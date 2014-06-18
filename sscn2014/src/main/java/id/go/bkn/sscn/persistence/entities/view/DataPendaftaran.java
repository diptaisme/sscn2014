package id.go.bkn.sscn.persistence.entities.view;

public class DataPendaftaran {
	private String noNik;
	private String noRegister;
	private String noPeserta;
	private String nama;
	private String tmpLahir;
	private String tglLahir;
	private String jnsKelamin;
	private String alamat;
	private String kodePos;
	private String propinsi;
	private String kota;
	private String telpon;
	private String email;
	private String asalInstitusiPendidikan;
	private String noIjazah;
	private String akreditasi;
	private String nilaiIpk;
	private String instansiKode;
	private String instansiNama;
	private String lokasiKode;
	private String lokasiNama;
	private String jabatanKode;
	private String jabatanNama;
	private String pendidikanKode;
	private String pendidikanNama;
	private String tglCreated;
	private String status;

	public DataPendaftaran() {
	}

	public DataPendaftaran(String noNik, String noRegister, String noPeserta,
			String nama, String tmpLahir, String tglLahir, String jnsKelamin,
			String alamat, String kodePos, String propinsi, String kota,
			String telpon, String email, String asalInstitusiPendidikan,
			String noIjazah, String akreditasi, String nilaiIpk,
			String instansiKode, String instansiNama, String lokasiKode,
			String lokasiNama, String jabatanKode, String jabatanNama,
			String pendidikanKode, String pendidikanNama, String tglCreated,
			String status) {
		super();
		this.noNik = noNik;
		this.noRegister = noRegister;
		this.noPeserta = noPeserta;
		this.nama = nama;
		this.tmpLahir = tmpLahir;
		this.tglLahir = tglLahir;
		this.jnsKelamin = jnsKelamin;
		this.alamat = alamat;
		this.kodePos = kodePos;
		this.propinsi = propinsi;
		this.kota = kota;
		this.telpon = telpon;
		this.email = email;
		this.asalInstitusiPendidikan = asalInstitusiPendidikan;
		this.noIjazah = noIjazah;
		this.akreditasi = akreditasi;
		this.nilaiIpk = nilaiIpk;
		this.instansiKode = instansiKode;
		this.instansiNama = instansiNama;
		this.lokasiKode = lokasiKode;
		this.lokasiNama = lokasiNama;
		this.jabatanKode = jabatanKode;
		this.jabatanNama = jabatanNama;
		this.pendidikanKode = pendidikanKode;
		this.pendidikanNama = pendidikanNama;
		this.tglCreated = tglCreated;
		this.status = status;
	}

	public String getNoNik() {
		return noNik;
	}

	public void setNoNik(String noNik) {
		this.noNik = noNik;
	}

	public String getNoRegister() {
		return noRegister;
	}

	public void setNoRegister(String noRegister) {
		this.noRegister = noRegister;
	}

	public String getNoPeserta() {
		return noPeserta;
	}

	public void setNoPeserta(String noPeserta) {
		this.noPeserta = noPeserta;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTmpLahir() {
		return tmpLahir;
	}

	public void setTmpLahir(String tmpLahir) {
		this.tmpLahir = tmpLahir;
	}

	public String getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(String tglLahir) {
		this.tglLahir = tglLahir;
	}

	public String getJnsKelamin() {
		return jnsKelamin;
	}

	public void setJnsKelamin(String jnsKelamin) {
		if (jnsKelamin.equalsIgnoreCase("P")) {
			jnsKelamin = "Pria";
		}
		if (jnsKelamin.equalsIgnoreCase("W")) {
			jnsKelamin = "Wanita";
		}
		this.jnsKelamin = jnsKelamin;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKodePos() {
		return kodePos;
	}

	public void setKodePos(String kodePos) {
		this.kodePos = kodePos;
	}

	public String getPropinsi() {
		return propinsi;
	}

	public void setPropinsi(String propinsi) {
		this.propinsi = propinsi;
	}

	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}

	public String getTelpon() {
		return telpon;
	}

	public void setTelpon(String telpon) {
		this.telpon = telpon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAsalInstitusiPendidikan() {
		return asalInstitusiPendidikan;
	}

	public void setAsalInstitusiPendidikan(String asalInstitusiPendidikan) {
		this.asalInstitusiPendidikan = asalInstitusiPendidikan;
	}

	public String getNoIjazah() {
		return noIjazah;
	}

	public void setNoIjazah(String noIjazah) {
		this.noIjazah = noIjazah;
	}

	public String getAkreditasi() {
		return akreditasi;
	}

	public void setAkreditasi(String akreditasi) {
		this.akreditasi = akreditasi;
	}

	public String getNilaiIpk() {
		return nilaiIpk;
	}

	public void setNilaiIpk(String nilaiIpk) {
		this.nilaiIpk = nilaiIpk;
	}

	public String getInstansiKode() {
		return instansiKode;
	}

	public void setInstansiKode(String instansiKode) {
		this.instansiKode = instansiKode;
	}

	public String getInstansiNama() {
		return instansiNama;
	}

	public void setInstansiNama(String instansiNama) {
		this.instansiNama = instansiNama;
	}

	public String getLokasiKode() {
		return lokasiKode;
	}

	public void setLokasiKode(String lokasiKode) {
		this.lokasiKode = lokasiKode;
	}

	public String getLokasiNama() {
		return lokasiNama;
	}

	public void setLokasiNama(String lokasiNama) {
		this.lokasiNama = lokasiNama;
	}

	public String getJabatanKode() {
		return jabatanKode;
	}

	public void setJabatanKode(String jabatanKode) {
		this.jabatanKode = jabatanKode;
	}

	public String getJabatanNama() {
		return jabatanNama;
	}

	public void setJabatanNama(String jabatanNama) {
		this.jabatanNama = jabatanNama;
	}

	public String getPendidikanKode() {
		return pendidikanKode;
	}

	public void setPendidikanKode(String pendidikanKode) {
		this.pendidikanKode = pendidikanKode;
	}

	public String getPendidikanNama() {
		return pendidikanNama;
	}

	public void setPendidikanNama(String pendidikanNama) {
		this.pendidikanNama = pendidikanNama;
	}

	public String getTglCreated() {
		return tglCreated;
	}

	public void setTglCreated(String tglCreated) {
		this.tglCreated = tglCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
