package org.mahorobo.exchangerate.domain.component.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.mahorobo.exchangerate.domain.component.service.DataFetchService;
import org.mahorobo.exchangerate.domain.enums.Currency;
import org.mahorobo.exchangerate.tool.FetchUrl;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

@Service
public class DataFetchImpl implements DataFetchService {

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
            jsonObject.add(key.replaceAll("[^A-Z]", ""), value);
        }
		return jsonObject;
	}

	@Override
	public Map<String, Object> fetch() throws IOException {
		var doc = Jsoup.connect(FetchUrl.WEBURL).get();
        var map = new HashMap<String, Object>();
        var elements = doc.select("tbody tr");
        for (Element element : elements) {
            String key = element.select(".xrt-cur-indent").text();
			if (Currency.OTHER.equals(Currency.getCurrency(key.replaceAll("[^A-Z]", "")))) 
				continue;
            var spotv = element.select(".rate-content-cash").text().split(" ");
            var cashv = element.select(".rate-content-sight").text().split(" ");
            var spot = Map.of("spotBuyingRate", spotv[0], "spotSellingRate", spotv[1]);
            var cash = Map.of("cashBuyingRate", cashv[0], "cashSellingRate", cashv[1]);
            var value = Map.of("spot", spot, "cash", cash);
            map.put(key.replaceAll("[^A-Z]", ""), value);
        }
		return map;
	}

}
