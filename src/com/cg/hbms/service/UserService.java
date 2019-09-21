package com.cg.hbms.service;


import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;

//Interface with all the functionalities related to the service class of User
public interface UserService {
	public int bookRoom(BookingDetails bookingDetails) throws HotelException;
	public ArrayList<BookingDetails> viewBookingStatus(String userId )throws HotelException ;
	public int addUser(Users user) throws HotelException;
	public String login(String userId,String password) throws HotelException;
	public ArrayList<roomDetails> searchRoom(String hotel_id) throws HotelException;
	public boolean validate_userName(String username) throws HotelException;
	public boolean validate_password(String password) throws HotelException;
	public boolean validate_name(String name) throws HotelException;
	public ArrayList<Hotel> getHotelsByName(String hotelName) throws HotelException;
	public ArrayList<Hotel> getHotelsByCity(String city) throws HotelException;
	public ArrayList<Hotel> getHotelIdByHotelNameAndCityName(String cityName) throws HotelException;
	public int changeAvailabilityStatus(char status,String room_id,String hotel_id) throws HotelException;
	public boolean validateDate(String dateToValidate, String dateFromat) throws HotelException;
	public boolean validateCheckOutDate(String bookedTo, LocalDate checkIn, String string) throws HotelException;
	public int changeAvailabilityStatusToYes(String string) throws HotelException;
}
