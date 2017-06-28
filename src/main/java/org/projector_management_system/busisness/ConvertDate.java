package org.projector_management_system.busisness;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



public class ConvertDate {

	@Autowired
	private static DateFormat dateFormat;
	
	
	
	
	public static long convertDate(String date) throws ParseException{
		Date date1 = dateFormat.parse(date);
		long time = date1.getTime();
		return time;
	}
	
	
	
}
