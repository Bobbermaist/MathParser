package it.calcomatic.parser;

import java.text.ParseException;

import it.calcomatic.math.ClosingBracket;
import it.calcomatic.math.Expression;
import it.calcomatic.math.MathematicalExpression;
import it.calcomatic.math.NumericSymbol;
import it.calcomatic.math.OpeningBracket;
import it.calcomatic.math.Operator;
import it.calcomatic.math.Symbol;
import it.calcomatic.symbols.InvalidSymbol;

public class ParseTree {

	private MathematicalExpression root = null;
	
	private MathematicalExpression current = null;
	
	private int enclosureLevel = 0;
	
	private int tokenCount = 0;
	
	public ParseTree() {
		this.root = new MathematicalExpression();
	}
	
	private MathematicalExpression getCurrent() {
		if (this.current != null) {
			return this.current;
		}
		return this.root;
	}
	
	public void addSymbol(Symbol currentSymbol) throws ParseException {
		this.tokenCount++;
		
		if (currentSymbol instanceof InvalidSymbol) {
			throw new ParseException("Unknown symbol: " + currentSymbol.getValue(), this.tokenCount);
		}
		
		if (currentSymbol instanceof Operator) {
			this.addOperator((Operator) currentSymbol, this.enclosureLevel);
			return;
		}
		
		if (currentSymbol instanceof NumericSymbol) {
			Expression num = (Expression) currentSymbol;
			this.getCurrent().addArgument(num);
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
	
	private void addOperator(Operator operator, int enclosureLevel) throws RuntimeException {
		MathematicalExpression current = this.getCurrent();
		if (current.getOperator() == null) {
			current.setOperator(operator);
			current.setEnclosureLevel(enclosureLevel);
			return;
		}
		
		MathematicalExpression expression = new MathematicalExpression();
		expression.setOperator(operator);
		expression.setEnclosureLevel(enclosureLevel);
		
		if (current.hasPriority(expression)) {
			expression.addArgument(current);
			this.root = expression;
			this.current = null;
		} else {
			Expression lastArgument = current.pollLastArgument();
			expression.addArgument(lastArgument);
			current.addArgument(expression);
			this.current = expression;
		}
	}
	
	// TODO TEST!
	public void print() {
		this.root.print();
	}
}
