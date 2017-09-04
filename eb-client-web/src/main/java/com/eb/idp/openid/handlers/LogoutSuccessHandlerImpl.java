package com.eb.idp.openid.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		if (authentication != null && authentication.getPrincipal() != null
				&& authentication.getPrincipal() instanceof User) {
			final String openid = ((User) authentication.getPrincipal()).getUsername();
			try {
				URI uri = new URI(openid);
				response.sendRedirect(
						String.format("%s://%s/applogout?openid=%s", uri.getScheme(), uri.getHost(), openid));
			} catch (URISyntaxException e) {
			}
		}
	}
}