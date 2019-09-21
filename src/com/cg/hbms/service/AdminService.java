package com.cg.hbms.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;

//Interface with all the functionalities related to the service class of admin
public interface AdminService {
	public ArrayList<Hotel> getAllHotels() throws HotelException;

	public int deleteHotel(int hotel_id) throws HotelException;

	public int updateHotel(Hotel hotel) throws HotelException;

	public ArrayList<BookingDetails> getAllBookingsByHotel(String hotel_id) throws HotelException;
	
	public ArrayList<BookingDetails> viewBookingForSpecificDate(LocalDate date) throws HotelException;
	
	public int addRoomDetails(roomDetails room) throws HotelException;
	
	public int deleteRoom(String roomId) throws HotelException;
	
	public roomDetails getRoomByRoomId(String roomId) throws HotelException;
	
	public int updateRoomDetails(roomDetails room) throws HotelException;
	
	public int addHotel(Hotel hotel) throws HotelException;

	public boolean hasHotelId(int id) throws HotelException;
	
	public Hotel getHotelByHotelId(String HotelId) throws HotelException;
	
	public int addAdmin(Users user) throws HotelException;

	// -------Validation for Hotels--------//
	public boolean validateHotelName(String nm);

	public boolean validateCityName(String city);

	public boolean validatePhoneNumber(String ph);

	public boolean validateEmail(String email);

	public boolean validateFax(String fax);
	
	public boolean validateRoomNo(String roomNo);
	
	public boolean validateAmount(double amount);

	public boolean validateName(String name);
	// -------Validation for Hotels--------//
}
