package com.ezreb.alarm.dataTEMPNAME.alarm;

import com.ezreb.alarm.dataTEMPNAME.StorableData;
import com.ezreb.alarm.refrence.Refrence;
import com.ezreb.alarm.util.data.DataOutputStream;
import com.ezreb.alarm.util.data.FileType;

public class AlarmSet extends StorableData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8728084873280556917L;
	
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
