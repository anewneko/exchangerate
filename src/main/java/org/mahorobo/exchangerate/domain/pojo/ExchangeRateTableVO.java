package org.mahorobo.exchangerate.domain.pojo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ExchangeRateTableVO {
	
	private String updateTime;
	private List<ExchangeRateElementVO> data = new ArrayList<>();
	
	
	public ExchangeRateTableVO add(ExchangeRateElementVO e) {
		data.add(e);
		return this;
	}
	
	public ExchangeRateTableVO addAll(List<ExchangeRateElementVO> e) {
		data.addAll(e);
		return this;
	}
}
