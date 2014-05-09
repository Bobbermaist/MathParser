package it.calcomatic.symbols;

import it.calcomatic.math.ClosingBracket;

public class ClosingRoundBracket implements ClosingBracket {

	private static final String PATTERN = "\\)";
	
	@Override
	public String getPattern() {
		return PATTERN;
	}
}