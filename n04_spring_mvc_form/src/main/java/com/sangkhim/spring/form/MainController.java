package com.sangkhim.spring.form;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
	
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String index(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "index"; 
	}
	
	@RequestMapping(value = {"/save"}, method = RequestMethod.POST)
	public String save(Model model, @Valid @ModelAttribute("student") Student student, BindingResult result) {
		if(result.hasErrors()){
			return "index";
		}
		return "success";
	}
	
	@RequestMapping(value = {"/success"}, method = RequestMethod.GET)
	public String success() {
		return "success"; 
	}
	
	@RequestMapping(value = {"/jquery"}, method = RequestMethod.GET)
	public String jqueryForm() {
		return "jquery_form";
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/formSerialize"}, method = RequestMethod.POST)
	public Employee formSerialize(@ModelAttribute Employee employee) {
		return employee;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/formData1"}, method = RequestMethod.POST)
	public Employee formData1(@ModelAttribute Employee employee) {
		return employee;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/formData2"}, method = RequestMethod.POST)
	public String formData2(
			@RequestParam("name") String name, 
			@RequestParam("file") MultipartFile file, 
			@RequestParam("address.files[0]") MultipartFile file0) {
		
		return name;
	}
	
	@ResponseBody 
	@RequestMapping(value = {"/jsonData"}, method = RequestMethod.POST)
	public Employee jsonData(@RequestBody Employee employee) {
		return employee;
	}
	
}
