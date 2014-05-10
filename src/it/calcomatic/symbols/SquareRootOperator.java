package it.calcomatic.symbols;

import it.calcomatic.math.UnaryOperator;

public class SquareRootOperator extends UnaryOperator {

	private static final String PATTERN = "sqrt";
	
	private static final int PRIORITY = 3;
	
	private String value;
	
	@Override
	public String getPattern() {
		return PATTERN;
	}

	@Override
	public int getPriority() {
		return PRIORITY;
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
