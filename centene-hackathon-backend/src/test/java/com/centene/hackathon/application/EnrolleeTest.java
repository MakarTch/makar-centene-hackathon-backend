package com.centene.hackathon.application;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.centene.hackathon.application.controller.EnrolleeController;
import com.centene.hackathon.application.model.Enrollee;
import com.centene.hackathon.application.service.EnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = EnrolleeController.class,excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class EnrolleeTest {

	
	@MockBean
	private EnrolleeService enrolleeService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final String baseUri = "http://localhost:8080/enrollee";
	
	@Test
	void testFindAllEnrollees() throws Exception {
		String mapping= "/findAll"; 
		String uri = baseUri + mapping;
		
		List<Enrollee> enrollees = new ArrayList<>();
		enrollees.add(new Enrollee(1,"Makar","Tchekalenkov", "07/02/1997", "347-623-7243", true));
		enrollees.add(new Enrollee());
		
		when(enrolleeService.findAllEnrollees()).thenReturn(enrollees);
		
		mockMvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(enrollees.size()))
		.andExpect(jsonPath("$[0].id").value(enrollees.get(0).getId()))
		.andExpect(jsonPath("$[0].firstName").value(enrollees.get(0).getFirstName()))
		.andExpect(jsonPath("$[0].lastName").value(enrollees.get(0).getLastName()))
		.andExpect(jsonPath("$[0].birthDate").value(enrollees.get(0).getBirthDate()))
		.andExpect(jsonPath("$[0].phoneNumber").value(enrollees.get(0).getPhoneNumber()))
		.andExpect(jsonPath("$[0].activationStatus").value(enrollees.get(0).isActivationStatus()))
		.andReturn();

		verify(enrolleeService, times(1)).findAllEnrollees();
		System.out.println("====================================================================================");
	}
	
	
	@Test
	void testFindEnrolleeById() throws Exception{
		String mapping= "/findById/1"; 
		String uri = baseUri + mapping;
		
		Enrollee enrollee = new Enrollee(1,"Makar","Tchekalenkov", "07/02/1997", "347-623-7243", true);
		
		when(enrolleeService.findEnrolleeById(1)).thenReturn(enrollee);
		
		mockMvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(enrollee.getId()))
		.andExpect(jsonPath("$.firstName").value(enrollee.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(enrollee.getLastName()))
		.andExpect(jsonPath("$.birthDate").value(enrollee.getBirthDate()))
		.andExpect(jsonPath("$.phoneNumber").value(enrollee.getPhoneNumber()))
		.andExpect(jsonPath("$.activationStatus").value(enrollee.isActivationStatus()))
		.andReturn();

		verify(enrolleeService, times(1)).findEnrolleeById(1);
		System.out.println("====================================================================================");
	}
	
	@Test
	void testPostEnrollee() throws Exception {
		String mapping= "/post"; 
		String uri = baseUri + mapping;
		
		Enrollee enrollee = new Enrollee(1, "Makar","Tchekalenkov", "07/02/1997", "347-623-7243", true);
		
		when(enrolleeService.postEnrollee(any(Enrollee.class))).thenReturn(enrollee);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(enrollee)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(enrollee.getId()))
		.andExpect(jsonPath("$.firstName").value(enrollee.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(enrollee.getLastName()))
		.andExpect(jsonPath("$.birthDate").value(enrollee.getBirthDate()))
		.andExpect(jsonPath("$.phoneNumber").value(enrollee.getPhoneNumber()))
		.andExpect(jsonPath("$.activationStatus").value(enrollee.isActivationStatus()))
		.andReturn();

		verify(enrolleeService, times(1)).postEnrollee(any(Enrollee.class));
		System.out.println("====================================================================================");
	}
	
	@Test
	void testUpdateEnrollee() throws Exception {
		String mapping= "/update/1"; 
		String uri = baseUri + mapping;
		
		Enrollee enrollee = new Enrollee(1, "Makar","Tchekalenkov", "07/02/1997", "347-623-7243", true);
		
		when(enrolleeService.updateEnrollee(any(Enrollee.class),any(Integer.class))).thenReturn(enrollee);

		mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(enrollee)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(enrollee.getId()))
		.andExpect(jsonPath("$.firstName").value(enrollee.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(enrollee.getLastName()))
		.andExpect(jsonPath("$.birthDate").value(enrollee.getBirthDate()))
		.andExpect(jsonPath("$.phoneNumber").value(enrollee.getPhoneNumber()))
		.andExpect(jsonPath("$.activationStatus").value(enrollee.isActivationStatus()))
		.andReturn();

		verify(enrolleeService, times(1)).updateEnrollee(any(Enrollee.class),any(Integer.class));
		System.out.println("====================================================================================");
	}
	
	
		
	public static String asJsonString(Enrollee enrollee) {
		try {
			return new ObjectMapper().writeValueAsString(enrollee);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}
