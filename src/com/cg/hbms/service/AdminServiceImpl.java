package com.cg.hbms.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cg.hbms.dao.AdminDao;
import com.cg.hbms.dao.AdminDaoImpl;
import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;

//Implementation of methods that are provided in AdminService Interface
public class AdminServiceImpl implements AdminService {
	final static Logger log = Logger.getLogger(AdminServiceImpl.class);
	AdminDao dao;

	//Constructor of the class used to call the connection object
	public AdminServiceImpl() throws HotelException {
		log.info("Inside AdminServiceImpl Constructor");
		dao = new AdminDaoImpl();
		log.info("AdminServiceImpl Constructor Method Implemented Successfully");
	}
	
	//Method used to call the list of hotels from the dao layer
	@Override
	public ArrayList<Hotel> getAllHotels() throws HotelException {
		log.info("Inside getAllHotels Method, Package: com.cg.hbms.service");
		return dao.getAllHotels();
	}
	
	//Method to delete Hotel by passing the hotel id to the dao layer
	@Override
	public int deleteHotel(int hotel_id) throws HotelException {
		log.info("Inside deleteHotel Method, Package: com.cg.hbms.service");
		return dao.deleteHotel(hotel_id);
	}

	//Method to update Hotel by passing the hotel object to the dao layer
	@Override
	public int updateHotel(Hotel hotel) throws HotelException {
		log.info("Inside updateHotel Method, Package: com.cg.hbms.service");
		return dao.updateHotel(hotel);
	}

	//Method to get All bookings by using hotel_id from the dao layer
	@Override
	public ArrayList<BookingDetails> getAllBookingsByHotel(String hotel_id) throws HotelException {
		log.info("Inside getAllBookingsByHotel Method, Package: com.cg.hbms.service");
		return dao.getAllBookingsByHotel(hotel_id);
	}

	//Method to view Booking for specific Date  by passing the date to the dao layer
	@Override
	public ArrayList<BookingDetails> viewBookingForSpecificDate(LocalDate date) throws HotelException {
		log.info("Inside viewBookingForSpecificDate Method, Package: com.cg.hbms.service");
		return dao.viewBookingForSpecificDate(date);
	}

	//Method to add Room Details to the Database by passing the roomDetails object to the dao layer
	@Override
	public int addRoomDetails(roomDetails room) throws HotelException {
		log.info("Inside addRoomDetails Method, Package: com.cg.hbms.service");
		return dao.addRoomDetails(room);
	}

	//Method to Delete Room Id from the Data Base  by passing the roomId to the Dao layer
	@Override
	public int deleteRoom(String roomId) throws HotelException {
		log.info("Inside deleteRoom Method, Package: com.cg.hbms.service");
		return dao.deleteRoom(roomId);
	}

	//Method to get a Room Detail by passing roomId to the dao layer 
	@Override
	public roomDetails getRoomByRoomId(String roomId) throws HotelException {
		log.info("Inside getRoomByRoomId Method, Package: com.cg.hbms.service");
		return dao.getRoomByRoomId(roomId);
	}

	//Method to update Room Details by passing the room Object to the dao layer
	@Override
	public int updateRoomDetails(roomDetails room) throws HotelException {
		log.info("Inside updateRoomDetails Method, Package: com.cg.hbms.service");
		return dao.updateRoomDetails(room);
	}
	
	//Method used to add hotel to the Database by passing the hotel Object to the dao layer
	@Override
	public int addHotel(Hotel hotel) throws HotelException {
		// TODO Auto-generated method stub
		log.info("Inside addHotel Method, Package: com.cg.hbms.service");
		return dao.addHotel(hotel);
	}
	
	//Method used to get Hotel Details by using Hotel id from the user and passing it to the dao layer
	@Override
	public Hotel getHotelByHotelId(String HotelId) throws HotelException  {
		// TODO Auto-generated method stub
		log.info("Inside getHotelByHotelId Method, Package: com.cg.hbms.service");
		return dao.getHotelByHotelId(HotelId);
	}

	// -------Validation for Hotels--------//
	@Override
	public boolean validateHotelName(String nm) {
		log.info("Inside validateHotelName Method, Package: com.cg.hbms.service");
		boolean f=false;
		if(nm.matches("^[\\p{L} .'-]+$"))
		{
			f=true;
		}		
		return f;
	}

	// -------Validation for Hotels--------//
	@Override
	public boolean validateCityName(String city) {
		log.info("Inside validateCityName Method, Package: com.cg.hbms.service");
		// TODO Auto-generated method stub
		boolean f=false;
		if(city.matches("^[\\p{L} .'-]+$"))
		{
			f=true;
		}	
		return f;
	}

	// -------Validation for Hotels--------//
	@Override
	public boolean validatePhoneNumber(String ph) {
		log.info("Inside validatePhoneNumber Method, Package: com.cg.hbms.service");
		// TODO Auto-generated method stub
		boolean f=false;
		if(ph.matches("[6-9][0-9]{9}"))
		{
			f=true;
		}	
		return f;
	}

	// -------Validation for Hotels--------//
	@Override
	public boolean validateEmail(String email) {
		log.info("Inside validateEmail Method, Package: com.cg.hbms.service");
		// TODO Auto-generated method stub
		boolean f=false;
		if(email.matches("^(.+)@(.+)$"))
		{
			f=true;
		}	
		return f;
	}

	// -------Validation for Hotels--------//
	@Override
	public boolean validateFax(String fax) {
		log.info("Inside validateFax Method, Package: com.cg.hbms.service");
		// TODO Auto-generated method stub
		boolean f=false;
		if(fax.matches("[+][1-9]+"))
		{
			f=true;
		}	
		return f;
	}
	// -------Validation for Room Numbers--------//
	@Override
	public boolean validateRoomNo(String roomNo) {
		log.info("Inside validateRoomNo Method, Package: com.cg.hbms.service");
		// TODO Auto-generated method stub
		boolean f=false;
		if(roomNo.matches("[0-9]{1,}"))
		{
			f=true;
		}	
		return f;
	}
	
	// -------Validation for Amount--------//
	@Override
	public boolean validateAmount(double amount) {
		log.info("Inside validateAmount Method, Package: com.cg.hbms.service");
		if(amount>0)	{
			return true;
		}
		return false;
	}
	// -------Validation for Name---------//
	@Override
	public boolean validateName(String name) {
		log.info("Inside validateName Method, Package: com.cg.hbms.service");
		boolean f=false;
		if(name.matches("^[\\p{L} .'-]+$"))
		{
			f=true;
		}		
		return f;
	}

	//Method Used to check if the database contains that particular hotel Id or not
	@Override
	public boolean hasHotelId(int id) throws HotelException {
		log.info("Inside hasHotelId Method, Package: com.cg.hbms.service");
		System.out.println("Inside check id");
		String hid = Integer.toString(id);
		boolean f = false;
		ArrayList<Hotel> hot = dao.getAllHotels();
		// System.out.println("All hotels="+hot);
		for (Hotel h : hot) {

			if (h.getHotel_id().equals(hid)) {
				f = true;
			} else {
				f = false;
			}
		}
		return f;
	}

	//Method used to add Admin to the database by passing Users object to the Database
	@Override
	public int addAdmin(Users user) throws HotelException {
		log.info("Inside addAdmin Method, Package: com.cg.hbms.service");
		return dao.addAdmin(user);
	}

}