package it.calcomatic.symbols;

import it.calcomatic.math.UnaryOperator;

public class SquareRootOperator implements UnaryOperator {

	private static final String PATTERN = "sqrt";
	
	private static final int PRIORITY = 3;
	
	private double argument;
	
	@Override
	public String getPattern() {
		return PATTERN;
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public void setOperand(String parameter) {
		this.argument = Double.parseDouble(parameter);
	}

}
