package id.go.bkn.sscn.model.json;

public class PendidikanJson {
	private String kode;
	private String nama;
	private String tingkat;
	
	public PendidikanJson() {
		
	}
	public PendidikanJson(String kode, String nama, String tingkat) {
		super();
		this.kode = kode;
		this.nama = nama;
		this.tingkat = tingkat;
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
	public String getTingkat() {
		return tingkat;
	}
	public void setTingkat(String tingkat) {
		this.tingkat = tingkat;
	}
	
}
