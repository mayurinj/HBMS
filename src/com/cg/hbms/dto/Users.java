package com.cg.hbms.dto;

//User Class with all the variables required declared
public class Users {
	//all variables
	String user_id;
	String password;
	String role;
	String user_name;
	String mobile_phone;
	String phone;
	String address;
	String email;
	
	//Getter and Setter for all the Variables
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMobile_phone() {
		return mobile_phone;
	}
	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//Constructor with the declaration of all the variables
	public Users(String user_id, String password, String role, String user_name, String mobile_phone, String phone,
			String address, String email) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.role = role;
		this.user_name = user_name;
		this.mobile_phone = mobile_phone;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}
	//default constructor
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//toString method for the given class
	@Override
	public String toString() {
		return "Users [user_id=" + user_id + ", password=" + password + ", role=" + role + ", user_name=" + user_name
				+ ", mobile_phone=" + mobile_phone + ", phone=" + phone + ", address=" + address + ", email=" + email
				+ "]";
	}
}
