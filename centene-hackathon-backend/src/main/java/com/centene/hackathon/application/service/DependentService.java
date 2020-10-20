package com.centene.hackathon.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centene.hackathon.application.dao.DependentDAO;
import com.centene.hackathon.application.exception.APIException;
import com.centene.hackathon.application.exception.ResourceNotFoundException;
import com.centene.hackathon.application.model.Dependent;

@Service
public class DependentService {
	
	@Autowired 
	DependentDAO dependentRepo;
	
	/*
	 * Controller methods
	 */
	
	//Post
	public Dependent postDependent(Dependent dependent) {
		try {
			return dependentRepo.save(dependent);
		} catch (Exception e) {
			throw new APIException(DependentService.verifyDependent(dependent));
		}
	}
	
	//Delete
	public void deleteDependent(int id) {
		dependentRepo.findById(id)
			.orElseThrow(() ->new ResourceNotFoundException("Dependent not found with id: " + id));
		dependentRepo.deleteById(id);
	}
	
	//Update
	public Dependent updateDependent(Dependent dependent, int id) {
		if(dependentRepo.existsById(id)) {
			try {
				dependent.setId(id);
				return dependentRepo.save(dependent);
			} catch (Exception e) {
				throw new APIException(DependentService.verifyDependent(dependent));
			}
		}
		throw new ResourceNotFoundException("Dependent not found with id: " + id);

	}
	
	/*
	 * Dependent verification
	 */
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