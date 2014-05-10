package it.calcomatic.symbols;

import it.calcomatic.math.BinaryOperator;

public class CaretOperator extends BinaryOperator {
	
	private static final String PATTERN = "\\^";
	
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
