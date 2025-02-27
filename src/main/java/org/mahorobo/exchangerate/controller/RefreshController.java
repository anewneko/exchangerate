package org.mahorobo.exchangerate.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.mahorobo.exchangerate.domain.component.Coach;
import org.mahorobo.exchangerate.domain.component.service.FetchDetailService;
import org.mahorobo.exchangerate.domain.component.service.FetchLogService;
import org.mahorobo.exchangerate.domain.pojo.ApiResponse;
import org.mahorobo.exchangerate.domain.pojo.ExchangeRateElementVO;
import org.mahorobo.exchangerate.domain.pojo.ExchangeRateTableVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RefreshController {
	private final Coach coach;
	private final FetchLogService flSvc;
	private final FetchDetailService fdSvc;
	
	
	@GetMapping("/refresh")
	public ApiResponse refresh() throws IOException {
		return ApiResponse.success(coach.getData());
	}
	
	
	@GetMapping("/refresh/{key}")
	public ApiResponse refresh(@PathVariable String key) {
		var fl = flSvc.findById(key);
		if(fl.isEmpty()) 
			throw new NullPointerException("No such key");
		
		var list = fdSvc.findByLog(fl.get()).stream().map(fd -> {
			var vo = new ExchangeRateElementVO();
			vo.setCurrency(fd.getCurrency().getName());
			vo.setCashBuyingRate(fd.getCashBuyingRate().toString());
			vo.setCashSellingRate(fd.getCashSellingRate().toString());
			vo.setSpotBuyingRate(fd.getSpotBuyingRate().toString());
			vo.setSpotSellingRate(fd.getSpotSellingRate().toString());
			return vo;
		}).toList();
		
		var t = new ExchangeRateTableVO();
		t.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fl.get().getCreateTime()));
		t.addAll(list);
		
		return ApiResponse.success(t);
	}

}
