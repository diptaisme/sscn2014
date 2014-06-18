package id.go.bkn.sscn.model.json;

public class InstansiJson {
	private String kode;
	private String nama;

	public InstansiJson() {
		super();
	}

	public InstansiJson(String kode, String nama) {
		super();
		this.kode = kode;
		this.nama = nama;
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
