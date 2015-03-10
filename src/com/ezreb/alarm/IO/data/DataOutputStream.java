package com.ezreb.alarm.IO.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ezreb.alarm.IO.file.FileFolder;
import com.ezreb.alarm.dataTEMPNAME.Compressable;
import com.ezreb.alarm.dataTEMPNAME.alarm.Alarm;
import com.ezreb.alarm.dataTEMPNAME.alarm.AlarmSet;
import com.ezreb.alarm.dataTEMPNAME.archive.ArchiveIndex;
import com.ezreb.alarm.util.ResultReturnBundle;

public class DataOutputStream {

	private FileType fileType;

	public DataOutputStream(FileType type) {
		this.fileType = type;
	}

	public void writeObject(FileFolder parent, String name, Object object) {
		if (this.fileType == FileType.ALARM_SET) {
			ZipOutputStream zos = null;
			try {
				new File(parent, name + ".alarms").createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				zos = new ZipOutputStream(new FileOutputStream(new File(parent,
						name + ".alarms")));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			AlarmSet alarms = (AlarmSet) object;
			for (Alarm alarm : alarms.alarms) {
				ResultReturnBundle r = writeCompressed(zos, alarm);
				if (r.getData() instanceof ZipOutputStream) {
					System.out.println(r.isSuccessful());
				} else {
					((IOException) r.getData()).printStackTrace();
				}
			}
			ArchiveIndex ai = alarms.getIndex();
			ai.writeCompressed(zos);
			try {
				zos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				zos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FileOutputStream fileOut = null;
			ObjectOutputStream objectOut = null;
			try {
				fileOut = new FileOutputStream(new File(parent, name + "."
						+ this.fileType.extension));
				System.out.println(new File(parent, name + "."
						+ this.fileType.extension).getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				objectOut = new ObjectOutputStream(fileOut);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				objectOut.writeObject(object);
				objectOut.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	private static void writeCompressed(ZipOutputStream zos, ZipEntry ze) {

	}

	private static ResultReturnBundle writeCompressed(ZipOutputStream zos,
			Compressable c) {
		return c.writeCompressed(zos);
	}
}
