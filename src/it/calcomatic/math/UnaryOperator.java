package it.calcomatic.math;

public abstract class UnaryOperator implements Operator {

	private static final int PRIORITY = 3;
	
	@Override
	public int getPriority() { return PRIORITY; }
	
	@Override
	public int getNumArgs() { return 1; }
	
	public abstract boolean operatorAfterArgument();
}
