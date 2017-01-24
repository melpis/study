package com.github.melpis.server.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DataBaseUtil {
	
	public static  String getSystemDate(){
		Date systemDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss", Locale.KOREA);
		String sysDate = dateFormat.format(systemDate);
		
		return sysDate;
	}
	
	
}
