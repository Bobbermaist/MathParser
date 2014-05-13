package it.calcomatic.parser;

import it.calcomatic.math.ClosingBracket;
import it.calcomatic.math.NumericSymbol;
import it.calcomatic.math.OpeningBracket;
import it.calcomatic.math.Operator;
import it.calcomatic.math.Symbol;
import it.calcomatic.math.UnaryOperator;
import it.calcomatic.symbols.BinaryMinusOperator;
import it.calcomatic.symbols.UnaryMinusOperator;

public class MathematicalTokenizer {

	private TokenList tokens;
	
	private TokenIterator iterator;
	
	private Symbol current;
	
	private Symbol lookahead;
	
	public TokenList getTokens() {
		return this.tokens;
	}
	
	public void tokenize(String input) {
		this.tokens = new TokenList();
		MathematicalMatcher matcher = new MathematicalMatcher();
		Symbol current;
		
		while (! input.equals("")) {
			input = input.trim();
			current = matcher.getNextSymbol(input);
			this.tokens.add(current);
			input = input.substring(current.getValue().length());
		}
		
		this.normalize();
	}
	
	private void normalize() {
		this.iterator = this.tokens.tokenIterator();
		
		while (this.iterator.hasNext()) {
			this.current = this.iterator.next();
			this.lookahead = this.iterator.getLookahead();
			
			this.normalizeMinusOperator();
			this.normalizeUnaryOperator();
		}
	}
	
	private void normalizeMinusOperator() {
		if (! (this.current instanceof Operator || this.current instanceof OpeningBracket)) {
			return;
		}
		if (! (this.lookahead instanceof BinaryMinusOperator)) {
			return;
		}
		
		Symbol normalized = new UnaryMinusOperator();
		normalized.setValue(lookahead.getValue());
		this.iterator.set(normalized);
	}
	
	private void normalizeUnaryOperator() {
		if (! (this.current instanceof UnaryOperator)) {
			return;
		}
		if (this.lookahead instanceof NumericSymbol) {
			return;
		}
		
		UnaryOperator unaryOperator = (UnaryOperator) this.current;
		if (! unaryOperator.operatorAfterArgument()) {
			return;
		}
		
		int bracketLevel = 0;
		this.iterator.remove();
		int startIndex = this.iterator.nextIndex();
		
		while (this.iterator.hasPrevious()) {
			this.current = this.iterator.previous();
			
			if (bracketLevel < 0) {
				throw new RuntimeException("Unexpected opening bracket");
			} else if (this.current instanceof OpeningBracket) {
				bracketLevel--;
			} else if (this.current instanceof ClosingBracket) {
				bracketLevel++;
			}
			
			if (bracketLevel == 0) {
				this.iterator.add(unaryOperator);
				break;
			}
		}
		
		this.iterator = this.tokens.tokenIterator(startIndex + 1);
	}
}
