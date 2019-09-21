package com.cg.hbms.ui;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.cg.hbms.service.UserService;
import com.cg.hbms.service.UserServiceImpl;

//Ui method with user functionalities
public class User {
	final static Logger log = Logger.getLogger(User.class);
	static String uId;
	static Scanner sc = new Scanner(System.in);
	static UserService userService = null;
	static AdminService admService = null;
	
	//Constructor used to initialize objects
	public User() {
		log.info("Inside User Constructor in User Class. Package: com.cg.hbms.ui");
		try {
			userService = new UserServiceImpl();
			admService = new AdminServiceImpl();
			log.info("User Constructor Method Implemented Successfully. Package: com.cg.hbms.ui");
		} catch (HotelException e) {
			log.error(
					"Inside User Constructor in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "
							+ e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	//method to get the user Id
	public static void userId(String uID) {
		uId = uID;
	}

	//User Registration method to be provided with required user details("user name,password,role,name,mobileNo,phoneNo,address,email") to register a user
	public static void register() {
		try {
			log.info("Inside Register Method in User Class. Package: com.cg.hbms.ui");
			System.out.println(
					"--------------------------------------------Register------------------------------------------------------------------------");
			@SuppressWarnings("unused")
			Admin admin = new Admin();
			admService = new AdminServiceImpl();
			// TODO Auto-generated method stub
			System.out.println("Enter your Name: ");
			String name = sc.nextLine();
			name = validateName(name);
			System.out.println("Enter username:");
			String username = sc.nextLine();
			username = validateUsername(username);
			System.out.println("Enter Password:");
			String password = sc.nextLine();
			password = validatePassword(password);
			String role = "user";
			System.out.println("Enter Mobile No:");
			String mobileNo = sc.nextLine();
			mobileNo = Admin.validatePhone(mobileNo);
			System.out.println("Enter Phone No:");
			String phoneNo = sc.nextLine();
			phoneNo = Admin.validatePhone(phoneNo);
			System.out.println("Enter Address:");
			String address = sc.nextLine();
			System.out.println("Enter Email:");
			String email = sc.nextLine();
			email = Admin.validateEmail(email);
			Users user = new Users(username, password, role, name, mobileNo, phoneNo, address, email);
			int addeduser = userService.addUser(user);

			if (addeduser != 0) {
				log.info("register user Method Implemented Successfully. Package: com.cg.hbms.ui. User added");
				System.out.println(
						"---------------------------------User Added Successfully----------------------------------------------------------------");

			}
		} catch (HotelException e) {
			log.error("Inside Register Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	
	//Method to book room 
	public static void bookRoom(String userId) {
		// TODO Auto-generated method stub
		log.info("Inside bookRoom Method in User Class. Package: com.cg.hbms.ui");
		try {
			System.out.println("Enter City(First letter must be capital):");
			String city = sc.nextLine();
			city = validateCityName(city);
			String hotel_id = getHotelNameAndCity(city);
			ArrayList<roomDetails> rooms = new ArrayList<roomDetails>();
			rooms = userService.searchRoom(hotel_id);

			while (rooms.isEmpty()) {
				System.out.println("There are no rooms Available");
				System.out.println("Enter Another Hotel: ");
				hotel_id = getHotelName(city);
				rooms = new ArrayList<roomDetails>();
				rooms = userService.searchRoom(hotel_id);
			}
			System.out.println("-----------------------Available Rooms: -----------------------------");
			System.out.println("**********************************************************************");
			System.out.println("Room ID \tRoom No. \tType \t Rate Per Night ");
			for (roomDetails room : rooms) {
				System.out.println(room.getRoom_id() + "\t\t" + room.getRoom_no() + "\t\t" + room.getRoom_type() + "\t"
						+ room.getPer_night_rate());
			}
			System.out.println("Enter room id to Book: ");
			String room_id = sc.nextLine();
			System.out.println("Enter Date of check in(dd-MM-yyyy):");
			String bookedFrom = sc.nextLine();
			boolean isValidate = userService.validateDate(bookedFrom, "dd-MM-yyyy");
			while (!isValidate) {
				System.out.println("Invalid Check in Date");
				System.out.println("Enter Date of check in(dd-MM-yyyy):");
				bookedFrom = sc.nextLine();
				isValidate = userService.validateDate(bookedFrom, "dd-MM-yyyy");
			}
			LocalDate checkIn = MyStringDateUtil.fromStringToLocalDate(bookedFrom);
			System.out.println("Enter Date of Check out(dd-MM-yyyy):");
			String bookedTo = sc.nextLine();
			isValidate = userService.validateCheckOutDate(bookedTo, checkIn, "dd-MM-yyyy");
			while (!isValidate) {
				System.out.println("Invalid Check out Date");
				System.out.println("Enter Date of check out(dd-MM-yyyy):");
				bookedFrom = sc.nextLine();
				isValidate = userService.validateCheckOutDate(bookedTo, checkIn, "dd-MM-yyyy");
			}
			LocalDate checkOut = MyStringDateUtil.fromStringToLocalDate(bookedTo);
			System.out.println("No. of peoples should not be greater than 4.");
			System.out.println("Enter no. of adults:");
			int adults = Integer.parseInt(sc.nextLine());
			System.out.println("Enter no. of childrens:");
			int childrens = Integer.parseInt(sc.nextLine());
			int total2 = adults + childrens;
			int total = adults + childrens;
			while(!(total<=4))
			{
				
				System.out.println("No. of peoples should not be greater than 4");
				System.out.println("Enter no. of adults:");
				adults = Integer.parseInt(sc.nextLine());
				System.out.println("Enter no. of childrens:");
				childrens = Integer.parseInt(sc.nextLine());
				total = adults + childrens;
			}
			BookingDetails bookRoom = new BookingDetails(hotel_id, room_id, userId, checkIn, checkOut, adults,
					childrens);
			double amount = 0;

			for (roomDetails room1 : rooms) {

				if (room1.getRoom_id().equals(room_id)) {
					amount = room1.getPer_night_rate();

				}
			}
			Period diff = checkIn.until(checkOut);
			int noOfDays = diff.getDays();
			double finalAmount = amount * noOfDays;
			bookRoom.setAmount(finalAmount);
			int isBooked = userService.bookRoom(bookRoom);
			if (isBooked != 0) {
				log.info("bookRoom Method Implemented Successfully. Package: com.cg.hbms.ui. ");
				System.out.println("Room Booked Successfully.");
				userService.changeAvailabilityStatus('N', room_id, hotel_id);
				if(total2>4)
				{
					System.out.println("If you want to book room for more than 4 people book another room.");
				}
			}
			

		} catch (HotelException e) {
			// TODO Auto-generated catch block
			log.error("Inside bookRoom Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
		}

	}

	//get hotel name method which gives hotels in the provided input city
	private static String getHotelName(String city) {
		// TODO Auto-generated method stub
		
		log.info("Inside getHotelName() Method in User Class. Package: com.cg.hbms.ui");
		String hotel_id = null;
		ArrayList<Hotel> hotels;
		try {
			hotels = userService.getHotelIdByHotelNameAndCityName(city);

		System.out.println("--------------------------------------Hotels Available in " + city
				+ "------------------------------------------------");

		System.out.println();
		System.out.println("Hotel Id\tHotelName\tHotel Address");
		for (Hotel hotel : hotels) {
			System.out.println(hotel.getHotel_id() + "\t\t" + hotel.getHotel_name() + "\t\t" + hotel.getAddress());
		}
		log.info("Inside getHotelName(). Displayed available hotels Successfully. Package: com.cg.hbms.ui. ");
		System.out.println();
		System.out.println("Enter Hotel Id for Booking Room: ");
		hotel_id = sc.nextLine();
		log.info("getHotelName() Method Implemented Successfully. Package: com.cg.hbms.ui. ");
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			log.error("Inside getHotelName() Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		return hotel_id;
	}

	//Method Used to get Hotels by using City
	public static String getHotelNameAndCity(String city) {
		log.info("Inside getHotelNameAndCity() Method in User Class. Package: com.cg.hbms.ui");
		String hotel_id = null;
	
		ArrayList<Hotel> hotels;
		try {
			hotels = userService.getHotelIdByHotelNameAndCityName(city);

			while (hotels.isEmpty()) {
				System.out.println("Invalid City Name or No Hotels Available");
				System.out.println("Enter City(First letter must be capital):");
				city = sc.nextLine();
				city = validateCityName(city);
				hotels = userService.getHotelIdByHotelNameAndCityName(city);
			}

			System.out.println("--------------------------------------Hotels Available in " + city
					+ "------------------------------------------------");

			System.out.println();
			System.out.println("Hotel Id\tHotelName\tHotel Address");
			for (Hotel hotel : hotels) {
				System.out.println(hotel.getHotel_id() + "\t\t" + hotel.getHotel_name() + "\t\t" + hotel.getAddress());
			}
			log.info("Inside gethotelNameAndCity(). Displayed available hotels Successfully. Package: com.cg.hbms.ui. ");
			System.out.println();
			System.out.println("Enter Hotel Id for Booking Room: ");
			hotel_id = sc.nextLine();
			log.info("getHotelNameAndCity Method Implemented Successfully. Package: com.cg.hbms.ui. ");
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			log.error("Inside getHotelNameAndCity Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
		}
		return hotel_id;
	}

	//Method Used to Validate City Name
	private static String validateCityName(String city) {
		log.info("Inside validateCityName Method in User Class. Package: com.cg.hbms.ui");
		try {
			if (userService.validate_name(city)) {
				return city;
			} else {
				while (!userService.validate_name(city)) {
					System.out.println("Invalid City -- First letter is not Capital:enter again");
					city = sc.nextLine();
				}

			}
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return city;
	}
	
	//check status method to check booking status
	public static void checkStatus(String userId) {
		// TODO Auto-generated method stub
		log.info("Inside checkStatus() Method in User Class. Package: com.cg.hbms.ui");
		ArrayList<BookingDetails> bookings = new ArrayList<BookingDetails>();
		try {
			bookings = userService.viewBookingStatus(userId);
			System.out.println(
					"*************************************Booking Details************************************************");
			System.out.println("Booking Id\tRoom Id\t\tCheck In\tCheck Out\tNo of Adults\tNo of Childrens\t\tAmount");

			for (BookingDetails booking : bookings) {
				System.out.println(booking.getBooking_id() + "\t\t" + booking.getRoom_id() + "\t\t"
						+ booking.getBooked_from() + "\t" + booking.getBooked_to() + "\t" + booking.getNo_of_adults()
						+ "\t\t" + booking.getNo_of_children() + "\t\t\t" + booking.getAmount());
			}
			log.info("checkStatus Method Implemented Successfully. Package: com.cg.hbms.ui. ");
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			log.error("Inside checkStatus Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			System.out.println(e.getMessage());
		}
	}

	//search method to search hotel by its name or city 
	public static void search(String userId) {
		log.info("Inside search() Method in User Class. Package: com.cg.hbms.ui");
		boolean value = true;
		while (value) {
			System.out.println("Search By: ");
			System.out.println("1.Hotel Name\n2.City\n3.Exit");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				System.out.println("Enter hotel Name:");
				String hotelName = sc.nextLine();
				try {
					if (!searchByHotelName(hotelName))	{
						continue;
				}
				else	{
					getDescription();
					value = false;
				}
				} catch (HotelException e) {
					log.error("Inside search Method in User Class. Package: com.cg.hbms.ui, This Method has Errors in searchByHotelName case, Error Message: "+ e.getMessage());
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter city:");
				String city = sc.nextLine();
				try {
					if (!searchByCity(city))	{
						continue;
					}
					else{
						getDescription();
						value = false;
					}
				} catch (HotelException e1) {
					log.error("Inside search Method in User Class. Package: com.cg.hbms.ui, This Method has Errors in searchByCity case, Error Message: "+ e1.getMessage());
					System.out.println(e1.getMessage());
				}
				break;
			case 3:
				value = false;
				break;
			}
		}
		log.info("search Method Implemented Successfully. Package: com.cg.hbms.ui. ");
	}

	//search hotel method for getting all the hotel names in provided input city 
	public static boolean searchByCity(String city) throws HotelException {
		log.info("Inside searchByCity() Method in User Class. Package: com.cg.hbms.ui");
		try {
			ArrayList<Hotel> listOfHotels = userService.getHotelsByCity(city);
			System.out.println("--------------------------------------Hotels Available in " + city
					+ "------------------------------------------------");
			System.out.println();
			System.out.println("Hotel Id" + "\t" + "Hotel Name" + "\t" + "Address" + "\t\t\t" + "Phone No.");
			Iterator<Hotel> i = listOfHotels.iterator();
			Hotel h = null;
			while (i.hasNext()) {
				h = i.next();
				System.out.println(h.getHotel_id() + "\t\t" + h.getHotel_name() + "\t\t" + h.getAddress() + "\t\t"
						+ h.getPhone_no1());
			}
			log.info("searchByCity Method Implemented Successfully. Package: com.cg.hbms.ui. ");
		} catch (HotelException e) {
			log.error("Inside searchbyCity Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			throw new HotelException("Invalid City or No Hotels Available ");
		}
		return true;

	}

	//search hotel with provided input hotel name 
	public static boolean searchByHotelName(String hotelName) throws HotelException {
		// TODO Auto-generated method stub
		log.info("Inside searhcByHotelName() Method in User Class. Package: com.cg.hbms.ui");
		try {
			ArrayList<Hotel> listOfHotels = userService.getHotelsByName(hotelName);
			System.out.println(
					"--------------------------------------------------------------------------------------------------");
			System.out.println(
					"Hotel Id" + "\t" + "Address" + "\t\t" + "Phone No1." + "\t" + "Phone No.2" + "\t" + "Email");
			Iterator<Hotel> i = listOfHotels.iterator();
			Hotel h = null;
			while (i.hasNext()) {
				h = i.next();
				System.out.println(h.getHotel_id() + "\t\t" + h.getAddress() + "\t" + h.getPhone_no1() + "\t"
						+ h.getPhone_no2() + "\t" + h.getEmail());
			}
			log.info("searchByHotelName() Method Implemented Successfully. Package: com.cg.hbms.ui. ");
		} catch (HotelException e) {
			log.error("Inside searchByHotelName Method in User Class. Package: com.cg.hbms.ui, This Method has Errors, Error Message: "+ e.getMessage());
			throw new HotelException("Invalid hotel name or No Hotels Available ");
		}
		return true;
	}
	
	//Method Used to Get description for the Hotels
	public static void getDescription()
	{
		log.info("Inside getDescription() Method in User Class. Package: com.cg.hbms.ui");
		System.out.println("Enter hotel id for Description:");
		String hotel_id = sc.nextLine();
		ArrayList<roomDetails> list2 = null;
		Hotel hotel = null;
		try {
			list2 = userService.searchRoom(hotel_id);
			hotel = admService.getHotelByHotelId(hotel_id);
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(
				"----------------------------------Hotel Details-------------------------------------------------------------");
		System.out.println("Description: " + hotel.getDescription());
		System.out.println("Email: " + hotel.getEmail());
		System.out.println("Ratings: " + hotel.getRating());
		System.out.println(
				"----------------------------------Room Details---------------------------------------------------------------");
		System.out.println("Room No\t\tRoom Type\tRate Per Night\t\tAvailability");
		for (roomDetails room : list2) {
			System.out.println(room.getRoom_no() + "\t\t" + room.getRoom_type() + "\t\t" + room.getPer_night_rate()
					+ "\t\t\t" + room.isAvailability());
		}
		log.info("getDescription() Method Implemented Successfully. Package: com.cg.hbms.ui. ");
	}
	
	//Name validation method 
	public static String validateName(String name) {
		// TODO Auto-generated method stub
		log.info("Inside validateName Method in User Class. Package: com.cg.hbms.ui");
		try {
			if (userService.validate_name(name)) {
				return name;
			} else {
				while (!userService.validate_name(name)) {
					System.out.println("First Letter Must Be Capital:enter again");
					name = sc.nextLine();
				}

			}
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return name;
	}

	//password validation method 
	public static String validatePassword(String password) {
		// TODO Auto-generated method stub
		log.info("Inside validatePassword Method in User Class. Package: com.cg.hbms.ui");
		try {
			if (userService.validate_password(password)) {
				return password;
			} else {
				while (!userService.validate_password(password)) {
					System.out.println("Password should be between 8 and 20 characters long.\r\n"
							+ "Contain at least one digit.\r\n" + "Contain at least one lower case character.\r\n"
							+ "Contain at least one upper case character.\r\n"
							+ "Contain at least on special character from [ @ # $ % ! . ].");
					System.out.println("Enter Password Again: ");
					password = sc.nextLine();
				}

			}
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return password;
	}

	//User Name validation method 
	public static String validateUsername(String username) {
		// TODO Auto-generated method stub
		log.info("Inside validateUsername Method in User Class. Package: com.cg.hbms.ui");
		try {
			if (userService.validate_userName(username)) {
				return username;
			} else {
				while (!userService.validate_userName(username)) {
					System.out.println("Username already taken or max length(4) exceeded:enter again");
					username = sc.nextLine();
				}
			}
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
		return username;
	}
}
