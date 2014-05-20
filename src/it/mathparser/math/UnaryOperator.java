package it.mathparser.math;

/**
 * Unary operator class.
 * 
 * Every unary operator must extend this class.
 * 
 * @author emiliano
 */
public abstract class UnaryOperator implements Operator {

	protected static final int PARAM_NUMBER = 1;
	
	private static final int PRIORITY = 3;
	
	@Override
	public int getPriority() { return PRIORITY; }
	
	public abstract boolean operatorAfterArgument();
}
