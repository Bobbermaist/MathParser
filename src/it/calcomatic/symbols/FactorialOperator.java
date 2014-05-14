package it.calcomatic.symbols;

import java.util.LinkedList;

import it.calcomatic.math.Expression;
import it.calcomatic.math.MathematicalException;
import it.calcomatic.math.UnaryOperator;

public class FactorialOperator extends UnaryOperator {

	private static final String PATTERN = "!";
	
	private String value;
	
	@Override
	public String getPattern() {
		return PATTERN;
	}

	@Override
	public boolean operatorAfterArgument() {
		return true;
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
		if (arguments.size() != this.getNumArgs()) {
			throw new MathematicalException("Invalid number of arguments for unary minus operator");
		}
		
		double doubleArgument = arguments.getFirst().solve();
		int argument = (int) doubleArgument;
		
		if (argument != doubleArgument) {
			throw new MathematicalException("Unable to calculate factorial of non-integer arguments");
		}
		
		if (argument < 0) {
			throw new MathematicalException("Unable to calculate factorial of negative argument");
		}
		
		if (argument == 0) {
			return 1;
		}
		
		int factorial = 1;
		for (int count=1; count <= argument; count++) {
			factorial *= count;
		}
		return factorial;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
