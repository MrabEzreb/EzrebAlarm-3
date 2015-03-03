package com.ezreb.alarm.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataInputStream {

	private FileType fileType;
	public DataInputStream(FileType type) {
		this.fileType = type;
	}
	public Object readObject(File parent, String name) {
		FileInputStream fileOut = null;
		ObjectInputStream objectOut = null;
		try {
			fileOut = new FileInputStream(new File(parent, name+"."+this.fileType.extension));
			System.out.println(new File(parent, name+"."+this.fileType.extension).getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			objectOut = new ObjectInputStream(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Object object = objectOut.readObject();
			return object;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
