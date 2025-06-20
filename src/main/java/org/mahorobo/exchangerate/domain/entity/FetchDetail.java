package org.mahorobo.exchangerate.domain.entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.mahorobo.exchangerate.domain.enums.Currency;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "FetchDetail")
@EntityListeners(AuditingEntityListener.class)
@Data
public class FetchDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private Currency currency;
	
	@Column(precision = 10, scale = 4)
	private BigDecimal cashBuyingRate;
	@Column(precision = 10, scale = 4)
	private BigDecimal cashSellingRate;
	@Column(precision = 10, scale = 4)
	private BigDecimal spotBuyingRate;
	@Column(precision = 10, scale = 4)
	private BigDecimal spotSellingRate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logId")
	private FetchLog log;

}
