
package com.eb.web.controllers;

import java.util.Date;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	//@Autowired
	//UserRepository userRepository;
	
	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			model.put("message", "");//userRepository.findByOpenId(userDetails.getUsername()).getFirstName());
		} else {
			model.put("message", "uu");
		}
		return "welcome";
	}

	@RequestMapping("/foo")
	public String foo(Map<String, Object> model) {
		throw new RuntimeException("Foo");
	}

}