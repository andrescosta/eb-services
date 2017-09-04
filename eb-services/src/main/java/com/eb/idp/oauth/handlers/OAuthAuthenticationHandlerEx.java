package com.eb.idp.oauth.handlers;

import java.security.Principal;
import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.provider.ConsumerAuthentication;
import org.springframework.security.oauth.provider.ConsumerCredentials;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.token.OAuthAccessProviderToken;
import org.springframework.stereotype.Component;

@Component
public class OAuthAuthenticationHandlerEx implements OAuthAuthenticationHandler {    
    
	final static Log log = LogFactory.getLog(OAuthAuthenticationHandlerEx.class);

    static SimpleGrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");

    @Override
    public Authentication createAuthentication(HttpServletRequest request, ConsumerAuthentication authentication, OAuthAccessProviderToken authToken) {
        Collection<GrantedAuthority> authorities = new HashSet<>(authentication.getAuthorities());
        String username = authentication.getName();
        authorities.add(ROLE_USER);
        Principal principal = new OAuthPrincipal(username, authorities,
                authentication.getConsumerCredentials().getConsumerKey(),
                authentication.getConsumerCredentials().getSignature(),
                authentication.getConsumerCredentials().getSignatureMethod(),
                authentication.getConsumerCredentials().getSignatureBaseString(),
                authentication.getConsumerCredentials().getToken()
        );
        Authentication auth = new UsernamePasswordAuthenticationToken(principal, null, authorities);
        return auth;
    }

    public static class OAuthPrincipal extends ConsumerCredentials implements Principal {
		private static final long serialVersionUID = 1L;
		public String name;
        public Collection<GrantedAuthority> authorities;

        public OAuthPrincipal(String name, Collection<GrantedAuthority> authorities, String consumerKey, String signature, String signatureMethod, String signatureBaseString, String token) {
            super(consumerKey, signature, signatureMethod, signatureBaseString, token);
            this.name = name;
            this.authorities = authorities;
        }

        @Override
        public String getName() {
            return name;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }
    }
}