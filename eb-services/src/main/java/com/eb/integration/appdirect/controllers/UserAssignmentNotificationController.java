package com.eb.integration.appdirect.controllers;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.eb.integration.appdirect.models.NotificationResponse;
import com.eb.integration.appdirect.models.NotificationSuccessResponse;
import com.eb.integration.appdirect.managers.AppDirectIntegrationService;
import com.eb.integration.appdirect.models.EventData;

@RestController
public class UserAssignmentNotificationController extends EventController {

	@Autowired
	private AppDirectIntegrationService subscriptionManager;

	protected static Supplier<ResponseEntity<NotificationResponse>> fakeResponseSupplier = () -> {
		return new ResponseEntity<NotificationResponse>(new NotificationSuccessResponse(), HttpStatus.OK);
	};

	@RequestMapping("api/v1/user/assignment/notitication")
	public HttpEntity<NotificationResponse> create(@RequestParam(required = false) String eventurl)
			throws RestClientException, NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			subscriptionManager.addUser(p.getPayload().getAccount().getAccountIdentifier(),
					data.getPayload().getAppdirectUser().asUser());
			return OK_RESPONSE();
		}, fakeResponseSupplier);
	}

	@RequestMapping("api/v1/user/unassignment/notification")
	public HttpEntity<NotificationResponse> change(@RequestParam(required = false) String eventurl)
			throws RestClientException, NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			subscriptionManager.removeUser(
					data.getPayload().getAppdirectUser().getEmail());
			return OK_RESPONSE();
		}, fakeResponseSupplier);
	}
	
	@RequestMapping("api/v1/user/update/notification")
	public HttpEntity<NotificationResponse> update(@RequestParam(required = false) String eventurl)
			throws RestClientException, NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			subscriptionManager.updateUser(
					data.getPayload().getAppdirectUser().asUser());
			return OK_RESPONSE();
		}, fakeResponseSupplier);
	}
}
