package com.eb.idp.openid.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import com.eb.idp.openid.handlers.LogoutSuccessHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class OpenIdConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationUserDetailsService<OpenIDAuthenticationToken> openIdUserDetailsService;

	@Autowired
	private LogoutSuccessHandlerImpl logoutSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
		http.openidLogin().authenticationUserDetailsService(openIdUserDetailsService)
				.loginProcessingUrl("/login/openid").permitAll().defaultSuccessUrl("/");
	}

}