package id.go.bkn.sscn.persistence.entities;

// Generated 13 Agu 14 17:10:57 by Hibernate Tools 3.4.0.CR1

import id.go.bkn.sscn.manager.Constanta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RefLokasiTest generated by hbm2java
 */
@Entity
@Table(name = "ref_lokasi_test", catalog = Constanta.DB_CATALOG)
public class RefLokasiTest implements java.io.Serializable {

	private String kode;
	private String nama;
	private String status;
	private RefInstansi instansi;

	public RefLokasiTest() {
	}

	public RefLokasiTest(String kode, RefInstansi instansi) {
		this.kode = kode;
		this.instansi = instansi;
	}

	public RefLokasiTest(String kode, String nama, String status,
			RefInstansi instansi) {
		this.kode = kode;
		this.nama = nama;
		this.status = status;
		this.instansi = instansi;
	}

	@Id
	@Column(name = "kode", unique = true, nullable = false, length = 8)
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	@Column(name = "nama", length = 100)
	public String getNama() {
		return this.nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTANSI", nullable = false)
	public RefInstansi getInstansi() {
		return this.instansi;
	}

	public void setInstansi(RefInstansi instansi) {
		this.instansi = instansi;
	}

}
