package com.ezreb.alarm.util.data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataInputStream {

	private FileType fileType;
	public DataInputStream(FileType type) {
		this.fileType = type;
	}
	public Object readObject(File parent, String name) throws IOException, ClassNotFoundException, EOFException {
		FileInputStream fileOut = null;
		ObjectInputStream objectOut = null;
		try {
			fileOut = new FileInputStream(new File(parent, name+"."+this.fileType.extension));
			System.out.println(new File(parent, name+"."+this.fileType.extension).getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		objectOut = new ObjectInputStream(fileOut);
		Object object = objectOut.readObject();
		try {
			objectOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
}
