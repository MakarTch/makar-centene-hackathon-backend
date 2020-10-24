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

import com.centene.hackathon.application.controller.DependentController;
import com.centene.hackathon.application.model.Dependent;
import com.centene.hackathon.application.service.DependentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = DependentController.class,excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class DependentTest {

	
	@MockBean
	private DependentService dependentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private final String baseUri = "http://localhost:8080/dependent";
	
	@Test
	void testFindAllDependents() throws Exception {
		String mapping= "/findAll"; 
		String uri = baseUri + mapping;
		
		List<Dependent> dependents = new ArrayList<>();
		dependents.add(new Dependent(1,1,"Makar","Tchekalenkov", "07/02/1997"));
		dependents.add(new Dependent());
		
		when(dependentService.findAllDependents()).thenReturn(dependents);
		
		mockMvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(dependents.size()))
		.andExpect(jsonPath("$[0].id").value(dependents.get(0).getId()))
		.andExpect(jsonPath("$[0].firstName").value(dependents.get(0).getFirstName()))
		.andExpect(jsonPath("$[0].lastName").value(dependents.get(0).getLastName()))
		.andExpect(jsonPath("$[0].birthDate").value(dependents.get(0).getBirthDate()))
		.andReturn();

		verify(dependentService, times(1)).findAllDependents();
		System.out.println("====================================================================================");
	}
	
	
	@Test
	void testFindDependentById() throws Exception{
		String mapping= "/findById/1"; 
		String uri = baseUri + mapping;
		
		Dependent dependent = new Dependent(1,1,"Makar","Tchekalenkov", "07/02/1997");
		
		when(dependentService.findDependentById(1)).thenReturn(dependent);
		
		mockMvc.perform(get(uri))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(dependent.getId()))
		.andExpect(jsonPath("$.firstName").value(dependent.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(dependent.getLastName()))
		.andExpect(jsonPath("$.birthDate").value(dependent.getBirthDate()))
		.andReturn();

		verify(dependentService, times(1)).findDependentById(1);
		System.out.println("====================================================================================");
	}
	
	@Test
	void testPostDependent() throws Exception {
		String mapping= "/post"; 
		String uri = baseUri + mapping;
		
		Dependent dependent = new Dependent(1,1,"Makar","Tchekalenkov", "07/02/1997");
		
		when(dependentService.postDependent(any(Dependent.class))).thenReturn(dependent);

		mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(dependent)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(dependent.getId()))
		.andExpect(jsonPath("$.firstName").value(dependent.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(dependent.getLastName()))
		.andExpect(jsonPath("$.birthDate").value(dependent.getBirthDate()))
		.andReturn();

		verify(dependentService, times(1)).postDependent(any(Dependent.class));
		System.out.println("====================================================================================");
	}
	
	@Test
	void testUpdateDependent() throws Exception {
		String mapping= "/update/1"; 
		String uri = baseUri + mapping;
		
		Dependent dependent = new Dependent(1,1,"Makar","Tchekalenkov", "07/02/1997");
		
		when(dependentService.updateDependent(any(Dependent.class),any(Integer.class))).thenReturn(dependent);

		mockMvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON).content(asJsonString(dependent)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(dependent.getId()))
		.andExpect(jsonPath("$.firstName").value(dependent.getFirstName()))
		.andExpect(jsonPath("$.lastName").value(dependent.getLastName()))
		.andExpect(jsonPath("$.birthDate").value(dependent.getBirthDate()))
		.andReturn();

		verify(dependentService, times(1)).updateDependent(any(Dependent.class),any(Integer.class));
		System.out.println("====================================================================================");
	}
	
	
		
	public static String asJsonString(Dependent dependent) {
		try {
			return new ObjectMapper().writeValueAsString(dependent);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}
