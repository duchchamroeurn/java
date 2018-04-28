package com.sangkhim.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorController {
	
	@RequestMapping(value = "/error404", method = RequestMethod.GET)
	public String error404(Model model) {			
		return "pages/404";
	}
	
	@RequestMapping(value = "/error500", method = RequestMethod.GET)
	public String error500(Model model) {			
		return "pages/500";
	}

}