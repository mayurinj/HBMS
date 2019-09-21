package com.cg.hbms.dao;

import java.util.ArrayList;

import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;

//interface class with all the user data base object implementation methods details
/************************ User Dao Interface **********************/
public interface UserDao {
	public int bookRoom(BookingDetails bookingDetails) throws HotelException;

	public ArrayList<BookingDetails> viewBookingStatus(String userId) throws HotelException;

	public int addUser(Users user) throws HotelException;

	public String login(String userId, String password) throws HotelException;

	public ArrayList<roomDetails> searchRoom(String hotel_id) throws HotelException;

	public ArrayList<String> getListofUsername() throws HotelException;

	public ArrayList<Hotel> getHotelsByName(String hotelName) throws HotelException;

	public ArrayList<Hotel> getHotelsByCity(String hotelCity) throws HotelException;

	public ArrayList<Hotel> getHotelIdByHotelNameAndCityName(String cityName) throws HotelException;

	public int changeAvailabilityStatus(char status, String room_id, String hotel_id) throws HotelException;

	public int changeAvailabilityStatusToYes(String string) throws HotelException;

	void deleteUserById(String uid);
}
