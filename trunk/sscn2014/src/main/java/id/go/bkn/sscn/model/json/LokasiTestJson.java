package id.go.bkn.sscn.model.json;

public class LokasiTestJson {
	private String kode;
	private String nama;
	private String status;
	private String instansi;

	public LokasiTestJson() {
		super();
	}

	public LokasiTestJson(String kode, String nama, String status,
			String instansi) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.status = status;
		this.instansi = instansi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstansi() {
		return instansi;
	}

	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

}
