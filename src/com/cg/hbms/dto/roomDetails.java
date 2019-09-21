package com.cg.hbms.dto;

//room details class with all the required variables declared
public class roomDetails {
	String hotel_id;
	String room_id;
	String room_no;
	String room_type;
	double per_night_rate;
	char availability;
	//getters and setters for all the declared variables 
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
	public String getRoom_no() {
		return room_no;
	}
	public void setRoom_no(String room_no) {
		this.room_no = room_no;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public double getPer_night_rate() {
		return per_night_rate;
	}
	public void setPer_night_rate(double per_night_rate) {
		this.per_night_rate = per_night_rate;
	}
	public char isAvailability() {
		return availability;
	}
	public void setAvailability(char availability) {
		this.availability = availability;
	}
	//constructor with all the declared values
	public roomDetails(String hotel_id, String room_id, String room_no, String room_type, double per_night_rate,
			char availability) {
		super();
		this.hotel_id = hotel_id;
		this.room_id = room_id;
		this.room_no = room_no;
		this.room_type = room_type;
		this.per_night_rate = per_night_rate;
		this.availability = availability;
	}
	//default constructor
	public roomDetails() {
		super();
	}
	//to string method
	@Override
	public String toString() {
		return "roomDetails [hotel_id=" + hotel_id + ", room_id=" + room_id + ", room_no=" + room_no + ", room_type="
				+ room_type + ", per_night_rate=" + per_night_rate + ", availability=" + availability + "]";
	}
}