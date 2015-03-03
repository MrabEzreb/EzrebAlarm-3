package com.ezreb.alarm.data;

import com.ezreb.alarm.Alarm;
import com.ezreb.alarm.AlarmSet;

public enum FileType {

	ALARM("alarm", Alarm.class),
	ALARM_SET("alarms", AlarmSet.class);
	
	public String extension;
	public Class<?> classRef;
	private FileType(String fileExtension, Class<?> classRef) {
		this.extension = fileExtension;
		this.classRef = classRef;
	}
}
