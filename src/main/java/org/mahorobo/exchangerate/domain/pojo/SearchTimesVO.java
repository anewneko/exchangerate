package org.mahorobo.exchangerate.domain.pojo;

import java.text.SimpleDateFormat;

import org.mahorobo.exchangerate.domain.entity.FetchLog;

import lombok.Data;

@Data
public class SearchTimesVO {
	private String id;
	private String date;
	
	public static SearchTimesVO of(FetchLog log) {
		var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		var vo = new SearchTimesVO();
		vo.setId(log.getId().toString());
		vo.setDate(dateFormat.format(log.getCreateTime()));
		return vo;
	}
}
