package com.ezreb.alarm.util;

import com.ezreb.alarm.dataTEMPNAME.configuration.ConfigurationHandler;
import com.ezreb.alarm.dataTEMPNAME.configuration.LoggerConfiguration;


public class Logger {

	public static void printErr(String message, String extraDebug) {
		String source = "";
		if(LoggerConfiguration.logConfiguration.printSource) {
			if(LoggerConfiguration.logConfiguration.sourceName == null) {
				source = "["+new Throwable().getStackTrace()[1].getClassName().substring(new Throwable().getStackTrace()[1].getClassName().lastIndexOf(".")+1)+"."+new Throwable().getStackTrace()[1].getMethodName()+"()]: ";
			} else {
				source = "["+"("+LoggerConfiguration.logConfiguration.sourceName+") "+new Throwable().getStackTrace()[1].getClassName().substring(new Throwable().getStackTrace()[1].getClassName().lastIndexOf(".")+1)+"."+new Throwable().getStackTrace()[1].getMethodName()+"()]: ";
			}
		} else {
			source = "["+LoggerConfiguration.logConfiguration.sourceName+"]: ";
		}
		System.err.println(source+message);
		if(LoggerConfiguration.logConfiguration.extraDebug) {
			System.err.println("\t"+extraDebug);
		}
	}
	public static void main(String[] args) {
		ConfigurationHandler.run();
		printErr("test", "testy");
		OnStartHandler.load();
	}
	@OnStart
	public static void test() {System.out.println("I TESTED! WHOO!");}
	@OnStart
	public void test2() {}
	static {
		System.out.println("loaded logger");
	}
}