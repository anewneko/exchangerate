package org.mahorobo.exchangerate.domain.component.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.mahorobo.exchangerate.domain.component.service.DataFetchService;
import org.mahorobo.exchangerate.domain.enums.Currency;
import org.mahorobo.exchangerate.domain.pojo.ExchangeRateElementVO;
import org.mahorobo.exchangerate.domain.pojo.ExchangeRateTableVO;
import org.mahorobo.exchangerate.tool.FetchUrl;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public class DataFetchImpl implements DataFetchService {
	private final String regex = "[^A-Z]";

	@Override
	public JsonObject fetchAsJson() throws IOException {
		var doc = Jsoup.connect(FetchUrl.WEBURL).get();
        var jsonObject = new JsonObject();
        var elements = doc.select("tbody tr");
        for (Element element : elements) {
            String key = element.select(".xrt-cur-indent").text();
            var spotv = element.select(".rate-content-cash").text().split(" ");
            var cashv = element.select(".rate-content-sight").text().split(" ");
            var value = new JsonObject();
            
            var spot = new JsonObject();
            spot.addProperty("spotBuyingRate", spotv[0]);
            spot.addProperty("spotSellingRate", spotv[1]);
            
            var cash = new JsonObject();
            cash.addProperty("cashBuyingRate", cashv[0]);
            cash.addProperty("cashSellingRate", cashv[1]);
            
            value.add("spot", spot);
            value.add("cash", cash);
            jsonObject.add(key.replaceAll(regex, ""), value);
        }
		return jsonObject;
	}

	@Override
	public ExchangeRateTableVO fetch() throws IOException {
		var doc = Jsoup.connect(FetchUrl.WEBURL).get();
		var t = new ExchangeRateTableVO();
        var elements = doc.select("tbody tr");
        for (Element element : elements) {
            String key = element.select(".xrt-cur-indent").text();
			if (Currency.OTHER.equals(Currency.getCurrency(key.replaceAll(regex, "")))) 
				continue;
			var spotv = element.select(".rate-content-cash").text().split(" ");
			var cashv = element.select(".rate-content-sight").text().split(" ");
			var vo = new  ExchangeRateElementVO();
			vo.setCurrency(key.replaceAll(regex, ""));
			vo.setCashBuyingRate(cashv[0]);
			vo.setCashSellingRate(spotv[1]);
			vo.setSpotBuyingRate(cashv[0]);
			vo.setSpotSellingRate(cashv[1]);
			t.add(vo);
        }
        t.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return t;
	}

}
