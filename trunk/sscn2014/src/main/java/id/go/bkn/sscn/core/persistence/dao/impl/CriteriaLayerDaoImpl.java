package id.go.bkn.sscn.core.persistence.dao.impl;

import id.go.bkn.sscn.core.persistence.dao.CriteriaLayerDao;
import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;
import id.go.bkn.sscn.core.persistence.tools.QueryComparator;
import id.go.bkn.sscn.core.persistence.tools.QueryOrder;
import id.go.bkn.sscn.core.persistence.tools.QueryOrderDirection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.IncompleteArgumentException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * @author Roberto
 * 
 */
public class CriteriaLayerDaoImpl<T> extends
		KernelDaoImpl<T> implements CriteriaLayerDao<T> {

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
	public CriteriaLayerDaoImpl(Class clazz,
			SessionFactory sessionFactory) {
		super(clazz, sessionFactory);
	}

	private interface CriterionCreator {
		Criterion create(String propertyName, Object value);
	}

	private static final Map<QueryComparator, CriterionCreator> CRITERION_CREATOR_MAP = new HashMap<QueryComparator, CriterionCreator>();
	static {
		CRITERION_CREATOR_MAP.put(QueryComparator.EQUALS,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.eq(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.GREATER_OR_EQUAL,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.ge(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.GREATER_THAN,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.gt(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.LESS_THAN,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.lt(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.LESS_OR_EQUAL,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.le(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.NOT_EQUAL,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.ne(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.LIKE, new CriterionCreator() {
			public Criterion create(String propertyName, Object value) {
				return Restrictions.ilike(propertyName, value);
			}
		});
		CRITERION_CREATOR_MAP.put(QueryComparator.BETWEEN,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return createBetweenCriterion(propertyName, value);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.IN, new CriterionCreator() {
			public Criterion create(String propertyName, Object value) {
				return createInCriterion(propertyName, value);
			}
		});
		CRITERION_CREATOR_MAP.put(QueryComparator.IS_NOT_NULL,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.isNotNull(propertyName);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.IS_NULL,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.isNull(propertyName);
					}
				});
		CRITERION_CREATOR_MAP.put(QueryComparator.SQL_STRING_RESTRICTION,
				new CriterionCreator() {
					public Criterion create(String propertyName, Object value) {
						return Restrictions.sqlRestriction(value.toString());
					}
				});
	}

	/**
	 * Auxiliar method to decrease the size of filter methods, abstracting the
	 * algorithm used for BEETWEEN criteria.
	 * 
	 * @param propName
	 *            the prop name
	 * @param propValue
	 *            the prop value
	 * @return the criterion
	 */
	@SuppressWarnings(UNCHECKED)
	protected static Criterion createBetweenCriterion(String propName,
			Object propValue) {
		if (propValue instanceof List) {
			List<Object> propValues = (List<Object>) propValue;
			if (propValues.size() > 1) {
				Object valueMin = propValues.get(0);
				Object valueMax = propValues.get(1);
				return Restrictions.between(propName, valueMin, valueMax);
			} else {
				throw new IncompleteArgumentException(
						"Criteria was BEETWEEN yet to the List of values there wasn't passed a pair of values.");
			}
		} else {
			throw new IllegalArgumentException(
					"Criteria was BEETWEEN yet no List nor Collection of two values were passed with the two values to be inbeetween of.");
		}
	}

	/**
	 * Auxiliar method to decrease the size of filter methods, abstracting the
	 * algorithm used for IN criteria.
	 * 
	 * @param propName
	 *            the prop name
	 * @param propValue
	 *            the prop value
	 * @return the criterion
	 */
	@SuppressWarnings(UNCHECKED)
	protected static Criterion createInCriterion(String propName,
			Object propValue) {
		if (propValue instanceof Collection) {
			Collection propValues = (Collection) propValue;
			return Restrictions.in(propName, propValues);
		} else {
			throw new IllegalArgumentException(
					"Criteria was IN yet no Collection of values was passed as argument of values.");
		}
	}

	/**
	 * Creates the criteria.
	 * 
	 * @return Criteria
	 */
	protected Criteria createCriteria() {
		return getCurrentSession().createCriteria(clazz);
	}

	/**
	 * Creates the criteria.
	 * 
	 * @param alias
	 *            the alias
	 * @return Criteria
	 */
	protected Criteria createCriteria(String alias) {
		return getCurrentSession().createCriteria(clazz, alias);
	}

	/**
	 * Auxiliary method to encapsulate the whole creation of the Hibernate
	 * Criteria API Object.
	 * 
	 * @param leftJoinFetch
	 *            the left join fetch
	 * @param filter
	 *            See <code>findUsingFilter</code> method's javaDoc for more
	 *            information on how to use filter
	 * @param orders
	 *            the orders
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results.<br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return Criteria
	 */
	protected Criteria createHibernateCriteriaObject(
			List<String> leftJoinFetch, List<PropCriteriaAndValue> filter,
			List<QueryOrder> orders, final int... rowStartIdxAndCount) {
		Criteria criteria = createCriteria();
		if (leftJoinFetch != null) {
			criteria = addLeftJoinFetchPhraseToCriteria(criteria, leftJoinFetch);
		}
		criteria = addRestrictionsToCriteria(criteria, filter);
		if (orders != null) {
			criteria = addOrderToCriteria(criteria, orders);
		}
		if (rowStartIdxAndCount != null) {
			criteria = addPaginationToCriteria(criteria, rowStartIdxAndCount);
		}
		return criteria;
	}

	/**
	 * Auxiliary method to encapsulate the logic of using in Hibernate Criteria
	 * API the pagination argument.
	 * 
	 * @param criteria
	 *            the criteria
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. <br>
	 *            rowStartIdxAndCount[0] specifies the the row index in the
	 *            query result-set to begin collecting the results.<br>
	 *            rowStartIdxAndCount[1] specifies the the maximum number of
	 *            results to return.
	 * @return the criteria
	 */
	protected Criteria addPaginationToCriteria(Criteria criteria,
			final int... rowStartIdxAndCount) {
		if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
			int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
			if (rowStartIdx > 0) {
				criteria.setFirstResult(rowStartIdx);
			}
			if (rowStartIdxAndCount.length > 1) {
				int rowCount = Math.max(0, rowStartIdxAndCount[1]);
				if (rowCount > 0) {
					criteria.setMaxResults(rowCount);
				}
			}
		}
		return criteria;
	}

	/**
	 * Auxiliar method to encapsulate the logic of aggregation of all
	 * restrictions of the filter.
	 * 
	 * @param criteria
	 *            the criteria
	 * @param filter
	 *            A list containing <code>PropCriteriaAndValue</code> elements,
	 *            which is a set of three elements typically composed of the
	 *            name of the property, the criteria used for comparation and
	 *            the value to which that property should be compared. However
	 *            there are some criteria in which value should be null, or in
	 *            which the value should be a collection of values. <br>
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
	 * @return the criteria
	 */
	private static Criteria addRestrictionsToCriteria(Criteria criteria,
			List<PropCriteriaAndValue> filter) {
		for (PropCriteriaAndValue propCritAndVal : filter) {
			String propName = propCritAndVal.getPropertyName();
			Object propValue = propCritAndVal.getValue();
			QueryComparator comparator = propCritAndVal.getComparator();
			CriterionCreator creator = CRITERION_CREATOR_MAP.get(comparator);
			if (creator != null) {
				criteria.add(creator.create(propName, propValue));
			} else {
				throw new UnsupportedOperationException(comparator.getValue());
			}
		}
		return criteria;
	}

	/**
	 * Adds the left join fetch phrase.
	 * 
	 * @param criteria
	 *            the criteria
	 * @param joinProperties
	 *            the join properties
	 * @return the criteria
	 */
	protected static Criteria addLeftJoinFetchPhraseToCriteria(
			Criteria criteria, List<String> joinProperties) {
		for (String prop : joinProperties) {
			if (prop != null) {
				prop = prop.trim();
				if (!prop.isEmpty()) {
					criteria.setFetchMode(prop, FetchMode.JOIN);
				}
			}
		}
		return criteria;
	}

	/**
	 * Auxiliary method which encapsulates the aggregation of the order
	 * established to the query to the Hibernate criteria.
	 * 
	 * @param criteria
	 *            the criteria
	 * @param orders
	 *            the orders
	 * @return the criteria
	 */
	protected static Criteria addOrderToCriteria(Criteria criteria,
			List<QueryOrder> orders) {
		for (QueryOrder order : orders) {
			String propertyName = order.getPropertyName();
			switch (order.getDirection()) {
			case ASC:
				if (propertyName.contains(".") && !propertyName.contains(".id")) {
					addOrderToSubCriteria(criteria, order);
				} else {
					criteria.addOrder(Order.asc(propertyName));
				}
				break;
			case DESC:
				if (propertyName.contains(".") && !propertyName.contains(".id")) {
					addOrderToSubCriteria(criteria, order);
				} else {
					criteria.addOrder(Order.desc(propertyName));
				}
				break;
			default:
				// Must never happen, but good practice:
				throw new UnsupportedOperationException(
						"Query direction not ASC not DESC!!");
			}
		}
		return criteria;
	}

	/**
	 * Add order with sub criteria. Called whenever the order is grouped by
	 * child property
	 * 
	 * @param criteria
	 *            hibernate criteria.
	 * @param order
	 *            <code>QueryOrder</code>
	 */
	private static void addOrderToSubCriteria(Criteria criteria,
			QueryOrder order) {
		String[] properties = StringUtils.split(order.getPropertyName(), '.');
		Criteria parentCriteria = criteria;
		int length = properties.length;
		for (int i = 0; i < length; i++) {
			int remaining = length - i;
			// 2 is defined as the first string is child object name, and last
			// string is property name of child object.
			if (remaining == 2) {
				Criteria subCriteria = parentCriteria
						.createCriteria(properties[i]);
				if (QueryOrderDirection.ASC.equals(order.getDirection())) {
					subCriteria.addOrder(Order.asc(properties[i + 1]));
				} else {
					subCriteria.addOrder(Order.desc(properties[i + 1]));
				}
			} else if (remaining > 2) {
				Criteria subCriteria = parentCriteria
						.createCriteria(properties[i]);
				parentCriteria = subCriteria;
			}
		}
	}

}
