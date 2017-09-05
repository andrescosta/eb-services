package com.eb.integration.appdirect.controllers;

import java.util.UUID;
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
public class SubscriptionNotificationController extends EventController {

	@Autowired
	private AppDirectIntegrationService subscriptionManager;
	
	protected Supplier<ResponseEntity<NotificationResponse>> fakeResponseSupplier = () -> {
		return new ResponseEntity<NotificationResponse>(new NotificationSuccessResponse(UUID.randomUUID().toString()),
				HttpStatus.OK);
	};


	@RequestMapping("api/v1/subscription/create/notitication")
	public HttpEntity<NotificationResponse> create(@RequestParam(required = false) String eventurl)
			throws NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			String id = subscriptionManager.create(p.getCreator().getEmail(), p.AsNewSubscription()).getIdentifier();
			return OK_RESPONSE(id);
		}, fakeResponseSupplier);
	}

	@RequestMapping("api/v1/subscription/change/notification")
	public HttpEntity<NotificationResponse> change(@RequestParam(required = false) String eventurl)
			throws RestClientException, NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			String id = subscriptionManager.change(p).getIdentifier();
			return OK_RESPONSE(id);
		}, fakeResponseSupplier);
	}

	@RequestMapping("api/v1/subscription/cancel/notification")
	public HttpEntity<NotificationResponse> cancel(@RequestParam(required = false) String eventurl)
			throws RestClientException, NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			String id = p.getPayload().getAccount().getAccountIdentifier();
					subscriptionManager.delete(id);
			return OK_RESPONSE(id);
		}, fakeResponseSupplier);
	}

	@RequestMapping("api/v1/subscription/status/notification")
	public HttpEntity<NotificationResponse> status(@RequestParam(required = false) String eventurl)
			throws RestClientException, NoSuchMethodException {
		EventData data = getEventData(eventurl);
		return apply(data, (p) -> {
			String id = subscriptionManager.UpdateStatus(p.getPayload().getAccount().getAccountIdentifier(),
					data.getPayload().getNotice().getType()).getIdentifier();
			return OK_RESPONSE(id);
		}, fakeResponseSupplier);
	}

}
