package com.eb.client.web.config;

import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.openid.OpenIDAuthenticationFilter;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.openid.OpenIDConsumer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationUserDetailsService<OpenIDAuthenticationToken> openIdUserDetailsService;
	
	@Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

	
	private OpenIDConsumer openIdConsumer;
	
	@Value("${oauth.consumer.key}")
    private String consumerKey;

    @Value("${oauth.consumer.secret}")
    private String consumerSecret;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
		 http.logout()
         .logoutUrl("/logout")
         .logoutSuccessHandler(logoutSuccessHandler);

 // deactivate Cross-Site Request Forgery
 http.csrf().disable();

 http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();

 http.openidLogin()
         .authenticationUserDetailsService(openIdUserDetailsService)
         .loginProcessingUrl("/login/openid")
         .permitAll()
         .defaultSuccessUrl("/");


		
		
        //http.addFilterBefore(oAuthProviderProcessingFilter(), OpenIDAuthenticationFilter.class);
    }
//	@Bean
//    public OAuthProviderProcessingFilter oAuthProviderProcessingFilter() {
//
//        final ProtectedResourceProcessingFilter filter = new ProtectedResourceProcessingFilter() {
//
//            @Override
//            protected boolean requiresAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) {
//
//                if (new AntPathRequestMatcher("/api/notification/**").matches(request)) {
//                    OAuthProcessingFilterEntryPoint authenticationEntryPoint = new OAuthProcessingFilterEntryPoint();
//                    setAuthenticationEntryPoint(authenticationEntryPoint);
//                    String realmName = request.getRequestURL().toString();
//                    authenticationEntryPoint.setRealmName(realmName);
//                    return true;
//                }
//                return false;
//            }
//        };
//        filter.setConsumerDetailsService(consumerDetailsService());
//        filter.setTokenServices(inMemoryProviderTokenServices());
//
//        return filter;
//    }
//	
//	@Bean
//    public ConsumerDetailsService consumerDetailsService() {
//        final BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
//        consumerDetails.setConsumerKey(consumerKey);
//        consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(consumerSecret));
//        consumerDetails.setRequiredToObtainAuthenticatedToken(false);
//
//        final InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();
//        consumerDetailsService.setConsumerDetailsStore(new HashMap<String, ConsumerDetails>() {{
//            put(consumerKey, consumerDetails);
//        }});
//        return consumerDetailsService;
//    }
//
//    @Bean
//    public InMemoryProviderTokenServices inMemoryProviderTokenServices() {
//        return new InMemoryProviderTokenServices();
//    }
//
//    @Bean
//    public ProtectedResourceDetails protectedResourceDetails() {
//        final BaseProtectedResourceDetails resource = new BaseProtectedResourceDetails();
//        resource.setConsumerKey(consumerKey);
//        resource.setSharedSecret(new SharedConsumerSecretImpl(consumerSecret));
//        return resource;
//    }

}