package com.cg.hbms.util;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.cg.hbms.exception.HotelException;
import com.cg.hbms.ui.Main;

//Used to connect to the database using oracle drivers
public class DBUtil {
	final static Logger log = Logger.getLogger(Main.class);
	static String unm;
	static String pwd;
	static String url;
	static String driver;

	//Connection method provided with Url,UserName,Password and driver to establish connection with Database
	public static Connection getcon() throws HotelException {
		log.info("Inside getcon method, Package: com.cg.hbms.util");
		Properties dbProps;
		Connection con = null;
		try {
			dbProps = getDBinfo();
			unm = dbProps.getProperty("dbUserName");
			pwd = dbProps.getProperty("dbPassword");
			url = dbProps.getProperty("dbUrl");
			driver = dbProps.getProperty("dbDriver");
			Class.forName(driver);
			con = DriverManager.getConnection(url, unm, pwd);
			log.info("Inside getcon method, Package: com.cg.hbms.util, Method Worked Successfully");
			return con;
		} catch (Exception ee) {
			log.info("Inside getcon method, Package: com.cg.hbms.util, This Method has Errors: "+ ee.getMessage());
			throw new HotelException(ee.getMessage());
		}

	}

	// File Reader method to read properties file("dbinfo.properties") provided with its location address
	public static Properties getDBinfo() throws HotelException {
		try	{
			log.info("Inside getDBinfo method, Package: com.cg.hbms.util");
			FileReader fr = new FileReader("D:\\Project\\HBMS\\src\\com\\cg\\hbms\\util\\dbinfo.properties");
			Properties myProps = new Properties();
			myProps.load(fr);
			log.info("Inside getDBinfo method, Package: com.cg.hbms.util, Method Worked Successfully");
			return myProps;
		}
		catch(Exception e) {
			log.info("Inside getDBinfo method, Package: com.cg.hbms.util, This Method has Errors, Error Message: "+e.getMessage());
			throw new HotelException(e.getMessage());
		}
	}

}
