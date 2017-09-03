package com.eb.store.managers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eb.integration.appdirect.models.NoticeType;
import com.eb.integration.appdirect.models.EventData;
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
	public Subscription create(Subscription subscription) throws SubscriptionAlreadyExistException {
		User user = userRepository.findByEmail(subscription.getOwner().getEmail());
		if (user != null) {
			throw (new SubscriptionAlreadyExistException());
		}

		return subscriptionRepository.save(subscription);
	}

	@Transactional
	public void delete(String identifier) {
		Long l = subscriptionRepository.deleteByIdentifier(identifier);
	}

	@Transactional
	public Subscription addUser(String accountIdentifier, User newuser) {
		Subscription sub = subscriptionRepository.findByIdentifier(accountIdentifier);
		sub.getUsers().add(newuser);	
		newuser.setSubscription(sub);
		subscriptionRepository.save(sub);
		return sub;
	}

	@Transactional
	public User removeUser(String email) {
		User user = userRepository.findByEmail(email);
		user.setSubscription(null);
		userRepository.delete(user);
		return user;
	}

	@Transactional
	public Subscription UpdateStatus(String accountIdentifier, NoticeType type) {
		Subscription subscription = subscriptionRepository.findByIdentifier(accountIdentifier);
		switch (type) {
		case REACTIVATED:
			reactivateUser(user);
			break;
		case DEACTIVATED:
			deactivateUser(user);
			break;
		case CLOSED:
			deleteUser(user);
			break;
		default:
			break;
		}
		return user.getSubscription();
	}

	private void deleteUser(User user) {
		subscriptionRepository.delete(user.getSubscription());
	}

	private void deactivateUser(User user) {
		user.setActive(false);
		userRepository.save(user);
	}

	private void reactivateUser(User user) {
		user.setActive(true);
		userRepository.save(user);
	}

	public Subscription change(EventData subscription) {
		User user = userRepository
				.findByAccountIdentifier(subscription.getPayload().getAccount().getAccountIdentifier());
		if (subscription.getPayload().getOrder().getItems().size() > 0)
			user.getSubscription().setQuantity(subscription.getPayload().getOrder().getItems().get(0).getQuantity());
		subscriptionRepository.save(user.getSubscription());
		return user.getSubscription();
	}
}
