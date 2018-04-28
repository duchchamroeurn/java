package com.sangkhim.spring.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphsController {

	@RequestMapping({"/graph_flot.html"})
	public String graph_flot() {
		return "graph_flot";
	}
	
}
