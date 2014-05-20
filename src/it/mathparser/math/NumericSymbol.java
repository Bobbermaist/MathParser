package it.mathparser.math;

/**
 * Numeric symbol interface.
 * 
 * Every numeric symbol must implement this interface.
 * A numeric symbol is at the same time a Symbol and
 * an Expression.
 * 
 * @author emiliano
 */
public interface NumericSymbol extends Symbol, Expression {}
