package com.centene.hackathon.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enrollee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String phoneNumber;
	private boolean activationStatus;
	
	public Enrollee() {
		super();
	}
	
	//constructor when all variables are given
	public Enrollee(int id, String firstName, String lastName, String birthDate, String phoneNumber,
			boolean activationStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.activationStatus = activationStatus;
	}

	//constructor without phone number, phone number is not required
	public Enrollee(int id, String firstName, String lastName, String birthDate, boolean activationStatus) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.phoneNumber = null;
		this.activationStatus = activationStatus;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean isActivationStatus() {
		return activationStatus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setActivationStatus(boolean activationStatus) {
		this.activationStatus = activationStatus;
	}

	@Override
	public String toString() {
		return "Enrollee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", phoneNumber=" + phoneNumber + ", activationStatus=" + activationStatus + "]";
	}
	
	
}
