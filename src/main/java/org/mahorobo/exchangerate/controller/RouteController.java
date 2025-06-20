package org.mahorobo.exchangerate.controller;

import java.io.IOException;

import org.mahorobo.exchangerate.domain.component.Coach;
import org.mahorobo.exchangerate.domain.component.Searcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RouteController {
	private final Coach coach;
	private final Searcher searcher;

	@GetMapping("/")
	public String index(Model model) throws IOException {
		model.addAttribute("table", coach.getData());
		model.addAttribute("searchTimes", searcher.getTimeInDays());
		model.addAttribute("currencies", searcher.listCurrency());
		model.addAttribute("lineChart", searcher.getLineChart("Cash","USD"));
	    return "index";
	}
}
