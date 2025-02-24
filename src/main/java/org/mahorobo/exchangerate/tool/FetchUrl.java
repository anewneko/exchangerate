package org.mahorobo.exchangerate.tool;

public final class FetchUrl {
	public static final String WEBURL = "https://rate.bot.com.tw/xrt?Lang=zh-TW";
	public static final String CSVURL = "https://rate.bot.com.tw/xrt/flcsv/0/day";
	
	private FetchUrl() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

}
