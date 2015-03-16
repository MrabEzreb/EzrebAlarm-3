package com.ezreb.alarm.dataTEMPNAME.alarm;

import com.ezreb.alarm.dataTEMPNAME.Time;

public class AlarmThread extends Thread {

	private Alarm alarm;
	public AlarmThread(final Alarm alarm) {
		super(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(alarm.getTime().equals(Time.getCurrentTime()) == false) {
						Thread.sleep(500);
					}
					alarm.getAction().run();
				} catch(InterruptedException ie) {
					return;
				}
			}
		});
		this.alarm = alarm;
	}
	/**
	 * @return the alarm
	 */
	public Alarm getAlarm() {
		return alarm;
	}
}
