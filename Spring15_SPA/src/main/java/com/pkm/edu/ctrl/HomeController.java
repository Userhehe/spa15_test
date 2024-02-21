package com.pkm.edu.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@GetMapping(value = "home.do")
	public String home() {
		log.info("처음시작하는 HomeController Home");
		return "home";
	}
}
