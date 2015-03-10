package com.ezreb.alarm.dataTEMPNAME;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ezreb.alarm.util.ResultReturnBundle;

public interface Compressable extends StorableData {

	public ZipEntry getCompressed();

	public ResultReturnBundle writeCompressed(ZipOutputStream outputStream);
}
