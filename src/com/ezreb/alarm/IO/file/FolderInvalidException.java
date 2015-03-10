package com.ezreb.alarm.IO.file;

public class FolderInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -114335725858299436L;

	public FileFolder cause;

	public FolderInvalidException(FileFolder cause) {
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return "FileFolder \"" + cause.getPath() + "\" is invalid.";
	}

}
