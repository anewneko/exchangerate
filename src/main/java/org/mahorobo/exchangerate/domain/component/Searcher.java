package org.mahorobo.exchangerate.domain.component;

import java.util.List;
import java.util.stream.IntStream;

import org.mahorobo.exchangerate.domain.component.service.FetchDetailService;
import org.mahorobo.exchangerate.domain.component.service.FetchLogService;
import org.mahorobo.exchangerate.domain.enums.Currency;
import org.mahorobo.exchangerate.domain.pojo.LineChartVO;
import org.mahorobo.exchangerate.domain.pojo.SearchTimesVO;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Searcher {
	private final FetchLogService flSvc;
	private final FetchDetailService fdSvc;
	
	public List<SearchTimesVO> getTimeInDays(){
		return getTimeInDays(3);
	}
	
	public List<SearchTimesVO> getTimeInDays(int days){
		return flSvc.getTimeInDays(days);
	}
	
	public List<String> listCurrency(){
		return List.of(Currency.values())
				   .stream()
				   .filter(c -> !Currency.OTHER.equals(c))
				   .map(Currency::getName).toList();
	}
	
	public List<LineChartVO> getLineChart(String type, String currency) {
		var limit = 30;
		var data = fdSvc.findByCurrency(Currency.valueOf(currency));
		var denominator = (int) Math.ceil((double) data.size() / limit);
		switch (type) {
		case "Cash": {
			return IntStream.range(0, data.size())
							.filter(i ->  i % denominator == 0)
							.mapToObj(data::get)
							.map(LineChartVO::cash).toList();
		}
		case "Spot": {
			return IntStream.range(0, data.size())
							.filter(i ->  i % denominator == 0)
							.mapToObj(data::get)
							.map(LineChartVO::spot).toList();
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + type);
		}
	}

}
