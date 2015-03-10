package com.ezreb.alarm.dataTEMPNAME.alarm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ezreb.alarm.IO.data.DataInputStream;
import com.ezreb.alarm.IO.data.DataOutputStream;
import com.ezreb.alarm.IO.data.FileType;
import com.ezreb.alarm.dataTEMPNAME.Compressable;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveCorruptedException;
import com.ezreb.alarm.refrence.FileRefrence;
import com.ezreb.alarm.util.ResultReturnBundle;

public class Alarm implements Compressable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120772799072450134L;

	public Alarm(String name) {
		this.name = name;
	}

	private String name;

	public void saveToFile() {
		DataOutputStream out = new DataOutputStream(FileType.ALARM);
		out.writeObject(FileRefrence.alarms, name, this);
	}

	public ZipEntry getCompressed() {
		return new ZipEntry(this.name + ".alarmArchive");
	}

	public ResultReturnBundle writeCompressed(ZipOutputStream outputStream) {
		try {
			ZipEntry self = this.getCompressed();
			outputStream.putNextEntry(self);
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			oos.writeObject(this);
			oos.flush();
			outputStream.closeEntry();
			outputStream.flush();
		} catch (IOException e) {
			return new ResultReturnBundle(false, e);
		}
		return new ResultReturnBundle(true, outputStream);
	}

	public static void main(String[] args) throws InterruptedException {
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
		Thread.sleep(30000);
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
