package com.eb.idp.oauth.config;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.nonce.InMemoryNonceServices;
import org.springframework.security.oauth.provider.nonce.OAuthNonceServices;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.oauth.provider.token.OAuthProviderTokenServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.eb.idp.oauth.services.AppDirectConsumerDetailsService;

@Component
public class AppDirectOAuthConfig  {

	@Autowired
    @Order(Ordered.HIGHEST_PRECEDENCE + 20)
    public void addAuthUsers(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("ADMIN", "USER")
                .and().withUser("user").password("user").roles("USER");
    }

    @Configuration
    @Order(2)
    public static class OAuthSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        private ZeroLeggedOAuthProviderProcessingFilter zeroLeggedOAuthProviderProcessingFilter;
        @Autowired
        AppDirectConsumerDetailsService oauthConsumerDetailsService;
        @Autowired
        OAuthAuthenticationHandler oauthAuthenticationHandler;
        @Autowired
        OAuthProcessingFilterEntryPoint oauthProcessingFilterEntryPoint;
        @Autowired
        OAuthProviderTokenServices oauthProviderTokenServices;
        @PostConstruct
        public void init() {
            zeroLeggedOAuthProviderProcessingFilter = new ZeroLeggedOAuthProviderProcessingFilter(oauthConsumerDetailsService, new InMemoryNonceServices(), oauthProcessingFilterEntryPoint, oauthAuthenticationHandler, oauthProviderTokenServices);
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/v1/**")
                    .addFilterBefore(zeroLeggedOAuthProviderProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests().anyRequest().hasRole("OAUTH");
        }
    }

    public static class OAuthProcessingFilterEntryPointImpl extends OAuthProcessingFilterEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            super.commence(request, response, authException);
        }
    }

    @Bean(name = "oauthAuthenticationEntryPoint")
    public OAuthProcessingFilterEntryPoint oauthAuthenticationEntryPoint() {
        return new OAuthProcessingFilterEntryPointImpl();
    }

    @Bean(name = "oauthProviderTokenServices")
    public OAuthProviderTokenServices oauthProviderTokenServices() {
        return new InMemoryProviderTokenServices();
    }

    public static class ZeroLeggedOAuthProviderProcessingFilter extends ProtectedResourceProcessingFilter {
        ZeroLeggedOAuthProviderProcessingFilter(AppDirectConsumerDetailsService oAuthConsumerDetailsService, OAuthNonceServices oAuthNonceServices, OAuthProcessingFilterEntryPoint oAuthProcessingFilterEntryPoint, OAuthAuthenticationHandler oAuthAuthenticationHandler, OAuthProviderTokenServices oAuthProviderTokenServices) {
            super();
            setAuthenticationEntryPoint(oAuthProcessingFilterEntryPoint);
            setAuthHandler(oAuthAuthenticationHandler);
            setConsumerDetailsService(oAuthConsumerDetailsService);
            setNonceServices(oAuthNonceServices);
            setTokenServices(oAuthProviderTokenServices);
        }
    }
	
}
