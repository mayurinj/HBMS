package com.cg.hbms.Test;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.hbms.dao.MyStringDateUtil;
import com.cg.hbms.dao.UserDaoImpl;
import com.cg.hbms.dto.BookingDetails;
import com.cg.hbms.dto.Hotel;
import com.cg.hbms.dto.Users;
import com.cg.hbms.dto.roomDetails;
import com.cg.hbms.exception.HotelException;
import com.cg.hbms.service.UserServiceImpl;

//Method used to perform test for functionalities of the User class
import junit.framework.Assert;

public class UserJUnitTest {
	static UserServiceImpl srv = null;
	static UserDaoImpl dao = null;

	//Method Used to perform some tasks to run before the unit testing can be done
	@BeforeClass
	public static void setUp() throws HotelException {
		srv = new UserServiceImpl();
		dao = new UserDaoImpl();
		System.out.println("This function is called once before the execution of all test cases");
	}

	//Method Used to run some tasks which are done when all the testing is done
	@AfterClass
	public static void teraDown() {
		System.out.println("This function is called once after the execution of all test cases");
	}

	//test for admin login
	@Test
	public void loginTest() throws HotelException {
		Assert.assertEquals("admin", dao.login("dan8", "12345"));

	}

	//Test for user Login
	@Test
	public void loginTest5() throws HotelException {
		Assert.assertEquals("user", dao.login("mayu", "Mayu@1234"));

	}

	//Test for login Exception
	@Test(expected = HotelException.class)
	public void loginTest2() throws HotelException {
		Assert.assertEquals(HotelException.class, dao.login("dan8", "123454"));

	}

	//Test for login Exception
	@Test(expected = HotelException.class)
	public void loginTest3() throws HotelException {
		Assert.assertEquals(HotelException.class, dao.login("aksh", "pwd"));

	}

	//Test for login Exception
	@Test(expected = HotelException.class)
	public void loginTest4() throws HotelException {
		Assert.assertEquals(HotelException.class, dao.login("", ""));

	}

	//Test for Search Room
	@Test
	public void searchRoomTest() {
		try {

			ArrayList<roomDetails> list = null;
			list = dao.searchRoom("123");
			for (roomDetails rd : list) {
				// checks if the returned object is of class hotel
				Assert.assertTrue(rd instanceof roomDetails);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Test for search by hotel Name
	@Test
	public void searchByHotelNameTest() throws HotelException {
		try {

			ArrayList<Hotel> list = null;
			list = dao.getHotelsByName("Hyatt");
			for (Hotel h : list) {
				// checks if the returned object is of class hotel
				Assert.assertTrue(h instanceof Hotel);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Test for search Exception
	@Test(expected = HotelException.class)
	public void searchByHotelNameTest1() throws HotelException {
		@SuppressWarnings("unused")
		ArrayList<Hotel> list = null;
		Assert.assertEquals(HotelException.class, dao.getHotelsByName("hdjs"));
	}

	//Test for search by city
	@Test
	public void searchByCityTest() throws HotelException {
		try {

			ArrayList<Hotel> list = null;
			list = dao.getHotelsByCity("Pune");
			for (Hotel h : list) {
				// checks if the returned object is of class hotel
				Assert.assertTrue(h instanceof Hotel);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//Test for Exception for search by city
	@Test(expected = HotelException.class)
	public void searchByHotelCityTest2() throws HotelException {
		@SuppressWarnings("unused")
		ArrayList<Hotel> list = null;
		Assert.assertEquals(HotelException.class, dao.getHotelsByCity("hdjs"));
	}

	//Test for Book Room
	@Test
	public void bookRoomTest() throws HotelException {

		try {
			BookingDetails room = new BookingDetails("123", "101", "aksh",
					MyStringDateUtil.fromStringToLocalDate("19-09-2019"),
					MyStringDateUtil.fromStringToLocalDate("25-09-2019"), 2, 1, 7899);
			Assert.assertEquals(1, dao.bookRoom(room));
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	//Test for Exception in Book Room
	@Test(expected = HotelException.class)
	public void bookRoomTest2() throws HotelException {
		BookingDetails room = new BookingDetails();
		Assert.assertEquals(HotelException.class, dao.bookRoom(room));
	}

	//Test for Add User
	@Test
	public void addUserTest() throws HotelException {
		Users user = null;

		try {
			user = new Users("arun", "1234", "user", "Aksha", "9784561230", "7896542310", "Chennai", "aksha@gmail.com");
			// BookingDetails room=new
			// BookingDetails("123","101","aksh",MyStringDateUtil.fromStringToLocalDate("19-09-2019"),MyStringDateUtil.fromStringToLocalDate("25-09-2019"),2,1,7899);
			Assert.assertEquals(1, dao.addUser(user));
		} catch (HotelException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			// To delete the user added for testing purpose
			System.out.println("user id=" + user.getUser_id());
			dao.deleteUserById(user.getUser_id());
		}

	}

	//Test For Exception in Add User
	@Test(expected = HotelException.class)
	public void addUserTest2() throws HotelException {
		Users user = new Users("aksha", "1234", "user", "Aksha", "9784561230", "7896542310", "Chennai",
				"aksha@gmail.com");
		Assert.assertEquals(HotelException.class, dao.addUser(user));
	}

}
