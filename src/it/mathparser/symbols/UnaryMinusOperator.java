package it.mathparser.symbols;

import java.util.LinkedList;

import it.mathparser.math.Expression;
import it.mathparser.math.MathematicalException;
import it.mathparser.math.UnaryOperator;

public class UnaryMinusOperator extends UnaryOperator {

	private static final String PATTERN = "-";
	
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
			throw new MathematicalException("Invalid number of arguments for unary minus operator");
		}
		return -(arguments.getFirst().solve());
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
