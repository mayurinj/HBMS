package com.cg.hbms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/******************************************
 * Implementation of Admin Dao Interface
 ******************************************/
public class AdminDaoImpl implements AdminDao {
	final static Logger log = Logger.getLogger(AdminDaoImpl.class);
	Connection con;

	// method to call the database connection object
	/*
	 * Contructor
	 * 
	 * @param - null
	 * 
	 * @Return - Connection Object
	 */
	public AdminDaoImpl() throws HotelException {
		log.warn("Inside AdminDaoImpl Constructor, Package: com.cg.hbms.dao");
		con = DBUtil.getcon();
		log.warn("Inside AdminDaoImpl Constructor; Connection object fetch Successfully, Package: com.cg.hbms.dao");
	}

	// method to get the hotel list from the database
	/*
	 * get List of All hotels Method
	 * 
	 * @param - null
	 * 
	 * @Return - hotels list(ArrayList)
	 */
	@Override
	public ArrayList<Hotel> getAllHotels() throws HotelException {
		log.info("Inside getAllHotels method, Package: com.cg.hbms.dao");
		String qry = "SELECT * FROM Hotel";
		ArrayList<Hotel> list = new ArrayList<Hotel>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(qry);
			while (rs.next()) {
				Hotel hotel = new Hotel(rs.getString("hotel_id"), rs.getString("city"), rs.getString("hotel_name"),
						rs.getString("address"), rs.getString("description"), rs.getDouble("avg_rate_per_night"),
						rs.getString("phone_no1"), rs.getString("phone_no2"), rs.getString("rating"),
						rs.getString("email"), rs.getString("fax"));
				list.add(hotel);
			}
		} catch (Exception e) {
			log.error("Inside getAllHotels method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside getAllHotels method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;
	}

	// method to delete a hotel and its details from the database
	/*
	 * Delete Hotel Method
	 * 
	 * @param - hotel_id
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int deleteHotel(int hotel_id) throws HotelException {
		log.info("Inside deleteHotel method, Package: com.cg.hbms.dao");
		int delrec = 0;
		try {
			PreparedStatement pst = con.prepareStatement("DELETE from Hotel WHERE hotel_id=?");
			String id = Integer.toString(hotel_id);
			pst.setString(1, id);
			delrec = pst.executeUpdate();
		} catch (Exception e) {
			log.error("Inside deleteHotel method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside deleteHotel method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return delrec;
	}

	// method to update the hotel details using its ID
	/*
	 * Update Hotel Method
	 * 
	 * @param - Hotel object
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int updateHotel(Hotel hotel) throws HotelException {
		log.info("Inside updateHotel method, Package: com.cg.hbms.dao");
		int i = 0;
		try {
			String qry = "update Hotel set city=?,hotel_name=?,address=?,description=?,avg_rate_per_night=?,phone_no1=?,phone_no2=?,rating=?,email=?,fax=?  WHERE hotel_id=?";

			PreparedStatement pstmt = con.prepareStatement(qry);
			pstmt.setString(1, hotel.getCity());
			pstmt.setString(2, hotel.getHotel_name());
			pstmt.setString(3, hotel.getAddress());
			pstmt.setString(4, hotel.getDescription());
			pstmt.setDouble(5, hotel.getAvg_rate_per_night());
			pstmt.setString(6, hotel.getPhone_no1());
			pstmt.setString(7, hotel.getPhone_no2());
			pstmt.setString(8, hotel.getRating());
			pstmt.setString(9, hotel.getEmail());
			pstmt.setString(10, hotel.getFax());
			pstmt.setString(11, hotel.getHotel_id());
			i = pstmt.executeUpdate();
		} catch (Exception e) {
			log.error("Inside updateHotel method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside updateHotel method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return i;

	}

	// method to get the booking details of a specific hotel from the database
	/*
	 * Get all Bookings using Hotel Method
	 * 
	 * @param - hotel_id
	 * 
	 * @return - Array list of booking Details
	 */
	@Override
	public ArrayList<BookingDetails> getAllBookingsByHotel(String hotel_id) throws HotelException {
		log.info("Inside getAllBookingsByHotel method, Package: com.cg.hbms.dao");
		ArrayList<BookingDetails> list = null;
		try {
			String qry = "select b.hotel_id,b.booking_id,b.room_id,b.user_id,b.booked_from,b.booked_to,b.no_of_adults,b.no_of_children,b.amount from bookingdetails b where b.hotel_id = ?";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setString(1, hotel_id);
			ResultSet rs = pst1.executeQuery();
			list = new ArrayList<BookingDetails>();

			while (rs.next()) {
				BookingDetails book = new BookingDetails(rs.getString("booking_id"), rs.getString("hotel_id"),
						rs.getString("room_id"), rs.getString("user_id"),
						MyStringDateUtil.fromSqlToLocalDate(rs.getDate("booked_from")),
						MyStringDateUtil.fromSqlToLocalDate(rs.getDate("booked_to")), rs.getInt("no_of_adults"),
						rs.getInt("no_of_children"), rs.getDouble("amount"));
				list.add(book);

			}
		} catch (Exception e) {
			log.error(
					"Inside getAllBookingsByHotel method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside getAllBookingsByHotel method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;
	}

	// method to view the booking details for specific Date
	/*
	 * Get all Bookings using Booking Date Method
	 * 
	 * @param - date(LocalDate)
	 * 
	 * @return - Array list of booking Details
	 */
	@Override
	public ArrayList<BookingDetails> viewBookingForSpecificDate(LocalDate date) throws HotelException {
		log.info("Inside viewBookingForSpecificDate method, Package: com.cg.hbms.dao");
		ArrayList<BookingDetails> list = null;
		try {
			Date date1 = MyStringDateUtil.fromLocalToSqlDate(date);
			String qry = "SELECT * from bookingdetails where booked_from=?";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setDate(1, date1);
			ResultSet rs = pst1.executeQuery();
			list = new ArrayList<BookingDetails>();
			while (rs.next()) {
				BookingDetails book = new BookingDetails(rs.getString("booking_id"), rs.getString("room_id"),
						rs.getString("user_id"), MyStringDateUtil.fromSqlToLocalDate(rs.getDate("booked_from")),
						MyStringDateUtil.fromSqlToLocalDate(rs.getDate("booked_to")), rs.getInt("no_of_adults"),
						rs.getInt("no_of_children"), rs.getDouble("amount"));
				list.add(book);
			}
		} catch (Exception e) {
			log.error(
					"Inside viewBookingForSpecificDate method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside viewBookingForSpecificDate method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return list;
	}

	// method to add the room details of a hotel into the database
	/*
	 * Add Room Details Method
	 * 
	 * @param - roomDetails Object
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int addRoomDetails(roomDetails room) throws HotelException {
		log.info("Inside addRoomDetails method, Package: com.cg.hbms.dao");
		int insRoom = 0;
		try {
			String qry = "INSERT INTO RoomDetails VALUES(?,?,?,?,?,?)";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setString(1, room.getHotel_id());
			pst1.setString(2, room.getRoom_id());
			pst1.setString(3, room.getRoom_no());
			pst1.setString(4, room.getRoom_type());
			pst1.setDouble(5, room.getPer_night_rate());
			pst1.setString(6, String.valueOf(room.isAvailability()));
			insRoom = pst1.executeUpdate();
		} catch (Exception e) {
			log.error("Inside addRoomDetails method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside addRoomDetails method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return insRoom;
	}

	// method to delete the room details of a hotel in the database
	/*
	 * Delete Room Details Method
	 * 
	 * @param - Room Id
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int deleteRoom(String roomId) throws HotelException {
		log.info("Inside deleteRoom method, Package: com.cg.hbms.dao");
		int delRoom = 0;
		try {
			String qry = "DELETE FROM RoomDetails WHERE room_id=?";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setString(1, roomId);
			delRoom = pst1.executeUpdate();
		} catch (Exception e) {
			log.error("Inside deleteRoom method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside deleteRoom method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return delRoom;
	}

	// method to view the room details of hotel in the database
	/*
	 * Get Room Details using Id Method
	 * 
	 * @param - Room Id
	 * 
	 * @return - roomDetails Object
	 */
	@Override
	public roomDetails getRoomByRoomId(String roomId) throws HotelException {
		log.info("Inside getRoomByRoomId method, Package: com.cg.hbms.dao");
		roomDetails room = null;
		try {
			String qry = "SELECT * FROM RoomDetails WHERE room_id=?";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setString(1, roomId);
			ResultSet rs = pst1.executeQuery();
			while (rs.next()) {
				room = new roomDetails(rs.getString("hotel_id"), rs.getString("room_id"), rs.getString("room_no"),
						rs.getString("room_type"), rs.getDouble("per_night_rate"),
						rs.getString("availability").charAt(0));
			}
		} catch (Exception e) {
			log.error("Inside getRoomByRoomId method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside getRoomByRoomId method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return room;
	}

	// method to update the room details of a room in the database
	/*
	 * Update Room Details Method
	 * 
	 * @param - roomDetails Object
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int updateRoomDetails(roomDetails room) throws HotelException {
		log.info("Inside updateRoomDetails method, Package: com.cg.hbms.dao");
		int upRoom = 0;
		try {
			String qry = "UPDATE RoomDetails SET per_night_rate = ? where room_id=?";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setDouble(1, room.getPer_night_rate());
			pst1.setString(2, room.getRoom_id());
			upRoom = pst1.executeUpdate();
		} catch (Exception e) {
			log.error(
					"Inside updateRoomDetails method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside updateRoomDetails method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return upRoom;
	}

	// method to get a hotel by its Id
	/*
	 * Get Hotel Details using Hotel Id Method
	 * 
	 * @param - Hotel Id
	 * 
	 * @return - Hotel Object
	 */
	@Override
	public Hotel getHotelByHotelId(String HotelId) throws HotelException {
		log.info("Inside getHotelByHotelId method, Package: com.cg.hbms.dao");
		Hotel hotel = null;
		try {
			String qry = "Select * from Hotel Where hotel_id=?";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setString(1, HotelId);
			ResultSet rs = pst1.executeQuery();
			while (rs.next()) {
				hotel = new Hotel(rs.getString("hotel_id"), rs.getString("city"), rs.getString("hotel_name"),
						rs.getString("address"), rs.getString("description"), rs.getDouble("avg_rate_per_night"),
						rs.getString("phone_no1"), rs.getString("phone_no2"), rs.getString("rating"),
						rs.getString("email"), rs.getString("fax"));
			}
		} catch (Exception e) {
			log.error(
					"Inside getHotelByHotelId method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
							+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside getHotelByHotelId method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return hotel;
	}

	// method to add a hotel into the database
	/*
	 * Add Hotel Details Method
	 * 
	 * @param - Hotel Object
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int addHotel(Hotel hotel) throws HotelException {
		log.info("Inside addHotel method, Package: com.cg.hbms.dao");
		int dataIns = 0;
		try {
			con = DBUtil.getcon();
			System.out.println("con=" + con);// for checking the connection
			PreparedStatement pst1 = con.prepareStatement("INSERT into Hotel Values (?,?,?,?,?,?,?,?,?,?,?)");
			pst1.setString(1, hotel.getHotel_id());
			pst1.setString(2, hotel.getCity());
			pst1.setString(3, hotel.getHotel_name());
			pst1.setString(4, hotel.getAddress());
			pst1.setString(5, hotel.getDescription());
			pst1.setDouble(6, hotel.getAvg_rate_per_night());
			pst1.setString(7, hotel.getPhone_no1());
			pst1.setString(8, hotel.getPhone_no2());
			pst1.setString(9, hotel.getRating());
			pst1.setString(10, hotel.getEmail());
			pst1.setString(11, hotel.getFax());
			dataIns = pst1.executeUpdate();
			System.out.println(dataIns);
		} catch (Exception e) {
			log.error("Inside addHotel method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside addHotel method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return dataIns;
	}

	// method to add an admin user into the database
	/*
	 * Add Admin Method
	 * 
	 * @param - Users Object
	 * 
	 * @return - true or false in the form of 1 or 0
	 */
	@Override
	public int addAdmin(Users user) throws HotelException {
		log.info("Inside addAdmin method, Package: com.cg.hbms.dao");
		int i = 0;
		try {
			String qry = "Insert into Users VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement pst1 = con.prepareStatement(qry);
			pst1.setString(1, user.getUser_id());
			pst1.setString(2, user.getPassword());
			pst1.setString(3, user.getRole());
			pst1.setString(4, user.getUser_name());
			pst1.setString(5, user.getMobile_phone());
			pst1.setString(6, user.getPhone());
			pst1.setString(7, user.getAddress());
			pst1.setString(8, user.getEmail());
			i = pst1.executeUpdate();
		} catch (Exception e) {
			log.error("Inside addAdmin method, This Method has errors, Package: com.cg.hbms.dao, Error Message: "
					+ e.getMessage());
			throw new HotelException(e.getMessage());
		}
		log.info("Inside addAdmin method,Method Implemented Successfully, Package: com.cg.hbms.dao");
		return i;
	}
}