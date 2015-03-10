package com.ezreb.alarm.dataTEMPNAME.alarm;

import java.util.zip.ZipEntry;

import com.ezreb.alarm.IO.data.DataOutputStream;
import com.ezreb.alarm.IO.data.FileType;
import com.ezreb.alarm.dataTEMPNAME.StorableData;
import com.ezreb.alarm.dataTEMPNAME.archive.Archive;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveIndex;
import com.ezreb.alarm.refrence.FileRefrence;

public class AlarmSet implements StorableData, Archive {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8728084873280556917L;

	public String name;

	public AlarmSet(String name) {
		this.name = name;
	}

	public Alarm[] alarms = new Alarm[0];

	public void addAlarm(Alarm a) {
		Alarm[] alarms2 = new Alarm[alarms.length + 1];
		System.arraycopy(alarms, 0, alarms2, 0, alarms.length);
		alarms2[alarms.length] = a;
		alarms = alarms2;
	}

	public void saveToFile() {
		DataOutputStream out = new DataOutputStream(FileType.ALARM_SET);
		out.writeObject(FileRefrence.alarms, name, this);
	}

	@Override
	public ArchiveIndex getIndex() {
		ArchiveIndex ai;
		ZipEntry[] zips = new ZipEntry[alarms.length];
		for (int i = 0; i < zips.length; i++) {
			zips[i] = new ZipEntry(alarms[i].getCompressed());
		}
		ai = new ArchiveIndex(zips, name);
		return ai;
	}
}
