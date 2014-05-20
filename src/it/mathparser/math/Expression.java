package it.mathparser.math;

/**
 * Represents a mathematical expression of complete sense.
 * 
 * Both "5" and "2+3" are Expression instances.
 * 
 * @author emiliano
 */
public interface Expression {
	
	public double solve();
}
