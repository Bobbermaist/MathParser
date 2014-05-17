package it.calcomatic.math;

import it.calcomatic.symbols.InvalidSymbol;

public class ComplexExpression implements ParametricExpression {

	private SimpleExpression root = null;
	
	private ParametricExpression current = null;
	
	public ComplexExpression() {
		this.root = new SimpleExpression();
	}
	
	@Override
	public Operator getOperator() {
		return this.root.getOperator();
	}
	
	@Override
	public int getNumArgs() {
		return this.root.getNumArgs();
	}
	
	@Override
	public void addArgument(Expression expression) {
		this.getCurrent().addArgument(expression);
		this.current = (ParametricExpression) expression;
	}
	
	@Override
	public void setOperator(Operator operator) throws RuntimeException {
		this.root.setOperator(operator);
	}
	
	@Override
	public boolean hasPriorityOver(ParametricExpression expression) {
		return this.root.hasPriorityOver(expression);
	}
	
	@Override
	public Expression pollLastArgument() {
		return this.root.pollLastArgument();
	}
	
	private ParametricExpression getCurrent() {
		if (this.current != null) {
			return this.current;
		}
		return this.root;
	}
	
	public void addSymbol(Symbol currentSymbol) throws RuntimeException {
		
		if (currentSymbol instanceof InvalidSymbol) {
			throw new RuntimeException("Unknown symbol: " + currentSymbol.getValue());
		}
		
		if (currentSymbol instanceof Operator) {
			this.addOperator((Operator) currentSymbol);
			return;
		}
		
		if (currentSymbol instanceof NumericSymbol) {
			this.getCurrent().addArgument((Expression) currentSymbol);
			return;
		}
	}
	
	private void addOperator(Operator operator) throws RuntimeException {
		ParametricExpression current = this.getCurrent();

		if (current.getOperator() == null) {
			current.setOperator(operator);
			return;
		}
		
		SimpleExpression expression = new SimpleExpression();
		expression.setOperator(operator);
		
		if (current.getOperator() instanceof UnaryOperator
				|| operator instanceof UnaryOperator) {
			this.addArgument(expression);
			return;
		}
		
		if (current.hasPriorityOver(expression)) {
			expression.addArgument(this.root);
			this.root = expression;
			this.current = null;
		} else {
			Expression lastArgument = current.pollLastArgument();
			expression.addArgument(lastArgument);
			
			this.addArgument(expression);
		}
	}

	@Override
	public double solve() {
		return this.root.solve();
	}
	
	@Override
	public String toString() {
		return this.expressionToString() + " = " + this.solutionToString();
	}
	
	public String solutionToString() {
		double result = this.solve();
		if ((int) result == result) {
			return Integer.toString((int) result);
		}
		return Double.toString(result);
	}
	
	public String expressionToString() {
		return "(" + this.root.toString() + ")";
	}
}
