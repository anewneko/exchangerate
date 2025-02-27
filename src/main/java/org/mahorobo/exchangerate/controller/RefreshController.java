package org.mahorobo.exchangerate.controller;

import java.io.IOException;

import org.mahorobo.exchangerate.domain.component.Coach;
import org.mahorobo.exchangerate.domain.pojo.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RefreshController {
	private final Coach coach;
	
	
	@GetMapping("/refresh")
	public ApiResponse refresh() throws IOException {
		return ApiResponse.success(coach.getData());
	}

}
