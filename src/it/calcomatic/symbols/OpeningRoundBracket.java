package it.calcomatic.symbols;

import it.calcomatic.math.OpeningBracket;

public class OpeningRoundBracket implements OpeningBracket {

	private static final String PATTERN = "\\(";
	
	@Override
	public String getPattern() {
		return PATTERN;
	}
}
