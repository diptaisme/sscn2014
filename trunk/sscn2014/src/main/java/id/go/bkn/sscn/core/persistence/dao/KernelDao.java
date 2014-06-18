package id.go.bkn.sscn.core.persistence.dao;

import java.util.List;

import org.hibernate.Query;

/**
 * The Interface KernelDao
 * 
 * @author Roberto
 */
public interface KernelDao<T> {

	/** The Constant UNCHECKED. */
	String UNCHECKED = "unchecked";

	/** The Constant I18N_INSTANCE. */
	String I18N_INSTANCE = " instance";

	/** The Constant I18N_INSERT. */
	String I18N_INSERT = "insert ";

	/** The Constant I18N_UPDATE. */
	String I18N_UPDATE = "update ";

	/** The Constant I18N_REMOVE. */
	String I18N_REMOVE = "remove ";

	/** The Constant I18N_FIND. */
	String I18N_FIND = "find ";

	/** The Constant I18N_COUNT. */
	String I18N_COUNT = "count ";

	/** The Constant I18N_FAILED. */
	String I18N_FAILED = " failed";

	/** The Constant I18N_SUCCEED. */
	String I18N_SUCCEED = " succesfull";

	/** The Constant I18N_INSTANCE_WITH_PROPERTY. */
	String I18N_INSTANCE_WITH_PROPERTY = " " + I18N_INSTANCE
			+ " with property: ";

	/** The Constant I18N_VALUE. */
	String I18N_VALUE = " value: ";

	/** The Constant AS_MODEL */
	String AS_MODEL = " AS model ";

	/** The Constant WHERE */
	String WHERE = " WHERE ";

	/** The Constant AND */
	String AND = " AND ";

	/** The Constant AND */
	String OR = " OR ";

	/** The Constant I18N_REMOVE_BULK. */
	String I18N_REMOVE_BULK = "remove bulk on ";

	/** The Constant JOIN_TEXT_ESTIMATED_LENGTH */
	int JOIN_TEXT_ESTIMATED_LENGTH = 32;

	/** The Constant JOIN_FETCH_TEXT_ESTIMATED_LENGTH */
	int ORDER_BY_TEXT_ESTIMATED_LENGTH = 32;

	/**
	 * Method that performs the, optionally paginated, query. It saves
	 * duplicating code lines.
	 * 
	 * @param query
	 *            Already well formed query with all parameters passed
	 * @param rowStartIdxAndCount
	 *            rowStartIdxAndCount Optional int varargs.<br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results.<br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.<br>
	 * @return The resulting list of the query
	 * @author Roberto
	 */
	List<T> doQuery(Query query, final int... rowStartIdxAndCount);
}
