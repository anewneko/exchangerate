package org.mahorobo.exchangerate.domain.component.service;

import java.util.List;

import org.mahorobo.exchangerate.domain.entity.FetchDetail;
import org.mahorobo.exchangerate.domain.entity.FetchLog;

public interface FetchDetailService {
	
	public List<FetchDetail> findByLog(FetchLog log);

}
