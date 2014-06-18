package id.go.bkn.sscn.persistence.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dt_verifikasi_nok", catalog = "dbseleksicpns")
public class DtVerifikasiNok implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2042267209404544143L;
	/**
	 * 
	 */
	
	private Integer id;
	private DtPersyaratan persyaratan;
	private DtPendaftaran pendaftar;
	private DtUser verifikator;
	private Date createdDate;

	public DtVerifikasiNok() {
		// NOP
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return persyaratan
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSYARATAN", nullable = false)
	public DtPersyaratan getPersyaratan() {
		return this.persyaratan;
	}

	/**
	 * @param persyaratan
	 *            
	 */
	public void setPersyaratan(DtPersyaratan persyaratan) {
		this.persyaratan = persyaratan;
	}

	/**
	 * @return createdDate
	 */
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * @param createdDate
	 *            createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the verifikator
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VERIFIKATOR", nullable = false)
	public DtUser getVerifikator() {
		return verifikator;
	}

	/**
	 * @param verifikator
	 *            verifikator to set
	 */
	public void setVerifikator(DtUser verifikator) {
		this.verifikator = verifikator;
	}
	
	/**
	 * @return the pendaftar
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PENDAFTAR", nullable = false)
	public DtPendaftaran getPendaftar() {
		return pendaftar;
	}

	/**
	 * @param pendaftar
	 *            pendaftar to set
	 */
	public void setPendaftar(DtPendaftaran pendaftar) {
		this.pendaftar = pendaftar;
	}

	
}
