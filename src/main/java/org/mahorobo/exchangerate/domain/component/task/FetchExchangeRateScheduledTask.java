package org.mahorobo.exchangerate.domain.component.task;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.mahorobo.exchangerate.domain.component.service.DataSaveService;
import org.mahorobo.exchangerate.tool.FetchUrl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FetchExchangeRateScheduledTask {
	private final DataSaveService dataSaveService;
	
	@Scheduled(cron = "0 0 0/2 * * ?")
	public void fetchExchangeRate() throws IOException {
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
        dataSaveService.saveData(jsonObject);
	}
	

}
