package com.ezreb.alarm.util.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataOutputStream {

	private FileType fileType;
	public DataOutputStream(FileType type) {
		this.fileType = type;
	}
	public void writeObject(File parent, String name, Object object) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try {
			fileOut = new FileOutputStream(new File(parent, name+"."+this.fileType.extension));
			System.out.println(new File(parent, name+"."+this.fileType.extension).getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			objectOut = new ObjectOutputStream(fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			objectOut.writeObject(object);
			objectOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
