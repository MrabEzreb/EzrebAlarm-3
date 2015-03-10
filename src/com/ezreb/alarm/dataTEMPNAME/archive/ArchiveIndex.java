package com.ezreb.alarm.dataTEMPNAME.archive;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ezreb.alarm.dataTEMPNAME.Compressable;
import com.ezreb.alarm.util.ResultReturnBundle;

public class ArchiveIndex implements Compressable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8652667479314053027L;
	public String[] entries;
	public String name;
	public static final String fileName = "index.archiveIndex";

	/**
	 * @param entries
	 * @param name
	 */
	public ArchiveIndex(ZipEntry[] entries, String name) {
		this.entries = new String[entries.length];
		for (int i = 0; i < entries.length; i++) {
			this.entries[i] = entries[i].getName();
		}
		this.name = name;
	}

	@Override
	public ZipEntry getCompressed() {
		return new ZipEntry(fileName);
	}

	@Override
	public ResultReturnBundle writeCompressed(ZipOutputStream outputStream) {
		ObjectOutputStream oos;
		try {
			outputStream.putNextEntry(getCompressed());
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultReturnBundle(false, outputStream);
		}
		try {
			oos = new ObjectOutputStream(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultReturnBundle(false, outputStream);
		}
		try {
			oos.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultReturnBundle(false, outputStream);
		}
		try {
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultReturnBundle(false, outputStream);
		}
		try {
			outputStream.closeEntry();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultReturnBundle(false, outputStream);
		}
		try {
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResultReturnBundle(false, outputStream);
		}
		return new ResultReturnBundle(true, outputStream);
	}
}
