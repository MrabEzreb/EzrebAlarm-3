package com.ezreb.alarm;

import java.io.IOException;

import com.ezreb.alarm.dataTEMPNAME.configuration.LoggerConfiguration;
import com.ezreb.alarm.util.Logger;
import com.ezreb.alarm.util.OnStartHandler;

public class EzrebAlarm {

	public static void main(String[] args) {
		OnStartHandler.load();
		OnStartTest();
	}
	
	public static void OnStartTest() {
		Logger.printErr(LoggerConfiguration.logConfiguration.extraDebug+"", "test1");
		byte[] bytes = new byte[1];
		try {
			System.in.read(bytes);
			String s = new String(bytes);
			System.out.println(s);
			if(s.equals("1")) {
				LoggerConfiguration.logConfiguration.extraDebug = true;
			} else {
				LoggerConfiguration.logConfiguration.extraDebug = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.printErr(""+LoggerConfiguration.logConfiguration.extraDebug, "etst");
	}

}
