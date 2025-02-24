package org.mahorobo.exchangerate.domain.enums;

public enum Currency {
	USD("USD"), HKD("HKD"), GBP("GBP"), AUD("AUD"), CAD("CAD"), SGD("SGD"), CHF("CHF"), JPY("JPY"),OTHER("OTHER");

	private String name;

	private Currency(String name) {
		this.name = name;
	}
	
	public static Currency getCurrency(String name) {
		for (Currency currency : Currency.values()) 
			if (currency.getName().equals(name)) 
				return currency;
		return OTHER;
	}

	public String getName() {
		return name;
	}

}
