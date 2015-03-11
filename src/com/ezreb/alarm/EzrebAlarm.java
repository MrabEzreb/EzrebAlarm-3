package com.ezreb.alarm;

import com.ezreb.alarm.util.Logger;
import com.ezreb.alarm.util.OnStartHandler;

public class EzrebAlarm {

	public static void main(String[] args) {
		OnStartHandler.load();
		OnStartTest();
	}
	
	public static void OnStartTest() {
		Logger.printErr("test", "test2");
	}

}
