package com.ezreb.alarm.refrence;

import java.io.File;

public class Refrence {

	
	public static final File home = new File(System.getProperty("user.home"), "AppData/Roaming/Ezreb/EzrebAlarm");
	public static final File alarms = new File(home, "Alarms");
	public static final File actions = new File(home, "Actions");
	static {
		System.out.println("");
		Refrence.home.mkdirs();
		Refrence.alarms.mkdirs();
		actions.mkdirs();
	}
}
