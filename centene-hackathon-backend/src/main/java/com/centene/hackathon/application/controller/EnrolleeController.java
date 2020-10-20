package com.centene.hackathon.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.centene.hackathon.application.dao.DependentDAO;
import com.centene.hackathon.application.dao.EnrolleeDAO;
import com.centene.hackathon.application.exception.APIException;
import com.centene.hackathon.application.exception.ResourceNotFoundException;
import com.centene.hackathon.application.model.Dependent;
import com.centene.hackathon.application.model.Enrollee;
import com.centene.hackathon.application.service.DependentService;
import com.centene.hackathon.application.service.EnrolleeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/enrollee")
@CrossOrigin(origins = "http://localhost:3000")
public class EnrolleeController {
	
	@Autowired
	EnrolleeService enrolleeService;
	
	@Autowired
	DependentService dependentService;
	
	//Get all Enrollees
	@GetMapping("/findAll")
	@ApiOperation(value = "Finds all Enrollees",
		notes="Returns a list of Enrollees", 
		response = Enrollee.class)
	public List<Enrollee> findAllEnrollees(){
		return enrolleeService.findAllEnrollees();
	}
	
	//Get Enrollee by ID
	@GetMapping("/findById/{id}")
	@ApiOperation(value = "Finds Enrollee by ID",
		notes="Returns an Enrollee object", 
		response = Enrollee.class)
	public Enrollee findEnrolleeById (@PathVariable int id) {
		return enrolleeService.findEnrolleeById(id);
	}	
	
	//Post a new Enrollee
	@PostMapping("/post")
	@ApiOperation(value = "Posts Enrollee",
		notes="Posts a new Enrollee into MySQL", 
		response = Enrollee.class)
	public Enrollee postEnrollee(@RequestBody Enrollee enrollee) {
		return enrolleeService.postEnrollee(enrollee);
	}
	
	//Delete an Enrollee by their ID
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Delete Enrollee",
		notes="Deletes the enrollee by ID as well as the enrollee's dependents", 
		response= Enrollee.class)
	public void deleteEnrollee(@PathVariable int id) {
		enrolleeService.deleteEnrollee(id);
	}
	
	//Update an Enrollee
	@PutMapping("/update/{id}")
	@ApiOperation(value = "Update Enrollee",
		notes="Updates the enrollee by ID", 
		response = Enrollee.class)
	public Enrollee updateEnrollee(@RequestBody Enrollee enrollee, @PathVariable int id) {
		return enrolleeService.updateEnrollee(enrollee, id);
	}
}
