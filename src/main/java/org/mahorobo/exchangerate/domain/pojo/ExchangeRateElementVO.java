package org.mahorobo.exchangerate.domain.pojo;

import lombok.Data;

@Data
public class ExchangeRateElementVO {
	private String currency;
	private String cashBuyingRate;
	private String cashSellingRate;
	private String spotBuyingRate;
	private String spotSellingRate; 

}
