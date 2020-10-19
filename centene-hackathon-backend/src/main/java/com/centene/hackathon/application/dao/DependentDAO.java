package com.centene.hackathon.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centene.hackathon.application.model.Dependent;

@Repository
public interface DependentDAO extends JpaRepository<Dependent, Integer>{
	List<Dependent> findAll();
	//Dependent findById(int id);
	void deleteByEnrolleeId(int id);
	boolean existsByEnrolleeId(int id);
	List <Dependent> findAllByEnrolleeId(int id);
}
