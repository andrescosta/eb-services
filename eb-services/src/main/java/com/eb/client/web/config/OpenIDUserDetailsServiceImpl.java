package com.eb.client.web.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;

import com.eb.store.models.User;
import com.eb.store.repositories.UserRepository;

@Component
public class OpenIDUserDetailsServiceImpl implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String openId) {
        
		return new org.springframework.security.core.userdetails.User(openId, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
	
	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		User user = userRepository.findByOpenId((String)token.getPrincipal());
		
		
		return loadUserByUsername(user.getOpenId());
	}
}
