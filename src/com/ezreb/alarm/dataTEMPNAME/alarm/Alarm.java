package com.ezreb.alarm.dataTEMPNAME.alarm;

import com.ezreb.alarm.dataTEMPNAME.StorableData;
import com.ezreb.alarm.dataTEMPNAME.configuration.TestConfiguration;
import com.ezreb.alarm.refrence.Refrence;
import com.ezreb.alarm.util.data.DataOutputStream;
import com.ezreb.alarm.util.data.FileType;

public class Alarm extends StorableData {

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
		out.writeObject(Refrence.alarms, name, this);
	}
	public static void main(String[] args) throws InterruptedException {
		System.out.println(TestConfiguration.testConfiguration.testValue);
		TestConfiguration.testConfiguration.testValue++;
		Thread.sleep(10000);
		System.out.println(TestConfiguration.testConfiguration.testValue);
	}
}
