package com.eb.idp.oauth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppDirectConsumerDetailsService extends ConsumerDetailsServiceEx {
   
	@Value("${oauth.consumer.key}")
	private String consumerKey;

	@Value("${oauth.consumer.secret}")
	private String consumerSecret;

	
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
		
		return "int";
	}

}