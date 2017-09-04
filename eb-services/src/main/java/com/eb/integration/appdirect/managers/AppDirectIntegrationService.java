package com.eb.integration.appdirect.managers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eb.integration.appdirect.models.EventData;
import com.eb.integration.appdirect.models.NoticeType;
import com.eb.store.models.Subscription;
import com.eb.store.models.User;
import com.eb.store.repositories.SubscriptionRepository;
import com.eb.store.repositories.UserRepository;

@Service
public class AppDirectIntegrationService {
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserRepository userRepository;

	public AppDirectIntegrationService(SubscriptionRepository subscriptionRepository) {
		super();
		this.subscriptionRepository = subscriptionRepository;

	}

	@Transactional
	public Subscription create(Subscription subscription) throws SubscriptionAlreadyExistException {
		//User user = userRepository.findByEmail(subscription.getOwner().getEmail());
		//if (user != null) {
		//	throw (new SubscriptionAlreadyExistException());
		//}

		return subscriptionRepository.save(subscription);
	}

	@Transactional
	public void delete(String identifier) {
		subscriptionRepository.deleteByIdentifier(identifier);
	}
	
	@Transactional
	public void delete(Subscription sub) {
		subscriptionRepository.delete(sub);
	}

	@Transactional
	public Subscription addUser(String identifier, User newuser) {
		Subscription sub = subscriptionRepository.findByIdentifier(identifier);
		sub.getUsers().add(newuser);	
		newuser.setSubscription(sub);
		subscriptionRepository.save(sub);
		return sub;
	}

	@Transactional
	public User removeUser(String id) {
		User user = userRepository.findByMarketPlaceId(id);
		user.setSubscription(null);
		userRepository.delete(user);
		return user;
	}

	@Transactional
	public Subscription UpdateStatus(String accountIdentifier, NoticeType type) {
		Subscription subscription = subscriptionRepository.findByIdentifier(accountIdentifier);
		switch (type) {
		case REACTIVATED:
			reactivate(subscription);
			break;
		case DEACTIVATED:
			deactivate(subscription);
			break;
		case CLOSED:
			delete(subscription);
			break;
		default:
			break;
		}
		return subscription;
	}

	private void deactivate(Subscription sub) {
		sub.setActive(false);
		subscriptionRepository.save(sub);
	}

	private void reactivate(Subscription sub) {
		sub.setActive(true);
		subscriptionRepository.save(sub);
	}

	public Subscription change(EventData event) {
		Subscription subscription = subscriptionRepository
				.findByIdentifier(event.getPayload().getAccount().getAccountIdentifier());
		if (event.getPayload().getOrder().getItems().size() > 0)
			subscription.setQuantity(event.getPayload().getOrder().getItems().get(0).getQuantity());
		subscriptionRepository.save(subscription);
		return subscription;
	}

	public void updateUser(User user) {
		User ouser = userRepository.findByMarketPlaceId(user.getMarketPlaceId());
		ouser.setEmail(user.getEmail());
		ouser.setFirstName(user.getFirstName());
		ouser.setLastName(user.getLastName());
		ouser.setOpenId(user.getOpenId());
		ouser.setUserName(user.getUserName());
		userRepository.save(ouser);
	}
}
