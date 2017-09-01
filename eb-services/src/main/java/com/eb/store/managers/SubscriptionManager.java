package com.eb.store.managers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eb.integration.appdirect.models.NoticeType;
import com.eb.integration.appdirect.models.SubscriptionEventData;
import com.eb.store.models.Subscription;
import com.eb.store.models.User;
import com.eb.store.repositories.SubscriptionRepository;
import com.eb.store.repositories.UserRepository;

@Service
public class SubscriptionManager {
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	public SubscriptionManager(SubscriptionRepository subscriptionRepository) {
		super();
		this.subscriptionRepository = subscriptionRepository;

	}

	@Transactional
	public Subscription CreateSubscription(Subscription subscription) throws SubscriptionAlreadyExistException {
		User user = userRepository.findByEmail(subscription.getOwner().getEmail());
		if (user != null) {
			throw (new SubscriptionAlreadyExistException());
		}

		return subscriptionRepository.save(subscription);
	}

	@Transactional
	public void delete(String accountIdentifier) {
		User user = userRepository.findByAccountIdentifier(accountIdentifier);
		if (user != null)
			subscriptionRepository.delete(user.getSubscription());
	}

	@Transactional
	public void addUser(String accountIdentifier, User user) {
		User usersub = userRepository.findByAccountIdentifier(accountIdentifier);
		if (usersub != null) {
			usersub.getSubscription().getUsers().add(user);
			user.setSubscription(usersub.getSubscription());
			userRepository.save(user);
		}
	}

	@Transactional
	public void removeUser(String accountIdentifier, String email) {
		User usersub = userRepository.findByEmail(email);
		usersub.setSubscription(null);
		userRepository.delete(usersub);
	}

	@Transactional
	public void UpdateStatus(String accountIdentifier, NoticeType type) {
		User user = userRepository.findByAccountIdentifier(accountIdentifier);
		switch (type) {
		case REACTIVATED:
			user.setActive(true);
			userRepository.save(user);
			break;
		case DEACTIVATED:
			user.setActive(false);
			userRepository.save(user);
			break;
		case CLOSED:
			subscriptionRepository.delete(user.getSubscription());
			break;
		}
	}

	public void change(SubscriptionEventData subscription) {
		User user = userRepository
				.findByAccountIdentifier(subscription.getPayload().getAccount().getAccountIdentifier());
		if (subscription.getPayload().getOrder().getItems().size() > 0)
			user.getSubscription().setQuantity(subscription.getPayload().getOrder().getItems().get(0).getQuantity());
		subscriptionRepository.save(user.getSubscription());
	}
}
