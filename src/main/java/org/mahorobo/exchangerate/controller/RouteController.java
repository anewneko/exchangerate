package org.mahorobo.exchangerate.controller;

import java.io.IOException;

import org.mahorobo.exchangerate.domain.component.Coach;
import org.mahorobo.exchangerate.domain.component.service.FetchLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RouteController {
	private final Coach coach;
	private final FetchLogService flSvc;

	@GetMapping("/")
	public String index(Model model) throws IOException {
		model.addAttribute("table", coach.getData());
		model.addAttribute("searchTimes", flSvc.getTimeInDays(3));
		model.addAttribute("message", "Hello, World!");
	    return "index";
	}
}
