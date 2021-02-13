package com.centene.hackathon.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centene.hackathon.application.model.Enrollee;

@Repository
public interface EnrolleeDAO extends JpaRepository<Enrollee, Integer>{
	
}
