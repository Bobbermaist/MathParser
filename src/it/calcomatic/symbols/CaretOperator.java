package it.calcomatic.symbols;

import java.util.LinkedList;

import it.calcomatic.math.BinaryOperator;
import it.calcomatic.math.Expression;
import it.calcomatic.math.MathematicalException;

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

	@Override
	public double execute(LinkedList<Expression> arguments) throws MathematicalException {
		if (arguments.size() != PARAM_NUMBER) {
			throw new MathematicalException("Invalid number of arguments for caret operator");
		}
		
		double base = arguments.getFirst().solve();
		double exponent = arguments.getLast().solve();
		
		return Math.pow(base, exponent);
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
