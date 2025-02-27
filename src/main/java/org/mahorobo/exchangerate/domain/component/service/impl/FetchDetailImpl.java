package org.mahorobo.exchangerate.domain.component.service.impl;

import java.util.List;

import org.mahorobo.exchangerate.domain.component.service.FetchDetailService;
import org.mahorobo.exchangerate.domain.entity.FetchDetail;
import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.mahorobo.exchangerate.repository.FetchDetailRepo;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FetchDetailImpl implements FetchDetailService {
	
	private final FetchDetailRepo fdRepo;

	@Override
	public List<FetchDetail> findByLog(FetchLog log) {
		return fdRepo.findByLog(log);
	}

}
