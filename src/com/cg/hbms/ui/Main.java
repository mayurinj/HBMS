package com.cg.hbms.ui;


import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.hbms.exception.HotelException;
import com.cg.hbms.service.AdminService;
import com.cg.hbms.service.AdminServiceImpl;
import com.cg.hbms.service.UserService;
import com.cg.hbms.service.UserServiceImpl;

public class Main {
	// logger initialization
	final static Logger log = Logger.getLogger(Main.class);
	// user service initialization
	static UserService userService = null;
	//admin service initialization
	static AdminService admService = null;
	// scanner initialization
	static Scanner sc = new Scanner(System.in);
	static boolean login = false;
	static int choice;
	public static void main(String[] args) {
		//method function with login ,register options
		while(login==false)	{
			try {
				userService = new UserServiceImpl();
				admService = new AdminServiceImpl();
				log.info("In the home Method,  Package: com.cg.hbms.ui");
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("*****************************Welcome to Hotel Booking Management System*********************************************");
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				@SuppressWarnings("unused")
				int updatedStatus = userService.changeAvailabilityStatusToYes("Y");
				System.out.println("1.Login");
				System.out.println("2.Register");
				System.out.println("3.Exit");
				int choice=0;
				choice = Integer.parseInt(sc.nextLine());
				switch(choice)
				{
					case 1:
						login();
						break;
					case 2:
						@SuppressWarnings("unused") 
						User use = new User();
						User.register();
						break;
					case 3:
						System.exit(0);
					default :
						System.out.println("Wrong Choice");
				}
			} catch (HotelException e) {
				log.error("In the home Method,  Package: com.cg.hbms.ui, The Methods has Errors, Error Message: "+e.getMessage());
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	//login method which requires userid,password that are to be validated
	private static void login()
	{
			try {
				log.info("Inside the login Method in Main Class, Package: com.cg.hbms.ui");
				System.out.println("Enter your userId:");
				String userId = sc.nextLine();
				System.out.println("Enter your password:");
				String password = sc.nextLine();
				String role = "";
				role = userService.login(userId, password);
				if (role.equals("")) {
					throw new HotelException("Please Enter UserName and Password Again");
				}
				
				if (role.equals("admin")) {
					log.info("Admin logged in, UserId: "+ userId);
					adminLogin(userId);
				} else if (role.equals("user")) {
					log.info("User logged in, UserId: "+ userId);
					userLogin(userId);
				}
				else	{
					throw new HotelException("Role Configured wrong by Admin. Please Contact Admin for update");
				}
			} catch (HotelException e) {
				log.error("Inside the login Method in Main Class, Package: com.cg.hbms.ui, The Methods has Errors, Error Message: "+e.getMessage());
				System.out.println(e.getMessage());
			}
	}
	
	//user login screen method with all the user functionalities mentioned 
	public static void userLogin(String userId)	{
		try {
			log.info("Inside userLogin Method in Main Class, Package: com.cg.hbms.ui");
			@SuppressWarnings("unused")
			User use = new User();
			User.userId(userId);
			login = true;
			System.out.println("------------------------------------------------------------------------------------------------------------------------");
			System.out.println("******************************************Welcome User*******************************************************************************");
			System.out.println("------------------------------------------------------------------------------------------------------------------------");
			while (login) {
				System.out.println("--------------------------------------Options Available--------------------------------------------------------------------------");
				System.out.println("1. Search Hotel" + "\n" + "2. Book Room" + "\n" + "3. Check Status of Booking"+"\n"+"4. Logout");
				choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					User.search(userId);
					break;
				case 2:
					User.bookRoom(userId);
					break;
				case 3:
					User.checkStatus(userId);
					break;
				case 4:
					login = false;
					log.info("User Logged Out, UserId: " + userId);
					break;
				default:
					System.out.println("Invalid Choice");
					break;
				}
			}
		}
		catch (Exception e) {
			log.error("Inside userLogin Method in Main Class, Package: com.cg.hbms.ui, The Methods has Errors, Error Message: "+e.getMessage());
			System.out.println(e.getMessage());
		}
	}
	
	//admin login screen method with all admin  functionalities mentioned 
	public static void adminLogin(String userId)	{
		log.info("Inside addAdmin Method in Main Class, Package: com.cg.hbms.ui");
		@SuppressWarnings("unused")
		Admin admin = new Admin();
		userId = Admin.UserID(userId);
		login = true;
		try {
			admService = new AdminServiceImpl();
			while (login) {
				System.out.println("--------------------------------------------------------------------------------------------------------------------");
				System.out.println("Welcome Admin");
				System.out.println("Options Available:-");
				System.out.println("1. Add Hotel");
				System.out.println("2. Delete Hotel");
				System.out.println("3. Update Hotel");
				System.out.println("4. View All Hotels");
				System.out.println("5. View All Bookings By Hotel");
				System.out.println("6. View Bookings for a Date");
				System.out.println("7. Add Room for a hotel");
				System.out.println("8. Delete Room of a Hotel");
				System.out.println("9. Update Room Details");
				System.out.println("10. Add a new Admin");
				System.out.println("11. Logout");
				choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					Admin.addHotel();
					break;
				case 2:
					Admin.deleteHotel();
					break;
				case 3:
					Admin.update();
					break;
				case 4:
					Admin.fetchAll();
					break;
				case 5:
					Admin.fetchAllBookings();
					break;
				case 6:
					Admin.viewBookingByDate();
					break;
				case 7:
					Admin.addRoom();
					break;
				case 8:
					Admin.delRoom();
					break;
				case 9:
					Admin.updateRoomDetails();
					break;
				case 10:
					Admin.addAdmin();
					break;
				case 11:
					login = false;
					log.info("Admin Logged Out, UserId: "+userId);
					break;
				default:
					System.out.println("Invalid Choice");					
					break;
				}
			}
		}
		catch(HotelException e) {
			log.error("Inside addAdmin Method in Main Class, Package: com.cg.hbms.ui, The Methods has Errors, Error Message: "+e.getMessage());
			System.out.println(e.getMessage());
			System.out.println("--------------------------------------------------------------------------------------------------------------------");
		}
	}
	
}