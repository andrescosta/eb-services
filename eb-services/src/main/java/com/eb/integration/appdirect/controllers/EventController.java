package com.eb.integration.appdirect.controllers;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.eb.integration.appdirect.models.EventData;
import com.eb.integration.appdirect.models.NotificationResponse;
import com.eb.integration.appdirect.models.NotificationSuccessResponse;

public class EventController {
	@Value("${oauth.consumer.key}")
	private String consumerKey;

	@Value("${oauth.consumer.secret}")
	private String consumerSecret;

	@Value("${com.eb.integration.appdirect.persist.stateless.event}")
	private boolean persistStateLessEvent;

	EventController() {

	}

	protected RestTemplate getTemplate(String eventurl) {
		return isDummyOrder(eventurl) ? new RestTemplate() : getOAuthRestTemplate();
	}

	private RestTemplate getOAuthRestTemplate() {
		BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setConsumerKey(consumerKey);
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(consumerSecret));
		return new OAuthRestTemplate(resourceDetails);
	}

	protected EventData getEventData(String eventurl) {
		RestTemplate template = getTemplate(eventurl);
		return template.getForObject(eventurl, EventData.class);
	}

	protected boolean isDummyOrder(String eventurl) {
		return eventurl.contains("dummyOrder");
	}

	protected HttpEntity<NotificationResponse> apply(EventData eventData,
			Function<EventData, HttpEntity<NotificationResponse>> func,
			Supplier<ResponseEntity<NotificationResponse>> fakeResponseSupplier) {
		if (isStatelessEvent(eventData) && !persistStateLessEvent) {
			return fakeResponseSupplier.get();
		}
		return func.apply(eventData);
	}
	
	protected static ResponseEntity<NotificationResponse> OK_RESPONSE(String id)
	{
		return new ResponseEntity<NotificationResponse>(new NotificationSuccessResponse(id), HttpStatus.OK);
	}
	
	protected static ResponseEntity<NotificationResponse> OK_RESPONSE()
	{
		return new ResponseEntity<NotificationResponse>(new NotificationSuccessResponse(), HttpStatus.OK);
	}

	protected boolean isStatelessEvent(EventData eventData) {
		return eventData.getFlag() != null && eventData.getFlag().equals("STATELESS");
	}

}
