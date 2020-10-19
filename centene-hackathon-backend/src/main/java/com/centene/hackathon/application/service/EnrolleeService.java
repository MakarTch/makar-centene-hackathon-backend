package com.centene.hackathon.application.service;

import com.centene.hackathon.application.model.Enrollee;

public class EnrolleeService {
	
	public static String verifyEnrollee(Enrollee enrollee) {
		String exceptionString= "";
		String firstName = enrollee.getFirstName();
		String lastName = enrollee.getLastName();
		String birthDate = enrollee.getBirthDate();
		String phoneNumber = enrollee.getPhoneNumber();
		
		if (firstName.length() > 50 || lastName.length() >50) {
			exceptionString+="Your first name and last name can't be more than 50 characters! ";
		}

		if (birthDate.length() > 20 || phoneNumber.length()> 20) {
			exceptionString+="Niether birth day or phone number can be more than 20 characters! ";
		}

		return exceptionString;
	}
}
