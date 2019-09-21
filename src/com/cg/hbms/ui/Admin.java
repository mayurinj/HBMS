package com.cg.hbms.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.hbms.dao.MyStringDateUtil;
import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;
import com.cg.hbms.service.AdminService;
import com.cg.hbms.service.AdminServiceImpl;

//Ui method with admin functionalities
public class Admin {
	final static Logger log = Logger.getLogger(Admin.class);
	static Scanner sc = new Scanner(System.in);
	static String uId;
	static AdminService admService = null;
	//Constructor Called to Initialize service object
	public Admin() 	{
		log.info("Inside Admin Constructor in Admin Class. Package: com.cg.hbms.ui");
		try {
			admService = new AdminServiceImpl();
			log.info("Admin Constructor Method Implemented Successfully. Package: com.cg.hbms.ui");
		} catch (HotelException e) {
			log.error("Inside Admin Constructor in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}
	//method to get the user Id
	public static String UserID(String uID)	{
		uId = uID;
		return uId;
	}
	//method to delete a hotel using hotel_id
	public static void deleteHotel() {
		log.info("Inside deleteHotel Method in Admin Class. Package: com.cg.hbms.ui");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter the hotel id to be deleted");
		int id = Integer.parseInt(sc.nextLine());
		try {
			if (admService.hasHotelId(id)) {
				int i = admService.deleteHotel(id);
				if(i>0)	{
					System.out.println("Data Deleted Successfully");
					log.info("deleteHotel Method Implemented Successfully. Package: com.cg.hbms.ui");
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
				}
				else	{
					throw new HotelException("Data was not deleted successfully");
				}
			} else {
				throw new HotelException("Hotel Id Doesnot Exist");
			}
		} catch (HotelException e) {
			log.error("Inside deleteHotel Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}

	//method to add hotel with all required details
	public static void addHotel() {
		log.info("Inside addHotel Method in Admin Class. Package: com.cg.hbms.ui");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter the Hotel ID");
		String id = sc.nextLine();
		System.out.println("Enter the Hotel City");
		String city = sc.nextLine();
		city = validateCityName(city);

		System.out.println("Enter the Hotel Name");
		String name = sc.nextLine();
		name = validateHotelName(name);

		System.out.println("Enter the Hotel Address");
		String address = sc.nextLine();
		System.out.println("Enter the Description");
		String description = sc.nextLine();
		System.out.println("Enter the Average rate per night");
		double avg_rate_per_night = Float.parseFloat(sc.nextLine());
		avg_rate_per_night = validateAmount(avg_rate_per_night);
		System.out.println("Enter the phone number 1");
		String phone_1 = sc.nextLine();
		phone_1 = validatePhone(phone_1);

		System.out.println("Enter the phone number 2");
		String phone_2 = sc.nextLine();
		phone_2 = validatePhone(phone_2);

		System.out.println("Enter the Rating");
		String rating = sc.nextLine();

		System.out.println("Enter the Email");
		String email = sc.nextLine();
		email = validateEmail(email);

		System.out.println("Enter the Fax");
		String fax = sc.nextLine();
		Hotel hotel = new Hotel(id, city, name, address, description, (int) avg_rate_per_night, phone_1, phone_2,
				rating, email, fax);
		try {
			int i = admService.addHotel(hotel);
			if(i>0)	{
				System.out.println("Data added successfully");
				log.info("addHotel Method Implemented Successfully. Package: com.cg.hbms.ui");
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
			}
			else	{
				throw new HotelException("Data has not been added to the Database");
			}
		} catch (HotelException e) {
			log.error("Inside addHotel Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}

	//method to fetch hotel list with all hotel details
	public static void fetchAll() {
		log.info("Inside fetchHotel Method in Admin Class. Package: com.cg.hbms.ui");
		ArrayList<Hotel> list = null;
		boolean show = true;
		try {
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			list = admService.getAllHotels();
			System.out.println("Name of All hotels");
			while(show)	{
				for(int i=0;i<list.size();i++)	{
					System.out.print((i+1)+". ");
					System.out.println(list.get(i).getHotel_name());
				}
				System.out.println("Enter the number of hotel whose description that you want");
				int choice = Integer.parseInt(sc.nextLine());
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("Hotel Name: " + list.get(choice-1).getHotel_name());
				System.out.println("Hotel Id: " + list.get(choice-1).getHotel_id());
				System.out.println("Hotel Address: "+ list.get(choice-1).getAddress());
				System.out.println("Hotel City: "+list.get(choice-1).getCity());
				System.out.println("Hotel Description: "+list.get(choice-1).getDescription());
				System.out.println("Hotel Phone Numbers: "+list.get(choice-1).getPhone_no1()+","+list.get(choice-1).getPhone_no2());
				System.out.println("Hotel Average Rate per Night: "+list.get(choice-1).getAvg_rate_per_night());
				System.out.println("Hotel Rating: "+ list.get(choice-1).getRating());
				System.out.println("Hotel Email: "+list.get(choice-1).getEmail());
				System.out.println("Hotel Fax: "+list.get(choice-1).getFax());
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("If you want to exit Press 0 or if you want to continue press any number key except 0");
				int ch = Integer.parseInt(sc.nextLine());
				if(ch == 0)	{
					log.info("fetchAll Method Implemented Successfully. Package: com.cg.hbms.ui");
					show = false;
				}
				else	{
					show = true;
				}
			}
		} catch (HotelException e) {
			log.error("Inside fetchAll Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}

	//method to update hotel details using its hotel_id
	public static void update() {
		log.info("Inside update Method in Admin Class. Package: com.cg.hbms.ui");
		try {
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter hotel_id");
			String hotel_id = sc.nextLine();
			Hotel hotel1 = admService.getHotelByHotelId(hotel_id);
			if (hotel1 == null) {
				throw new HotelException("No Hotel By this Id");
			}
			System.out.println("Enter city");
			String city = sc.nextLine();
			city = validateCityName(city);
			System.out.println("Enter hotel_name");
			String hotel_name = sc.nextLine();
			hotel_name = validateHotelName(hotel_name);
			System.out.println("Enter address");
			String address = sc.nextLine();
			System.out.println("Enter description");
			String description = sc.nextLine();
			System.out.println("Enter avg_rate_per_night");
			String avg_rate_per_night = sc.nextLine();
			double avgRatePerNight = Double.parseDouble(avg_rate_per_night);
			avgRatePerNight = validateAmount(avgRatePerNight);
			System.out.println("Enter phone_no1");
			String phone_no1 = sc.nextLine();
			phone_no1 = validatePhone(phone_no1);
			System.out.println("Enter phone_no2");
			String phone_no2 = sc.nextLine();
			phone_no2 = validatePhone(phone_no2);
			System.out.println("Enter rating");
			String rating = sc.nextLine();
			System.out.println("email");
			String email = sc.nextLine();
			email = validateEmail(email);
			System.out.println("Enter fax");
			String fax = sc.nextLine();

			Hotel hotel = new Hotel(hotel_id, city, hotel_name, address, description, avgRatePerNight, phone_no1,
					phone_no2, rating, email, fax);
			int i = 0;
			i = admService.updateHotel(hotel);
			if (i > 0) {
				System.out.println("data Updated");
				log.info("update Method Implemented Successfully. Package: com.cg.hbms.ui");
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
			} else {
				throw new HotelException("Data not Inserted");
			}
		} catch (HotelException e) {
			log.error("Inside update Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}

	}

	//method to fetch booking details by hotel_id
	public static void fetchAllBookings() {
		log.info("Inside fetchAllBookings Method in Admin Class. Package: com.cg.hbms.ui");
		try {
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter hotel_id");
			String hotel_id = sc.nextLine();
			Hotel hotel1 = admService.getHotelByHotelId(hotel_id);
			if (hotel1 == null) {
				throw new HotelException("No Hotel By this Id");
			}
			ArrayList<BookingDetails> list = null;
			list = admService.getAllBookingsByHotel(hotel_id);
			boolean show = true;
			while(show)	{
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("These are the Booking Id and UserId that are registered into the system");
				for(int i=0;i<list.size();i++)	{
					System.out.println(i+1 +". Booking Id: " + list.get(i).getBooking_id()+"\n"+"User Id: "+list.get(i).getUser_id());
				}
				System.out.println("Select the Booking Id and User Id For which you want info");
				int choice = Integer.parseInt(sc.nextLine());
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("Booking Id: "+list.get(choice-1).getBooking_id());
				System.out.println("Hotel Id: "+list.get(choice-1).getHotel_id());
				System.out.println("Room Id: "+list.get(choice-1).getRoom_id());
				System.out.println("User Id: "+list.get(choice-1).getUser_id());
				System.out.println("Date of Check In: "+list.get(choice-1).getBooked_from());
				System.out.println("Date of Check Out: "+list.get(choice-1).getBooked_to());
				System.out.println("No of Adults: "+list.get(choice-1).getNo_of_adults());
				System.out.println("No of Children: "+list.get(choice-1).getNo_of_children());
				System.out.println("Amount for Booking: "+list.get(choice-1).getAmount());
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("If you want to exit Press 0 or if you want to continue press any number key except 0");
				int ch = Integer.parseInt(sc.nextLine());
				if(ch == 0)	{
					show = false;
					log.info("fetchAllBookings Method Implemented Successfully. Package: com.cg.hbms.ui");
				}
				else	{
					show = true;
				}
			}
		} catch (HotelException e) {
			log.error("Inside fetchAllBookings Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}

	}

	//method to fetch booking details for a period
	public static void viewBookingByDate() {
		log.info("Inside viewBookinfByDate Method in Admin Class. Package: com.cg.hbms.ui");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter Booking Date:");
		String str = sc.nextLine();
		LocalDate date = MyStringDateUtil.fromStringToLocalDate(str);
		ArrayList<BookingDetails> list = null;
		try {
			list = admService.viewBookingForSpecificDate(date);
			if (list.isEmpty()) {
				throw new HotelException("No booking for this date");
			} else {
				boolean show = true;
				while(show)	{
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
					System.out.println("These are the Booking Id and UserId that are registered into the system");
					for(int i=0;i<list.size();i++)	{
						System.out.println(i+1 +". Booking Id: " + list.get(i).getBooking_id()+"\n"+"User Id: "+list.get(i).getUser_id());
					}
					System.out.println("Select the Booking Id and User Id For which you want info");
					int choice = Integer.parseInt(sc.nextLine());
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
					System.out.println("Booking Id: "+list.get(choice-1).getBooking_id());
					System.out.println("Hotel Id: "+list.get(choice-1).getHotel_id());
					System.out.println("Room Id: "+list.get(choice-1).getRoom_id());
					System.out.println("User Id: "+list.get(choice-1).getUser_id());
					System.out.println("Date of Check In: "+list.get(choice-1).getBooked_from());
					System.out.println("Date of Check Out: "+list.get(choice-1).getBooked_to());
					System.out.println("No of Adults: "+list.get(choice-1).getNo_of_adults());
					System.out.println("No of Children: "+list.get(choice-1).getNo_of_children());
					System.out.println("Amount for Booking: "+list.get(choice-1).getAmount());
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
					System.out.println("If you want to exit Press 0 or if you want to continue press any number key except 0");
					int ch;
					try	{
						ch = Integer.parseInt(sc.nextLine());
					}
					catch(Exception e)	{
						throw new HotelException("Wrong Choice");
					}
					if(ch == 0)	{
						show = false;
						log.info("viewBookingByDate Method Implemented Successfully. Package: com.cg.hbms.ui");
					}
					else	{
						show = true;
					}
				}
			}
		} catch (HotelException e) {
			log.error("Inside viewBookinfByDate Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}

	//method to add room in a hotel 
	public static void addRoom() {
		log.info("Inside addRoom Method in Admin Class. Package: com.cg.hbms.ui");
		System.out.println("--------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter Hotel Id:");
		try {
			String hotel_id = sc.nextLine();
			Hotel hotel = admService.getHotelByHotelId(hotel_id);
			if (hotel == null) {
				throw new HotelException("No Hotels by this Id");
			} else {
				System.out.println("Enter Room Id:");
				String roomId = sc.nextLine();
				System.out.println("Enter Room No:");
				String roomNo = sc.nextLine();
				roomNo = validateRoomNo(roomNo);
				System.out.println("Enter Room Type:");
				String roomType = sc.nextLine();
				System.out.println("Enter Rate per Night:");
				double rate = Double.parseDouble(sc.nextLine());
				rate = validateAmount(rate);
				char avail = 'Y';
				roomDetails room = new roomDetails(hotel_id, roomId, roomNo, roomType, rate, avail);
				int i = admService.addRoomDetails(room);
				if (i == 1) {
					System.out.println("Details Entered Successfully");
					log.info("addRoom Method Implemented Successfully. Package: com.cg.hbms.ui");
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
				}
			}
		} catch (HotelException e) {
			log.error("Inside addRoom Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}

	//method to delete hotel room
	public static void delRoom() {
		log.info("Inside delRoom Method in Admin Class. Package: com.cg.hbms.ui");
		try {
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter Room Id: ");
			String roomId = sc.nextLine();
			roomDetails room = admService.getRoomByRoomId(roomId);
			if (room == null) {
				throw new HotelException("No Room by this room Id");
			} else {
				int i = admService.deleteRoom(roomId);
				if (i > 0) {
					System.out.println("Data Deleted Successfully");
					log.info("delRoom Method Implemented Successfully. Package: com.cg.hbms.ui");
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
				}
			}
		} catch (HotelException e) {
			log.error("Inside delRoom Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}

	//method to update hotel room details
	public static void updateRoomDetails() {
		log.info("Inside updateRoomDetails Method in Admin Class. Package: com.cg.hbms.ui");
		try {
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter Room Id:");
			String id = sc.nextLine();
			roomDetails room;
			room = admService.getRoomByRoomId(id);
			if (room == null) {
				throw new HotelException("No Room by this room Id");
			} else {
				System.out.println("Enter Updated Rate per Night: ");
				double rate = Double.parseDouble(sc.nextLine());
				rate = validateAmount(rate);
				roomDetails room1 = new roomDetails(room.getHotel_id(), room.getRoom_id(), room.getRoom_no(),
						room.getRoom_type(), rate, room.isAvailability());
				int i = admService.updateRoomDetails(room1);
				if (i > 0) {
					System.out.println("Room Details updated Successfully");
					log.info("updateRoomDetails Method Implemented Successfully. Package: com.cg.hbms.ui");
					System.out.println("--------------------------------------------------------------------------------------------------------------------");
				}
			}
		} catch (HotelException e) {
			log.error("Inside updateRoomDetails Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}
	//method to add an admin user
	public static void addAdmin()	{
		log.info("Inside addAdmin Method in Admin Class. Package: com.cg.hbms.ui");
		try	{
			@SuppressWarnings("unused")
			User user1 = new User();
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
			System.out.println("Enter User Id");
			String uId = sc.nextLine();
			uId = User.validateUsername(uId);
			System.out.println("Enter Password");
			String pass = sc.nextLine();
			pass = User.validatePassword(pass);
			String role = "admin";
			System.out.println("Enter User Name");
			String uName = sc.nextLine();
			uName = validateUserName(uName);
			System.out.println("Enter Mobile Number");
			String phone = sc.nextLine();
			phone = validatePhone(phone);
			System.out.println("Enter Phone Number");
			String phone1 = sc.nextLine();
			phone1 = validateFax(phone1);
			System.out.println("Enter Address");
			String addr = sc.nextLine();
			System.out.println("Enter Email");
			String email = sc.nextLine();
			email = validateEmail(email);
			Users user = new Users(uId, pass, role, uName,phone, phone1, addr, email);
			int i = admService.addAdmin(user);
			if(i>0)	{
				System.out.println("Admin Data Entered Successfully");
				log.info("addAdmin Method Implemented Successfully. Package: com.cg.hbms.ui");
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
			}
			else	{
				throw new HotelException("Data is not Entered");
			}
		}
		catch(HotelException e)	{
			log.error("Inside addAdmin Method in Admin Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}
	//method to validate email addresses
	public static String validateEmail(String email) {
		log.info("Inside validateEmail Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validateEmail(email)) {
			return email;
		} else {
			while (!admService.validateEmail(email)) {
				System.out.println("invalid email:enter again");
				email = sc.nextLine();
			}
			return email;
		}
	}

	//method to validate phone number
	public static String validatePhone(String ph) {
		log.info("Inside validatePhone Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validatePhoneNumber(ph)) {
			return ph;
		} else {
			while (!admService.validatePhoneNumber(ph)) {
				System.out.println("invalid mobile number(Enter 10 digit Phone Number Starting with 6-9):enter again");
				ph = sc.nextLine();
			}
			return ph;
		}
	}

	//method to validate hotel name with predefined rules
	public static String validateHotelName(String name) {
		log.info("Inside validateHotelName Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validateHotelName(name)) {
			return name;
		} else {
			while (!admService.validateHotelName(name)) {
				System.out.println("invalid Hotel name number(Enter first letter capital):enter again");
				name = sc.nextLine();
			}
			return name;
		}
	}

	//method to validate username
	public static String validateUserName(String name) {
		log.info("Inside validateUserName Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validateName(name)) {
			return name;
		} else {
			while (!admService.validateName(name)) {
				System.out.println("invalid Name(Enter first letter capital):enter again");
				name = sc.nextLine();
			}
			return name;
		}
	}

	//method to validate city name
	public static String validateCityName(String name) {
		log.info("Inside validateCityName Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validateCityName(name)) {
			return name;
		} else {
			while (!admService.validateCityName(name)) {
				System.out.println("invalid City Name(Enter First Letter Capital):enter again");
				name = sc.nextLine();
			}
			return name;
		}
	}
	
	//method to validate fax number
	public static String validateFax(String ph) {
		log.info("Inside validateFax Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validateFax(ph)) {
			return ph;
		} else {
			while (!admService.validateFax(ph)) {
				System.out.println("invalid phone number(Start with +):enter again");
				ph = sc.nextLine();
			}
			return ph;
		}
	}
	
	//method to validate room number
	public static String validateRoomNo(String rn) {
		log.info("Inside validateRoomNo Method in Admin Class. Package: com.cg.hbms.ui");
		if (admService.validateRoomNo(rn)) {
			return rn;
		} else {
			while (!admService.validateFax(rn)) {
				System.out.println("invalid Room number(Enter only Digits):enter again");
				rn = sc.nextLine();
			}
			return rn;
		}
	}
	
	//method to validate amount
	public static double validateAmount(double amount)	{
		log.info("Inside validateAmount Method in Admin Class. Package: com.cg.hbms.ui");
		if(admService.validateAmount(amount))	{
			return amount;
		}
		else	{
			while(!admService.validateAmount(amount))	{
				System.out.println("invalid Amount(Enter Positive Amount): enter again");
				amount = Double.parseDouble(sc.nextLine());
			}
			return amount;
		}
	}
}
