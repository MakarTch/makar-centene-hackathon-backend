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
import com.centene.hackathon.application.model.Dependent;
import com.centene.hackathon.application.model.Enrollee;
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
	
	@GetMapping("/enrollee")
	@ApiOperation(value = "Finds all Enrollees",
		notes="Returns a list of Enrollees", 
		response = Enrollee.class)
	public List<Enrollee> findAllEnrollees(){
		return enrolleeRepo.findAll();
	}
	
	@GetMapping("/dependent")
	@ApiOperation(value = "Finds all Enrollees",
		notes="Returns a list of all Dependents", 
		response = Dependent.class)
	public List<Dependent> findAllDependents(){
		return dependentRepo.findAll();
	}
	
	@GetMapping("/enrollee/{id}")
	@ApiOperation(value = "Finds Enrollee by ID",
		notes="Returns an Enrollee object", 
		response = Enrollee.class)
	public Enrollee findEnrolleeById(@ApiParam(value="ID value for the Enrollee you need to retrieve", required=true)@PathVariable int id) {
		return enrolleeRepo.findById(id);
	}
	
	@GetMapping("/dependent/{id}")
	@ApiOperation(value = "Finds Dependent by ID",
		notes="Returns a Dependent object", 
		response = Dependent.class)
	public Dependent findDependentById(@ApiParam(value="ID value for the Dependent you need to retrieve", required=true)@PathVariable int id) {
		return dependentRepo.findById(id);
	}
		
	@PostMapping("/enrollee")
	@ApiOperation(value = "Posts Enrollee",
		notes="Posts a new Enrollee into MySQL", 
		response = Enrollee.class)
	public Enrollee postEnrollee(@RequestBody Enrollee enrollee) {
		return enrolleeRepo.save(enrollee);
	}
	
	@PostMapping("/dependent")
	@ApiOperation(value = "Posts Dependent",
		notes="Posts a new Dependent into MySQL", 
		response = Dependent.class)
	public Dependent postDependent(@RequestBody Dependent dependent) {
		return dependentRepo.save(dependent);
	}
	
	@DeleteMapping("/enrollee/{id}")
	@ApiOperation(value = "Delete Enrollee",
		notes="Deletes the enrollee by ID as well as the enrollee's dependents")
	public void deleteEnrollee(@PathVariable int id) {
		if (dependentRepo.existsByEnrolleeId(id)) {
			List<Dependent> dependents = dependentRepo.findAllByEnrolleeId(id);
			for (Dependent dependent : dependents) 
				dependentRepo.delete(dependent);
		}
		enrolleeRepo.deleteById(id);
	}
	
	@DeleteMapping("/dependent/{id}")
	@ApiOperation(value = "Delete Dependent",
		notes="Deletes the dependent by its EnrolleeID")
	public void deleteDependent(@PathVariable int id) {
		dependentRepo.deleteById(id);
		
	}
	
	@PutMapping("/enrollee")
	@ApiOperation(value = "Update Enrollee",
		notes="Updates the enrollee by ID", 
		response = Enrollee.class)
	public Enrollee updateEnrollee(@RequestBody Enrollee enrollee) {
		return enrolleeRepo.save(enrollee);
	}
	
	@PutMapping("/dependent")
	@ApiOperation(value = "Update Dependent",
		notes="Updates the dependent by ID", 
		response = Dependent.class)
	public Dependent updateDependent(@RequestBody Dependent dependent) {
		return dependentRepo.save(dependent);
	}
	
}
