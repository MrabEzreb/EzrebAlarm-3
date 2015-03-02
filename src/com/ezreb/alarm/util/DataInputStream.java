package com.ezreb.alarm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataInputStream {

	private FileInputStream fileOut;
	private ObjectInputStream objectOut;
	public DataInputStream(File file) {
		try {
			this.fileOut = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.objectOut = new ObjectInputStream(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Object readObject() {
		try {
			return this.objectOut.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
