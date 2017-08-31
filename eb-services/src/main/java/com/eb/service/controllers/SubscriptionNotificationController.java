package com.eb.service.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eb.service.models.NotificationResponse;
import com.eb.service.models.SubscriptionEventData;
import com.eb.store.models.Subscription;
import com.eb.store.models.User;
import com.eb.store.repositories.SubscriptionRepository;


@RestController
//@EnableJpaRepositories(basePackages = {"com.eb.store.repositories"})
public class SubscriptionNotificationController {
	
	private SubscriptionRepository subscriptionRepository;
	


	public SubscriptionNotificationController(SubscriptionRepository subscriptionRepository) {
		super();
		this.subscriptionRepository = subscriptionRepository;
	}

	@RequestMapping("api/v1/subscription/create/notitication")
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
		subscriptionRepository.save(data.AsSubscription());
		return new NotificationResponse();
	}

	@RequestMapping("api/v1/subscription/change/notification")
	public NotificationResponse change(@RequestParam(required=false) String eventurl ) {
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

	@RequestMapping("api/v1/subscription/cancel/notification")
	public NotificationResponse cancel(@RequestParam(required=false) String eventurl ) {
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

	@RequestMapping("api/v1/subscription/status/notification")
	public NotificationResponse status(@RequestParam(required=false) String eventurl ) {
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
