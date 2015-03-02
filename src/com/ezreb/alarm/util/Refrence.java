package com.ezreb.alarm.util;

import java.io.File;

public class Refrence {

	
	public static final File home = new File(System.getProperty("user.home"), "/Roaming/Ezreb/EzrebAlarm");
	public static final File alarms = new File(home, "Alarms");
	static {
	}
}
