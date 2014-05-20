package it.mathparser.math;

import java.util.LinkedList;

/**
 * Operator interface.
 * 
 * An operator should implement this interface only if
 * isn't neither unary or binary.
 * 
 * @author emiliano
 */
public interface Operator extends Symbol {
	
	public int getPriority();
	
	public double execute(LinkedList<Expression> arguments) throws MathematicalException;
}
