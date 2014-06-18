package id.go.bkn.sscn.persistence.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dt_persyaratan", catalog = "dbseleksicpns")
public class DtPersyaratan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6091534596714007977L;
	private Integer id;
	private RefInstansi refInstansi;
	private String syarat;
	private DtUser user;
	private Integer urutan;

	public DtPersyaratan() {
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
	 * @return the refInstansi
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INSTANSI", nullable = false)
	public RefInstansi getRefInstansi() {
		return refInstansi;
	}

	/**
	 * @param refInstansi
	 *            the refInstansi to set
	 */
	public void setRefInstansi(RefInstansi refInstansi) {
		this.refInstansi = refInstansi;
	}

	/**
	 * @return the syarat
	 */
	@Column(name = "SYARAT")
	public String getSyarat() {
		return syarat;
	}

	/**
	 * @param syarat
	 *            the syarat to set
	 */
	public void setSyarat(String syarat) {
		this.syarat = syarat;
	}

	/**
	 * @return the user
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER", nullable = false)
	public DtUser getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(DtUser user) {
		this.user = user;
	}

	/**
	 * @return the order
	 */
	@Column(name = "URUTAN")
	public Integer getUrutan() {
		return urutan;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setUrutan(Integer urutan) {
		this.urutan = urutan;
	}

}
