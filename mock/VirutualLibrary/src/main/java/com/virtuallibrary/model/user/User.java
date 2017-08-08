package com.virtuallibrary.model.user;

import com.virtuallibrary.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User extends BaseModel {


	public User() {}

	public User(Long id, String roleID, String libID, String username, String password, String lastName, String firstName, Date birthDay, String address, String phone, String email, String cardID, boolean active) {
		this.id = id;
		this.roleID = roleID;
		this.libID = libID;
		this.userName = username;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDay = birthDay;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.cardID = cardID;
		this.active = active;
	}

	private String roleID;
	private String libID;
	private String userName;
	private String password;
	private String lastName;
	private String firstName;
	private Date birthDay;
	private String address;
	private String phone;
	private String email;
	private String cardID;
	private boolean active;

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getLibID() {
		return libID;
	}

	public void setLibID(String libID) {
		this.libID = libID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardID() {
		return cardID;
	}

	public void setCardID(String cardID) {
		this.cardID = cardID;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
