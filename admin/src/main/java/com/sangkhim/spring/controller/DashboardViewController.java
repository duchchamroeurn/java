package com.sangkhim.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardViewController {

	private final Logger logger = LoggerFactory.getLogger(DashboardViewController.class);

	@RequestMapping(value = {"/admin/", "/admin/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index() is executed!");
		
		model.addAttribute("pageName", "index");
		return "layouts/admin";
	}

}