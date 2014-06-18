package id.go.bkn.sscn.core.persistence.tools;

import java.io.Serializable;
import java.util.List;


/**
 * Helper Pojo / Object that hold find for pagination. It contains search result
 * and some properties that can be use for pagination.
 * 
 * @param <T>
 *            the generic type
 */
public final class PaginationResult<T> implements Serializable {

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = -9032819855135489068L;

	/** The row count. */
	private Integer rowCount = 0;

	/** The result list. */
	private List<T> resultList;

	/**
	 * Constructor.
	 */
	public PaginationResult() {
		// NOOP
	}

	/**
	 * Constructor.
	 * 
	 * @param rowCount
	 *            total row count.
	 * @param resultList
	 *            rows value.
	 */
	public PaginationResult(Integer rowCount, List<T> resultList) {
		super();
		this.rowCount = rowCount;
		this.resultList = resultList;
	}

	/**
	 * Gets the row count.
	 * 
	 * @return the rowCount
	 */
	public Integer getRowCount() {
		return rowCount;
	}

	/**
	 * Sets the row count.
	 * 
	 * @param rowCount
	 *            the rowCount to set
	 */
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * Gets the result list.
	 * 
	 * @return the resultList
	 */
	public List<T> getResultList() {
		return resultList;
	}

	/**
	 * Sets the result list.
	 * 
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}

