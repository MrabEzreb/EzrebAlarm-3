package com.ezreb.alarm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataOutputStream {

	private FileOutputStream fileOut;
	private ObjectOutputStream objectOut;
	public DataOutputStream(File file) {
		try {
			this.fileOut = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.objectOut = new ObjectOutputStream(fileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeObject(Object object) {
		try {
			this.objectOut.writeObject(object);
			this.objectOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
