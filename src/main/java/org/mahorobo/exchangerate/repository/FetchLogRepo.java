package org.mahorobo.exchangerate.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FetchLogRepo extends JpaRepository<FetchLog, UUID> {
	
	
	@Query("SELECT f FROM FetchLog f WHERE f.createTime between :startDate AND :endDate"
			+ " ORDER BY f.createTime DESC")
	List<FetchLog> getInDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
