package org.mahorobo.exchangerate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String index(Model model) {
	    model.addAttribute("message", "Hello, Spring Boot!");
	    return "index";
	}
}
