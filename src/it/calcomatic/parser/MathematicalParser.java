package it.calcomatic.parser;

import it.calcomatic.math.BinaryOperator;
import it.calcomatic.math.ClosingBracket;
import it.calcomatic.math.MathematicalExpression;
import it.calcomatic.math.NumericExpression;
import it.calcomatic.math.NumericSymbol;
import it.calcomatic.math.OpeningBracket;
import it.calcomatic.math.Operator;
import it.calcomatic.math.UnaryOperator;
import it.calcomatic.symbols.InvalidSymbol;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.ListIterator;

public class MathematicalParser {
	
	private MathematicalExpression expression;
	
	private int tokenCount;
	
	public void parse(String input) throws ParseException {
		MathematicalTokenizer tokenizer = new MathematicalTokenizer();
		tokenizer.tokenize(input);
		LinkedList<Token> tokens = tokenizer.getTokens();
		ListIterator<Token> it = tokens.listIterator();
		
		this.expression = new MathematicalExpression();
		Token currentToken = null;
		this.tokenCount = 0;
		
		while (it.hasNext()) {
			this.tokenCount++;
			currentToken = it.next();
			this.parseInvalidSymbol(currentToken);
			this.parseOperator(currentToken);
			this.parseNumericSymbol(currentToken);
			this.parseBrackets(currentToken, it);
		}
		
		this.expression.print();
	}

	private void parseInvalidSymbol(Token current) throws ParseException {
		if (current.getSymbol() instanceof InvalidSymbol) {
			throw new ParseException("Unknown symbol: " + current.getInfo(), this.tokenCount);
		}
	}
	
	private void parseOperator(Token current) {
		this.parseOperator(current, 0);
	}
	
	private void parseOperator(Token current, int bracketLevel) {
		if (! (current.getSymbol() instanceof Operator)) {
			return;
		}
		
		MathematicalExpression operator = new MathematicalExpression();
		operator.setOperator((Operator) current.getSymbol());
		operator.setBracketLevel(bracketLevel);
		this.expression.addExpression(operator);
	}
	
	private void parseNumericSymbol(Token current) throws ParseException {
		if (! (current.getSymbol() instanceof NumericSymbol)) {
			return;
		}
		
		NumericExpression num = new NumericExpression(current.getInfo());
		this.expression.addExpression(num);
	}
	
	private void parseBrackets(Token current, ListIterator<Token> it) throws ParseException {
		if (! (current.getSymbol() instanceof OpeningBracket)) {
			return;
		}
		int bracketCount = 1;
		
		while (bracketCount != 0) {
			if (! it.hasNext()) {
				throw new ParseException("Cannot find a closing bracket", this.tokenCount);
			}
			current = it.next();
			
			this.parseInvalidSymbol(current);
			this.parseOperator(current, bracketCount);
			this.parseNumericSymbol(current);
			
			if (current.getSymbol() instanceof ClosingBracket) {
				bracketCount--;
			}
			if (current.getSymbol() instanceof OpeningBracket) {
				bracketCount++;
			}
			
			this.tokenCount++;
		}
	}
}
