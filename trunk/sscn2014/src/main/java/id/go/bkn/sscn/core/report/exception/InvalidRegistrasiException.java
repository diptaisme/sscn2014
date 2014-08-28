package id.go.bkn.sscn.core.report.exception;

/**
 * The Class InvalidRegistrasi Exception.
 */
public class InvalidRegistrasiException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -527217351114547939L;

	/**
	 * Instantiates a new report exception.
	 * 
	 * @param message
	 *            the message
	 */
	public InvalidRegistrasiException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new report exception.
	 */
	public InvalidRegistrasiException() {
		super();
	}

	/**
	 * Instantiates a new report exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public InvalidRegistrasiException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new report exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public InvalidRegistrasiException(Throwable cause) {
		super(cause);
	}
}
