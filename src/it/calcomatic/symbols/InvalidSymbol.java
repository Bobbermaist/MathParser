package it.calcomatic.symbols;

import it.calcomatic.math.Symbol;

public class InvalidSymbol implements Symbol {

	private static final String PATTERN = "\\S+\\s?";
	
	@Override
	public String getPattern() {
		return PATTERN;
	}
}
