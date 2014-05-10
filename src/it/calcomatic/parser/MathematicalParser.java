package it.calcomatic.parser;

import it.calcomatic.math.Symbol;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.ListIterator;

public class MathematicalParser {
	
	private ParseTree tree;
	
	public void parse(String input) throws ParseException {
		MathematicalTokenizer tokenizer = new MathematicalTokenizer();
		tokenizer.tokenize(input);
		LinkedList<Symbol> tokens = tokenizer.getTokens();
		ListIterator<Symbol> it = tokens.listIterator();
		int tokenCount = 0;
		
		this.tree = new ParseTree();
		
		while (it.hasNext()) {
			tokenCount++;
			try {
				this.tree.addSymbol(it.next());
			} catch (RuntimeException e) {
				throw new ParseException(e.getMessage(), tokenCount);
			}
		}
		
		this.tree.print(); // TODO TEST!
	}
}
