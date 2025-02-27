package org.mahorobo.exchangerate.domain.component.service;

import java.util.List;

import org.mahorobo.exchangerate.domain.pojo.SearchTimesVO;

public interface FetchLogService {
	
	public List<SearchTimesVO> getTimeInDays(int days);

}
