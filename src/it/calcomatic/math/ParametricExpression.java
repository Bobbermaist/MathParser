package it.calcomatic.math;

public interface ParametricExpression extends Expression {

	public Operator getOperator();
	
	public void setOperator(Operator operator) throws RuntimeException;
	
	public void addArgument(Expression expression);
	
	public boolean hasPriority(ParametricExpression expression);
	
	public Expression pollLastArgument();
}
