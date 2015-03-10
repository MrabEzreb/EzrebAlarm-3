package com.ezreb.alarm.IO.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class FileFolder extends File {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746755446106827481L;

	/**
	 * @param parent
	 *            the parent folder
	 * @param child
	 *            the name of this folder
	 */
	public FileFolder(File parent, String child) {
		super(parent, child);
	}

	/**
	 * @param parent
	 *            the parent folder
	 * @param child
	 *            the name of this folder
	 */
	public FileFolder(String parent, String child) {
		super(parent, child);
	}

	/**
	 * @param pathname
	 *            the full pathname, in the form
	 *            drive/folder1/folder2/folder3/etc/thisFolder
	 */
	public FileFolder(String pathname) {
		super(pathname);
	}

	/**
	 * @param uri
	 *            a uri representing this folder's path
	 */
	public FileFolder(URI uri) {
		super(uri);
	}

	/**
	 * @param original
	 *            A file to convert into a filefolder
	 */
	public FileFolder(File original) {
		super(original.getAbsolutePath());
	}

	public boolean isCorrect = isDirectory() && exists();
	public boolean hasParent = new File(getAbsolutePath().substring(0,
			getAbsolutePath().lastIndexOf("\\"))).exists();

	private void refreshValues() {
		isCorrect = isDirectory() && exists();
		hasParent = new File(getAbsolutePath().substring(0,
				getAbsolutePath().lastIndexOf("/"))).exists();
	}

	public void validate() throws FolderInvalidException {
		this.validate(0);
	}

	public void validate(int rep) throws FolderInvalidException {
		refreshValues();
		if (hasParent == false) {
			this.mkdirs();
			refreshValues();
		}
		if (isCorrect == false) {
			this.mkdirs();
			refreshValues();
		}
		if (hasParent == false && isCorrect == false && rep < 10) {
			validate(++rep);
		} else if (rep == 10) {
			throw new FolderInvalidException(this);
		}
	}

	@Override
	public boolean createNewFile() throws IOException {
		return super.mkdir();
	}

}
