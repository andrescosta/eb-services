package com.eb.service.mock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eb.service.models.NotificationResponse;

//@RestController
public class OrderController {
	
	//@RequestMapping("api/v1/subscription/create/notitication")
	public NotificationResponse create(@RequestParam() String url) {
		return null;
	}
}
