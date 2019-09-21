package com.cg.hbms.dto;

import java.time.LocalDate;
//booking details class with all the required variables declared
public class BookingDetails {
	String booking_id;
	String hotel_id;
	String room_id;
	String user_id;
	LocalDate booked_from;
	LocalDate booked_to;
	int no_of_adults;
	int no_of_children;
	double amount;
	//getters and setters for all the declared variables
	public String getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(String booking_id) {
		this.booking_id = booking_id;
	}
	public String getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(String hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public LocalDate getBooked_from() {
		return booked_from;
	}
	public void setBooked_from(LocalDate booked_from) {
		this.booked_from = booked_from;
	}
	public LocalDate getBooked_to() {
		return booked_to;
	}
	public void setBooked_to(LocalDate booked_to) {
		this.booked_to = booked_to;
	}
	public int getNo_of_adults() {
		return no_of_adults;
	}
	public void setNo_of_adults(int no_of_adults) {
		this.no_of_adults = no_of_adults;
	}
	public int getNo_of_children() {
		return no_of_children;
	}
	public void setNo_of_children(int no_of_children) {
		this.no_of_children = no_of_children;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	//constructor with all the declared values
	public BookingDetails(String booking_id, String hotel_id, String room_id, String user_id, LocalDate booked_from,
			LocalDate booked_to, int no_of_adults, int no_of_children, double amount) {
		super();
		this.booking_id = booking_id;
		this.hotel_id = hotel_id;
		this.room_id = room_id;
		this.user_id = user_id;
		this.booked_from = booked_from;
		this.booked_to = booked_to;
		this.no_of_adults = no_of_adults;
		this.no_of_children = no_of_children;
		this.amount = amount;
	}
	//default constructor 
	public BookingDetails() {
		super();
	}
	//constructor with all the declared values except hotel id
	public BookingDetails(String booking_id, String room_id, String user_id, LocalDate booked_from, LocalDate booked_to,
			int no_of_adults, int no_of_children, double amount) {
		super();
		this.booking_id = booking_id;
		this.room_id = room_id;
		this.user_id = user_id;
		this.booked_from = booked_from;
		this.booked_to = booked_to;
		this.no_of_adults = no_of_adults;
		this.no_of_children = no_of_children;
		this.amount = amount;
	}
	//constructor with all the declared values except booking id
	public BookingDetails(String hotel_id, String room_id, String user_id, LocalDate booked_from, LocalDate booked_to,
			int no_of_adults, int no_of_children) {
		super();
		this.hotel_id = hotel_id;
		this.room_id = room_id;
		this.user_id = user_id;
		this.booked_from = booked_from;
		this.booked_to = booked_to;
		this.no_of_adults = no_of_adults;
		this.no_of_children = no_of_children;
	}
	//to string method
	@Override
	public String toString() {
		return "BookingDetails [booking_id=" + booking_id + ", hotel_id=" + hotel_id + ", room_id=" + room_id
				+ ", user_id=" + user_id + ", booked_from=" + booked_from + ", booked_to=" + booked_to
				+ ", no_of_adults=" + no_of_adults + ", no_of_children=" + no_of_children + ", amount=" + amount + "]";
	}

}
