package com.ezreb.alarm;

import java.io.Serializable;

import com.ezreb.alarm.data.DataOutputStream;
import com.ezreb.alarm.data.FileType;
import com.ezreb.alarm.util.Refrence;

public class AlarmSet implements Serializable {

	public String name;
	public AlarmSet(String name) {this.name = name;}
	public Alarm[] alarms = new Alarm[0];
	public void addAlarm(Alarm a) {
		Alarm[] alarms2 = new Alarm[alarms.length + 1];
		System.arraycopy(alarms, 0, alarms2, 0, alarms.length);
		alarms2[alarms.length] = a;
		alarms = alarms2;
	}
	public void saveToFile() {
		DataOutputStream out = new DataOutputStream(FileType.ALARM_SET);
		out.writeObject(Refrence.alarms, name, this);
	}
}
