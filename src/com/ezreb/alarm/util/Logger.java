package com.ezreb.alarm.util;

import com.ezreb.alarm.dataTEMPNAME.configuration.ConfigurationHandler;
import com.ezreb.alarm.dataTEMPNAME.configuration.LoggerConfiguration;


public class Logger {

	public static void printErr(String message, String extraDebug) {
		System.err.println(message);
		if(LoggerConfiguration.logConfiguration.extraDebug) {
			System.err.println(extraDebug);
		}
	}
	public static void main(String[] args) {
		ConfigurationHandler.run();
		printErr("test", "testy");
	}
}