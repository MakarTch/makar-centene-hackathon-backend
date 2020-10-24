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

import com.centene.hackathon.application.model.Dependent;
import com.centene.hackathon.application.service.DependentService;
import com.centene.hackathon.application.service.EnrolleeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/dependent")
@CrossOrigin(origins = "http://localhost:3000")
public class DependentController {

	
	@Autowired
	DependentService dependentService;
	
	//Get all Dependents
	@GetMapping("/findAll")
	@ApiOperation(value = "Finds all Dependents",
		notes="Returns a list of all Dependents", 
		response = Dependent.class)
	public List<Dependent> findAllDependents(){
		return dependentService.findAllDependents();
	}
	
	//Get Dependent by ID
	@GetMapping("/findById/{id}")
	@ApiOperation(value = "Finds Dependent by ID",
		notes="Returns a Dependent", 
		response = Dependent.class)
	public Dependent findDependentById(@ApiParam(value = "The Dependent id", required = true) @PathVariable int id) {
		return dependentService.findDependentById(id);
	}
	
	//Post a new Dependent
	@PostMapping("/post")
	@ApiOperation(value = "Posts Dependent",
		notes="Posts a new Dependent into database", 
		response = Dependent.class)
	public Dependent postDependent(@RequestBody Dependent dependent) {
		return dependentService.postDependent(dependent);
	}
	
	//Delete a Dependent by their ID
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "Deletes Dependent",
		notes="Deletes the dependent by ID",
		response = Dependent.class)
	public void deleteDependent(@ApiParam(value = "The Dependent id", required = true) @PathVariable int id) {
		dependentService.deleteDependent(id);
	}
	
	//Update a Dependent
	@PutMapping("/update/{id}")
	@ApiOperation(value = "Update Dependent",
		notes="Updates the dependent by ID", 
		response = Dependent.class)
	public Dependent updateDependent(@RequestBody Dependent dependent, @ApiParam(value = "The Dependent id", required = true) @PathVariable int id) {
		return dependentService.updateDependent(dependent, id);
	}
}
