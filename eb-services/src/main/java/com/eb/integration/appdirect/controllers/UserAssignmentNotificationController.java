package com.eb.integration.appdirect.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eb.integration.appdirect.models.NotificationResponse;
import com.eb.integration.appdirect.models.SubscriptionEventData;
import com.eb.store.managers.SubscriptionManager;

@RestController
public class UserAssignmentNotificationController {

	@Autowired
	private SubscriptionManager subscriptionManager;
	
	@RequestMapping("api/v1/user/assignment/notitication")
	public NotificationResponse create(@RequestParam(required=false) String eventurl) {
		RestTemplate template;
		template = getTemplate(eventurl);
		
		
		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		subscriptionManager.addUser(data.getPayload().getAccount().getAccountIdentifier(), data.getPayload().getUser().asUser());
		return new NotificationResponse();
	}

	private RestTemplate getTemplate(String eventurl) {
		RestTemplate template;
		if (!eventurl.contains("dummyOrder"))
		{
			BaseProtectedResourceDetails resourceDetails=new BaseProtectedResourceDetails();
	        resourceDetails.setConsumerKey("sports-predictions-as-a-service-177618");
	        resourceDetails.setSharedSecret(new SharedConsumerSecretImpl("mktBg7xVpnika2DY"));
			template = new OAuthRestTemplate(resourceDetails);
		
		}
		else {
			template = new RestTemplate();
		}
		return template;
	}

	@RequestMapping("api/v1/user/unassignment/notification")
	public NotificationResponse change(@RequestParam(required=false) String eventurl) {
		RestTemplate template;
		template = getTemplate(eventurl);
			
		
		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		subscriptionManager.removeUser(data.getPayload().getAccount().getAccountIdentifier(), data.getPayload().getUser().getEmail());
		
		return new NotificationResponse();
	}
}
