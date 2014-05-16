package it.calcomatic.parser;

import it.calcomatic.math.ClosingBracket;
import it.calcomatic.math.OpeningBracket;
import it.calcomatic.math.Symbol;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.ListIterator;

public class MathematicalParser {
	
	private ParseTree expression;
	
	private ArrayList<ParseTree> trees;
	
	private int enclosureLevel = 0;
	
	public ParseTree getTree() {
		return this.expression;
	}
	
	public void parse(String input) throws ParseException {
		MathematicalTokenizer tokenizer = new MathematicalTokenizer();
		tokenizer.tokenize(input);
		TokenList tokens = tokenizer.getTokens();
		
		Symbol currentSymbol;
		this.trees = new ArrayList<ParseTree>();
		TokenIterator it = tokens.tokenIterator();
		while (it.hasNext()) {
			currentSymbol = it.next();
			
			if (currentSymbol instanceof OpeningBracket) {
				this.enclosureLevel++;
			} else if (currentSymbol instanceof ClosingBracket) {
				this.enclosureLevel--;
			} else {
				this.addSymbolToTree(currentSymbol);
			}
		}
		
		this.createExpression();
	}
	
	private void addSymbolToTree(Symbol symbol) {
		ParseTree tree;
		try {
			tree = this.trees.get(this.enclosureLevel);
		} catch (IndexOutOfBoundsException e) {
			this.trees.add(this.enclosureLevel, new ParseTree());
			tree = this.trees.get(this.enclosureLevel);
		}
		
		tree.addSymbol(symbol);
	}
	
	private void createExpression() {
		ParseTree tree;
		ListIterator<ParseTree> it = this.trees.listIterator();
		while (it.hasNext()) {
			tree = it.next();
			if (this.expression == null) {
				this.expression = tree;
			} else {
				this.expression.addArgument(tree);
			}
		}
	}
}
