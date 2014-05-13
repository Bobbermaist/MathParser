package it.calcomatic.symbols;

import it.calcomatic.math.ClosingBracket;

public class ClosingRoundBracket implements ClosingBracket {

	private static final String PATTERN = "\\)";
	
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
	
	@Override
	public String toString() {
		return this.value;
	}
}