package org.mahorobo.exchangerate.domain.component.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mahorobo.exchangerate.domain.component.service.FetchLogService;
import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.mahorobo.exchangerate.domain.pojo.SearchTimesVO;
import org.mahorobo.exchangerate.repository.FetchLogRepo;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FetchLogImpl implements FetchLogService {
	
	private final FetchLogRepo repo;
	
	public List<SearchTimesVO> getTimeInDays(int days) {
		var endDate = new Date();
		var startDate = Date.from(LocalDate.now().minusDays(days).atStartOfDay(ZoneId.systemDefault()).toInstant());
		var r = repo.getInDays(startDate,endDate).stream().map(SearchTimesVO::of).toList();
		return r.isEmpty() ? List.of() : r;
	}

	@Override
	public Optional<FetchLog> findById(String id) {
		return repo.findById(UUID.fromString(id));
	}

}
