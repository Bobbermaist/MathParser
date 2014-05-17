package it.calcomatic.symbols;

import java.util.LinkedList;

import it.calcomatic.math.Expression;
import it.calcomatic.math.MathematicalException;
import it.calcomatic.math.UnaryOperator;

public class SquareRootOperator extends UnaryOperator {

	private static final String PATTERN = "sqrt";
	
	private String value;
	
	@Override
	public String getPattern() {
		return PATTERN;
	}
	
	@Override
	public boolean operatorAfterArgument() {
		return false;
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
			throw new MathematicalException("Invalid number of arguments for square root operator");
		}
		
		double argument = arguments.getFirst().solve();
		
		return Math.sqrt(argument);
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
