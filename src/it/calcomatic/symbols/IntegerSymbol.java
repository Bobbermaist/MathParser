package it.calcomatic.symbols;

import it.calcomatic.math.NumericSymbol;

public class IntegerSymbol implements NumericSymbol {
	
	private static final String PATTERN = "[0-9]+";

	@Override
	public String getPattern() {
		return PATTERN;
	}
}
