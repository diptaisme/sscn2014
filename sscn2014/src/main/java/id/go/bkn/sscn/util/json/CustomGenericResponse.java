package id.go.bkn.sscn.util.json;


/**
 * A custom POJO that will be automatically converted to JSON format.
 * <p>
 * We can use this to send generic messages to our JqGrid, whether a request is
 * successful or not. Of course, you will use plain JavaScript to parse the JSON
 * response.
 */
public class CustomGenericResponse {

	/**
	 * true if successful.
	 */
	private String success;

	/**
	 * Any custom message, i.e, 'Your request has been processed successfully!'
	 */
	private String message;

	public CustomGenericResponse() {
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message=message;
	}

}
