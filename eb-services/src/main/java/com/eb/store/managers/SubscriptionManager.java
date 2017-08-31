package com.eb.store.managers;

import com.eb.store.models.Subscription;
import com.eb.store.models.User;
import com.eb.store.repositories.SubscriptionRepository;

public class SubscriptionManager {
	private SubscriptionRepository subscriptionRepository;

	public SubscriptionManager(SubscriptionRepository subscriptionRepository) {
		super();
		this.subscriptionRepository = subscriptionRepository;
	
	}
	
	public void CreateSubscription(Subscription subscription, User owner)
	{
	
	}
}
