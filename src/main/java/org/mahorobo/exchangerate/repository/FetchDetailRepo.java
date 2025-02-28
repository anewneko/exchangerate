package org.mahorobo.exchangerate.repository;

import java.util.List;
import java.util.UUID;

import org.mahorobo.exchangerate.domain.entity.FetchDetail;
import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.mahorobo.exchangerate.domain.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FetchDetailRepo extends JpaRepository<FetchDetail, UUID> {
	
	
	@Query("select fd from FetchDetail fd where fd.log = :log Order By fd.currency ASC")
	List<FetchDetail> findByLog(FetchLog log);
	
	
	@Query("select fd from FetchDetail fd where fd.currency = :currency Order By fd.log.createTime ASC")
	List<FetchDetail> findByCurrency(Currency currency);

}
