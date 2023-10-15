package com.ispan.eeit69.utils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialException;

public class SystemService {
	public static String JNDI_String = "java:comp/env/jdbc/JSPDB";
	
	public static String PRODUCT_IMAGE_FILE_FOLDER = "C:\\SpringBootExample\\images\\product";
	
	public static Clob stringToClob(String text) throws SerialException, SQLException {
		char[] ca = text.toCharArray();
		return new SerialClob(ca);
	};
	public static String clobToString(Clob clob) throws SerialException, SQLException {
		if (clob == null) {
			return "";
		}
		return clob.getSubString(1, (int)clob.length());
	};

	static public Date toSqlDate(String dateString) {
		java.sql.Date date = null;
		java.util.Date utilDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		try {
			utilDate = sdf.parse(dateString);
			date = new Date(utilDate.getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
		return date;
	}	
	public static String fileToBase64(File file) {
	    try {
	        byte[] fileContent = Files.readAllBytes(file.toPath());
	        return Base64.getEncoder().encodeToString(fileContent);
	    } catch (IOException e) {
	        throw new IllegalStateException("could not read file " + file, e);
	    }
	}
	public static Integer stringToInteger(String input, Integer defaultValue) {
	    try {
	        return Integer.parseInt(input);
	    } catch (NumberFormatException e) {
	        // 轉換失敗，返回默認值或拋出異常
	        if (defaultValue != null) {
	            return defaultValue;
	        } else {
	            throw new IllegalArgumentException("無法將字符串轉換為整數：" + input);
	        }
	    }
	}	
}