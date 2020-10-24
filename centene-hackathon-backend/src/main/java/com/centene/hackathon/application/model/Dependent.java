package com.centene.hackathon.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Dependent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int enrolleeId;
	private String firstName;
	private String lastName;
	@Pattern(regexp = "[0-9]{2}/[0-9]{2}/[0-9]{4}", message= "Must enter birth data as xx/xx/xxxx, for example: 04/09/1995")
	private String birthDate;
	
	public Dependent() {
		super();
	}

	public Dependent(int id, int enrolleeId, String firstName, String lastName, String birthDate) {
		super();
		this.id = id;
		this.enrolleeId = enrolleeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public int getId() {
		return id;
	}

	public int getEnrolleeId() {
		return enrolleeId;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setEnrolleeId(int enrolleeId) {
		this.enrolleeId = enrolleeId;
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

	@Override
	public String toString() {
		return "Dependent [id=" + id + ", enrolleeId=" + enrolleeId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", birthDate=" + birthDate + "]";
	}
	
}
