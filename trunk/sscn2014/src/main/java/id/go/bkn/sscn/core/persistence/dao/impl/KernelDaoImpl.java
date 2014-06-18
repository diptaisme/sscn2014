package id.go.bkn.sscn.core.persistence.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.KernelDao;
import id.go.bkn.sscn.core.persistence.domain.CoreDomain;
import id.go.bkn.sscn.core.persistence.exception.CoreExceptionText;
import id.go.bkn.sscn.core.persistence.exception.CorePersistenceException;
import id.go.bkn.sscn.core.persistence.tools.QueryComparator;
import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.core.util.SqlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class KernelDaoImpl<T> implements KernelDao<T> {

	/** The Session Factory. */
	private SessionFactory sessionFactory;

	/** The Constant LOG. */
	static final Log LOG = LogFactory.getLog(KernelDaoImpl.class);

	/** The clazz. */
	protected final Class<T> clazz;

	/**
	 * This variable contains the String representation of the clazz class used
	 * in the constructor
	 */
	private final String clazzSimpleName;

	/**
	 * This variable contains a string with the format
	 * "SELECT model FROM ClassName AS model ".
	 */
	private final String selectFindQuery;

	/**
	 * This variable contains a string with the format
	 * "SELECT COUNT(*) FROM ClassName AS model ".
	 */
	private final String selectCountQuery;

	/**
	 * This variable contains a string with the format
	 * "DELETE FROM ClassName AS model ".
	 */
	private final String deleteFromQuery;

	/**
	 * This variable contains a string with the format
	 * "UPDATE ClassName AS model ".
	 */
	private final String updateFromQuery;

	/** The Constant DOT_PROPERTY_ID. */
	static final String DOT_PROPERTY_ID = ".id";

	/** The Constant PROPERTY_ID. */
	static final String PROPERTY_ID = "id";

	/**
	 * This should be used with careful, only for generic algorithms in which we
	 * can't know the class to which the query should be done
	 */
	private String classNameForGenericDao = null;

	public KernelDaoImpl(Class<T> clazz,
			SessionFactory sessionFactory) {
		super();
		this.clazz = clazz;
		this.sessionFactory = sessionFactory;
		clazzSimpleName = clazz.getSimpleName();
		this.selectFindQuery = new StringBuilder("SELECT model FROM ")
				.append(clazzSimpleName).append(AS_MODEL).toString();
		this.selectCountQuery = new StringBuilder("SELECT COUNT(*) FROM ")
				.append(clazzSimpleName).append(AS_MODEL).toString();
		this.deleteFromQuery = new StringBuilder("DELETE FROM ")
				.append(clazzSimpleName).append(AS_MODEL).toString();
		this.updateFromQuery = new StringBuilder("UPDATE ")
				.append(clazzSimpleName).append(AS_MODEL).toString();
	}

	/**
	 * Sets the class name for generic dao that should be used with careful,
	 * only for generic algorithms in which we can't know the class to which the
	 * query should be done
	 * 
	 * @author Roberto
	 * @param classNameForGenericDao
	 */
	void setClassNameForGenericDao(String classNameForGenericDao) {
		this.classNameForGenericDao = classNameForGenericDao;
	}

	/**
	 * gets the Select Find Query
	 * 
	 * @author Roberto
	 * @return
	 */
	protected String getSelectFindQuery() {
		if (classNameForGenericDao != null
				&& clazzSimpleName.equals(CoreDomain.class.getSimpleName())) {
			return StringUtils.replace(selectFindQuery, clazzSimpleName,
					classNameForGenericDao);
		} else {
			return selectFindQuery;
		}
	}

	/**
	 * @author Roberto
	 * @return the selectCountQuery
	 */
	public String getSelectCountQuery() {
		if (classNameForGenericDao != null
				&& clazzSimpleName.equals(CoreDomain.class.getSimpleName())) {
			return StringUtils.replace(selectCountQuery, clazzSimpleName,
					classNameForGenericDao);
		} else {
			return selectCountQuery;
		}
	}

	/**
	 * @author Roberto
	 * @return the deleteFromQuery
	 */
	public String getDeleteFromQuery() {
		if (classNameForGenericDao != null
				&& clazzSimpleName.equals(CoreDomain.class.getSimpleName())) {
			return StringUtils.replace(deleteFromQuery, clazzSimpleName,
					classNameForGenericDao);
		} else {
			return deleteFromQuery;
		}
	}

	/**
	 * @author Roberto
	 * @return the updateFromQuery
	 */
	public String getUpdateFromQuery() {
		if (classNameForGenericDao != null
				&& clazzSimpleName.equals(CoreDomain.class.getSimpleName())) {
			return StringUtils.replace(updateFromQuery, clazzSimpleName,
					classNameForGenericDao);
		} else {
			return updateFromQuery;
		}
	}

	/**
	 * Sets the session factory.
	 * 
	 * @param factory
	 *            the new session factory
	 * @author Roberto
	 */
	public void setSessionFactory(SessionFactory factory) {
		this.sessionFactory = factory;
	}

	/**
	 * Gets the current session.
	 * 
	 * @return Current session.
	 */
	protected final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * This method creates a Query object based on the query text.
	 * 
	 * @param queryText
	 *            HQL text.
	 * @return Query object.
	 */
	protected final Query createQuery(CharSequence queryText) {
		return getCurrentSession().createQuery(queryText.toString());
	}

	/**
	 * This method creates an SQLQuery object based on the SQL text.
	 * 
	 * @param sqlText
	 *            the sql text
	 * @return Query object.
	 */
	protected final SQLQuery createSqlQuery(CharSequence sqlText) {
		return getCurrentSession().createSQLQuery(sqlText.toString());
	}

	/**
	 * Create the ORDER BY string part of the query provided a list of
	 * <code>QueryOrder</code>.
	 * 
	 * @param orders
	 *            the orders
	 * @return the string
	 * @author Roberto
	 */
	protected static String createQueryOrderPhrase(List<QueryOrder> queryOrders) {
		return createQueryOrderPhrase(queryOrders, true);
	}

	/**
	 * Create the ORDER BY string part of the query provided a list of
	 * <code>QueryOrder</code>.
	 * 
	 * @param orders
	 *            the orders
	 * @param paginated
	 *            To determine whether the query is paginated or not. Defaults
	 *            to true. If is paginated we add order by ID to solve an error
	 *            in the pagination that doesnt show all elements when changing
	 *            pages.
	 * @return the string
	 * @author Roberto
	 */
	protected static String createQueryOrderPhrase(
			List<QueryOrder> queryOrders, Boolean paginated) {
		if (paginated == null) {
			paginated = true;
		}
		// copy the list to new container
		List<QueryOrder> orders = null;
		if (queryOrders == null) {
			orders = new ArrayList<QueryOrder>(1);
		} else {
			orders = new ArrayList<QueryOrder>(queryOrders);
		}
		// to have proper pagination, the last query order must be the id
		if (paginated) {
			orders.add(new QueryOrder("id"));
		}
		// build the order query
		StringBuilder buffer = new StringBuilder(orders.size()
				* ORDER_BY_TEXT_ESTIMATED_LENGTH);
		boolean firstOrder = true;
		for (QueryOrder order : orders) {
			if (order == null) {
				continue;
			}
			if (firstOrder) {
				buffer.append(" ORDER BY ");
				firstOrder = false;
			} else {
				buffer.append(", ");
			}
			buffer.append("model.").append(order.getPropertyName());
			if (order.getDirection() != null) {
				buffer.append(" ").append(order.getDirection());
			}
		}
		return buffer.toString();
	}

	/**
	 * Create the ORDER BY string part of the query provided a list of
	 * <code>QueryOrder</code>.
	 * 
	 * @param orders
	 *            the orders
	 * @return the string
	 * @author Roberto
	 */
	protected static String createQueryOrderPhrase(QueryOrder... queryOrders) {
		List<QueryOrder> orders = new ArrayList<QueryOrder>(queryOrders.length);
		return createQueryOrderPhrase(orders);
	}

	/**
	 * This method creates a 'LEFT JOIN FETCH' phrase.
	 * 
	 * @param columnNames
	 *            The column names used in the phrase.
	 * @return The ready to use phrase.
	 */
	protected static String createLeftJoinFetchPhrase(String... columnNames) {
		return createJoinPhraseOfAnyKind(true, columnNames);
	}

	/**
	 * Creates the 'INNER JOIN' (not fetch) phrase.
	 * 
	 * @param columnNames
	 *            the column names
	 * @return The ready to use phrase.
	 */
	protected static String createInnerJoinPhrase(String... columnNames) {
		return createJoinPhraseOfAnyKind(false, columnNames);
	}

	/**
	 * Creates a 'LEFT JOIN FETCH' or 'INNER JOIN' (not fetch) phrase
	 * 
	 * @author Roberto
	 * @param left
	 *            whether to build a JOIN LEFT phrase (when true) or an INNER
	 *            JOIN phrase (when false)
	 * @param columnNames
	 * @return The ready to use phrase.
	 */
	private static String createJoinPhraseOfAnyKind(boolean left,
			String... columnNames) {
		if (columnNames == null || columnNames.length == 0) {
			return "";
		}
		StringBuilder phrase = new StringBuilder(columnNames.length
				* JOIN_TEXT_ESTIMATED_LENGTH);
		for (String columnName : columnNames) {
			if (columnName != null) {
				columnName = columnName.trim();
				if (!columnName.isEmpty()) {
					if (left) {
						phrase.append(" LEFT JOIN FETCH ");
					} else {
						phrase.append(" INNER JOIN ");
					}
					// if the columnName doesn't contain the object name we
					// assume it will be model and append it accordingly
					if (!columnName.contains(".")) {
						phrase.append("model.");
					}
					phrase.append(columnName);
				}
			}
		}
		phrase.append(" ");
		return phrase.toString();
	}

	/**
	 * This method creates a 'LEFT JOIN FETCH' phrase.
	 * 
	 * @param columnNames
	 *            The list of column names used in the phrase.
	 * @return The ready to use phrase.
	 */
	protected static String createLeftJoinFetchPhrase(List<String> columnNames) {
		if (columnNames == null || columnNames.isEmpty()) {
			return "";
		}
		return createLeftJoinFetchPhrase(columnNames
				.toArray(new String[columnNames.size()]));
	}

	/**
	 * <b>WARNING:</b> This should be private. Do not lower your parameter using
	 * this method. Use: <code>setsParamToValueOrToLowercase </code><br/>
	 * Helper function to safely convert to lower case the value passed. It will
	 * attempt the operation only if not null and if is an instanceof String.
	 * 
	 * @param value
	 *            the value
	 * @return the object
	 * @author Roberto
	 */
	private Object lowerParam(Object value) {
		if (value instanceof String) {
			return ((String) value).toLowerCase();
		}
		return value;
	}

	/**
	 * Helper function to create the where clause supporting null, string (which
	 * won't be case sensitive) or anything else, even conditions referencing
	 * foreign tables alias.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param comparator
	 *            the comparator
	 * @return the single where clause
	 * @author Roberto
	 */
	protected String retrieveSingleWhereClause(String propertyName,
			Object value, QueryComparator comparator) {
		return SqlUtil.createSingleWhereClause(propertyName, value, comparator);
	}

	/**
	 * Given a select string (could be a select count(*)) and map of properties
	 * and the the values to search for, this method creates the query.
	 * 
	 * @param selectQueryStr
	 *            the select query str
	 * @param comparator
	 *            the comparator
	 * @param propertiesMap
	 *            the properties map
	 * @param orders
	 *            the orders
	 * @param lockMode
	 *            the lock mode (not used for creating the query, but for
	 *            deciding if the query ordering will be included or not)
	 * @param isLowercase
	 *            the lowercase flag, ignored for IDs
	 * @return the query
	 */
	protected Query createQueryForMaps(String selectQueryStr,
			QueryComparator comparator,
			Map<String, ? extends Object> propertiesMap,
			List<QueryOrder> orders, LockMode lockMode, Boolean... isLowercase) {
		StringBuilder queryString = new StringBuilder(selectQueryStr);
		
		if (comparator.equals(QueryComparator.PREFIXLIKE)){
			queryString.append(retrieveSingleWhereClauseForMaps(propertiesMap,
					QueryComparator.LIKE));
		} else {
			queryString.append(retrieveSingleWhereClauseForMaps(propertiesMap,
					comparator));
		}
		
		// row ordering
		if (lockMode == null || lockMode == LockMode.NONE) {
			queryString.append(createQueryOrderPhrase(orders));
		}
		Query query = createQuery(queryString);
		int order = 0;
		for (Entry<String, ? extends Object> propValue : propertiesMap
				.entrySet()) {
			Object value = propValue.getValue();
			if (value == null) {
				continue;
			}
			String propertyName = propValue.getKey();
			setsParamToValueOrToLowercaseValue(query, propertyName, comparator,
					value, order++, isLowercase);
		}
		return query;
	}

	/**
	 * Equivalent to {@link getSingleWhereClause} but for maps of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param comparator
	 *            the comparator
	 * @return the single where clause for maps
	 * @author Roberto
	 */
	protected String retrieveSingleWhereClauseForMaps(
			Map<String, ? extends Object> propertiesMap,
			QueryComparator comparator) {
		StringBuilder queryString = new StringBuilder();
		boolean firstItem = true;
		for (String propertyName : propertiesMap.keySet()) {
			if (firstItem) {
				firstItem = false;
			} else {
				queryString.append(" AND ");
			}
			queryString.append(SqlUtil.createSingleWhereClause(propertyName,
					propertiesMap.get(propertyName), comparator));
		}
		return queryString.toString();
	}

	/**
	 * Do query. {@inheritDoc}
	 * 
	 * @param query
	 *            the query
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list
	 */
	@SuppressWarnings(UNCHECKED)
	public List<T> doQuery(Query query, final int... rowStartIdxAndCount) {
		return (List<T>) SqlUtil.doQuery(query, rowStartIdxAndCount);
	}

	/**
	 * Given a select string (could be a select of a select count(*) and map of
	 * properties and the the values to search for it creates the query and
	 * performs it returning the result the query itself.
	 * 
	 * @param selectQueryStr
	 *            The select/count part of the query (requires that the entity
	 *            use 'model' as alias)
	 * @param comparator
	 *            Either COMPARATOR_EQUAL or COMPARATOR_LIKE to perform query by
	 *            equal or by like
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param orders
	 *            The orders of the result.
	 * @param lockMode
	 *            The Hibernate LockMode.
	 * @param isLowercase
	 *            A flag for detemining if a lowercase will be use or not.
	 * @param rowStartIdxAndCount
	 *            rowStartIdxAndCount Optional int varargs.
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results.
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return List<T> The result of finally performing the query
	 */
	@SuppressWarnings(UNCHECKED)
	protected List<T> doQueryForMaps(String selectQueryStr,
			QueryComparator comparator,
			Map<String, ? extends Object> propertiesMap,
			List<QueryOrder> orders, LockMode lockMode, Boolean isLowercase,
			final int... rowStartIdxAndCount) {
		try {
			Query query = createQueryForMaps(selectQueryStr, comparator,
					propertiesMap, orders, lockMode, isLowercase);
			if (lockMode != null) {
				query.setLockMode("model", lockMode);
			}
			return (List<T>) SqlUtil.doQuery(query, rowStartIdxAndCount);
		} catch (Exception e) {
			LOG.error(I18N_FIND + I18N_FAILED, e);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, e);
		}
	}

	/**
	 * Performs the setParameter operation, with the lowercase of value if it is
	 * an String which is not an ID
	 * 
	 * @author Roberto
	 * @param query
	 * @param propertyName
	 * @param comparator
	 * @param value
	 * @param paramPositionOrName
	 *            should be either Integer or String
	 * @param isLowercase
	 */
	protected void setsParamToValueOrToLowercaseValue(Query query,
			String propertyName, QueryComparator comparator, Object value,
			Object paramPositionOrName, Boolean... isLowercase) {
		if (value != null) {
			// this value of -1 will tell us that paramPositionOrName is a name
			// not a position
			int paramPosition = -1;
			String paramName = "";
			Object valueToSet = value;
			if (paramPositionOrName instanceof Integer) {
				paramPosition = ((Integer) paramPositionOrName).intValue();
			} else {
				paramName = paramPositionOrName.toString();
			}
			if (value instanceof String) {
				String valueStr = value.toString();
				if (SqlUtil.isComparatorLike(comparator)
						&& !SqlUtil.isAllreadyLikeExpression(valueStr)) {
					valueStr = "%" + valueStr.trim() + "%";
				}
				
				if (SqlUtil.isComparatorPrefixLike(comparator)
						&& !SqlUtil.isAllreadyPrefixLikeExpression(valueStr)) {
					valueStr = valueStr.trim() + "%";
				}
				// default behaviour (if is not an ID) is lower case, unless
				// otherwise set in
				// isLowercase[0]. For IDs never lowercase.
				if (SqlUtil.isLowercaseSetToYesOrNull(isLowercase)
						&& !SqlUtil.isAnId(propertyName)) {
					//valueToSet = lowerParam(valueStr); robertof
					valueToSet = valueStr;
				} else {
					valueToSet = valueStr;
				}
			}
			if (paramPosition == -1) {
				query.setParameter(paramName, valueToSet);
			} else {
				query.setParameter(paramPosition, valueToSet);
			}
		}
	}
}
