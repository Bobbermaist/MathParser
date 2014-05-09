package it.calcomatic.parser;

import java.util.LinkedList;

public class MathematicalTokenizer {

	private LinkedList<Token> tokens;
	
	public void tokenize(String input) {
		this.tokens = new LinkedList<Token>();
		MathematicalMatcher matcher = new MathematicalMatcher();
		Token token = null;
		
		while (! input.equals("")) {
			input = input.trim();
			token = matcher.getNextToken(input);
			this.tokens.add(token);
			input = input.substring(token.getInfo().length());
		}
	}
	
	public LinkedList<Token> getTokens() {
		return this.tokens;
	}
}
