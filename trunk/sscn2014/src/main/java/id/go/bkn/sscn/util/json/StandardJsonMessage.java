package id.go.bkn.sscn.util.json;

import java.util.Map;

public class StandardJsonMessage {
	public StandardJsonMessage(int result, Object data, Map<String, Object> mapData, String message) {
		super();
		this.result = result;
		this.data = data;
		this.message = message;
		this.mapData = mapData;
	}
	
	
	private int result;
	private Object data;
	private Map<String, Object> mapData;
	private String message;
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the mapData
	 */
	public Map<String, Object> getMapData() {
		return mapData;
	}
	/**
	 * @param mapData the mapData to set
	 */
	public void setMapData(Map<String, Object> mapData) {
		this.mapData = mapData;
	}
	
	
}
