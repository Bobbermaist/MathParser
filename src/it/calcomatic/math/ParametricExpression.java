package it.calcomatic.math;

public interface ParametricExpression extends Expression {

	public Operator getOperator();
	
	public void setOperator(Operator operator) throws RuntimeException;
	
	public int getNumArgs();
	
	public void addArgument(Expression expression);
	
	public Expression pollLastArgument();
	
	public boolean hasPriorityOver(ParametricExpression expression);
}
