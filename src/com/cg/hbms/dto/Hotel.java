package com.cg.hbms.dto;

//hotel details class with all the required variables declared
public class Hotel {
	String hotel_id;
	String city;
	String hotel_name;
	String address;
	String description;
	double avg_rate_per_night;
	String phone_no1;
	String phone_no2;
	String rating;
	String email;
	String fax;

	//getters and setters for all the declared variables 
	public String getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(String hotel_id) {
		this.hotel_id = hotel_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHotel_name() {
		return hotel_name;
	}

	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAvg_rate_per_night() {
		return avg_rate_per_night;
	}

	public void setAvg_rate_per_night(double avg_rate_per_night) {
		this.avg_rate_per_night = avg_rate_per_night;
	}

	public String getPhone_no1() {
		return phone_no1;
	}

	public void setPhone_no1(String phone_no1) {
		this.phone_no1 = phone_no1;
	}

	public String getPhone_no2() {
		return phone_no2;
	}

	public void setPhone_no2(String phone_no2) {
		this.phone_no2 = phone_no2;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	//constructor with all Variables
	public Hotel(String hotel_id, String city, String hotel_name, String address, String description,
			double avg_rate_per_night, String phone_no1, String phone_no2, String rating, String email, String fax) {
		super();
		this.hotel_id = hotel_id;
		this.city = city;
		this.hotel_name = hotel_name;
		this.address = address;
		this.description = description;
		this.avg_rate_per_night = avg_rate_per_night;
		this.phone_no1 = phone_no1;
		this.phone_no2 = phone_no2;
		this.rating = rating;
		this.email = email;
		this.fax = fax;
	}
	
	//default constructor
	public Hotel() {
		super();
		// TODO Auto-generated constructor stub
	}
	//constructor with hotel_id,address,address,phone_no1,phone_no2,rating,email
	public Hotel(String hotel_id,String address, String phone_no1, String phone_no2, String rating, String email) {
		super();
		this.hotel_id=hotel_id;
		this.address = address;
		this.phone_no1 = phone_no1;
		this.phone_no2 = phone_no2;
		this.rating = rating;
		this.email = email;
	}
	//constructor with ahotel_id,hotel_name,address,phone_no1
	public Hotel(String hotel_id,String hotel_name, String address, String phone_no1) {
		super();
		this.hotel_id=hotel_id;
		this.hotel_name = hotel_name;
		this.address = address;
		this.phone_no1 = phone_no1;
	}
	//to string method
	@Override
	public String toString() {
		return "Admin [hotel_id=" + hotel_id + ", city=" + city + ", hotel_name=" + hotel_name + ", address=" + address
				+ ", description=" + description + ", avg_rate_per_night=" + avg_rate_per_night + ", phone_no1="
				+ phone_no1 + ", phone_no2=" + phone_no2 + ", rating=" + rating + ", email=" + email + ", fax=" + fax
				+ "]";
	}

}
