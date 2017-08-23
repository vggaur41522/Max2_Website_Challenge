package com.max2.model;

import java.io.Serializable;

/* ***************** QUESTION - 1 *****************
 * This part of code cover the problem statement Question-1: 
 * It is a POJO class corresponding to raw INput Data 
 */
public class Message implements Serializable  {
	public String firstName;
	public String lastName;
	public String address;
	public String zipCode;
	public String phoneNo;
	public String color;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public String toString()
	{
		return this.firstName + " | " +this.lastName + " | " +this.address + " | " +this.zipCode + " | " +this.phoneNo + " | " +this.color;
	}
}
