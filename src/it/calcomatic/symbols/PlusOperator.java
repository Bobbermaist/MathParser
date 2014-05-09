package it.calcomatic.symbols;

import it.calcomatic.math.BinaryOperator;

public class PlusOperator implements BinaryOperator {

	private static final String PATTERN= "\\+";
	
	private static final int PRIORITY = 1;
	
	private double leftAddend;
	
	private double rightAddend;
	
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
		this.leftAddend = Double.parseDouble(parameter);
	}

	@Override
	public void setRightOperand(String parameter) {
		this.rightAddend = Double.parseDouble(parameter);
	}

}
