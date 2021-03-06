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
	private boolean extraDebug;
	private boolean printSource;
	private String sourceName;
	/**
	 * @return the extraDebug
	 */
	public boolean isExtraDebug() {
		return extraDebug;
	}
	/**
	 * @param extraDebug the extraDebug to set
	 */
	public void setExtraDebug(boolean extraDebug) {
		this.extraDebug = extraDebug;
	}
	/**
	 * @return the printSource
	 */
	public boolean isPrintSource() {
		return printSource;
	}
	/**
	 * @param printSource the printSource to set
	 */
	public void setPrintSource(boolean printSource) {
		this.printSource = printSource;
	}
	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	@Override
	@Config.OnLoad
	public String load() {
		super.load();
		System.out.println(new File(FileRefrence.configurations, "LoggerConfiguration.EAConfig").exists());
		if(new File(FileRefrence.configurations, "LoggerConfiguration.EAConfig").exists() == false) {
			createFile();
			//System.out.println("LOADED! WHOO!O");
			extraDebug = false;
			printSource = false;
			sourceName = "EzrebAlarm-3";
		} else {
			File configFile = new File(FileRefrence.configurations, "LoggerConfiguration.EAConfig");
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
		File configFile = new File(FileRefrence.configurations, "LoggerConfiguration.EAConfig");
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
		if(FileRefrence.configurations.exists() == false) {
			FileRefrence.configurations.mkdirs();
			createFile();
		} else {
			File configFile = new File(FileRefrence.configurations, "LoggerConfiguration.EAConfig");
			System.out.println("created file");
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
