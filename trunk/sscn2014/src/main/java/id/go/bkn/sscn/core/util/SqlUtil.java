package id.go.bkn.sscn.core.util;

import id.go.bkn.sscn.core.persistence.domain.CoreDomain;
import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;
import id.go.bkn.sscn.core.persistence.tools.QueryComparator;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.IncompleteArgumentException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

/**
 * The Class SqlUtil.
 * 
 * @author Roberto
 */
public final class SqlUtil {

	/** The Constant I18N_ILLEGAL_PROPERTY_NAME. */
	public static final String I18N_ILLEGAL_PROPERTY_NAME = "Property name cannot be null or empty.";

	/** The Constant MODEL_ALIAS. */
	private static final String MODEL_ALIAS = "model.";

	/** The Constant PROPERTY_ID. */
	private static final String PROPERTY_ID = "Id";

	/** The Constant DOT. */
	private static final String DOT = ".";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant DOT_PROPERTY_ID. */
	private static final String DOT_PROPERTY_ID = ".id";

	/** The Constant QUERY_PARAMETER. */
	private static final String QUERY_PARAMETER = " ? ";

	/**
	 * Constant UNCHECKED
	 */
	private static final String UNCHECKED = "unchecked";

	/**
	 * to prevent instantiation of a new sql util.
	 */
	private SqlUtil() {
		// to prevent instantiation
	}

	/**
	 * Creates the single where clause.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param comparator
	 *            the comparator
	 * @param isLowercase
	 *            the lowercase flag, ignored for IDs
	 * @return the string
	 */
	public static String createSingleWhereClause(String propertyName,
			Object value, QueryComparator comparator, Boolean... isLowercase) {
		if (propertyName == null || propertyName.trim().isEmpty()) {
			throw new IllegalArgumentException(I18N_ILLEGAL_PROPERTY_NAME);
		}
		String propName = propertyName.trim();
		StringBuilder queryString = new StringBuilder();
		final String appendModel;
		// This will allow us to look up for properties outside current entity
		// The bit regarding the ".id" is due to historical legacy code
		if (propertyName.contains(DOT)
				&& !propertyName.contains(DOT_PROPERTY_ID)) {
			appendModel = "";
		} else {
			appendModel = MODEL_ALIAS;
		}
		if (value == null) {
			queryString.append(appendModel).append(propName)
					.append(" IS NULL ");
		} else {
			// default behaviour (if is not an ID) is lower case, unless
			// otherwise set in
			// isLowercase[0]. For IDs never lowercase.
			if (value instanceof String
					&& (SqlUtil.isLowercaseSetToYesOrNull(isLowercase) && !SqlUtil
							.isAnId(propertyName))) {
//				queryString.append(" lower(str(").append(appendModel)
//						.append(propName).append(")) ");  robertof
				queryString.append(appendModel).append(propName).append(" ");
				queryString.append(comparator).append(QUERY_PARAMETER);
			} else {
				queryString.append(SPACE).append(appendModel).append(propName)
						.append(SPACE);
				queryString.append(comparator).append(QUERY_PARAMETER);
			}
		}
		return queryString.toString();
	}

	/**
	 * Create single line where clause. Note : All String property comparison
	 * except "id" will be converted to lower case.
	 * 
	 * @param parameters
	 *            List that holds Query parameter.
	 * @param filter
	 *            <code>PropCriteriaAndValue</code> List.
	 * @return where clause string
	 */
	public static String createSingleWhereClause(List<Object> parameters,
			PropCriteriaAndValue filter) {
		String propertyName = filter.getPropertyName();
		String model = null;
		Object value = filter.getValue();
		if (StringUtils.isEmpty(StringUtils.trim(propertyName))) {
			throw new IllegalArgumentException(I18N_ILLEGAL_PROPERTY_NAME);
		}
		StringBuilder queryString = new StringBuilder();
		if (propertyName.contains(MODEL_ALIAS)
				&& !propertyName.contains(DOT_PROPERTY_ID)) {
			model = "";
		} else {
			model = MODEL_ALIAS;
		}
		if (isAnId(propertyName)) {
			queryString.append(model).append(propertyName).append(SPACE);
		} else {
			if (value instanceof String) {
				queryString.append(" lower(str(").append(model)
						.append(propertyName).append(")) ");
			} else {
				queryString.append(SPACE).append(model).append(propertyName)
						.append(SPACE);
			}
		}
		addExpressionClause(filter, parameters, queryString);
		return queryString.toString();
	}

	/**
	 * Add expression based on the comparator.
	 * 
	 * @param filter
	 *            <code>PropCriteriaAndValue</code>.
	 * @param parameters
	 *            list of parameter.
	 * @param queryString
	 *            query string.
	 */
	private static void addExpressionClause(PropCriteriaAndValue filter,
			List<Object> parameters, StringBuilder queryString) {
		Object propValue = filter.getValue();
		QueryComparator comparator = filter.getComparator();
		if (propValue == null) {
			queryString.append(QueryComparator.IS_NULL);
		} else {
			switch (comparator) {
			case IS_NULL:
			case IS_NOT_NULL:
				queryString.append(comparator);
				break;
			case LIKE:
				queryString.append(comparator).append(QUERY_PARAMETER);
				parameters.add("%" + propValue + "%");
				break;
			case BETWEEN:
				addBetweenExpression(queryString, parameters, propValue);
				break;
			case IN:
				addInExpression(queryString, parameters, propValue);
				break;
			case SQL_STRING_RESTRICTION:
				// Not sure how this one works
				queryString.append(propValue);
				break;
			default:
				// case EQUALS:
				// case GREATER_OR_EQUAL:
				// case GREATER_THAN:
				// case LESS_OR_EQUAL:
				// case LESS_THAN:
				// case NOT_EQUAL:
				queryString.append(comparator).append(QUERY_PARAMETER);
				parameters.add(propValue);
				break;
			}
		}
	}

	/**
	 * Auxiliar method to decrease the size of addExpressionClause methods,
	 * abstracting the algorithm used for IN expression.
	 * 
	 * @param queryString
	 *            hql query string
	 * @param parameters
	 *            list of parameter.
	 * @param propValue
	 *            property value.
	 */
	@SuppressWarnings(UNCHECKED)
	private static void addInExpression(StringBuilder queryString,
			List<Object> parameters, Object propValue) {
		if (propValue instanceof Collection) {
			Collection propValues = (Collection) propValue;
			boolean isFirst = true;
			queryString.append(" in ( ");
			for (Object object : propValues) {
				if (!isFirst) {
					queryString.append(", ");
				}
				queryString.append(QUERY_PARAMETER);
				parameters.add(object);
				isFirst = false;
			}
			queryString.append(" ) ");
		} else {
			throw new IllegalArgumentException(
					"Comparator was BETWEEN or IN yet no Collection of values was passed as argument of values.");
		}
	}

	/**
	 * Auxiliar method to decrease the size of addExpressionClause methods,
	 * abstracting the algorithm used for BEETWEEN expression.
	 * 
	 * @param queryString
	 *            hql query string
	 * @param parameters
	 *            list of parameters
	 * @param propValue
	 *            parameter value.
	 */
	@SuppressWarnings(UNCHECKED)
	private static void addBetweenExpression(StringBuilder queryString,
			List<Object> parameters, Object propValue) {
		queryString.append(" between ").append(QUERY_PARAMETER).append(" and ")
				.append(QUERY_PARAMETER).append(SPACE);
		if (propValue instanceof List) {
			List<Object> propValues = (List<Object>) propValue;
			if (propValues.size() > 1) {
				Object valueMin = propValues.get(0);
				Object valueMax = propValues.get(1);
				parameters.add(valueMin);
				parameters.add(valueMax);
			} else {
				throw new IncompleteArgumentException(
						"Expression was BETWEEN yet to the List of values there wasn't passed a pair of values.");
			}
		} else {
			throw new IllegalArgumentException(
					"Expression was BETWEEN yet no List nor Collection of two values were passed with the two values to be inbeetween of.");
		}
	}

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
	 */
	@SuppressWarnings(UNCHECKED)
	public static List<? extends CoreDomain> doQuery(Query query,
			int... rowStartIdxAndCount) {
		if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
			int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
			if (rowStartIdx > 0) {
				query.setFirstResult(rowStartIdx);
			}
			if (rowStartIdxAndCount.length > 1) {
				int rowCount = Math.max(0, rowStartIdxAndCount[1]);
				if (rowCount > 0) {
					query.setMaxResults(rowCount);
				}
			}
		}
		return query.list();
	}

	/**
	 * checks whether comparator exists and is a LIKE
	 * 
	 * @param comparator
	 * @return
	 */
	public static boolean isComparatorLike(QueryComparator comparator) {
		return (comparator != null && comparator.equals(QueryComparator.LIKE));
	}

	/**
	 * checks whether comparator exists and is a PREFFIX LIKE
	 * 
	 * @param comparator
	 * @return
	 */
	public static boolean isComparatorPrefixLike(QueryComparator comparator) {
		return (comparator != null && comparator.equals(QueryComparator.PREFIXLIKE));
	}
	
	/**
	 * checks whether property is an ID
	 * 
	 * @param propertyName
	 * @return
	 */
	public static boolean isAnId(String propertyName) {
		return propertyName.endsWith(DOT_PROPERTY_ID)
				|| propertyName.equalsIgnoreCase(PROPERTY_ID)
				|| propertyName.endsWith(PROPERTY_ID);
	}

	/**
	 * checks whether the string expression is already a like-expression
	 * 
	 * @param expression
	 * @return
	 */
	public static boolean isAllreadyLikeExpression(String expression) {
		return (expression.startsWith("%") && expression.endsWith("%"));
	}
	
	/**
	 * checks whether the string expression is already a like-expression
	 * 
	 * @param expression
	 * @return
	 */
	public static boolean isAllreadyPrefixLikeExpression(String expression) {
		return (expression.endsWith("%"));
	}

	/**
	 * Returns true if the array is empty, or the first element is null, or the
	 * first element's value is true.
	 * 
	 * @param isLowercase
	 * @return
	 */
	public static boolean isLowercaseSetToYesOrNull(Boolean[] isLowercase) {
		return ((ArrayUtils.isEmpty(isLowercase) || isLowercase[0] == null) || isLowercase[0]);
	}

}
