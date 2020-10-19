package com.centene.hackathon.application.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centene.hackathon.application.model.Enrollee;

@Repository
public interface EnrolleeDAO extends JpaRepository<Enrollee, Integer>{
	List<Enrollee> findAll();
	//Enrollee findById(int id);
	Optional<Enrollee> deleteById(int id);
}
