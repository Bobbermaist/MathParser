package it.calcomatic.symbols;

import it.calcomatic.math.BinaryOperator;

public class CaretOperator implements BinaryOperator {
	
	private static final String PATTERN = "\\^";
	
	private static final int PRIORITY = 3;
	
	private double base;
	
	private double exponent;

	@Override
	public String getPattern() {
		return PATTERN;
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public void setLeftOperand(String parameter) {
		this.base = Double.parseDouble(parameter);
	}

	@Override
	public void setRightOperand(String parameter) {
		this.exponent = Double.parseDouble(parameter);
	}
}
