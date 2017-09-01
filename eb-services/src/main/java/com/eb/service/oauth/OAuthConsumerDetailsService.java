package com.eb.service.oauth;

import java.util.logging.Logger;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.stereotype.Component;

@Component
public class OAuthConsumerDetailsService implements ConsumerDetailsService {
    //final static Logger log = LoggerFactory.getLogger(OAuthConsumerDetailsService.class);

	@Value("${oauth.consumer.key}")
	private String consumerKey;

	@Value("${oauth.consumer.secret}")
	private String consumerSecret;

	
    @Override
    public ConsumerDetails loadConsumerByConsumerKey(String consumerKey) throws OAuthException {
        BaseConsumerDetails cd;
        // NOTE: really lookup the key and secret, for the sample here we just hardcoded
        if (consumerKey.equals(consumerKey)) {
            // allow this oauth request
            cd = new BaseConsumerDetails();
            cd.setConsumerKey(consumerKey);
            cd.setSignatureSecret(new SharedConsumerSecretImpl(consumerSecret));
            cd.setConsumerName("Sample");
            cd.setRequiredToObtainAuthenticatedToken(false); // no token required (0-legged)
            cd.getAuthorities().add(new SimpleGrantedAuthority("ROLE_OAUTH")); // add the ROLE_OAUTH (can add others as well)
            //log.info("OAuth check SUCCESS, consumer key: " + consumerKey);
        } else {
            // deny - failed to match
            throw new OAuthException("For this example, key must be 'key'");
        }
        return cd;
    }

}