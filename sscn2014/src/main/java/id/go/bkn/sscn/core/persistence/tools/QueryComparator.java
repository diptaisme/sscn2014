package id.go.bkn.sscn.core.persistence.tools;

/**
 * Created to be used to establish whether a query should look up equal elements
 * or using a LIKE criteria.
 * 
 * @author Roberto
 */
public enum QueryComparator {
	/**
	 * To be used to perform queries using a = criteria in HQL. When using
	 * hibernate Criteria API this is a = represented eq
	 */
	EQUALS("="),

	/**
	 * To be used to perform queries using a LIKE criteria (is case
	 * insensitive).
	 */
	PREFIXLIKE("PREFIXLIKE"),
	
	/**
	 * To be used to perform queries using a LIKE criteria (is case
	 * insensitive).
	 */
	LIKE("LIKE"),

	/** When using hibernate Criteria API this is a >. */
	GREATER_THAN("gt", ">"),

	/** When using hibernate Criteria API this is a <. */
	LESS_THAN("lt", "<"),

	/** When using hibernate Criteria API this is a !=. */
	NOT_EQUAL("ne", "<>"),

	/** When using hibernate Criteria API this is a >=. */
	GREATER_OR_EQUAL("ge", ">="),

	/** When using hibernate Criteria API this is a <=. */
	LESS_OR_EQUAL("le", "<="),
	/**
	 * When using hibernate Criteria API this is a BETWEEN. <br>
	 * Will require a List (advisable, because is ordered) or a Collection
	 * (supported) and will use only first and second elements.
	 */
	BETWEEN("between"),
	/**
	 * When using hibernate Criteria API this is a IN. <br>
	 * Will require a Collection
	 */
	IN("in"),

	/**
	 * When using hibernate Criteria API this is a IS NULL <br>
	 * Will work regardless propertyValue.
	 */
	IS_NULL("isNull", "is null"),

	/**
	 * When using hibernate Criteria API this is a IS NOT NULL <br>
	 * Will work regardless propertyValue.
	 */
	IS_NOT_NULL("isNotNull", "is not null"),

	/**
	 * When using hibernate Criteria API this is a sqlRestriction <br>
	 * .
	 */
	SQL_STRING_RESTRICTION("sqlRestriction");

	/** The value. */
	private String value;

	/**
	 * the Symbol
	 */
	private String symbol;

	/**
	 * constructor.
	 * 
	 * @param value
	 *            the value	 * 
	 */
	private QueryComparator(String value) {
		this(value, value);

	}

	/**
	 * Constructor
	 * 
	 * @param value
	 *            The value.
	 * @param symbol
	 *            The symbol.
	 */
	private QueryComparator(String value, String symbol) {
		this.value = value;
		this.symbol = symbol;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * it returns a string representing the SQL string equivalent to the value
	 * of the comparator, while getValue returns to Hibernate Criteria API
	 * string.
	 * 
	 * @return the string
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return symbol;
	}
}

