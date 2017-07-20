package org.projectormanagementsystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectorManagementController {

	
	private static final Logger logger = LoggerFactory.getLogger(ProjectorManagementController.class);
	
	@GetMapping("/")
	public String home(){
		logger.info("-- User Requesting Home Page --");
		return "home";
	}
	
	
	@GetMapping("/projector-reserve")
	public String projectorRequestorPage(){
		logger.info("-- User requesting Projector Request Page --");
		return "projectorReserve";
	}
	
	
	@GetMapping("/home")
	public String goTohome(){
		logger.info("-- User Requesting Home Page --");
		return "home";
	}
	
	
}
