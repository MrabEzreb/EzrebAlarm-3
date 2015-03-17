package com.ezreb.alarm.dataTEMPNAME.alarm;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.DefaultComboBoxModel;

import com.ezreb.alarm.IO.data.DataOutputStream;
import com.ezreb.alarm.IO.data.FileType;
import com.ezreb.alarm.dataTEMPNAME.Compressable;
import com.ezreb.alarm.dataTEMPNAME.Time;
import com.ezreb.alarm.dataTEMPNAME.action.Action;
import com.ezreb.alarm.refrence.FileRefrence;
import com.ezreb.alarm.util.ResultReturnBundle;
import com.ezreb.alarm.window.ManageAlarms;

public class Alarm implements Compressable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5120772799072450134L;

	private static Map<String, Alarm> alarms = new HashMap<String, Alarm>();
	private static DefaultComboBoxModel<String> comboBox = new DefaultComboBoxModel<String>();
	public Alarm(String name, Time time, Action action) {
		this.name = name;
		this.time = time;
		this.action = action;
	}
	
	public Alarm(String name, Time time) {
		this.name = name;
		this.time = time;
	}

	public Alarm(String name) {
		this.name = name;
	}

	private Time time;
	private String name;
	private Action action;
	private AlarmThread thread;
	private boolean running = false;

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

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
	
	
	
	
	//Generated Stuff
	/**
	 * @return the running
	 */
	public boolean isRunning() {
		return running;
	}
	/**
	 * 
	 * @see java.lang.Thread#interrupt()
	 */
	public void interrupt() {
		thread.interrupt();
		this.running = false;
		ManageAlarms.comboBox.removeItem(this.getName());
		alarms.remove(this);
	}
	/**
	 * 
	 * @see java.lang.Thread#start()
	 */
	public void start() {
		this.thread = new AlarmThread(this);
		thread.start();
		this.running = true;
		ManageAlarms.comboBox.addItem(this.getName());
		alarms.put(name, this);
	}

	/**
	 * @return the time
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * @return the comboBox
	 */
	public static DefaultComboBoxModel<String> getComboBox() {
		return comboBox;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return this.name+"@"+this.time.toString();
	}

	/**
	 * @param arg0
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public static boolean containsKey(Object arg0) {
		return alarms.containsKey(arg0);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public static boolean containsValue(Object value) {
		return alarms.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public static Alarm get(Object key) {
		return alarms.get(key);
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public static boolean isEmpty() {
		return alarms.isEmpty();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	public static Set<String> keySet() {
		return alarms.keySet();
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public static Alarm put(String key, Alarm value) {
		return alarms.put(key, value);
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	public static void putAll(Map<? extends String, ? extends Alarm> m) {
		alarms.putAll(m);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public static Alarm remove(Object key) {
		return alarms.remove(key);
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	public static int size() {
		return alarms.size();
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	public static Collection<Alarm> values() {
		return alarms.values();
	}
}