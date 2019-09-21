package com.cg.hbms.Test;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import com.cg.hbms.dao.AdminDao;
import com.cg.hbms.dao.AdminDaoImpl;
import com.cg.hbms.dao.UserDao;
import com.cg.hbms.dao.UserDaoImpl;
import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;
import com.cg.hbms.service.AdminService;
import com.cg.hbms.service.AdminServiceImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

//class used to perform Junit testing
public class AdminJUnitTest {
	static Hotel hotel = null;
	static AdminDao adminDoa = null;
	static ArrayList<Hotel> list = null;
	static Hotel hot = null;
	static roomDetails rd = null;
	static ArrayList<BookingDetails> list1 = null;
	static ArrayList<BookingDetails> list2 = null;
	static Users user = null;
	static AdminService admser = null;
	static UserDao uDao = null;

	//Method used to set up things before the test can be run
	@BeforeClass
	public static void setup() {
		try {
			adminDoa = new AdminDaoImpl();
			hot = adminDoa.getHotelByHotelId("121");
			System.out.println(hot);
			uDao = new UserDaoImpl();
			admser = new AdminServiceImpl();
			list = adminDoa.getAllHotels();
			rd = adminDoa.getRoomByRoomId("R124");
			System.out.println(rd);
			list1 = adminDoa.getAllBookingsByHotel("121");
			System.out.println(list1);
			LocalDate d = LocalDate.now();
			user = new Users("12", "12", "admin", "Parth", "8018157255", "8018721555", "Pune", "p@gmail.com");
			list2 = adminDoa.viewBookingForSpecificDate(d);

			System.out.println(list2);

		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("BeforeClass");
	}

	// test case for getAllHotels
	@Test
	public void getAllHotelstest() {
		try {
			// check if the object is != null
			if (list.isEmpty()) {
				list = null;
			}
			Assert.assertNotNull(list);

			// checks if the returned object is of class hotel
			for (Hotel h : list) {
				Assert.assertTrue(h instanceof Hotel);
			}
		} catch (Exception e) {
			fail("got Exception , I want an object");
		}
	}

	// test case for getHotelById
	@Test
	public void getHotelbyIdtest() {
		try {
			// check if the object is != null
			Assert.assertNotNull(hot);

			// checks if the returned object is of class hotel
			Assert.assertTrue(hot instanceof Hotel);
		} catch (Exception e) {
			fail("got Exception , please check if the hotel with id 105 is present");
		}
	}

	// test case for getRoom by room Id will fail if the table does not have the
	// specified data
	@Test
	public void getRoomByRoomIdTest() {
		try {
			// check if the object is != null
			Assert.assertNotNull(rd);

			// checks if the returned object is of class hotel
			Assert.assertTrue(rd instanceof roomDetails);
		} catch (Exception e) {
			fail("got Exception , please check if the room with id 121 is present");
		}
	}

	// test case for getAllBookingsByHotel by Id will fail if the table does not
	// have the specified
	// data
	@Test
	public void getAllBookingsByHotelTest() {
		try {
			// check if the object is != null\
			if (list1.isEmpty()) {
				list1 = null;
			}
			Assert.assertNotNull(list1);

			// checks if the returned object is of class hotel
			for (Hotel h : list) {
				Assert.assertTrue(h instanceof Hotel);
			}

		} catch (Exception e) {
			fail("got Exception , I want an object");
		}
	}

	// test case for viewBookingForSpecificDate
	@Test
	public void viewBookingForSpecificDate() {
		try {
			// check if the object is != null
			if (list2.isEmpty()) {
				list2 = null;
			}
			Assert.assertNotNull(list2);

			// checks if the returned object is of class hotel
			/*
			 * for (Hotel h : list) { Assert.assertTrue(h instanceof Hotel); }
			 */
		} catch (Exception e) {
			fail("got Exception , I want an object");
		}
	}

	// test to for Add admin method
	@Test
	public void addAdminTest() {
		try {
			Assert.assertEquals(1, adminDoa.addAdmin(user));
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		} finally {
			// To delete the user added for testing purpose
			System.out.println("user id=" + user.getUser_id());
			uDao.deleteUserById(user.getUser_id());
		}
	}

	/*--------------Test case for Validation-----------------*/
	// To test the validate city name
	@Test
	public void validateCityName() {

		Assert.assertTrue("Name is Valid", admser.validateCityName("Pune"));
	}

	/*--------------Test case for Validation-----------------*/
	// To test the validate Phone number
	@Test
	public void validatePhone() {

		Assert.assertTrue("Phone number is valid", admser.validatePhoneNumber("8018157255"));
	}

	/*--------------Test case for Validation-----------------*/
	// To test the Email
	@Test
	public void validateEmail() {

		Assert.assertTrue("Email is valid", admser.validateEmail("parth@gmail.com"));
	}

	/*--------------Test case for Validation-----------------*/
	// To test the fax number
	@Test
	public void validateFax() {

		Assert.assertTrue("Fax is valid", admser.validateFax("+5456456"));
	}

	/*--------------Test case for Validation-----------------*/
	// To test the room number
	@Test
	public void validateRoomNo() {

		Assert.assertTrue("Room number is valid", admser.validateRoomNo("24"));
	}

	/*--------------Test case for Validation-----------------*/
	// To test the Amount
	@Test
	public void validateAmount() {

		Assert.assertTrue("Amount is not zero", admser.validateAmount(2400.0));
	}

	/*--------------Test case for Validation-----------------*/
	// To test the Amount
	@Test
	public void hasHotelId() {

		try {
			System.out.println(admser.hasHotelId(124));
			Assert.assertTrue("Amount is not zero", admser.hasHotelId(124));
		} catch (HotelException e) {
			System.out.println(e.getMessage());
		}
	}

	/*--------------Test case for Exceptions-----------------*/
	@Test (expected = HotelException.class)
    public  void throwsExceptionWhendeleteHotelgetsInvalidId() throws HotelException {
        // act
        adminDoa.deleteHotel(155);
    }
	
	/*--------------Test case for Exceptions-----------------*/
	@Test (expected = HotelException.class)
    public  void throwsExceptionWhenUpdateHotelgetsInvalidhotel() throws HotelException {
        // act
        adminDoa.updateHotel(null);
    }
	
	/*--------------Test case for Exceptions-----------------*/
	@Test (expected = HotelException.class)
    public  void throwsExceptionWhenDeleteRoomgetsInvalidRoomId() throws HotelException {
        // act
        adminDoa.deleteRoom("");
    }
}
