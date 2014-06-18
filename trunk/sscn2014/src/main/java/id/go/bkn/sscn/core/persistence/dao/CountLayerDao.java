package id.go.bkn.sscn.core.persistence.dao;

import id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue;

import java.util.List;
import java.util.Map;


/**
 * @author Roberto
 * 
 */
public interface CountLayerDao<T> extends CriteriaLayerDao<T> {

	/**
	 * Counts all elements of the table.
	 * 
	 * @return Integer
	 */
	Integer countAll();

	/**
	 * Counts instances by a map of properties. All properties should be members
	 * of the entity. For foreign members do your own custom query. In case that
	 * one of the properties value is null it performs the IS NULL operation for
	 * that parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @return List<T>
	 */
	Integer countByMapOfProperties(Map<String, ? extends Object> propertiesMap);

	/**
	 * Performs the count operation equivalent to the
	 * <code>findByProperty</code> method.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @return Integer
	 */
	Integer countByProperty(String propertyName, Object value);

	/**
	 * Counts instances like a map of properties. All properties should be
	 * members of the entity. For foreign members do your own custom query. In
	 * case that one of the properties value is null it performs the IS NULL
	 * operation for that parameter.
	 * 
	 * @param propertiesMap
	 *            A map containing the properties we're looking up for and their
	 *            searched values, supports <code>null</code>
	 * @return Integer
	 */
	Integer countLikeMapOfProperties(Map<String, ? extends Object> propertiesMap);

	/**
	 * Performs the count operation equivalent to the
	 * <code>findLikeProperty</code> method.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param value
	 *            the value
	 * @return Integer
	 */
	Integer countLikeProperty(String propertyName, Object value);

	/**
	 * Counts filtered instances by several criteria <br>
	 * Finds instances using different criteria for different properties. All
	 * criteria are stored in a list and they will all be added up to the query
	 * with <em>AND</em> relationship. Note that this is not an <em>OR</em>
	 * filter.
	 * 
	 * @param filter
	 *            The filter itself, is actually a list containing
	 *            <code>PropCriteriaAndValue</code> elements, which is a set of
	 *            three elements typically composed of the name of the property,
	 *            the criteria used for comparation and the value to which that
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
	 *            <li>BEETWEEN List is mandatory as it it ordered
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
	 * @return List<T>
	 * @see id.go.bkn.sscn.core.persistence.tools.PropCriteriaAndValue
	 */
	Integer countUsingFilter(List<PropCriteriaAndValue> filter);
}