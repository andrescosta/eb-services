package com.eb.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eb.contracts.EBResponse;
import com.eb.contracts.EBUser;
import com.eb.contracts.EBUserState;
import com.eb.store.models.User;
import com.eb.store.repositories.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/user")
	public HttpEntity<EBResponse> getByOpenId(@RequestParam(required = false) String openId)
	{
		User user = userRepository.findByOpenId(openId);
		return new HttpEntity<EBResponse>(getEBResponse(user));
	}
	
	public EBResponse getEBResponse(User user)
	{
		EBUser ebUser = new EBUser();
		EBResponse response = new EBResponse(ebUser);
		ebUser.setFirstName(user.getFirstName());
		ebUser.setLastName(user.getLastName());
		if (user.getSubscription().isActive())
			ebUser.setState(EBUserState.ACTIVE);
		else
			ebUser.setState(EBUserState.INACTIVE);
		ebUser.setOpenId(user.getOpenId());
		response.setEbUser(ebUser);
		return response;
	}
}
