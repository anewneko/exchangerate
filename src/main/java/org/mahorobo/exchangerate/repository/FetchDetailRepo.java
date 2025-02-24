package org.mahorobo.exchangerate.repository;

import java.util.UUID;

import org.mahorobo.exchangerate.domain.entity.FetchDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FetchDetailRepo extends JpaRepository<FetchDetail, UUID> {

}
