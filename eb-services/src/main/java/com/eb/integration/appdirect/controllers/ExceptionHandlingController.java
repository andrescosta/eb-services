package com.eb.integration.appdirect.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.eb.integration.appdirect.managers.SubscriptionAlreadyExistException;
import com.eb.integration.appdirect.models.ErrorCodes;
import com.eb.integration.appdirect.models.NotificationErrorResponse;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(SubscriptionAlreadyExistException.class)
	public ResponseEntity<NotificationErrorResponse> subscriptionAlreadyExist(SubscriptionAlreadyExistException ex) {
		NotificationErrorResponse response = new NotificationErrorResponse(ErrorCodes.USER_ALREADY_EXISTS, ex);
		return new ResponseEntity<NotificationErrorResponse>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<NotificationErrorResponse> handleAll(Exception ex, WebRequest request) {
		ex.printStackTrace();
		return new ResponseEntity<NotificationErrorResponse>(NotificationErrorResponse.GENERIC_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
