package com.eb.idp.oauth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Component;

@Component
public class ApiConsumerDetailsService extends ConsumerDetailsServiceEx implements ConsumerDetailsService {
    

	@Value("${api.consumer.key}")
	protected String consumerKey;

	@Value("${api.consumer.secret}") 
	protected String consumerSecret;

	@Override
	protected String getConsumerKey() {
		
		return consumerKey;
	}

	@Override
	protected String getConsumerSecret() {
		return consumerSecret;
	}

	@Override
	protected String getConsumerName() {
		
		return "dev";
	}

}