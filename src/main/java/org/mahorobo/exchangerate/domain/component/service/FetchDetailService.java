package org.mahorobo.exchangerate.domain.component.service;

import java.util.List;

import org.mahorobo.exchangerate.domain.entity.FetchDetail;
import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.mahorobo.exchangerate.domain.enums.Currency;

public interface FetchDetailService {
	
	public List<FetchDetail> findByLog(FetchLog log);
	
	public List<FetchDetail> findByCurrency(Currency currency);

}
