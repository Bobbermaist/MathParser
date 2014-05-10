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
		
		this.tree = new ParseTree();
		
		while (it.hasNext()) {
			this.tree.addSymbol(it.next());
		}
		
		this.tree.print();
	}
}
