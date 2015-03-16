package com.ezreb.alarm;

import java.io.IOException;

import com.ezreb.alarm.IO.data.DataInputStream;
import com.ezreb.alarm.IO.data.FileType;
import com.ezreb.alarm.dataTEMPNAME.Time;
import com.ezreb.alarm.dataTEMPNAME.alarm.Alarm;
import com.ezreb.alarm.dataTEMPNAME.alarm.AlarmSet;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveCorruptedException;
import com.ezreb.alarm.dataTEMPNAME.configuration.LoggerConfiguration;
import com.ezreb.alarm.refrence.FileRefrence;
import com.ezreb.alarm.util.Logger;
import com.ezreb.alarm.util.OnStartHandler;
import com.ezreb.alarm.window.ManageAlarms;

public class EzrebAlarm {

	public static void main(String[] args) {
		OnStartHandler.load();
		windowTest();
	}
	
	public static void OnStartTest() {
		Logger.printErr(LoggerConfiguration.logConfiguration.isExtraDebug()+"", "test1");
		byte[] bytes = new byte[1];
		try {
			System.in.read(bytes);
			String s = new String(bytes);
			System.out.println(s);
			if(s.equals("1")) {
				LoggerConfiguration.logConfiguration.setExtraDebug(true);
			} else {
				LoggerConfiguration.logConfiguration.setExtraDebug(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger.printErr(""+LoggerConfiguration.logConfiguration.isExtraDebug(), "etst");
	}
	
	public static void windowTest() {
		ManageAlarms ma = new ManageAlarms();
		Alarm a1 = new Alarm("test1", new Time(15, 5, 5));
		Alarm a2 = new Alarm("poopy", new Time(5, 5, 5));
		Alarm a3 = new Alarm("amazing toilet tm", new Time(5, 5, 5));
		Alarm a4 = new Alarm("such wow", new Time(5, 5, 5));
		Alarm a5 = new Alarm(")D", new Time(5, 5, 5));
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		ma.setVisible(true);
	}
	
	public static void alarmTest() {
		AlarmSet as = new AlarmSet("Test Alarm Set");
		Alarm a1 = new Alarm("test1");
		Alarm a2 = new Alarm("poopy");
		Alarm a3 = new Alarm("amazing toilet tm");
		Alarm a4 = new Alarm("such wow");
		Alarm a5 = new Alarm(")D");
		as.addAlarm(a1);
		as.addAlarm(a2);
		as.addAlarm(a3);
		as.addAlarm(a4);
		as.addAlarm(a5);
		System.out.println(as.alarms.length);
		as.saveToFile();
		System.out.println("30 seconds");
		DataInputStream dis = new DataInputStream(FileType.ALARM_SET);
		try {
			AlarmSet as2 = (AlarmSet) dis.readObject(FileRefrence.alarms,
					"Test Alarm Set");
			System.out.println(as2.name);
			System.out.println(as2.alarms.length);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (ArchiveCorruptedException e) {
			e.printStackTrace();
		}
	}
	

}
