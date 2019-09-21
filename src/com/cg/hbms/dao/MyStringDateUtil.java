package com.cg.hbms.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Class used to convert dates into different formats
/******************************
 * My String Date Utility Class
 ******************************/
public class MyStringDateUtil {
	/*
	 * Method to convert sql date to local date
	 * 
	 * @Param - sql Date
	 * 
	 * @Return - LocalDate
	 */
	public static LocalDate fromSqlToLocalDate(Date sqlDate) {
		LocalDate dobLocalDate = sqlDate.toLocalDate();
		return dobLocalDate;
	}

	/*
	 * Method to convert string into ArrayList
	 * 
	 * @Param - String
	 * 
	 * @Return - ArrayList
	 */
	public static ArrayList<String> fromStringToArrayList(String str) {
		ArrayList<String> stringList = new ArrayList<String>();
		for (String tempStr : str.split(",")) {
			stringList.add(tempStr);
		}
		return stringList;
	}

	/*
	 * Method to convert ArrayList into Array
	 * 
	 * @Param - ArrayList
	 * 
	 * @Return - Array
	 */
	public static String[] fromArrayListToArray(ArrayList<String> strList) {

		String stringArray[] = strList.toArray(new String[strList.size()]);

		for (int i = 0; i < stringArray.length; i++) {
			System.out.println(stringArray[i]);
		}
		return stringArray;
	}

	/*
	 * Method to convert string array which into String
	 * 
	 * @Param - String Array
	 * 
	 * @Return - String
	 */
	public static String fromArrayToCommaSeparatedString(String strArray[]) {
		StringBuffer str = new StringBuffer();
		for (String tempStr : strArray) {
			str.append(tempStr + ",");
		}
		return str.toString();
	}

	/*
	 * Method to convert LocalDate into SqlDate
	 * 
	 * @Param - LocalDate
	 * 
	 * @Return - SqlDate
	 */
	public static Date fromLocalToSqlDate(LocalDate localDate) {
		System.out.println(" from local to sql.....................");
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
		return sqlDate;

	}

	/*
	 * Method to convert string into LocalDate
	 * 
	 * @Param - String
	 * 
	 * @Return - LocalDate
	 */
	public static LocalDate fromStringToLocalDate(String dobText) {
		LocalDate localDob = LocalDate.parse(dobText, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		return localDob;

	}

	/*
	 * Method to convert string into SqlDate
	 * 
	 * @Param - String
	 * 
	 * @Return - SqlDate
	 */
	public static Date fromStringToSqlDate(String dobText) {
		LocalDate localDob = LocalDate.parse(dobText, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDob);
		return sqlDate;

	}

}
