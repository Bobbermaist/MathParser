package it.calcomatic.symbols;

import it.calcomatic.math.BinaryOperator;

public class SlashOperator implements BinaryOperator {
	
	private static final String PATTERN = "/";
	
	private static final int PRIORITY = 2;

	private double divisor;
	
	private double dividend;
	
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
		this.divisor = Double.parseDouble(parameter);
	}

	@Override
	public void setRightOperand(String parameter) {
		this.dividend = Double.parseDouble(parameter);
	}

}
