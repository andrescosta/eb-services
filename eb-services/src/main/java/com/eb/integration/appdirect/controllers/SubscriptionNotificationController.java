package com.eb.integration.appdirect.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eb.integration.appdirect.models.NotificationErrorResponse;
import com.eb.integration.appdirect.models.NotificationResponse;
import com.eb.integration.appdirect.models.SubscriptionEventData;
import com.eb.store.managers.SubscriptionAlreadyExistException;
import com.eb.store.managers.SubscriptionManager;
import com.eb.store.models.Subscription;
import com.eb.store.models.User;
import com.eb.store.repositories.SubscriptionRepository;

@RestController
// @EnableJpaRepositories(basePackages = {"com.eb.store.repositories"})
public class SubscriptionNotificationController {

	@Autowired
	private SubscriptionManager subscriptionManager;

	@RequestMapping("api/v1/subscription/create/notitication")
	public NotificationResponse create(@RequestParam(required = false) String eventurl) {
		try {
			RestTemplate template;
			template = getTemplate(eventurl);
			SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
			String id;
			id = subscriptionManager.CreateSubscription(data.AsSubscription()).getOwner().getAccountIdentifier();
			NotificationResponse response = new NotificationResponse();
			response.setAccountIdentifier(id);
			return response;
		} catch (SubscriptionAlreadyExistException e) {
			return new NotificationErrorResponse("E-0001", "Subscription already exist");
		}
	}

	@RequestMapping("api/v1/subscription/change/notification")
	public NotificationResponse change(@RequestParam(required = false) String eventurl) {
		RestTemplate template;
		template = getTemplate(eventurl);

		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		subscriptionManager.change(data);
		return new NotificationResponse();
	}

	@RequestMapping("api/v1/subscription/cancel/notification")
	public NotificationResponse cancel(@RequestParam(required = false) String eventurl) {
		RestTemplate template;
		template = getTemplate(eventurl);

		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		subscriptionManager.delete(data.getPayload().getAccount().getAccountIdentifier());

		return new NotificationResponse();
	}

	@RequestMapping("api/v1/subscription/status/notification")
	public NotificationResponse status(@RequestParam(required = false) String eventurl) {
		RestTemplate template;
		template = getTemplate(eventurl);

		SubscriptionEventData data = template.getForObject(eventurl, SubscriptionEventData.class);
		subscriptionManager.UpdateStatus(data.getPayload().getAccount().getAccountIdentifier(), data.getPayload().getNotice().getType());
		return new NotificationResponse();
	}

	private RestTemplate getTemplate(String eventurl) {
		RestTemplate template;
		if (!eventurl.contains("dummyOrder")) {
			BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
			resourceDetails.setConsumerKey("sports-predictions-as-a-service-177618");
			resourceDetails.setSharedSecret(new SharedConsumerSecretImpl("mktBg7xVpnika2DY"));
			template = new OAuthRestTemplate(resourceDetails);

		} else {
			template = new RestTemplate();
		}
		return template;
	}
}
