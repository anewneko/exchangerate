package org.mahorobo.exchangerate.domain.component.service;

import java.io.IOException;
import java.util.Map;

import com.google.gson.JsonObject;

public interface DataFetchService {

	public JsonObject fetchAsJson() throws IOException ;
	
	public Map<String,Object> fetch() throws IOException ;
}
