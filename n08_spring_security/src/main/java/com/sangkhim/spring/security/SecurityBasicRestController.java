package com.sangkhim.spring.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityBasicRestController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "security_basic/everyone";
	}
	
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String user() {
		return "security_basic/normal_user";
	}
	
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public String admin() {
		return "security_basic/admin_user";
	}
	
}
