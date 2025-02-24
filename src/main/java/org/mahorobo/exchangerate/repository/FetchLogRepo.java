package org.mahorobo.exchangerate.repository;

import java.util.UUID;

import org.mahorobo.exchangerate.domain.entity.FetchLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FetchLogRepo extends JpaRepository<FetchLog, UUID> {

}
