package com.dw.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping(value = { "/", "/home" })
	public String callMember() {
		return "member";
	}

	@GetMapping("/login")
	public String callLogin() {
		return "login";
	}
}
