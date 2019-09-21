package com.cg.hbms.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.cg.hbms.dao.MyStringDateUtil;
import com.cg.hbms.dao.UserDao;
import com.cg.hbms.dao.UserDaoImpl;
import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;

//Class that implements all the functionalities of the UserService interface
public class UserServiceImpl implements UserService{
	final static Logger log = Logger.getLogger(AdminServiceImpl.class);
	UserDao userDao;
	
	//Constructor used to call the connection Object
	public UserServiceImpl() throws HotelException
	{
		log.info("Inside UserServiceImpl Constructor");
		userDao = new UserDaoImpl();
		log.info("UserServiceImpl Constructor Method Implemented Successfully");
	}
	
	//Method used to book rooms by passing the BookingDetails object to the dao layer
	@Override
	public int bookRoom(BookingDetails bookingDetails) throws HotelException {
		log.info("Inside bookRoom() Method, Package: com.cg.hbms.service");
		return userDao.bookRoom(bookingDetails);
	}
	
	//Method used to see the Booking Status for the single user by passing the user Id to the dao layer
	@Override
	public ArrayList<BookingDetails> viewBookingStatus(String userId) throws HotelException {
		log.info("Inside viewBookingStatus() Method, Package: com.cg.hbms.service");
		return userDao.viewBookingStatus(userId);
	}
	
	//Method used to add User to the Database by using Users object and passing it to the dao layer
	@Override
	public int addUser(Users user) throws HotelException {
		log.info("Inside addUser() Method, Package: com.cg.hbms.service");
		return userDao.addUser(user);
	}
	
	//Method used to check the credentials of the user by passing userId and password entered by the user to the dao layer
	@Override
	public String login(String userId, String password) throws HotelException{
		log.info("Inside login() Method, Package: com.cg.hbms.service");
		return userDao.login(userId, password);
	}
	
	//Method used to search room by passing hotel id to the dao layer
	@Override
	public ArrayList<roomDetails> searchRoom(String hotel_id) throws HotelException{
		log.info("Inside searchRoom() Method, Package: com.cg.hbms.service");
		return userDao.searchRoom(hotel_id);
	}
	
	//-----------Method to Validate UserName------------//
	@Override
	public boolean validate_userName(String username) throws HotelException {
		log.info("Inside validate_userName() Method, Package: com.cg.hbms.service");
		ArrayList<String> userNames = userDao.getListofUsername();
		if(username.length()>4)
		{
			return false;
		}
			
		if(userNames.contains(username))
			return false;
		
		return true;
	}
	
	//-------------Method used for Validation of Passwords--------------//
	@Override
	public boolean validate_password(String password) throws HotelException {
		log.info("Inside validate_password() Method, Package: com.cg.hbms.service");
		String pattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,20})";
		if(password.matches(pattern))
			return true;
		return false;
	}
	
	//------------Method Used for the Validation of names-------------//
	@Override
	public boolean validate_name(String name) throws HotelException {
		log.info("Inside validate_name() Method, Package: com.cg.hbms.service");
		String pattern = "[A-Z][a-z]{1,20}";
		if(name.matches(pattern))
			return true;
		return false;
	}
	
	//Method Used to get hotel By their Name by passing hotel name to the dao layer
	@Override
	public ArrayList<Hotel> getHotelsByName(String hotelName) throws HotelException {
		log.info("Inside getHotelsByName() Method, Package: com.cg.hbms.service");
		return userDao.getHotelsByName(hotelName);
	}

	//Method to get Hotel by city through passing city name to the dao layer
	@Override
	public ArrayList<Hotel> getHotelsByCity(String city) throws HotelException {
		log.info("Inside getHotelsByCity() Method, Package: com.cg.hbms.service");
		return userDao.getHotelsByCity(city);
	}
	
	//Method used to get hotel Id by passing city name to the dao layer
	@Override
	public ArrayList<Hotel> getHotelIdByHotelNameAndCityName(String cityName) throws HotelException {
		log.info("Inside getHotelIdByHotelNameAndCityName() Method, Package: com.cg.hbms.service");
		return userDao.getHotelIdByHotelNameAndCityName(cityName);
	}
	
	//Method used to check the Availability Status of the Booking done by a user
	@Override
	public int changeAvailabilityStatus(char status, String room_id, String hotel_id) throws HotelException {
		log.info("Inside changeAvailabilityStatus() Method, Package: com.cg.hbms.service");
		return userDao.changeAvailabilityStatus(status, room_id, hotel_id);
	}
	
	//--------------Method used to validate date------------// 
	@Override
	public boolean validateDate(String dateToValidate, String dateFromat) throws HotelException {
		log.info("Inside validateDate() Method, Package: com.cg.hbms.service");
		if(dateToValidate == null){
			return false;
		}
		LocalDate date1,today ;
		try {
			date1 = MyStringDateUtil.fromStringToLocalDate(dateToValidate);
			today = LocalDate.now();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);
			@SuppressWarnings("unused")
			Date date = sdf.parse(dateToValidate);
		} catch (Exception e) {
			return false;
		}
		if(date1.compareTo(today)>=0)
		{
			
			return true;
		}
		
		return false;
		
	}
	
	//------------------Method used to validate date--------------//
	@Override
	public boolean validateCheckOutDate(String dateToValidate, LocalDate checkIn, String dateFormat) throws HotelException {
		log.info("Inside validateCheckOutDate() Method, Package: com.cg.hbms.service");
		if(dateToValidate == null){
			return false;
		}
		LocalDate date1 = MyStringDateUtil.fromStringToLocalDate(dateToValidate);
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			@SuppressWarnings("unused")
			Date date = sdf.parse(dateToValidate);
	
		
		} catch (ParseException e) {
			return false;
		}
		if(date1.compareTo(checkIn)>0)
		{
			
			return true;
		}
		return false;
	}
	
	//Method used to change the availability status of the rooms for which the checkout date has arrived
	@Override
	public int changeAvailabilityStatusToYes(String string) throws HotelException {
		log.info("Inside changeAvailabilityStatusToYes() Method, Package: com.cg.hbms.service");
		return userDao.changeAvailabilityStatusToYes(string);
	}

}
