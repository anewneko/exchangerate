package org.mahorobo.exchangerate.domain.component;

import java.io.IOException;
import java.util.Date;

import org.mahorobo.exchangerate.domain.component.service.DataFetchService;
import org.mahorobo.exchangerate.domain.pojo.ExchangeRateTableVO;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Coach {
	private final DataFetchService fSvc;
	private Date timeFlag = new Date();
	private ExchangeRateTableVO data;
	
	
	public boolean hasData() {
		return new Date().getTime() - timeFlag.getTime() < 5 * 60 * 1000 && data != null;
	}
	
	public Date getTimeFlag() {
		return timeFlag;
	}

	public ExchangeRateTableVO getData() throws IOException {
		if (!hasData()) {
			data = fSvc.fetch();
			timeFlag = new Date();
		}
		
		return data;
	}
	
	

}
