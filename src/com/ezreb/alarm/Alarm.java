package com.ezreb.alarm;

import java.io.File;
import java.io.Serializable;

import com.ezreb.alarm.data.DataInputStream;
import com.ezreb.alarm.data.DataOutputStream;
import com.ezreb.alarm.data.FileType;
import com.ezreb.alarm.util.Refrence;

public class Alarm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120772799072450134L;
	public Alarm(String name) {
		this.name = name;
	}
	private String name;
	public void saveToFile() {
		DataOutputStream out = new DataOutputStream(FileType.ALARM);
		out.writeObject(Refrence.alarms, name, this);
	}
	public static void main(String[] args) {
		Alarm a = new Alarm("Test");
		a.saveToFile();
		AlarmSet a2 = new AlarmSet("TestSet");
		a2.addAlarm(a);
		a2.addAlarm(new Alarm("testing2"));
		a2.saveToFile();
	}
}
