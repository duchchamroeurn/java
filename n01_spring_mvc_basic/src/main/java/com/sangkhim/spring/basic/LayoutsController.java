package com.sangkhim.spring.basic;

import org.springframework.web.bind.annotation.RequestMapping;

public class LayoutsController {

	@RequestMapping("/layouts.html")
	public String layouts() {
		return "layouts";
	}
	
}
