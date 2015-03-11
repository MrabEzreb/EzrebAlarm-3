package com.ezreb.alarm.dataTEMPNAME.configuration;

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
		System.out.println("LOADED! WHOO!O");
		extraDebug = true;
		printSource = true;
		sourceName = "EzrebAlarm-3";
		return "";
	}
	@Override
	@Config.OnExit
	public void save() {
		System.out.println("And saved :D");
	}
	static {
		
	}
}
