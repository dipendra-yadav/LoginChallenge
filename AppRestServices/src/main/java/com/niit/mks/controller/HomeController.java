package com.niit.mks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	@RequestMapping("/")
	public String displayIndex()
	{
		return  "index";
	}

}
