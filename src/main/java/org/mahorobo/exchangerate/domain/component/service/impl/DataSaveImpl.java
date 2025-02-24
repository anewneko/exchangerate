package org.mahorobo.exchangerate.domain.component.service.impl;

import org.mahorobo.exchangerate.domain.component.service.DataSaveService;
import org.mahorobo.exchangerate.domain.entity.FetchDetail;
import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.mahorobo.exchangerate.domain.enums.Currency;
import org.mahorobo.exchangerate.repository.FetchDetailRepo;
import org.mahorobo.exchangerate.repository.FetchLogRepo;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataSaveImpl implements DataSaveService {
	private final FetchLogRepo logRepo;
	private final FetchDetailRepo detailRepo;
	
	@Override
	public void saveData(JsonObject json) {
		Gson gson = new Gson().newBuilder().setPrettyPrinting().create();
        String s = gson.toJson(json);
        
        var log = new FetchLog();
        log.setOrgData(s);
        logRepo.save(log);
        
        var details = json.entrySet()
        					.stream()
			        		.map(e -> {
			        			try {
			        				var d = new FetchDetail();
			        				var currency = e.getKey();
			        				var spot = e.getValue().getAsJsonObject().getAsJsonObject("spot");
			        				var cash = e.getValue().getAsJsonObject().getAsJsonObject("cash");
			        				d.setLog(log);
			        				d.setCurrency(Currency.getCurrency(currency));
			        				d.setCashBuyingRate(cash.get("cashBuyingRate").getAsBigDecimal());
			        				d.setCashSellingRate(cash.get("cashSellingRate").getAsBigDecimal());
			        				d.setSpotBuyingRate(spot.get("spotBuyingRate").getAsBigDecimal());
			        				d.setSpotSellingRate(spot.get("spotSellingRate").getAsBigDecimal());
			        				return d;
								} catch (Exception e2) {
									return null;
								}
			        		})
			        		.filter(d -> d != null)
			        		.filter(d -> !Currency.OTHER.equals(d.getCurrency()))
			        		.toList();
        
        detailRepo.saveAll(details);
	}

}
