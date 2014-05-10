package it.calcomatic.math;

public abstract class UnaryOperator implements Operator {

	@Override
	public int getNumArgs() { return 1; }
}
