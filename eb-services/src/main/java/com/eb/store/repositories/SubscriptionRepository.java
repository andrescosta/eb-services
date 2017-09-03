package com.eb.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eb.store.models.Subscription;
import com.eb.store.models.User;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long>{
	Subscription findByIdentifier(@Param("identifier") String identifier);
	Long deleteByIdentifier(@Param("identifier") String identifier);
	

}
