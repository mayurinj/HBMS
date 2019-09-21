package com.cg.hbms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;
import com.cg.hbms.util.DBUtil;

/*************************************
 * Implementation of UserDao Interface
 *************************************/
public class UserDaoImpl implements UserDao {
	Connection con;
	final static Logger log = Logger.getLogger(AdminDaoImpl.class);

	// method to call the database connection object
	/*
	 * Constructor
	 * 
	 * @Param - null
	 * 
	 * @Return - Connection Object
	 */
	public UserDaoImpl() throws HotelException {
		log.warn("Inside UserDaoImpl Constructor, Package: com.cg.hbms.dao");
		con = DBUtil.getcon();
		log.warn("Inside UserDaoImpl Constructor; Connection object fetch Successfully, Package: com.cg.hbms.dao");

	}

	// method to match the username and password provided with the database
	/*
	 * Login Method
	 * 
	 * @Param - userId, Password
	 * 
	 * @Return - role of the user
	 */
	@Override
	public String login(String userId, String password) throws HotelException {
		log.info("Inside login() method, Package: com.cg.hbms.dao");
		String role = "";
		try {
			String qry = "Select role from Users where user_Id=? and password=?";

			try {
				PreparedStatement pstmt = con.prepareStatement(qry);
				pstmt.setString(1, userId);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {

					role = rs.getString(1);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			log.error("Inside login() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());

		}
		if (role.equals(""))
			throw new HotelException("Invalid username and password");
		log.info("Inside login() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return role;
	}

	// method to check the availability of rooms in a hotel
	/*
	 * Search Room Method
	 * 
	 * @Param - hotel_id(String)
	 * 
	 * @Return - ArrayList(roomDetails Object)
	 */
	@Override
	public ArrayList<roomDetails> searchRoom(String hotel_id) throws HotelException {
		log.info("Inside searchRoom() method, Package: com.cg.hbms.dao");
		String qry = "Select * from RoomDetails where hotel_id=? and availability = 'Y'";
		ArrayList<roomDetails> list = new ArrayList<roomDetails>();
		try {
			PreparedStatement pstmt = con.prepareStatement(qry);
			pstmt.setString(1, hotel_id);

			roomDetails room = null;
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				room = new roomDetails(rs.getString("hotel_id"), rs.getString("room_id"), rs.getString("room_no"),
						rs.getString("room_type"), rs.getDouble("per_night_rate"),
						rs.getString("availability").charAt(0));
				list.add(room);
			}
		} catch (Exception e) {
			log.error("Inside searchRoom() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside searchRoom() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;
	}

	// method to update the database with booking details
	/*
	 * Book Room Method
	 * 
	 * @Param - bookingDetails Object
	 * 
	 * @Return - true or false in the form of 1 or 0
	 */
	@Override
	public int bookRoom(BookingDetails bookingDetails) throws HotelException {
		log.info("Inside bookRoom() method, Package: com.cg.hbms.dao");
		String qry = "INSERT INTO BookingDetails VALUES(booking_id_seq.nextval,?,?,?,?,?,?,?,?)";
		PreparedStatement pst;
		int bookRoom = 0;
		try {
			pst = con.prepareStatement(qry);
			pst.setString(8, bookingDetails.getHotel_id());
			pst.setString(1, bookingDetails.getRoom_id());
			pst.setString(2, bookingDetails.getUser_id());
			pst.setDate(3, Date.valueOf(bookingDetails.getBooked_from()));
			pst.setDate(4, Date.valueOf(bookingDetails.getBooked_to()));
			pst.setInt(5, bookingDetails.getNo_of_adults());
			pst.setInt(6, bookingDetails.getNo_of_children());
			pst.setDouble(7, bookingDetails.getAmount());
			bookRoom = pst.executeUpdate();

		} catch (Exception e) {
			log.error("Inside bookRoom() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException("Not enough values provided for booking room");
		}
		log.info("Inside bookRoom() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return bookRoom;

	}

	// method to get the booking status from the database
	/*
	 * View Booking Status Method
	 * 
	 * @Param - userId(String)
	 * 
	 * @Return - ArrayList(BookingDetails)
	 */
	@Override
	public ArrayList<BookingDetails> viewBookingStatus(String userId) throws HotelException {
		log.info("Inside viewBookingStatus() method, Package: com.cg.hbms.dao");
		String qry = "SELECT * from BookingDetails where user_id=? and booked_to >= sysdate";
		ArrayList<BookingDetails> list = new ArrayList<BookingDetails>();
		PreparedStatement pst1;
		try {
			pst1 = con.prepareStatement(qry);
			pst1.setString(1, userId);
			ResultSet rs = pst1.executeQuery();

			while (rs.next()) {
				LocalDate bookedFrom = rs.getDate(4).toLocalDate();
				LocalDate bookedTo = rs.getDate(5).toLocalDate();
				list.add(new BookingDetails(rs.getString(1), rs.getString(9), rs.getString(2), rs.getString(3),
						bookedFrom, bookedTo, rs.getInt(6), rs.getInt(7), rs.getDouble(8)));

			}
		} catch (SQLException e) {
			log.error(
					"Inside viewBookingStatus() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside viewBookingStatus() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;

	}

	// method to update the database with new user
	/*
	 * add User Method
	 * 
	 * @Param - Users Object
	 * 
	 * @Return - true or false in the form of 1 or 0
	 */
	@Override
	public int addUser(Users user) throws HotelException {
		log.info("Inside addUser() method, Package: com.cg.hbms.dao");
		String qry = "INSERT INTO Users values(?,?,?,?,?,?,?,?)";
		PreparedStatement pst;
		int insUser = 0;
		try {
			pst = con.prepareStatement(qry);
			pst.setString(1, user.getUser_id());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getRole());
			pst.setString(4, user.getUser_name());
			pst.setString(5, user.getMobile_phone());
			pst.setString(6, user.getPhone());
			pst.setString(7, user.getAddress());
			pst.setString(8, user.getEmail());
			insUser = pst.executeUpdate();

		} catch (SQLException e) {
			log.error("Inside addUser() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException("Values for users are not properly provided");
		}

		log.info("Inside addUser() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return insUser;
	}

	// method to get the user list from the database
	/*
	 * get List of Users Method
	 * 
	 * @Param - Users Object
	 * 
	 * @Return - ArrayList(String of UserNames)
	 */
	@Override
	public ArrayList<String> getListofUsername() throws HotelException {
		log.info("Inside getListofUsername() method, Package: com.cg.hbms.dao");
		ArrayList<String> userNameList = new ArrayList<String>();
		String qry = "Select user_id from Users ";
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while (rs.next()) {
				userNameList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			log.error(
					"Inside getListofUsername() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside getListofUsername() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return userNameList;
	}

	// method to get the hotel list in a city from the database
	/*
	 * get Hotel By using City Name Method
	 * 
	 * @Param - City Name(String)
	 * 
	 * @Return - ArrayList(Hotel)
	 */
	@Override
	public ArrayList<Hotel> getHotelIdByHotelNameAndCityName(String cityName) throws HotelException {
		log.info("Inside getHotelIdByHotelNameAndCityName() method, Package: com.cg.hbms.dao");
		String qry = "select * from Hotel where city = ?";
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, cityName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Hotel hotel = new Hotel(rs.getString("hotel_id"), rs.getString("city"), rs.getString("hotel_name"),
						rs.getString("address"), rs.getString("description"), rs.getInt("avg_rate_per_night"),
						rs.getString("phone_no1"), rs.getString("phone_no2"), rs.getString("rating"),
						rs.getString("email"), rs.getString("fax"));
				list.add(hotel);
			}
		} catch (Exception e) {
			log.error(
					"Inside getHotelIdByHotelNameAndCityName() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info(
				"Inside getHotelIdByHotelNameAndCityName() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;

	}

	// method to update the availability status of a room in a hotel
	/*
	 * Change Availability of Room Method
	 * 
	 * @Param - Users Object
	 * 
	 * @Return - true or false in the form of 1 or 0
	 */
	@Override
	public int changeAvailabilityStatus(char status, String room_id, String hotel_id) throws HotelException {
		log.info("Inside changeAvailabilityStatus() method, Package: com.cg.hbms.dao");
		int updatedStatus = 0;
		PreparedStatement pst;
		try {
			if (status == 'Y' || status == 'y') {
				String qry = "update RoomDetails set availability = 'Y' where room_id = ? and hotel_id = ?";

				pst = con.prepareStatement(qry);
				pst.setString(1, room_id);
				pst.setString(2, hotel_id);
				updatedStatus = pst.executeUpdate();
			} else {
				String qry = "update RoomDetails set availability = 'N' where room_id = ? and hotel_id = ?";
				pst = con.prepareStatement(qry);
				pst.setString(1, room_id);
				pst.setString(2, hotel_id);
				updatedStatus = pst.executeUpdate();
			}
		} catch (SQLException e) {
			log.error(
					"Inside changeAvailabilityStatus() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside changeAvailabilityStatus() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return updatedStatus;
	}

	// method to get the hotel details with the name provided from the database
	/*
	 * Get Hotel by HotelName Method
	 * 
	 * @Param - HotelName(String)
	 * 
	 * @Return - ArrayList(Hotel)
	 */
	@Override
	public ArrayList<Hotel> getHotelsByName(String hotelName) throws HotelException {
		log.info("Inside getHotelsByName() method, Package: com.cg.hbms.dao");
		String qry = "Select hotel_id,address,phone_no1,phone_no2,rating,email from Hotel where hotel_name=?";
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		Hotel h = null;
		try {
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, hotelName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				h = new Hotel(rs.getString("hotel_id"), rs.getString("address"), rs.getString("phone_no1"),
						rs.getString("phone_no2"), rs.getString("rating"), rs.getString("email"));
				list.add(h);
			}

		} catch (SQLException e) {
			log.error(
					"Inside getHotelsByName() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		if (list.isEmpty())
			throw new HotelException("Hotel not available");
		log.info("Inside getHotelsByName() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;
	}

	// method to get the hotel list of a city from the database
	/*
	 * Get Hotel by City Method
	 * 
	 * @Param - City Name(String)
	 * 
	 * @Return - ArrayList(Hotel)
	 */
	@Override
	public ArrayList<Hotel> getHotelsByCity(String city) throws HotelException {
		log.info("Inside getHotelsByCity() method, Package: com.cg.hbms.dao");
		String qry = "Select hotel_id,hotel_name,address,phone_no1 from Hotel where city=?";
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		Hotel h = null;
		try {
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, city);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				h = new Hotel(rs.getString("hotel_id"), rs.getString("hotel_name"), rs.getString("address"),
						rs.getString("phone_no1"));
				list.add(h);
			}
			if (h == null)
				throw new HotelException();

		} catch (SQLException e) {
			log.error(
					"Inside getHotelsByCity() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside getHotelsByCity() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;
	}

	// method to update availability status of room after booking period in database
	/*
	 * Change Availability to Yes Method
	 * 
	 * @Param - status(String)
	 * 
	 * @Return - true or false in the form of 1 or 0
	 */
	@Override
	public int changeAvailabilityStatusToYes(String status) throws HotelException {
		log.info("Inside changeAvailabilityStatusToYes() method, Package: com.cg.hbms.dao");
		int updatedStatus = 0;
		PreparedStatement pst;
		try {
			if (status == "Y" || status == "y") {
				String qry = "update RoomDetails r set availability = 'Y' where room_id in(select room_id from BookingDetails where booked_to < SYSDATE and r.hotel_id=hotel_id) ";

				pst = con.prepareStatement(qry);
				updatedStatus = pst.executeUpdate();
			}

		} catch (SQLException e) {
			log.error(
					"Inside changeAvailabilityStatusToYes() method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info(
				"Inside changeAvailabilityStatusToYes() method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return updatedStatus;
	}

	// This method is written for test cases
	/*
	 * Delete User by Id
	 * 
	 * @Param - uId(String)
	 * 
	 * @Return - null
	 */
	@Override
	public void deleteUserById(String uid) {
		try {
			PreparedStatement pst = con.prepareStatement("DELETE from Users WHERE user_id=?");
			pst.setString(1, uid);
			pst.executeUpdate();
			System.out.println("User Deleted");
		} catch (Exception e) {
			try {
				throw new HotelException(e.getMessage());
			} catch (HotelException e1) {
				e1.printStackTrace();
			}
		}
	}

}
