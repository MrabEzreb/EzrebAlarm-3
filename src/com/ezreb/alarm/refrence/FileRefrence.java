package com.ezreb.alarm.refrence;

import java.io.File;

import com.ezreb.alarm.IO.file.FileFolder;
import com.ezreb.alarm.util.OnStart;

public class FileRefrence {

	public static final FileFolder home = new FileFolder(new File(System.getProperty("user.home"), "AppData/Roaming/Ezreb/EzrebAlarm"));
	public static final FileFolder alarms = new FileFolder(new File(home, "Alarms"));
	public static final FileFolder actions = new FileFolder(new File(home, "Actions"));
	public static final FileFolder configurations = new FileFolder(new File(home, "configuration"));
	@OnStart
	public static void make() {
		System.out.println("");
		home.mkdirs();
		alarms.mkdirs();
		actions.mkdirs();
		configurations.mkdirs();
	}
}
