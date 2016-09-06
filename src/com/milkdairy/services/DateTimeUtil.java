package com.milkdairy.services;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

	// System.out.println("getCurrentData :"+getCurrentData());
	// System.out.println("getCurrentMonth :"+getCurrentMonth());
	// System.out.println("getCurrentYear :"+getCurrentYear());
	// System.out.println("convertStringToDate
	// :"+convertStringToDate("22-02-1999"));
	// System.out.println("convertStringToDateWithTime
	// :"+convertStringToDateWithTime("22-02-1999 11:20 am"));
	// System.out.println("convertdateToStringeWithTime
	// :"+convertdateToStringeWithTime(new Date()));

	public static String getCurrentData() {

		return DATE_FORMAT.format(new Date());
	}

	@SuppressWarnings("deprecation")
	public static int getCurrentMonth() {

		return new Date().getMonth();
	}

	@SuppressWarnings("deprecation")
	public static int getCurrentYear() {
		
		return new Date().getYear();
	}

	public static Date convertStringToDate(String date) {
		Date dt = null;
		try {
			dt = DATE_FORMAT.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}

	public static Date convertStringToDateWithTime(String date) {
		Date dt = null;
		try {
			dt = DATE_TIME_FORMAT.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dt;
	}

	public static String convertdateToStringeWithTime(Date date) {

		return DATE_TIME_FORMAT.format(date);
	}

}