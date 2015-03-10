package com.ezreb.alarm.dataTEMPNAME.archive;

public class ArchiveCorruptedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4202925437957299858L;

	public enum Reason {
		
		FILE_MISSING("File missing", "Figure out what is missing and fix it."),
		UNKNOWN_FILE("Unknown file", "Remove unknown file from the archive."),
		INDEX_MISSING("File index.archiveIndex is missing from archive.", "Either dynamically generate an index on read, or create it on write.");
		
		public String reason;
		public String solution;
		private Reason(String reason, String solution) {
			this.reason = reason;
			this.solution = solution;
		}
	}
	private Archive a;
	private Reason message;
	public ArchiveCorruptedException(Archive a, Reason message) {
		this.a = a;
		this.message = message;
	}
	@Override
	public String getMessage() {
		return "Archive "+a.getIndex().name+" is corrupted/invalid.";
	}
	public String getReason() {
		 return "Reason: "+this.message.reason;
	}
	public String getSolution() {
		return "Possible Solution(s): "+this.message.solution;
	}
	@Override
	public void printStackTrace() {
		super.printStackTrace();
		System.err.println("DETAILED DATA:");
		System.err.println("An ArchiveCorruptedException occured because ");
	}

}
