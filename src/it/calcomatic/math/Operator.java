package it.calcomatic.math;

import java.util.LinkedList;

public interface Operator extends Symbol {
	
	public int getPriority();
	
	public double execute(LinkedList<Expression> arguments) throws MathematicalException;
}
