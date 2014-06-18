package id.go.bkn.sscn.core.persistence.dao;

import id.go.bkn.sscn.core.persistence.domain.CoreDomain;
import id.go.bkn.sscn.core.persistence.tools.PaginationResult;
import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;
import id.go.bkn.sscn.core.persistence.tools.QueryOrder;

import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;

/**
 * A data access object (DAO) providing persistence and search support for the
 * entities. Transaction control of the insert(), update() and remove()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @param <T>
 *            the generic type
 * @author Roberto
 */
public interface CoreDao<T> extends FindLayerDao<T> {

	/**
	 * Inserts a new instance element.
	 * 
	 * @param instance
	 *            the instance
	 * @return T The full instance just inserted
	 */
	T insert(T instance);

	/**
	 * Updates an instance.
	 * 
	 * @param instance
	 *            the instance
	 * @return T Updated instance
	 */
	T update(T instance);

	/**
	 * Removes and instance from the database. Always return true or an
	 * exception in case of instance not found on database.
	 * 
	 * @param instance
	 *            the instance
	 * @return boolean Always return true or an exception in case of instance
	 *         not found on database
	 */
	boolean remove(T instance);

	/**
	 * Finds and counts instances by a property. If the relevant parameters are
	 * set results could be ordered, the lock mode set, and fetching join
	 * columns.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            The list of child objects column names we want to eagerly
	 *            fetch. Set to null if not using.
	 * @param orders
	 *            The list of property name and sorting direction pairs. Set to
	 *            null if not using.
	 * @param lockMode
	 *            The Hibernate LockMode. Set to null if not using.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results. <br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return A pagination result
	 */
	PaginationResult<T> paginateByProperty(String propertyName, Object value,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders, LockMode lockMode,
			final int... rowStartIdxAndCount);

	/**
	 * Finds and counts instances by a map of properties. In case that one of
	 * the properties value is null it performs the IS NULL operation for that
	 * parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param leftJoinFetchColumns
	 *            The list of child objects column names we want to eagerly
	 *            fetch. Set to null, if not using.
	 * @param orders
	 *            The list of property name and sorting direction pairs. Set to
	 *            null, if not using.
	 * @param lockMode
	 *            The Hibernate LockMode. Set to null, if not using.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return PagintationResult<T>
	 * 
	 */
	PaginationResult<T> paginateByMapOfProperties(Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders, LockMode lockMode,
			final int... rowStartIdxAndCount);

	/**
	 * Finds and counts instances like a map of properties. In case that one of
	 * the properties value is null it performs the IS NULL operation for that
	 * parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @param leftJoinFetchColumns
	 *            List of left join fetch property name. Set to null if not
	 *            using.
	 * @param orders
	 *            Sorting orders. Set to null if not using.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results. <br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return List<T>
	 */
	PaginationResult<T> paginateLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount);

	/**
	 * Filters finding and counting instances by several criteria <br>
	 * Finds instances using different criteria for different properties. All
	 * criteria are stored in a list and they will all be added up to the query
	 * with <em>AND</em> relationship. Note that this is not an <em>OR</em>
	 * filter.
	 * 
	 * @param leftJoinFetch
	 *            List of Left Join Fetch property name. Set to null if not
	 *            using.
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
	 *            Sorting orders. Set to null if not using.
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results. <br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return PaginationResult<T>
	 * @see id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue
	 */
	PaginationResult<T> paginateUsingFilter(List<String> leftJoinFetch,
			List<PropCriteriaAndValue> filter, List<QueryOrder> orders,
			final int... rowStartIdxAndCount);

	/**
	 * Finds and counts all elements of the table in order.
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
	 * @return PaginationResult<T>
	 */
	PaginationResult<T> paginateAll(List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount);

	/**
	 * Finds and counts elements such that a string property is like the
	 * parameter given. It searchs using %keyword% pattern. Set query string as
	 * constant IS NULL if value parameter equals null
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
	 * @return PaginationResult<T>
	 */
	PaginationResult<T> paginateLikeProperty(String propertyName, Object value,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount);

	/**
	 * Removes the bulk list of entity.
	 * 
	 * @param entities
	 *            the list of <code>CoreDomain</code> Entity.
	 * @return true, if successful {@inheritDoc}
	 */
	boolean removeBulk(List<T> entities);

	/**
	 * Removes the bulk Using list of filter. The way you can call
	 * removeBulkUsingFilter(filter) and default behavior will be "AND"
	 * Expression, you can also call removeBulkUsingFilter(filter, null)
	 * equivalent as "AND" Expression. removeBulkUsingFilter(filter, true) will
	 * do the same ("AND" Expression), removeBulkUsingFilter(filter, false) will
	 * do "OR" Expression (Disjunction). The method will ignore the third and
	 * successive parameters in case we have them, such as
	 * removeBulkUsingFilter(filter, true, true, true, false), this would just
	 * consider the first one (will be consider as "AND" Expression).
	 * 
	 * @param filter
	 *            the list of <code>PropCriteriaAndValue</code> filter.
	 * @param and
	 *            Comparison expression either is conjunction ("AND") or
	 *            disjunction ("OR"). Put <code>null</code> will make the
	 *            expression is ("AND") by default. "false, true, true" will be
	 *            consider as "OR", "true, false, true" will be consider as
	 *            "AND" only take first element, and ignore the rest.
	 * 
	 * @return true, if successful {@inheritDoc}
	 */
	boolean removeBulkUsingFilter(List<PropCriteriaAndValue> filter, Boolean... and);

	/**
	 * Remove object from session cache.
	 * 
	 * @param coreDomain
	 */
	void evict(CoreDomain coreDomain);

}
