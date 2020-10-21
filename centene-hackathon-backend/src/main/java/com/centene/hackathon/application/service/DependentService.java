package com.centene.hackathon.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.centene.hackathon.application.dao.DependentDAO;
import com.centene.hackathon.application.dao.EnrolleeDAO;
import com.centene.hackathon.application.exception.APIException;
import com.centene.hackathon.application.exception.ResourceNotFoundException;
import com.centene.hackathon.application.model.Dependent;

@Service
public class DependentService {
	
	@Autowired 
	DependentDAO dependentRepo;
	
	@Autowired
	EnrolleeDAO enrolleeRepo;
	
	/*
	 * Controller methods
	 */
	
	//Get all
	public List<Dependent> findAllDependents(){
		return dependentRepo.findAll();
	}
	
	//Get byID
	public Dependent findDependentById(int id) {
		return dependentRepo.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("Dependent not found with id: " + id));
	}
	//Post
	public Dependent postDependent(Dependent dependent) {
		if(enrolleeRepo.existsById(dependent.getEnrolleeId())) {
			try {
				dependent.setId(0);
				return dependentRepo.save(dependent);
			} catch (Exception e) {
				throw new APIException(DependentService.verifyDependent(dependent));
			}
		}else {
			throw new ResourceNotFoundException("Enrollee not found with id: " + dependent.getEnrolleeId());
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
		
		//Checks both first and last name are less than 50 characters
		if (firstName.length() > 50 || lastName.length() >50) 
			exceptionString+="Your first name and last name can't be more than 50 characters. ";
		
		//Check format of birth date
		if(!birthDate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))
			exceptionString+= "Please format birth data as xx/xx/xxxx, for example: 04/09/1995. ";
	
		return exceptionString;
	}
}