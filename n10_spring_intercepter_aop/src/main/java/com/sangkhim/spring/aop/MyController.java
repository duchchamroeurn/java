package com.sangkhim.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MyController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}	
	
}