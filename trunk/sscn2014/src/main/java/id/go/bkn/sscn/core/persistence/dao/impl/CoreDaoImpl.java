package id.go.bkn.sscn.core.persistence.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.CoreDao;
import id.go.bkn.sscn.core.persistence.domain.CoreDomain;
import id.go.bkn.sscn.core.persistence.exception.CoreExceptionText;
import id.go.bkn.sscn.core.persistence.exception.CorePersistenceException;
import id.go.bkn.sscn.core.persistence.tools.PaginationResult;
import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;
import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.core.util.SqlUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate3.HibernateSystemException;

/**
 * A data access object (DAO) providing persistence and search support for T
 * entities. Transaction control of the save(), update() and remove() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @param <T>
 *            the generic type
 * @see id.go.bkn.sscn.core.persistence.domain.CoreDomain
 * @author Roberto
 */
public abstract class CoreDaoImpl<T> extends
		FindLayerDaoImpl<T> implements CoreDao<T> {

	/**
	 * constructor.
	 * 
	 * @param clazz
	 *            The same class as the typed parameter used when defining
	 *            CoreDaoImpl<T> sadly we need it to be passed in the
	 *            constructor also to be able to access class methods
	 * @param sessionFactory
	 *            This is the sessionFactory to be injected upon construction of
	 *            the bean. Remember to annotate the constructor with @Inject
	 *            annotation
	 */
	public CoreDaoImpl(Class clazz,
			SessionFactory sessionFactory) {
		super(clazz, sessionFactory);
	}

	/**
	 * Inserts the instance.
	 * 
	 * @param instance
	 *            the instance
	 * @return the t {@inheritDoc}
	 */
	public T insert(T instance) {
		LOG.debug(I18N_INSERT + clazz.getSimpleName() + I18N_INSTANCE);
		try {
			//instance.setId(""+(String) getCurrentSession().save(instance));
			getCurrentSession().save(instance);
			LOG.debug(I18N_INSERT + I18N_SUCCEED);
			return instance;
		} catch (ConstraintViolationException e) {
			LOG.error(I18N_INSERT + I18N_FAILED, e);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_DATA_ALREADY_IN_DB, e);
		} catch (DataIntegrityViolationException e) {
			LOG.error(I18N_INSERT + I18N_FAILED, e);
			if (e.getCause() instanceof PropertyValueException) {
				throw new CorePersistenceException(
						CoreExceptionText.I18N_NOT_NULL_IS_NULL, e);
			} else {
				throw new CorePersistenceException(
						CoreExceptionText.I18N_DATA_ALREADY_IN_DB, e);
			}
		} catch (Exception re) {
			LOG.error(I18N_INSERT + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * Updates the instance.
	 * 
	 * @param instance
	 *            the instance
	 * @return the t {@inheritDoc}
	 */
	public T update(T instance) {
		LOG.debug(I18N_UPDATE + clazz.getSimpleName() + I18N_INSTANCE);
		try {
			getCurrentSession().update(instance);
			LOG.debug(I18N_UPDATE + I18N_SUCCEED);
		} catch (HibernateSystemException he) {
			// If we have a "a different object with the same identifier value
			// was already associated with the session" error we will try merge
			// instead of update
			try {
				LOG.debug(I18N_UPDATE + " using merge " + I18N_SUCCEED);
				getCurrentSession().merge(instance);
				LOG.debug(I18N_UPDATE + I18N_SUCCEED);
			} catch (Exception re) {
				exceptionOnUpdate(re);
			}
		} catch (Exception re) {
			exceptionOnUpdate(re);
		}
		return instance;
	}

	/**
	 * remove object from current session.
	 * 
	 * @param coreDomain
	 */
	public final void evict(CoreDomain coreDomain) {
		this.getCurrentSession().evict(coreDomain);
	}

	private void exceptionOnUpdate(Exception re) {
		LOG.error(I18N_UPDATE + I18N_FAILED, re);
		throw new CorePersistenceException(
				CoreExceptionText.I18N_OPERATION_FAILED, re);
	}

	/**
	 * Removes the instance.
	 * 
	 * @param instance
	 *            the instance
	 * @return true, if successful {@inheritDoc}
	 */
	public boolean remove(T instance) {
		LOG.debug(I18N_REMOVE + clazz.getSimpleName() + I18N_INSTANCE);
		try {
			getCurrentSession().delete(instance);
			LOG.debug(I18N_REMOVE + I18N_SUCCEED);
			return true;
		} catch (Exception re) {
			LOG.error(I18N_REMOVE + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * Removes the bulk list of entity.
	 * 
	 * @param entities
	 *            the list of <code>CoreDomain</code> Entity.
	 * @return true, if successful {@inheritDoc}
	 */
	public boolean removeBulk(List<T> entities) {
		LOG.debug(I18N_REMOVE_BULK + clazz.getSimpleName() + I18N_INSTANCE);
		try {
			for(T entiti : entities){
				getCurrentSession().delete(entiti);
			}			
		} catch (Exception re) {
			LOG.error(I18N_REMOVE_BULK + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
		return true;
	}

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
	public boolean removeBulkUsingFilter(List<PropCriteriaAndValue> filter,
			Boolean... and) {
		LOG.debug(I18N_REMOVE_BULK + clazz.getSimpleName() + I18N_INSTANCE);
		if (filter == null || filter.isEmpty()) {
			throw new IllegalArgumentException(
					CoreExceptionText.I18N_FILTER_IS_EMPTY);
		}
		try {
			// construct hql
			StringBuilder hql = new StringBuilder(getDeleteFromQuery());
			hql.append(WHERE);
			String conjuction = determineConjuction(and);
			List<Object> parameters = new ArrayList<Object>();
			for (int i = 0; i < filter.size(); i++) {
				if (i > 0) {
					hql.append(conjuction);
				}
				PropCriteriaAndValue aFilter = filter.get(i);
				hql.append(SqlUtil.createSingleWhereClause(parameters, aFilter));
			}
			// prepare query
			Query query = createQuery(hql.toString());
			// set query parameters
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
			int deletedRows = query.executeUpdate();
			LOG.debug("NUMBER OF ROWS DELETED = " + deletedRows);
			return deletedRows > 0;
		} catch (ConstraintViolationException e) {
			return false;
		} catch (Exception re) {
			LOG.error(I18N_REMOVE_BULK + I18N_FAILED, re);
			throw new CorePersistenceException(
					CoreExceptionText.I18N_OPERATION_FAILED, re);
		}
	}

	/**
	 * determines comparation argument which either is conjunction ("AND") or
	 * disjunction ("OR"). Put <code>null</code> will make the expression is
	 * ("AND") by default. "false, true, true" will be consider as "OR",
	 * "true, false, true" will be consider as "AND" only take first element,
	 * and ignore the rest.
	 * 
	 * @param and
	 * @return
	 */
	private String determineConjuction(Boolean... and) {
		// only check first index, the rest is ignored
		if (and != null && and.length > 0 && !and[0]) {
			return OR;
		}
		// default is and, so if no value set, then return and
		return AND;
	}

	/**
	 * Paginate by property.
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
	 * @return the pagination result {@inheritDoc}
	 */
	public PaginationResult<T> paginateByProperty(String propertyName,
			Object value, List<String> leftJoinFetchColumns,
			List<QueryOrder> orders, LockMode lockMode,
			final int... rowStartIdxAndCount) {
		List<T> resultList = this.findByProperty(propertyName, value,
				leftJoinFetchColumns, orders, lockMode, rowStartIdxAndCount);
		Integer rowCount = this.countByProperty(propertyName, value);
		return new PaginationResult<T>(rowCount, resultList);
	}

	/**
	 * Paginate by map of properties.
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
	 * @return the pagination result {@inheritDoc}
	 */
	public PaginationResult<T> paginateByMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			LockMode lockMode, final int... rowStartIdxAndCount) {
		List<T> resultList = this.findByMapOfProperties(propertiesMap,
				leftJoinFetchColumns, orders, lockMode, rowStartIdxAndCount);
		Integer rowCount = this.countByMapOfProperties(propertiesMap);
		return new PaginationResult<T>(rowCount, resultList);
	}

	/**
	 * Paginate using filter.
	 * 
	 * @param leftJoinFetch
	 *            the left join fetch
	 * @param filter
	 *            the filter
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the pagination result {@inheritDoc}
	 */
	public PaginationResult<T> paginateUsingFilter(List<String> leftJoinFetch,
			List<PropCriteriaAndValue> filter, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		List<T> resultList = this.findUsingFilter(leftJoinFetch, filter,
				orders, rowStartIdxAndCount);
		Integer rowCount = this.countUsingFilter(filter);
		return new PaginationResult<T>(rowCount, resultList);
	}

	/**
	 * Paginate like map of properties.
	 * 
	 * @param propertiesMap
	 *            the properties map
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the pagination result {@inheritDoc}
	 */
	public PaginationResult<T> paginateLikeMapOfProperties(
			Map<String, ? extends Object> propertiesMap,
			List<String> leftJoinFetchColumns, List<QueryOrder> orders,
			final int... rowStartIdxAndCount) {
		List<T> resultList = this.findLikeMapOfProperties(propertiesMap,
				leftJoinFetchColumns, orders, rowStartIdxAndCount);
		Integer rowCount = this.countLikeMapOfProperties(propertiesMap);
		return new PaginationResult<T>(rowCount, resultList);
	}

	/**
	 * Paginate all.
	 * 
	 * @param leftJoinFetchColumns
	 *            the left join fetch columns
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            the row start idx and count
	 * @return the pagination result {@inheritDoc}
	 */
	public PaginationResult<T> paginateAll(List<String> leftJoinFetchColumns,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		List<T> resultList = this.findAll(leftJoinFetchColumns, orders,
				rowStartIdxAndCount);
		Integer rowCount = this.countAll();
		return new PaginationResult<T>(rowCount, resultList);
	}

	/**
	 * Paginate like property.
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
	 * @return the pagination result {@inheritDoc}
	 */
	public PaginationResult<T> paginateLikeProperty(String propertyName,
			Object value, List<String> leftJoinFetchColumns,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		List<T> resultList = this.findLikeProperty(propertyName, value,
				leftJoinFetchColumns, orders, rowStartIdxAndCount);
		Integer rowCount = this.countLikeProperty(propertyName, value);
		return new PaginationResult<T>(rowCount, resultList);
	}

}
