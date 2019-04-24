package com.jpm.hr.SpringBoot3_RestJpa_Jsr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@ResponseBody
	@GetMapping("/")
	public String greetUser()
	{
		return "hello from Spring boot Rest";
	}

}
