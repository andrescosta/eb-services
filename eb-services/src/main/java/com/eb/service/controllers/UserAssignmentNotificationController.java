package com.eb.service.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eb.service.models.NotificationResponse;
import com.eb.service.models.SubscriptionEventData;

@RestController
public class UserAssignmentNotificationController {

	@RequestMapping("api/v1/user/assignment/notitication")
	public NotificationResponse create(@RequestParam(required=false) String eventurl) {
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
			
		
		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		System.out.println(data);
		System.out.println(eventurl);
		return new NotificationResponse();
	}

	@RequestMapping("api/v1/user/unassignment/notification")
	public NotificationResponse change(@RequestParam(required=false) String eventurl) {
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
			
		
		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		System.out.println(data);
		
		return new NotificationResponse();
	}
}
