package com.cg.hbms.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;

//interface class with all the admin data base object implementation methods details
/*******************************************
 * Admin Dao Interface
 *******************************************/
public interface AdminDao {
	public ArrayList<Hotel> getAllHotels() throws HotelException;

	public int deleteHotel(int hotel_id) throws HotelException;

	public int addHotel(Hotel hotel) throws HotelException;

	public int updateHotel(Hotel hotel) throws HotelException;

	public ArrayList<BookingDetails> getAllBookingsByHotel(String hotel_id) throws HotelException;

	public ArrayList<BookingDetails> viewBookingForSpecificDate(LocalDate date) throws HotelException;

	public int addRoomDetails(roomDetails room) throws HotelException;

	public int deleteRoom(String roomId) throws HotelException;

	public roomDetails getRoomByRoomId(String roomId) throws HotelException;

	public int updateRoomDetails(roomDetails room) throws HotelException;

	public Hotel getHotelByHotelId(String HotelId) throws HotelException;

	public int addAdmin(Users user) throws HotelException;

}
