package com.ezreb.alarm;

import java.io.File;
import java.io.Serializable;

import com.ezreb.alarm.util.DataOutputStream;
import com.ezreb.alarm.util.Refrence;

public class Alarm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120772799072450134L;
	public Alarm(String name) {
		this.name = name;
	}
	private String name;
	public void saveToFile() {
		File saveTo = new File(Refrence.alarms, name+".alarm");
		DataOutputStream out = new DataOutputStream(saveTo);
		out.writeObject(this);
	}
	public static void main(String[] args) {
		Refrence.home.mkdirs();
		Refrence.alarms.mkdirs();
		Alarm a = new Alarm("Test");
		a.saveToFile();
	}
}
