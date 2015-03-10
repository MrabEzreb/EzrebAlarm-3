package com.ezreb.alarm.IO.data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.ezreb.alarm.dataTEMPNAME.alarm.Alarm;
import com.ezreb.alarm.dataTEMPNAME.alarm.AlarmSet;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveCorruptedException;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveCorruptedException.Reason;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveIndex;

public class DataInputStream {

	private FileType fileType;

	public DataInputStream(FileType type) {
		this.fileType = type;
	}

	public Object readObject(File parent, String name) throws IOException,
			ClassNotFoundException, EOFException, ArchiveCorruptedException {
		if (this.fileType == FileType.ALARM_SET) {
			ZipEntry index = new ZipEntry(ArchiveIndex.fileName);
			File f = new File(parent, name + ".alarms");
			ZipFile zf = new ZipFile(f);
			AlarmSet as = new AlarmSet(name);
			if(zf.getInputStream(index) == null) {
				zf.close();
				throw new ArchiveCorruptedException(as, Reason.FILE_MISSING);
			}
			Enumeration<? extends ZipEntry> zef = zf.entries();
			while (zef.hasMoreElements()) {
				ObjectInputStream ois = new ObjectInputStream(
						zf.getInputStream(zef.nextElement()));
				Object o = ois.readObject();
				try {
					Alarm a = (Alarm) o;
					ois.close();
					as.addAlarm(a);
				} catch(ClassCastException c) {
					try {
						ArchiveIndex a = (ArchiveIndex) o;
						ois.close();
					} catch(ClassCastException c2) {
						
					}
				}
			}
			zf.close();
			return as;
		} else {
			FileInputStream fileOut = null;
			ObjectInputStream objectOut = null;
			try {
				fileOut = new FileInputStream(new File(parent, name + "."
						+ this.fileType.extension));
				System.out.println(new File(parent, name + "."
						+ this.fileType.extension).getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			objectOut = new ObjectInputStream(fileOut);
			Object object = objectOut.readObject();
			try {
				objectOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return object;
		}
	}
}
