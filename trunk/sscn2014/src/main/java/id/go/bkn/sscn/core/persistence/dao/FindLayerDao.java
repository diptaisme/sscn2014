package id.go.bkn.sscn.core.persistence.dao;

import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;
import id.go.bkn.sscn.core.persistence.tools.QueryOrder;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.NonUniqueResultException;

/**
 * @author Roberto
 * 
 */
public interface FindLayerDao<T> extends CountLayerDao<T> {

	/**
	 * Finds an instance by its Id.
	 * 
	 * @param id
	 *            the id
	 * @return T The instance which id is the one passed by parameter. An
	 *         exception if no instance is found.
	 */
	T findById(String id);

	/**
	 * Finds an instance by its Id
	 * 
	 * @author efraim
	 * @param id
	 *            the id
	 * @returnT The instance which id is the one passed by parameter. An
	 *          exception if no instance is found.
	 */
	T findById(Integer id);

	/**
	 * Finds an instance by its Id, eagerly fetching specific child objects.
	 * 
	 * @param id
	 *            The id to look up for
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @return T The instance which id is the one passed by parameter. An
	 *         exception if no instance is found.
	 */
	T findById(String id, String... leftJoinFetchColumns);

	/**
	 * Finds all elements of the table.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findAll(final int... rowStartIdxAndCount);

	/**
	 * Finds all elements of the table in order.
	 * 
	 * @param leftJoinFetchColumns
	 *            List of left join fetch property name..
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findAll(List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	/**
	 * Finds instances by a property. Set query string as constant IS NULL if
	 * value parameter equals null
	 * 
	 * @param propertyName
	 *            The property's name.
	 * @param value
	 *            The value of the property.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findByProperty(String propertyName, Object value,
	        final int... rowStartIdxAndCount);

	/**
	 * Find unique by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param lockMode
	 *            the lock mode
	 * @return the <T>
	 * @throws NonUniqueResultException
	 *             if there is more than one matching result
	 */
	T findUniqueByProperty(String propertyName, Object value,
	        List<String> leftJoinFetchColumns, LockMode lockMode);

	/**
	 * Finds instances by a property. Set query string as constant IS NULL if
	 * value parameter equals null. The instances are ordered.
	 * 
	 * @param propertyName
	 *            The property's name.
	 * @param value
	 *            The value of the property.
	 * @param orders
	 *            The list of property name and sorting direction pairs.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findByProperty(String propertyName, Object value, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	/**
	 * Finds ordered instances by a property, and set the lock mode.
	 * 
	 * @param propertyName
	 *            The property's name.
	 * @param value
	 *            The value of the property.
	 * @param orders
	 *            The list of property name and sorting direction pairs.
	 * @param lockMode
	 *            The Hibernate LockMode.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findByProperty(String propertyName, Object value, List<QueryOrder> orders,
	        LockMode lockMode, final int... rowStartIdxAndCount);

	/**
	 * Finds ordered instances by a property, fetches child objects eagerly, and
	 * set the lock mode.
	 * 
	 * @param propertyName
	 *            The property's name.
	 * @param value
	 *            The value of the property.
	 * @param leftJoinFetchColumns
	 *            The list of child objects' column names we want to eagerly
	 *            fetch.
	 * @param orders
	 *            The list of property name and sorting direction pairs.
	 * @param lockMode
	 *            The Hibernate LockMode.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findByProperty(String propertyName, Object value,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        LockMode lockMode, final int... rowStartIdxAndCount);

	/**
	 * Finds elements such that a string property is like the parameter given.
	 * It searchs using %keyword% pattern. Set query string as constant IS NULL
	 * if value parameter equals null
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findLikeProperty(String propertyName, Object value,
	        final int... rowStartIdxAndCount);

	/**
	 * Finds elements such that a string property is like the parameter given.
	 * It searchs using %keyword% pattern. Set query string as constant IS NULL
	 * if value parameter equals null
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param orders
	 *            The order clause.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findLikeProperty(String propertyName, Object value, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	/**
	 * Finds elements such that a string property is like the parameter given.
	 * It searchs using %keyword% pattern. Set query string as constant IS NULL
	 * if value parameter equals null
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            List of left join fetch column name.
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findLikeProperty(String propertyName, Object value,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	/**
	 * Filters instances by several criteria <br>
	 * Finds instances using different criteria for different properties. All
	 * criteria are stored in a list and they will all be added up to the query
	 * with <em>AND</em> relationship. Note that this is not an <em>OR</em>
	 * filter.
	 * 
	 * @param filter
	 *            The filter itself, is actually a list containing
	 *            <code>PropCriteriaAndValue</code> elements, which is a set of
	 *            three elements typically composed of the name of the property,
	 *            the criteria used to compare and the value to which that
	 *            property should be compared. However there are some criteria
	 *            in which value should be null, or in which the value should be
	 *            a collection of values. <br>
	 *            Follows a list of the supported queryComparators for criteria
	 *            and a brief explanation of usage:
	 *            <ul>
	 *            <li>Property is compared with one value:
	 *            <ul>
	 *            <li>EQUALS
	 *            <li>GREATER_OR_EQUAL
	 *            <li>GREATER_THAN
	 *            <li>LESS_OR_EQUAL
	 *            <li>LESS_THAN
	 *            <li>NOT_EQUAL
	 *            <li>LIKE
	 *            </ul>
	 *            <li>Property is compared with value which is a List or
	 *            Collection:
	 *            <ul>
	 *            <li>BEETWEEN List is advisable as it it ordered, although
	 *            collection is supported
	 *            <li>IN
	 *            </ul>
	 *            <li>Property is checked against criteria regardless value
	 *            content:
	 *            <ul>
	 *            <li>IS_NOT_NULL
	 *            <li>IS_NULL
	 *            </ul>
	 *            <li>Value is a String and is used regardless the content of
	 *            Property
	 *            <ul>
	 *            <li>SQL_STRING_RESTRICTION This one is quite undocumented by
	 *            Hibernate.
	 *            </ul>
	 *            </ul>
	 *            See <code>PropCriteriaAndValue</code> for more details.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 * @see id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue
	 */
	List<T> findUsingFilter(List<PropCriteriaAndValue> filter,
	        final int... rowStartIdxAndCount);

	/**
	 * Filters instances by several criteria <br>
	 * Finds instances using different criteria for different properties. All
	 * criteria are stored in a list and they will all be added up to the query
	 * with <em>AND</em> relationship. Note that this is not an <em>OR</em>
	 * filter.
	 * 
	 * @param filter
	 *            The filter itself, is actually a list containing
	 *            <code>PropCriteriaAndValue</code> elements, which is a set of
	 *            three elements typically composed of the name of the property,
	 *            the criteria used to compare and the value to which that
	 *            property should be compared. However there are some criteria
	 *            in which value should be null, or in which the value should be
	 *            a collection of values. <br>
	 *            Follows a list of the supported queryComparators for criteria
	 *            and a brief explanation of usage:
	 *            <ul>
	 *            <li>Property is compared with one value:
	 *            <ul>
	 *            <li>EQUALS
	 *            <li>GREATER_OR_EQUAL
	 *            <li>GREATER_THAN
	 *            <li>LESS_OR_EQUAL
	 *            <li>LESS_THAN
	 *            <li>NOT_EQUAL
	 *            <li>LIKE
	 *            </ul>
	 *            <li>Property is compared with value which is a List or
	 *            Collection:
	 *            <ul>
	 *            <li>BEETWEEN List is advisable as it it ordered, although
	 *            collection is supported
	 *            <li>IN
	 *            </ul>
	 *            <li>Property is checked against criteria regardless value
	 *            content:
	 *            <ul>
	 *            <li>IS_NOT_NULL
	 *            <li>IS_NULL
	 *            </ul>
	 *            <li>Value is a String and is used regardless the content of
	 *            Property
	 *            <ul>
	 *            <li>SQL_STRING_RESTRICTION This one is quite undocumented by
	 *            Hibernate.
	 *            </ul>
	 *            </ul>
	 *            See <code>PropCriteriaAndValue</code> for more details.
	 * @param orders
	 *            Sorting orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results. <br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return List<T>
	 * @see id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue
	 */
	List<T> findUsingFilter(List<PropCriteriaAndValue> filter, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	/**
	 * Filters instances by several criteria <br>
	 * Finds instances using different criteria for different properties. All
	 * criteria are stored in a list and they will all be added up to the query
	 * with <em>AND</em> relationship. Note that this is not an <em>OR</em>
	 * filter.
	 * 
	 * @param leftJoinFetch
	 *            List of Left Join Fetch property name.
	 * @param filter
	 *            The filter itself, is actually a list containing
	 *            <code>PropCriteriaAndValue</code> elements, which is a set of
	 *            three elements typically composed of the name of the property,
	 *            the criteria used to compare and the value to which that
	 *            property should be compared. However there are some criteria
	 *            in which value should be null, or in which the value should be
	 *            a collection of values. <br>
	 *            Follows a list of the supported queryComparators for criteria
	 *            and a brief explanation of usage:
	 *            <ul>
	 *            <li>Property is compared with one value:
	 *            <ul>
	 *            <li>EQUALS
	 *            <li>GREATER_OR_EQUAL
	 *            <li>GREATER_THAN
	 *            <li>LESS_OR_EQUAL
	 *            <li>LESS_THAN
	 *            <li>NOT_EQUAL
	 *            <li>LIKE
	 *            </ul>
	 *            <li>Property is compared with value which is a List or
	 *            Collection:
	 *            <ul>
	 *            <li>BEETWEEN List is advisable as it it ordered, although
	 *            collection is supported
	 *            <li>IN
	 *            </ul>
	 *            <li>Property is checked against criteria regardless value
	 *            content:
	 *            <ul>
	 *            <li>IS_NOT_NULL
	 *            <li>IS_NULL
	 *            </ul>
	 *            <li>Value is a String and is used regardless the content of
	 *            Property
	 *            <ul>
	 *            <li>SQL_STRING_RESTRICTION This one is quite undocumented by
	 *            Hibernate.
	 *            </ul>
	 *            </ul>
	 *            See <code>PropCriteriaAndValue</code> for more details.
	 * @param orders
	 *            Sorting orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results. <br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return List<T>
	 * @see id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue
	 */
	List<T> findUsingFilter(List<String> leftJoinFetch,
	        List<PropCriteriaAndValue> filter, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	/**
	 * Finds instances by a map of properties in an specific order. All
	 * properties should be members of the entity. For foreign members do your
	 * own custom query. In case that one of the properties value is null it
	 * performs the IS NULL operation for that parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param orders
	 *            The list of property name and sorting direction pairs.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findByMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount);

	/**
	 * Finds instances by a map of properties. In case that one of the
	 * properties value is null it performs the IS NULL operation for that
	 * parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param leftJoinFetchColumns
	 *            The list of child objects column names we want to eagerly
	 *            fetch.
	 * @param orders
	 *            The list of property name and sorting direction pairs.
	 * @param lockMode
	 *            The Hibernate LockMode.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findByMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        LockMode lockMode, final int... rowStartIdxAndCount);

	/**
	 * Finds instances like a map of propertis. All properties should be members
	 * of the entity. For foreign members do your own custom query. In case that
	 * one of the properties value is null it performs the IS NULL operation for
	 * that parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        final int... rowStartIdxAndCount);

	/**
	 * Finds instances like a map of propertis. All properties should be members
	 * of the entity. For foreign members do your own custom query. In case that
	 * one of the properties value is null it performs the IS NULL operation for
	 * that parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount);

	/**
	 * Finds instances like a map of propertis. All properties should be members
	 * of the entity. For foreign members do your own custom query. In case that
	 * one of the properties value is null it performs the IS NULL operation for
	 * that parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<T>
	 */
	List<T> findLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount);

	List<T> findPrefixLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders, int[] rowStartIdxAndCount);
}
