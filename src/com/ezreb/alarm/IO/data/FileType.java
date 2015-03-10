package com.ezreb.alarm.IO.data;

import com.ezreb.alarm.dataTEMPNAME.alarm.Alarm;
import com.ezreb.alarm.dataTEMPNAME.alarm.AlarmSet;
import com.ezreb.alarm.dataTEMPNAME.configuration.Configuration;

public enum FileType {

	ALARM("alarm", Alarm.class), ALARM_SET("alarms", AlarmSet.class), ALARM_ARCHIVE(
			"alarmArchive", Alarm.class), CONFIGURATION("ezrebConfig",
			Configuration.class);

	public String extension;
	public Class<?> classRef;

	private FileType(String fileExtension, Class<?> classRef) {
		this.extension = fileExtension;
		this.classRef = classRef;
	}
}
