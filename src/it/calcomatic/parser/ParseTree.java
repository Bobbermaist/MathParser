package it.calcomatic.parser;

import it.calcomatic.math.ClosingBracket;
import it.calcomatic.math.Expression;
import it.calcomatic.math.MathematicalExpression;
import it.calcomatic.math.NumericSymbol;
import it.calcomatic.math.OpeningBracket;
import it.calcomatic.math.Operator;
import it.calcomatic.math.Symbol;
import it.calcomatic.math.UnaryOperator;
import it.calcomatic.symbols.InvalidSymbol;

public class ParseTree implements Expression {

	private MathematicalExpression root = null;
	
	private MathematicalExpression current = null;
	
	private int enclosureLevel = 0;
	
	public ParseTree() {
		this.root = new MathematicalExpression();
	}
	
	private MathematicalExpression getCurrent() {
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
		
		if (currentSymbol instanceof OpeningBracket) {
			this.enclosureLevel++;
			return;
		}
		
		if (currentSymbol instanceof ClosingBracket) {
			this.enclosureLevel--;
			return;
		}
	}
	
	private void addOperator(Operator operator) throws RuntimeException {
		MathematicalExpression current = this.getCurrent();

		if (current.getOperator() == null) {
			current.setOperator(operator);
			current.setEnclosureLevel(this.enclosureLevel);
			return;
		}
		
		MathematicalExpression expression = new MathematicalExpression();
		expression.setOperator(operator);
		expression.setEnclosureLevel(this.enclosureLevel);
		
		if (current.getOperator() instanceof UnaryOperator
				|| operator instanceof UnaryOperator) {
			// TODO
			current.addArgument(expression);
			this.current = expression;
			return;
		}
		
		if (current.hasPriority(expression)) {
			expression.addArgument(this.root);
			this.root = expression;
			this.current = null;
		} else {
			Expression lastArgument = current.pollLastArgument();
			expression.addArgument(lastArgument);
			
			current.addArgument(expression);
			this.current = expression;
		}
	}
	
	/*
	private void addUnaryOperator(MathematicalExpression expression) throws RuntimeException {
		if (expression.getNumArgs() != 0) {
			throw new RuntimeException("Unexpected args in new operator expression");
		}
		
		if (this.addMissingOperator(expression)) {
			return;
		}
		
		MathematicalExpression current = this.getCurrent();
		
		UnaryOperator operator = (UnaryOperator) expression.getOperator();
		if (operator.operatorAfterArgument()) {
			expression.addArgument(current);
			current.replaceArguments(expression);
		} else {
			current.addArgument(expression);
			this.current = expression;
		}
	}
	*/

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
		return this.root.toString();
	}
}
