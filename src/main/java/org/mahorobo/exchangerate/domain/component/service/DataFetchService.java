package org.mahorobo.exchangerate.domain.component.service;

import java.io.IOException;

import org.mahorobo.exchangerate.domain.pojo.ExchangeRateTableVO;

import com.google.gson.JsonObject;

public interface DataFetchService {

	public JsonObject fetchAsJson() throws IOException ;
	
	public ExchangeRateTableVO fetch() throws IOException ;
}
