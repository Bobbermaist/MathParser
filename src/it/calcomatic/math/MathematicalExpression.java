package it.calcomatic.math;

import java.util.LinkedList;

public class MathematicalExpression implements Expression {

	private Operator operator = null;
	
	private LinkedList<Expression> arguments = null;
	
	private int enclosureLevel = 0;
	
	public MathematicalExpression() {
		this.arguments = new LinkedList<Expression>();
	}
	
	public void setOperator(Operator operator) throws RuntimeException {
		if (this.operator != null) {
			throw new RuntimeException("Unable to change operator");
		}
		this.operator = operator;
	}
	
	public void addArgument(Expression expression) {
		this.arguments.add(expression);
	}
	
	@Override
	public double solve() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}
}
