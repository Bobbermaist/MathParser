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
	
	private ParseTree tree;
	
	public void parse(String input) throws ParseException {
		MathematicalTokenizer tokenizer = new MathematicalTokenizer();
		tokenizer.tokenize(input);
		LinkedList<Token> tokens = tokenizer.getTokens();
		ListIterator<Token> it = tokens.listIterator();
		
		this.tree = new ParseTree();
		Token currentToken = null;
		
		while (it.hasNext()) {
			this.tree.addToken(it.next());
		}
		
		this.tree.print();
	}
}
