package it.calcomatic.math;

import java.util.LinkedList;
import java.util.ListIterator;

public class MathematicalExpression implements Expression {

	private Operator operator = null;
	
	private LinkedList<Expression> arguments = null;
	
	private int numArgs = 0;
	
	private int enclosureLevel = 0;
	
	public MathematicalExpression() {
		this.arguments = new LinkedList<Expression>();
	}
	
	public Operator getOperator() {
		return this.operator;
	}
	
	public void setOperator(Operator operator) throws RuntimeException {
		if (this.operator != null) {
			throw new RuntimeException("Unable to change operator");
		}
		this.operator = operator;
	}
	
	public void setEnclosureLevel(int enclosureLevel) {
		this.enclosureLevel = enclosureLevel;
	}
	
	public void addArgument(Expression expression) {
		this.numArgs++;
		if (this.operator instanceof UnaryOperator && this.numArgs > 1) {
			throw new RuntimeException("Unexpected second argument for unary operator");
		}
		if (this.operator instanceof BinaryOperator && this.numArgs > 2) {
			throw new RuntimeException("Unexpected third argument for binary operator");
		}
		this.arguments.add(expression);
	}
	
	public Expression pollLastArgument() {
		Expression lastArgument = this.arguments.pollLast();
		if (lastArgument != null) {
			this.numArgs--;
		}
		return lastArgument;
	}
	
	public boolean hasPriority(MathematicalExpression expression) {
		if (this.enclosureLevel < expression.enclosureLevel) {
			return false;
		}
		if (this.enclosureLevel > expression.enclosureLevel) {
			return true;
		}
		if (this.operator.getPriority() < expression.operator.getPriority()) {
			return false;
		}
		return true;
	}
	
	@Override
	public double solve() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void print() {
		if (this.operator != null) {
			System.out.print(this.operator.getPattern());
		} else {
			System.out.print("?");
		}
		System.out.print("(");
		
		
		ListIterator<Expression> it = this.arguments.listIterator();
		while (it.hasNext()) {
			it.next().print();
		}
		
		System.out.print(")");
	}
}
