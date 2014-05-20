package it.mathparser.symbols;

import java.util.LinkedList;

import it.mathparser.math.BinaryOperator;
import it.mathparser.math.Expression;
import it.mathparser.math.MathematicalException;

/**
 * The binary minus operator implements the subtraction.
 * 
 * @author emiliano
 */
public class BinaryMinusOperator extends BinaryOperator {

	private static final String PATTERN = "-";
	
	private static final int PRIORITY = 1;
	
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
			throw new MathematicalException("Invalid number of arguments for binary minus operator");
		}
		
		double minuend = arguments.getFirst().solve();
		double subtrahend = arguments.getLast().solve();
		
		return minuend - subtrahend;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
