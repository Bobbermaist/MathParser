package it.mathparser.symbols;

import it.mathparser.math.Symbol;

public class InvalidSymbol implements Symbol {

	private static final String PATTERN = "\\S+\\s?";
	
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
