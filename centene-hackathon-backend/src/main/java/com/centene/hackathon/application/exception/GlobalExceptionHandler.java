package com.centene.hackathon.application.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	// handling specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(APIException.class)
	public ResponseEntity<?> APIExceptionHandling(APIException exception, WebRequest request){
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	
	// handling global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
		System.out.println(exception.toString());
		System.out.println(exception.getMessage());
		ErrorDetails errorDetails = 
				new ErrorDetails(new Date(), exception.toString(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
}
