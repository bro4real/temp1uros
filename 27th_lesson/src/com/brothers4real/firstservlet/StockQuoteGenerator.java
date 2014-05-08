package com.brothers4real.firstservlet;

import java.util.ArrayList;

public class StockQuoteGenerator {
	private String price = null;
	private ArrayList<String> symbols = new ArrayList<String>();

	public StockQuoteGenerator() {
		super();
		// Define some hard-coded NASDAQ symbols
		symbols.add("AAPL");
		symbols.add("MSFT");
		symbols.add("YHOO");
		symbols.add("AMZN");
		symbols.add("MOT");
	}

	public String getQuote(String symbol) {
		if (symbols.indexOf(symbol.toUpperCase()) != -1) {
			// Generate a random price for valid symbols
			price = (new Double(Math.random() * 100)).toString();
		} else {
			price = "Symbol doesn't exist! Please try again.";
		}

		return price;
	}

	public ArrayList<String> getSymbols() {
		return symbols;

	}

}
