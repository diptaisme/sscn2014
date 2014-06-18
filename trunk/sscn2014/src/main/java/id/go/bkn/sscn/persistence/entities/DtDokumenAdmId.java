package id.go.bkn.sscn.persistence.entities;

// Generated Aug 12, 2013 12:44:57 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DtDokumenAdmId generated by hbm2java
 */
@Embeddable
public class DtDokumenAdmId implements java.io.Serializable {

	private String instansi;
	private String kode;

	public DtDokumenAdmId() {
	}

	public DtDokumenAdmId(String instansi, String kode) {
		this.instansi = instansi;
		this.kode = kode;
	}

	@Column(name = "INSTANSI", nullable = false, length = 4)
	public String getInstansi() {
		return this.instansi;
	}

	public void setInstansi(String instansi) {
		this.instansi = instansi;
	}

	@Column(name = "KODE", nullable = false, length = 2)
	public String getKode() {
		return this.kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DtDokumenAdmId))
			return false;
		DtDokumenAdmId castOther = (DtDokumenAdmId) other;

		return ((this.getInstansi() == castOther.getInstansi()) || (this
				.getInstansi() != null && castOther.getInstansi() != null && this
				.getInstansi().equals(castOther.getInstansi())))
				&& ((this.getKode() == castOther.getKode()) || (this.getKode() != null
						&& castOther.getKode() != null && this.getKode()
						.equals(castOther.getKode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getInstansi() == null ? 0 : this.getInstansi().hashCode());
		result = 37 * result
				+ (getKode() == null ? 0 : this.getKode().hashCode());
		return result;
	}

}
