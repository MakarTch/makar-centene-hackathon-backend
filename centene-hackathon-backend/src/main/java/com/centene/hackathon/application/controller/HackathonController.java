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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class HackathonController {

	@Autowired
	EnrolleeDAO enrolleeRepo;
	
	@Autowired
	DependentDAO dependentRepo;
	
	@Autowired
	EnrolleeService enrolleeService;
	
	@Autowired
	DependentService dependentService;
	
	
	//Get all Enrollees
	@GetMapping("/enrollee")
	@ApiOperation(value = "Finds all Enrollees",
		notes="Returns a list of Enrollees", 
		response = Enrollee.class)
	public List<Enrollee> findAllEnrollees(){
		return enrolleeRepo.findAll();
	}
	
	//Get all Dependents
	@GetMapping("/dependent")
	@ApiOperation(value = "Finds all Dependents",
		notes="Returns a list of all Dependents", 
		response = Dependent.class)
	public List<Dependent> findAllDependents(){
		return dependentRepo.findAll();
	}
	
	//Get Enrollee by ID
	@GetMapping("/enrollee/{id}")
	@ApiOperation(value = "Finds Enrollee by ID",
		notes="Returns an Enrollee object", 
		response = Enrollee.class)
	public Enrollee findEnrolleeById (@PathVariable int id) {
		return enrolleeRepo.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("Enrollee not found with id: " + id));
	}
	
	//Get Dependent by ID
	@GetMapping("/dependent/{id}")
	@ApiOperation(value = "Finds Dependent by ID",
		notes="Returns a Dependent object", 
		response = Dependent.class)
	public Dependent findDependentById(@PathVariable int id) {
		return dependentRepo.findById(id)
				.orElseThrow(() ->new ResourceNotFoundException("Dependent not found with id: " + id));
	}
	
	//Post a new Enrollee
	@PostMapping("/enrollee")
	@ApiOperation(value = "Posts Enrollee",
		notes="Posts a new Enrollee into MySQL", 
		response = Enrollee.class)
	public Enrollee postEnrollee(@RequestBody Enrollee enrollee) {
		return enrolleeService.postEnrollee(enrollee);
	}
	
	//Post a new Dependent
	@PostMapping("/dependent")
	@ApiOperation(value = "Posts Dependent",
		notes="Posts a new Dependent into MySQL", 
		response = Dependent.class)
	public Dependent postDependent(@RequestBody Dependent dependent) {
		return dependentService.postDependent(dependent);
	}
	
	//Delete an Enrollee by their ID
	@DeleteMapping("/enrollee/{id}")
	@ApiOperation(value = "Delete Enrollee",
		notes="Deletes the enrollee by ID as well as the enrollee's dependents", 
		response= Enrollee.class)
	public void deleteEnrollee(@PathVariable int id) {
		enrolleeService.deleteEnrollee(id);
	}
	
	//Delete a Dependent by their ID
	@DeleteMapping("/dependent/{id}")
	@ApiOperation(value = "Delete Dependent",
		notes="Deletes the dependent by its EnrolleeID",
		response = Dependent.class)
	public void deleteDependent(@PathVariable int id) {
		dependentService.deleteDependent(id);
	}
	
	//Update an Enrollee
	@PutMapping("/enrollee/{id}")
	@ApiOperation(value = "Update Enrollee",
		notes="Updates the enrollee by ID", 
		response = Enrollee.class)
	public Enrollee updateEnrollee(@RequestBody Enrollee enrollee, @PathVariable int id) {
		return enrolleeService.updateEnrollee(enrollee, id);
	}
	
	//Update a Dependent
	@PutMapping("/dependent/{id}")
	@ApiOperation(value = "Update Dependent",
		notes="Updates the dependent by ID", 
		response = Dependent.class)
	public Dependent updateDependent(@RequestBody Dependent dependent, @PathVariable int id) {
		return dependentService.updateDependent(dependent, id);
	}
	
}
