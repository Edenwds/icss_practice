package com.icss.util;

import java.sql.Timestamp;
import java.util.Date;

public class LogTime {

	public static Timestamp getTimestamp(){
		return new java.sql.Timestamp(new Date().getTime());
	}
}
