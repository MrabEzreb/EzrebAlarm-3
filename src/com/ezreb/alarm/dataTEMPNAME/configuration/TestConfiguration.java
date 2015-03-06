package com.ezreb.alarm.dataTEMPNAME.configuration;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;

import com.ezreb.alarm.refrence.Refrence;
import com.ezreb.alarm.util.data.DataInputStream;
import com.ezreb.alarm.util.data.DataOutputStream;
import com.ezreb.alarm.util.data.FileType;


public class TestConfiguration extends Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8658901289971878592L;

	public static TestConfiguration testConfiguration = new TestConfiguration();
	public int testValue;
	static {
		if(Refrence.home.exists() == false) {
			while(Refrence.home.exists() == false) {
				System.out.println("Waiting for EzrebAlarm home folder to be created...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("EzrebAlarm home folder already exists, skipping...");
		}
		if(new File(Refrence.home, "testConfiguration.ezrebConfig").exists() == false) {
			System.out.println("Creating testConfiguration file...");
			try {
				new File(Refrence.home, "testConfiguration.ezrebConfig").createNewFile();
				System.out.println("Successfully created testConfiguration.ezrebConfig!");
			} catch (IOException e) {
				System.err.println("ERROR: Could not create testConfiguration.ezrebConfig!");
			}
		}
		System.out.println("Loading testConfiguration from file...");
		try {
			testConfiguration = (TestConfiguration) new DataInputStream(FileType.CONFIGURATION).readObject(Refrence.home, "testConfiguration");
			System.out.println("Successfully loaded testConfiguration!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("testConfiguration.settings has not been initialized, initializing now...");
			new DataOutputStream(FileType.CONFIGURATION).writeObject(Refrence.home, "testConfiguration", testConfiguration);
			System.out.println("Successfully initialized, reloading...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			testConfiguration = (TestConfiguration) new DataInputStream(FileType.CONFIGURATION).readObject(Refrence.home, "testConfiguration");
			System.out.println("Successfully loaded testConfiguration!");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Saving testConfiguration to file...");
				new DataOutputStream(FileType.CONFIGURATION).writeObject(Refrence.home, "testConfiguration", testConfiguration);
				System.out.println("Successfully saved testConfiguration!");
			}
		}));
	}
}
