package id.go.bkn.sscn.core.persistence.tools;

import java.io.Serializable;

public class QueryOrder implements Serializable{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2070749793749118252L;

	/** The property name. */
	private final String propertyName;

	/** The direction. */
	private final QueryOrderDirection direction;

	/**
	 * Complete constructor.
	 * 
	 * @param propertyName
	 *            The name of the property.
	 * @param direction
	 *            The direction of the order: ASC or DESC
	 */
	public QueryOrder(String propertyName, QueryOrderDirection direction) {
		if (propertyName == null || propertyName.trim().isEmpty()) {
			throw new IllegalArgumentException("Property name cannot be null or empty.");
		}
		this.propertyName = propertyName.trim();
		this.direction = direction;
	}

	/**
	 * Minimal constructor.
	 * 
	 * @param propertyName
	 *            The name of the property.
	 */
	public QueryOrder(String propertyName) {
		this(propertyName, null);
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
	 * Gets the order direction. If non was set, returns ASC wich is the
	 * default.
	 * 
	 * @return Query direction.
	 * @author Roberto
	 */
	public QueryOrderDirection getDirection() {
		return (direction != null ? this.direction : QueryOrderDirection.ASC);
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 * @author Roberto
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{").append(propertyName);
		if (direction != null) {
			sb.append(" ").append(direction);
		}
		sb.append("}");
		return sb.toString();
	}
}
