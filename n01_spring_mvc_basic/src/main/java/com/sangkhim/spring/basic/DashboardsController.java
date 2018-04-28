package com.sangkhim.spring.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardsController {
	
	@RequestMapping(value = {"/", "/index", "/index.html"}, method = RequestMethod.GET)
	public String index(Model model) {
		
		model.addAttribute("pageName", "index");
		
		return "default"; 
	}
	
	@RequestMapping(value = {"/dashboard_2.html"}, method = RequestMethod.GET)
	public String dashboard_2(Model model) {
		
		model.addAttribute("pageName", "dashboard_2");
		
		return "default";
	}
	
	@RequestMapping(value = {"/dashboard_3.html"}, method = RequestMethod.GET)
	public String dashboard_3(Model model) {
		
		model.addAttribute("pageName", "dashboard_3");
		
		return "default";
	}
	
	@RequestMapping(value = {"/dashboard_4_1.html"}, method = RequestMethod.GET)
	public String dashboard_4_1() {
		return "dashboard_4_1";
	}
	
	@RequestMapping(value = {"/model"}, method = RequestMethod.GET)
	public String model(Model model) {
		
		model.addAttribute("id", "ID001");
		
		return "model"; 
	}
	
	@RequestMapping(value = {"/path-variable/{id}"}, method = RequestMethod.GET)
	public String pathVariable(Model model, @PathVariable("id") String id) {
		
		model.addAttribute("id", id);
		
		return "path_variable"; 
	}
	
}
