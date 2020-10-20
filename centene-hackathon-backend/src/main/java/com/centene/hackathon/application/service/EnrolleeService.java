package com.centene.hackathon.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centene.hackathon.application.dao.DependentDAO;
import com.centene.hackathon.application.dao.EnrolleeDAO;
import com.centene.hackathon.application.exception.APIException;
import com.centene.hackathon.application.exception.ResourceNotFoundException;
import com.centene.hackathon.application.model.Dependent;
import com.centene.hackathon.application.model.Enrollee;

@Service
public class EnrolleeService {
	
	@Autowired
	EnrolleeDAO enrolleeRepo;
	
	@Autowired
	DependentDAO dependentRepo;
	
	/*
	 * Controller methods
	 */
	
	//Post
	public Enrollee postEnrollee(Enrollee enrollee) {
		try {
			return enrolleeRepo.save(enrollee);
		} catch (Exception e){
			throw new APIException(EnrolleeService.verifyEnrollee(enrollee));
		}
	}
	
	//Delete
	public void deleteEnrollee(int id) {
		if (dependentRepo.existsByEnrolleeId(id)) {
			List<Dependent> dependents = dependentRepo.findAllByEnrolleeId(id);
			for (Dependent dependent : dependents) 
				dependentRepo.delete(dependent);
		}
		enrolleeRepo.findById(id)
			.orElseThrow(() ->new ResourceNotFoundException("Enrollee not found with id: " + id));
		enrolleeRepo.deleteById(id);
	}
	
	//Update
	public Enrollee updateEnrollee(Enrollee enrollee, int id) {
		if(enrolleeRepo.existsById(id)) {
			try {
				enrollee.setId(id);
				return enrolleeRepo.save(enrollee);
			} catch (Exception e){
				throw new APIException(EnrolleeService.verifyEnrollee(enrollee));
			}	
		}
		throw new ResourceNotFoundException("Enrollee not found with id: " + id);
	}
	
	
	/*
	 * Enrollee verification
	 */
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
