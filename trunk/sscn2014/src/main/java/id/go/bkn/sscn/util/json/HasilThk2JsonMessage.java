package id.go.bkn.sscn.util.json;

import id.go.bkn.sscn.persistence.entities.DtHasil;
import id.go.bkn.sscn.persistence.entities.DtPeserta;
import id.go.bkn.sscn.persistence.entities.HasilThk2;

import java.util.List;
import java.util.Map;


public class HasilThk2JsonMessage {
	
	private int result;
	private List<HasilThk2> hasil;
	private String message;
	private int count;
	private int numpage;
	private int activePage;
	private int part2;
	private int index;
	
	
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
	public List<HasilThk2> getHasil() {
		return hasil;
	}
	/**
	 * @param hasil the hasil to set
	 */
	public void setHasil(List<HasilThk2> hasil) {
		this.hasil = hasil;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the numpage
	 */
	public int getNumpage() {
		return numpage;
	}
	/**
	 * @param numpage the numpage to set
	 */
	public void setNumpage(int numpage) {
		this.numpage = numpage;
	}
	/**
	 * @return the activePage
	 */
	public int getActivePage() {
		return activePage;
	}
	/**
	 * @param activePage the activePage to set
	 */
	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}
	/**
	 * @return the part2
	 */
	public int getPart2() {
		return part2;
	}
	/**
	 * @param part2 the part2 to set
	 */
	public void setPart2(int part2) {
		this.part2 = part2;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
		
}
