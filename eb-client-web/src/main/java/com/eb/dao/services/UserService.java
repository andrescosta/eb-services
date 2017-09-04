package com.eb.dao.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eb.contracts.EBResponse;

@Service
public class UserService {
	
	@Value("${api.consumer.key}")
	private String consumerKey;

	@Value("${api.consumer.secret}")
	private String consumerSecret;

	@Value("${eb.service.url}")
	private String url;

	
	public EBResponse getUserByOpenId(String openId)
	{
		BaseProtectedResourceDetails resourceDetails = new BaseProtectedResourceDetails();
		resourceDetails.setConsumerKey(consumerKey);
		resourceDetails.setSharedSecret(new SharedConsumerSecretImpl(consumerSecret));
		RestTemplate template = new OAuthRestTemplate(resourceDetails);
		String callByOpendId = url+"?openId=" +openId;
		System.out.println("URLLL:" + callByOpendId);
		return template.getForObject(callByOpendId, EBResponse.class);
	}
	
}
