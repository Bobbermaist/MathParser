package it.calcomatic.symbols;

import it.calcomatic.math.OpeningBracket;

public class OpeningRoundBracket implements OpeningBracket {

	private static final String PATTERN = "\\(";
	
	private String value;
	
	@Override
	public String getPattern() {
		return PATTERN;
	}

	@Override
	public String getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(String value) {
		this.value = value;
	}
}
