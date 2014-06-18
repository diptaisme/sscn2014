package id.go.bkn.sscn.core.persistence.exception;

import org.hibernate.HibernateException;

/**
 * The Class CorePersistenceException.
 */
public class CorePersistenceException extends HibernateException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7311418088412161513L;

	/**
	 * Customized persistence exception for custom flex messages.
	 * 
	 * @param e
	 *            the e
	 */
	public CorePersistenceException(Throwable e) {
		super(e);
	}

	/**
	 * Customized persistence exception for custom flex messages.
	 * 
	 * @param msg
	 *            the msg
	 * @param e
	 *            the e
	 */
	public CorePersistenceException(String msg, Throwable e) {
		super(msg, e);
	}

	/**
	 * Customized persistence exception for custom flex messages.
	 * 
	 * @param s
	 *            the s
	 */
	public CorePersistenceException(String s) {
		super(s);
	}

}