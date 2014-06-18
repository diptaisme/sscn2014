package id.go.bkn.sscn.core.persistence.exception;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * The Class ExceptionText.
 * 
 Series of String constants for the messages to be shown on the
 *         flex side.
 */
public final class CoreExceptionText implements Serializable {
	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = -465783417496758070L;

	/** The Constant I18N_OPERATION_FAILED. */
	public static final String I18N_OPERATION_FAILED = "Operasi Gagal";

	/** The Constant I18N_TEXT_NOT_FOUND. */
	public static final String I18N_TEXT_NOT_FOUND = "Text tidak ditemukan.";

	/** The Constant I18N_DATE_PARAMETER_ERROR. */
	public static final String I18N_DATE_PARAMETER_ERROR = "Parameter tanggal tidak sesuai";

	/** The Constant I18N_NOT_NULL_IS_NULL. */
	public static final String I18N_NOT_NULL_IS_NULL = "Property tidak boleh null atau Data belum tersimpan";

	/** The Constant I18N_DATA_ALREADY_IN_DB. */
	public static final String I18N_DATA_ALREADY_IN_DB = "Data sudah ada dalam database.";

	/** The Constant I18N_FILTER_IS_EMPTY. */
	public static final String I18N_FILTER_IS_EMPTY = "Filter should not null or empty";

	/** The Constant I18N_INSTANCE_WAS_NOT_REMOVED. */
	public static final String I18N_INSTANCE_WAS_NOT_REMOVED = "Attempt to unremove an instance that wasn't removed";

	/** The Constant I18N_INSTANCE_WAS_ALREADY_REMOVED. */
	public static final String I18N_INSTANCE_WAS_ALREADY_REMOVED = "Data tidak ada dalam database.";

	/** The Constant I18N_NOT_YET_IMPLEMENTED. */
	public static final String I18N_NOT_YET_IMPLEMENTED = "Not yet implemented!!";

	/** The Constant I18N_REPORT_NOT_FOUND. */
	public static final String I18N_REPORT_NOT_FOUND = "Laporan tidak ditemukan";

	private CoreExceptionText() {
		// to prevent instantiation of this class
		super();
	}

	/**
	 * Normalize message with parameter. Parameter format would be {index}
	 * 
	 * @param message
	 * @param arguments
	 * @return final message to user.
	 */
	public static String getMessage(String message, String... arguments) {
		String result = message;
		if (arguments != null) {
			for (int i = 0; i < arguments.length; i++) {
				result = StringUtils.replace(result, "{" + i + "}",
						arguments[i]);
			}
		}
		return result;
	}
}
