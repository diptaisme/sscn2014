package id.go.bkn.sscn.core.persistence.tools;

import java.io.Serializable;


/**
 * Class that encapsulates the three components set of a filter with several
 * elements and variable criteria for each element. This three elements of the
 * filter are the propertyName, the comparator and the value. The comparator is
 * of type queryComparator and typically requires a value to be compared with,
 * but in the case of some special comparators it requires a null value (or just
 * asumes value is null) or requires a collection of values as the value.
 *
 * 
 * @see org.sscn.core.persistence.tools.QueyComparator
 * 
 */
public class PropCriteriaAndValue implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2214899919968643761L;

	/** The property name. */
	private String propertyName;

	/** The comparator. */
	private QueryComparator comparator;

	/** The value. */
	private Object value;

	
	
	public PropCriteriaAndValue() {
		super();
	}

	/**
	 * Complete constructor.
	 * 
	 * @param propertyName
	 *            The name of the property.
	 * @param comparator
	 *            The comparator that is going to be used for that property
	 * @param value
	 *            The value to search for using the established
	 *            comparator.<strong>Value depends on what you choose as
	 *            comparator</strong>, following the next table:<br>
	 *            <ul>
	 *            <li>Property will be compared with one value:
	 *            <ul>
	 *            <li>EQUALS
	 *            <li>GREATER_OR_EQUAL
	 *            <li>GREATER_THAN
	 *            <li>LESS_OR_EQUAL
	 *            <li>LESS_THAN
	 *            <li>NOT_EQUAL
	 *            <li>LIKE
	 *            </ul>
	 *            <li>Property will be compared with value which is a List or
	 *            Collection:
	 *            <ul>
	 *            <li>BEETWEEN List is advisable as it it ordered, although
	 *            collection is supported
	 *            <li>IN
	 *            </ul>
	 *            <li>Property will be checked against criteria regardless value
	 *            content (so value can be null, or anything):
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
	 * 
	 */
	public PropCriteriaAndValue(String propertyName, QueryComparator comparator, Object value) {
		if (propertyName == null || propertyName.trim().isEmpty()) {
			throw new IllegalArgumentException("Property name cannot be null or empty.");
		}
		this.propertyName = propertyName.trim();
		this.comparator = comparator;
		this.value = value;
	}

	/**
	 * Get the property name.
	 * 
	 * @return The property name.
	 */
	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * Get the comparator used to search for the value.
	 * 
	 * @return QueryComparator comparator.
	 */
	public QueryComparator getComparator() {
		return this.comparator;
	}

	/**
	 * Get the value we are looking for.
	 * 
	 * @return Object value
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Sets the property name.
	 * 
	 * @param propertyName
	 *            the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Sets the comparator.
	 * 
	 * @param comparator
	 *            the comparator to set
	 */
	public void setComparator(QueryComparator comparator) {
		this.comparator = comparator;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Returns an String representation of the object.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("{").append(this.getPropertyName()).append(" ");
		out.append(this.getComparator().getValue()).append(" ");
		out.append(this.getValue()).append("}");
		return out.toString();
	}
}

