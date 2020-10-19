package com.centene.hackathon.application.service;

import com.centene.hackathon.application.model.Dependent;

public class DependentService {
	
	public static String verifyDependent(Dependent dependent) {
		String exceptionString= "";
		String firstName = dependent.getFirstName();
		String lastName = dependent.getLastName();
		String birthDate = dependent.getBirthDate();
		
		if (firstName.length() > 50 || lastName.length() >50) {
			exceptionString+="Your first name and last name can't be more than 50 characters! ";
		}
	
		if (birthDate.length() > 20) {
			exceptionString+="Niether birth day or phone number can be more than 20 characters! ";
		}
	
		return exceptionString;
	}
}