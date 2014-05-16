package it.calcomatic.math;

import java.util.Iterator;
import java.util.LinkedList;

public class SimpleExpression implements ParametricExpression {

	private Operator operator = null;
	
	private LinkedList<Expression> arguments = null;
	
	private int numArgs = 0;
	
	public SimpleExpression() {
		this.arguments = new LinkedList<Expression>();
	}
	
	@Override
	public Operator getOperator() {
		return this.operator;
	}
	
	@Override
	public int getNumArgs() {
		return this.numArgs;
	}
	
	@Override
	public void setOperator(Operator operator) throws RuntimeException {
		if (this.operator != null) {
			throw new RuntimeException("Unable to change operator");
		}
		if (this.numArgs > operator.getNumArgs()) {
			throw new RuntimeException("Too many arguments for " +  this.operator.getClass());
		}
		
		this.operator = operator;
	}
	
	@Override
	public void addArgument(Expression expression) throws RuntimeException {
		this.numArgs++;
		
		if (this.operator != null && this.numArgs > this.operator.getNumArgs()) {
			throw new RuntimeException("Too many arguments for " + this.operator.getClass());
		}
		
		this.arguments.add(expression);
	}
	
	@Override
	public boolean hasPriority(ParametricExpression expression) {
		if (this.operator.getPriority() < expression.getOperator().getPriority()) {
			return false;
		}
		return true;
	}
	
	@Override
	public Expression pollLastArgument() {
		Expression lastArgument = this.arguments.pollLast();
		if (lastArgument != null) {
			this.numArgs--;
		}
		return lastArgument;
	}
	
	public void replaceArguments(Expression expression) {
		this.arguments = new LinkedList<Expression>();
		this.numArgs = 0;
		
		this.addArgument(expression);
	}
	
	@Override
	public double solve() throws MathematicalException {
		if (this.operator == null) {
			throw new MathematicalException("Missing operator");
		}
		
		return this.operator.execute(this.arguments);
	}
	
	@Override
	public String toString() {
		String operator = this.operator != null ? this.operator.toString() : "?";
		String firstArgument = "null", lastArgument = "null";
		
		if (! this.arguments.isEmpty()) {
			firstArgument = this.arguments.getFirst().toString();
			lastArgument = this.arguments.getLast().toString();
		}
		
		switch (this.arguments.size()) {
		case 1: return operator + " " + firstArgument;
		case 2: return firstArgument + " " + operator + " " + lastArgument;
		default:
			StringBuilder out = new StringBuilder(operator + " (");
			Iterator<Expression> it = this.arguments.iterator();
			while (it.hasNext()) {
				out.append(it.next().toString());
			}
			out.append(")");
			return out.toString();
		}
	}
}