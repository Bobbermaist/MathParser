package it.calcomatic.symbols;

import it.calcomatic.math.BinaryOperator;

public class StarOperator implements BinaryOperator {

	private static final String PATTERN = "\\*";
	
	private static final int PRIORITY = 2;
	
	private double multiplicand;
	
	private double multiplier;
	
	@Override
	public String getPattern(){
		return PATTERN;
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public void setLeftOperand(String parameter) {
		this.multiplicand = Double.parseDouble(parameter);
	}

	@Override
	public void setRightOperand(String parameter) {
		this.multiplier = Double.parseDouble(parameter);
	}

}
