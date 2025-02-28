package org.mahorobo.exchangerate.domain.pojo;

import java.text.SimpleDateFormat;

import org.mahorobo.exchangerate.domain.entity.FetchDetail;

import lombok.Data;

@Data
public class LineChartVO {
	private String date;
	private String buyingRate;
	private String sellingRate;
	
	
	public static LineChartVO cash(FetchDetail fd) {
        var vo = new LineChartVO();
        vo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fd.getLog().getCreateTime()));
        vo.setBuyingRate(fd.getCashBuyingRate().toString());
        vo.setSellingRate(fd.getCashSellingRate().toString());
        return vo;
		
	}
	
	public static LineChartVO spot(FetchDetail fd) {
        var vo = new LineChartVO();
        vo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(fd.getLog().getCreateTime()));
        vo.setBuyingRate(fd.getSpotBuyingRate().toString());
        vo.setSellingRate(fd.getSpotSellingRate().toString());
        return vo;
		
	}
}
