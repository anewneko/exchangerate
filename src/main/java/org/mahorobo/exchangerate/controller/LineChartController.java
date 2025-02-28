package org.mahorobo.exchangerate.controller;

import org.mahorobo.exchangerate.domain.component.Searcher;
import org.mahorobo.exchangerate.domain.pojo.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LineChartController {
	private final Searcher searcher;
	
	
	@GetMapping("/linechart/{type}/{currency}")
	public ApiResponse getLineChart(@PathVariable String type, @PathVariable String currency) {
		return ApiResponse.success(searcher.getLineChart(type, currency));
	}

}
