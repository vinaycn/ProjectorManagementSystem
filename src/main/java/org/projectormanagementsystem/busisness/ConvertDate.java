package org.projectormanagementsystem.busisness;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConvertDate {

	@Autowired
	private DateFormat dateFormat;
	
	
	
	
	public long convertDate(String date) throws ParseException{
		Date date1 = dateFormat.parse(date);
		long time = date1.getTime();
		return time;
	}
	
	
	
}
