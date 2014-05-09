package it.calcomatic.symbols;

import it.calcomatic.math.BinaryOperator;

public class MinusOperator implements BinaryOperator {

	private static final String PATTERN = "-";
	
	private static final int PRIORITY = 1;
	
	private double minuend;
	
	private double subtrahend;
	
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
		this.minuend = Double.parseDouble(parameter);
	}

	@Override
	public void setRightOperand(String parameter) {
		this.subtrahend = Double.parseDouble(parameter);
	}

}
