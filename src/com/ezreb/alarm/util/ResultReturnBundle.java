package com.ezreb.alarm.util;

public class ResultReturnBundle {

	private boolean successful;
	private Object data;

	public ResultReturnBundle(boolean result, Object data) {
		this.successful = result;
		this.data = data;
	}

	/**
	 * @return whether the operation was successful
	 */
	public boolean isSuccessful() {
		return successful;
	}

	/**
	 * @return the data returned
	 */
	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return successful + data.toString();
	}
}
