package org.mahorobo.exchangerate.domain.component.task;

import java.io.IOException;

import org.mahorobo.exchangerate.domain.component.service.DataFetchService;
import org.mahorobo.exchangerate.domain.component.service.DataSaveService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FetchExchangeRateScheduledTask {
	private final DataSaveService dataSaveService;
	private final DataFetchService dataFetchService;
	
	@Scheduled(cron = "0 0 0/2 * * ?", zone="Asia/Taipei")
	public void fetchExchangeRate() throws IOException {
        var data = dataFetchService.fetchAsJson();
        dataSaveService.saveData(data);
	}
	

}
