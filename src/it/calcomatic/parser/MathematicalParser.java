package it.calcomatic.parser;

import java.text.ParseException;

public class MathematicalParser {
	
	private ParseTree tree;
	
	public ParseTree getTree() {
		return this.tree;
	}
	
	public void parse(String input) throws ParseException {
		MathematicalTokenizer tokenizer = new MathematicalTokenizer();
		tokenizer.tokenize(input);
		TokenList tokens = tokenizer.getTokens();
		TokenIterator it = tokens.tokenIterator();
		
		this.tree = new ParseTree();
		System.out.println(tokens);
		
		while (it.hasNext()) {
			
			this.tree.addSymbol(it.next());
			/*
			try {
				this.tree.addSymbol(it.next());
			} catch (RuntimeException e) {
				throw new ParseException(e.getMessage(), tokenCount);
			}
			*/
		}
	}
}
