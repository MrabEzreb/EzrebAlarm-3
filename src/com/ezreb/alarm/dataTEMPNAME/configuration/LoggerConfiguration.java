package com.ezreb.alarm.dataTEMPNAME.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.ezreb.alarm.refrence.FileRefrence;

@Config
public class LoggerConfiguration extends Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4135585701432689451L;
	@Config.Instance
	public static LoggerConfiguration logConfiguration = new LoggerConfiguration();
	public boolean extraDebug;
	public boolean printSource;
	public String sourceName;
	@Override
	@Config.OnLoad
	public String load() {
		super.load();
		System.out.println(new File(FileRefrence.home, "LoggerConfiguration.EAConfig").exists());
		if(new File(FileRefrence.home, "LoggerConfiguration.EAConfig").exists() == false) {
			createFile();
			//System.out.println("LOADED! WHOO!O");
			extraDebug = false;
			printSource = false;
			sourceName = "EzrebAlarm-3";
		} else {
			File configFile = new File(FileRefrence.home, "LoggerConfiguration.EAConfig");
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(configFile));
				logConfiguration = (LoggerConfiguration) ois.readObject();
				ois.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}			
		}
		return "";
	}
	@Override
	@Config.OnExit
	public void save() {
		File configFile = new File(FileRefrence.home, "LoggerConfiguration.EAConfig");
		configFile.delete();
		try {
			configFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(configFile));
			oos.writeObject(logConfiguration);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("And saved :D");
	}
	static {
		
	}
	private static void createFile() {
		if(FileRefrence.home.exists() == false) {
			FileRefrence.home.mkdirs();
			createFile();
		} else {
			File configFile = new File(FileRefrence.home, "LoggerConfiguration.EAConfig");
			System.out.println("created file");
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
