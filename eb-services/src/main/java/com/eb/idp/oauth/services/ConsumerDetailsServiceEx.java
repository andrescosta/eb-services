package com.eb.idp.oauth.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDetailsServiceEx implements ConsumerDetailsService {
    final static Log log = LogFactory.getLog(ConsumerDetailsServiceEx.class);

	@Value("${oauth.consumer.key}")
	private String consumerKey;

	@Value("${oauth.consumer.secret}")
	private String consumerSecret;

	
    @Override
    public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
        BaseConsumerDetails cd;
        if (consumerKey.equals(consumerKey)) {
            cd = new BaseConsumerDetails();
            cd.setConsumerKey(consumerKey);
            cd.setSignatureSecret(new SharedConsumerSecretImpl(consumerSecret));
            cd.setConsumerName("Dev");
            cd.setRequiredToObtainAuthenticatedToken(false); 
            cd.getAuthorities().add(new SimpleGrantedAuthority("ROLE_OAUTH"));
        } else {
            throw new IllegalConsumerKeyException();
        }
        return cd;
    }

}