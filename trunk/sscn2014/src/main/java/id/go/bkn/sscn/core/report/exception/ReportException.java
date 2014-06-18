package id.go.bkn.sscn.core.report.exception;

/**
 * The Class Report Exception.
 */
public class ReportException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -527217351114547939L;

	/**
	 * Instantiates a new report exception.
	 * 
	 * @param message
	 *            the message
	 */
	public ReportException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new report exception.
	 */
	public ReportException() {
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
	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new report exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public ReportException(Throwable cause) {
		super(cause);
	}
}
