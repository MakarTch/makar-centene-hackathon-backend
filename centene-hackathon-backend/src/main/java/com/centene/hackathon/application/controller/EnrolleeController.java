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
import com.centene.hackathon.application.model.Enrollee;
import com.centene.hackathon.application.service.EnrolleeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/enrollee")
@CrossOrigin(origins = "http://makarshealthcareapplication.s3-website.us-east-2.amazonaws.com")
public class EnrolleeController {
	
	@Autowired
	EnrolleeService enrolleeService;
	
	
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
	public Enrollee findEnrolleeById (@ApiParam(value = "The Enrollee id", required = true) @PathVariable int id) {
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
	@ApiOperation(value = "Deletes Enrollee",
		notes="Deletes the enrollee by ID as well as the enrollee's dependents", 
		response= Enrollee.class)
	public int deleteEnrollee(@ApiParam(value = "The Enrollee id", required = true) @PathVariable int id) {
		enrolleeService.deleteEnrollee(id);
		return(id);
	}
	
	//Update an Enrollee
	@PutMapping("/update/{id}")
	@ApiOperation(value = "Update Enrollee",
		notes="Updates the enrollee by ID", 
		response = Enrollee.class)
	public Enrollee updateEnrollee(@RequestBody Enrollee enrollee,@ApiParam(value = "The Enrollee id", required = true)  @PathVariable int id) {
		return enrolleeService.updateEnrollee(enrollee, id);
	}
}
