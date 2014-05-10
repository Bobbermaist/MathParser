package it.calcomatic.parser;

import it.calcomatic.math.Symbol;

import java.util.LinkedList;

public class MathematicalTokenizer {

	private LinkedList<Symbol> tokens;
	
	public void tokenize(String input) {
		this.tokens = new LinkedList<Symbol>();
		MathematicalMatcher matcher = new MathematicalMatcher();
		Symbol current;
		
		while (! input.equals("")) {
			input = input.trim();
			current = matcher.getNextSymbol(input);
			this.tokens.add(current);
			input = input.substring(current.getValue().length());
		}
	}
	
	public LinkedList<Symbol> getTokens() {
		return this.tokens;
	}
}
