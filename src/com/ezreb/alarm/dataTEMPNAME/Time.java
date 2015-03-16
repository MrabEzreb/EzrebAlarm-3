package com.ezreb.alarm.dataTEMPNAME;

import java.util.Calendar;

import com.ezreb.alarm.util.OnStart;

public class Time implements StorableData {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -774853234891355223L;
	private static Time currentTime;
	/**
	 * @param h hour
	 * @param m minute
	 * @param s second
	 */
	public Time(int h, int m, int s) {
		this.h = h;
		this.m = m;
		this.s = s;
	}
	private Time(Calendar c) {
		this.h = c.get(Calendar.HOUR_OF_DAY);
		this.m = c.get(Calendar.MINUTE);
		this.s = c.get(Calendar.SECOND);
	}
	private int h;
	private int m;
	private int s;
	@Override
	public String toString() {
		String h2 = h+"";
		String m2 = m+"";
		String s2 = s+"";
		if(h2.length() == 1) {
			h2 = 0+h2;
		}
		if(m2.length() == 1) {
			m2 = 0+m2;
		}
		if(s2.length() == 1) {
			s2 = 0+s2;
		}
		return h2+":"+m2+":"+s2;
	}
	@Override
	public boolean equals(Object obj) {
		try {
			return ((Time) obj).h == this.h && ((Time) obj).m == this.m && ((Time) obj).s == this.s;
		} catch(ClassCastException c) {
			return false;
		}
	}
	@OnStart
	private void refreshTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					currentTime = new Time(Calendar.getInstance());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	/**
	 * @return the current Time
	 */
	public static Time getCurrentTime() {
		currentTime = new Time(Calendar.getInstance());
		return currentTime;
	}
}
