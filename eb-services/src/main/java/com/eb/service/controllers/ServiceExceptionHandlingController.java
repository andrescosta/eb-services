package com.eb.service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.eb.contracts.EBError;
import com.eb.contracts.EBResponse;

@ControllerAdvice(annotations=Controller.class)
public class ServiceExceptionHandlingController {

	private static EBResponse GENERIC_ERROR = new EBResponse(new EBError("EB-99999","Ups something happend."));
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<EBResponse> handleAll(Exception ex, WebRequest request) {

		return new ResponseEntity<EBResponse>(GENERIC_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
