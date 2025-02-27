package org.mahorobo.exchangerate.domain.component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.mahorobo.exchangerate.domain.component.service.DataFetchService;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Coach {
	private final DataFetchService fSvc;
	private Date timeFlag = new Date();
	private Map<String,Object> data;
	
	
	public boolean hasData() {
		return new Date().getTime() - timeFlag.getTime() < 5 * 60 * 1000 && data != null;
	}
	
	public Date getTimeFlag() {
		return timeFlag;
	}

	public Map<String,Object> getData() throws IOException {
		if (!hasData()) {
			data = fSvc.fetch();
			timeFlag = new Date();
		}
		
		return data;
	}
	
	

}
