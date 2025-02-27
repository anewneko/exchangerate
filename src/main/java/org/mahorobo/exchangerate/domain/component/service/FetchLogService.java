package org.mahorobo.exchangerate.domain.component.service;

import java.util.List;
import java.util.Optional;

import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.mahorobo.exchangerate.domain.pojo.SearchTimesVO;

public interface FetchLogService {
	
	public List<SearchTimesVO> getTimeInDays(int days);
	
	public Optional<FetchLog> findById(String id);

}
