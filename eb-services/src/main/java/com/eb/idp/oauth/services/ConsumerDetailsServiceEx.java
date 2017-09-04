package com.eb.idp.oauth.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;

public abstract class ConsumerDetailsServiceEx implements ConsumerDetailsService {

	final static Log log = LogFactory.getLog(ApiConsumerDetailsService.class);

	public ConsumerDetailsServiceEx() {
		super();
	}

	@Override
	public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
	    BaseConsumerDetails cd;
	    if (consumerKey.equals(getConsumerKey())) {
	        cd = new BaseConsumerDetails();
	        cd.setConsumerKey(consumerKey);
	        cd.setSignatureSecret(new SharedConsumerSecretImpl(getConsumerSecret()));
	        cd.setConsumerName(getConsumerName());
	        cd.setRequiredToObtainAuthenticatedToken(false); 
	        cd.getAuthorities().add(new SimpleGrantedAuthority("ROLE_OAUTH"));
	    } else {
	        throw new IllegalConsumerKeyException();
	    }
	    return cd;
	}
	
	protected abstract String getConsumerKey();

	protected abstract String getConsumerSecret();
	
	protected abstract String getConsumerName();
	

}