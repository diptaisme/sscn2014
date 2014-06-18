package id.go.bkn.sscn.util.json;

import id.go.bkn.sscn.persistence.entities.DtHasil;
import id.go.bkn.sscn.persistence.entities.DtPeserta;

import java.util.List;
import java.util.Map;


public class HasilJsonMessage {
	
	private int result;
	private DtHasil hasil;
	private DtPeserta peserta;
	private String message;
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the hasil
	 */
	public DtHasil getHasil() {
		return hasil;
	}
	/**
	 * @param hasil the hasil to set
	 */
	public void setHasil(DtHasil hasil) {
		this.hasil = hasil;
	}
	/**
	 * @return the peserta
	 */
	public DtPeserta getPeserta() {
		return peserta;
	}
	/**
	 * @param peserta the peserta to set
	 */
	public void setPeserta(DtPeserta peserta) {
		this.peserta = peserta;
	}
	
	
}
