package id.go.bkn.sscn.core.persistence.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.FindLayerDao;
import id.go.bkn.sscn.core.persistence.exception.CoreExceptionText;
import id.go.bkn.sscn.core.persistence.exception.CorePersistenceException;
import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;
import id.go.bkn.sscn.core.persistence.tools.QueryComparator;
import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.core.util.SqlUtil;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * @author Roberto
 * 
 */
public class FindLayerDaoImpl<T> extends CountLayerDaoImpl<T> implements FindLayerDao<T> {

	/**
	 * constructor.
	 * 
	 * @param clazz
	 *            The same class as the typed parameter used when defining
	 *            CoreDaoImpl<T> sadly we need it to be passed in the
	 *            constructor also to be able to access class methods
	 * @param sessionFactory
	 *            This is the sessionFactory to be injected upon construction of
	 *            the bean. Remember to annotate the constructor with @Autowired
	 *            annotation
	 */
	public FindLayerDaoImpl(Class clazz, SessionFactory sessionFactory) {
		super(clazz, sessionFactory);
	}

	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @return the t {@inheritDoc}
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public T findById(String id) {
		try {
			return (T) getCurrentSession().get(clazz.getName(), id);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(CoreExceptionText.I18N_OPERATION_FAILED,
			        re);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sscn.core.persistence.dao.FindLayerDao#findById(java.lang.Integer)
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public T findById(Integer id) {
		try {
			return (T) getCurrentSession().get(clazz.getName(), id);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(CoreExceptionText.I18N_OPERATION_FAILED,
			        re);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.sscn.core.persistence.dao.FindLayerDao#findById(java.lang.Long)
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public T findById(Long id) {
		try {
			return (T) getCurrentSession().get(clazz.getName(), id);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(CoreExceptionText.I18N_OPERATION_FAILED,
			        re);
		}
	}


	/**
	 * Find by id.
	 * 
	 * @param id
	 *            the id
	 * @param leftJoinFetchNames
	 *            the left join fetch names
	 * @return the t {@inheritDoc}
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public T findById(String id, String... leftJoinFetchNames) {
		StringBuilder sb = new StringBuilder(getSelectFindQuery());
		sb.append(createLeftJoinFetchPhrase(leftJoinFetchNames));
		sb.append("WHERE model.id = ?");
		Query query = createQuery(sb);
		query.setString(0, id);
		return (T) query.uniqueResult();
	}

	/**
	 * Find all.
	 * 
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findAll(final int... rowStartIdxAndCount) {
		return findAll(null, null, rowStartIdxAndCount);
	}

	/**
	 * Find all.
	 * 
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public List<T> findAll(List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount) {
		StringBuilder sqlString = new StringBuilder(getSelectFindQuery());
		// left join fetch phrase
		sqlString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		// sorting orders
		sqlString.append(createQueryOrderPhrase(orders));
		try {
			Query query = createQuery(sqlString);
			return (List<T>) SqlUtil.doQuery(query, rowStartIdxAndCount);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(CoreExceptionText.I18N_OPERATION_FAILED,
			        re);
		}
	}

	/**
	 * Find by map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findByMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findByMapOfProperties(propertiesMap, null, orders, null,
		        rowStartIdxAndCount);
	}

	/**
	 * Find by map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param lockMode
	 *            the lock mode
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findByMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        LockMode lockMode, final int... rowStartIdxAndCount) {
		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		// Create LEFT JOIN FETCH phrase
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		return doQueryForMaps(queryString.toString(), QueryComparator.EQUALS,
		        propertiesMap, orders, lockMode, null, rowStartIdxAndCount);
	}

	/**
	 * Find like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        final int... rowStartIdxAndCount) {
		return findLikeMapOfProperties(propertiesMap, null, rowStartIdxAndCount);
	}

	/**
	 * Find like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findLikeMapOfProperties(propertiesMap, null, orders, rowStartIdxAndCount);
	}

	/**
	 * Find like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount) {
		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		return doQueryForMaps(queryString.toString(), QueryComparator.LIKE,
		        propertiesMap, orders, null, null, rowStartIdxAndCount);
	}
	
	

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value,
	        final int... rowStartIdxAndCount) {
		return findByProperty(propertyName, value, null, null, null, rowStartIdxAndCount);
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findByProperty(propertyName, value, null, orders, null,
		        rowStartIdxAndCount);
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param orders
	 *            the orders
	 * @param lockMode
	 *            the lock mode
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value,
	        List<QueryOrder> orders, LockMode lockMode, final int... rowStartIdxAndCount) {
		return findByProperty(propertyName, value, null, orders, lockMode,
		        rowStartIdxAndCount);
	}

	/**
	 * Find like property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findLikeProperty(String propertyName, Object value,
	        final int... rowStartIdxAndCount) {
		return findLikeProperty(propertyName, value, null, rowStartIdxAndCount);
	}

	/**
	 * Find like property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findLikeProperty(String propertyName, Object value,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		return findLikeProperty(propertyName, value, null, orders, rowStartIdxAndCount);
	}

	/**
	 * Find like property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findLikeProperty(String propertyName, Object value,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount) {
		return (List<T>) findGenericUsingProperty(propertyName, QueryComparator.LIKE,
		        value, leftJoinFetchColumns, orders, null, null, rowStartIdxAndCount);
	}

	/**
	 * Find by property.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param lockMode
	 *            the lock mode
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value,
	        List<String> leftJoinFetchColumns, List<QueryOrder> orders,
	        LockMode lockMode, final int... rowStartIdxAndCount) {
		return (List<T>) findGenericUsingProperty(propertyName, QueryComparator.EQUALS,
		        value, leftJoinFetchColumns, orders, lockMode, null, rowStartIdxAndCount);
	}

	/**
	 * Find unique by property.
	 * 
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
	@Override
	@SuppressWarnings("unchecked")
	public T findUniqueByProperty(String propertyName, Object value,
	        List<String> leftJoinFetchColumns, LockMode lockMode) {
		return (T) findGenericUsingProperty(propertyName, QueryComparator.EQUALS, value,
		        leftJoinFetchColumns, null, lockMode, true);
	}

	/**
	 * Finds elements using the comparator (EQUALS,LIKE)
	 * 
	 * @param propertyName
	 * @param comparator
	 * @param value
	 * @param leftJoinFetchColumns
	 * @param orders
	 * @param lockMode
	 * @param uniqueResult
	 *            For unique results, defaults to false. USE WISELY!
	 * @param rowStartIdxAndCount
	 * @return Object Can be List<T> or <T> depending on uniqueResult
	 * @throws NonUniqueResultException
	 *             if there is more than one matching result
	 */
	private Object findGenericUsingProperty(String propertyName,
	        QueryComparator comparator, Object value, List<String> leftJoinFetchColumns,
	        List<QueryOrder> orders, LockMode lockMode, Boolean uniqueResult,
	        final int... rowStartIdxAndCount) {
		if (uniqueResult == null) {
			uniqueResult = false;
		}
		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		// Creating LEFT JOIN FETCH phrase
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		queryString.append(SqlUtil.createSingleWhereClause(propertyName, value,
		        comparator));
		// sorting orders
		if (uniqueResult == false && (lockMode == null || lockMode == LockMode.NONE)) {
			queryString.append(createQueryOrderPhrase(orders));
		}
		try {
			Query query = createQuery(queryString);
			if (lockMode != null) {
				query.setLockMode("model", lockMode);
			}
			setsParamToValueOrToLowercaseValue(query, propertyName, comparator, value, 0);
			if (uniqueResult) {
				return query.uniqueResult();
			}
			return SqlUtil.doQuery(query, rowStartIdxAndCount);
		} catch (Exception re) {
			LOG.error(I18N_FIND + I18N_FAILED, re);
			throw new CorePersistenceException(CoreExceptionText.I18N_OPERATION_FAILED,
			        re);
		}
		// dbejar: Calling findByMapOfProperties is much less efficient!!!
	}

	/**
	 * Find using filter.
	 * 
	 * @param filter
	 *            the filter
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	public List<T> findUsingFilter(List<PropCriteriaAndValue> filter,
	        final int... rowStartIdxAndCount) {
		return findUsingFilter(filter, null, rowStartIdxAndCount);
	}

	/**
	 * Find using filter.
	 * 
	 * @param filter
	 *            the filter
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public List<T> findUsingFilter(List<PropCriteriaAndValue> filter,
	        List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		Criteria criteria = createHibernateCriteriaObject(null, filter, orders,
		        rowStartIdxAndCount);
		return criteria.list();
	}

	/**
	 * Find using filter.
	 * 
	 * @param leftJoinFetch
	 *            the left join fetch
	 * @param filter
	 *            the filter
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the list {@inheritDoc}
	 */
	@Override
	@SuppressWarnings(UNCHECKED)
	public List<T> findUsingFilter(List<String> leftJoinFetch,
	        List<PropCriteriaAndValue> filter, List<QueryOrder> orders,
	        final int... rowStartIdxAndCount) {
		Criteria criteria = createHibernateCriteriaObject(leftJoinFetch, filter, orders,
		        rowStartIdxAndCount);
		return criteria.list();
	}

	@Override
	public List<T> findPrefixLikeMapOfProperties(Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders, int[] rowStartIdxAndCount) {

		StringBuilder queryString = new StringBuilder(getSelectFindQuery());
		queryString.append(createLeftJoinFetchPhrase(leftJoinFetchColumns));
		queryString.append(WHERE);
		return doQueryForMaps(queryString.toString(), QueryComparator.PREFIXLIKE,
		        propertiesMap, orders, null, null, rowStartIdxAndCount);
	}

}
