package it.mathparser.parser;

import it.mathparser.math.Symbol;

import java.util.ListIterator;

public interface TokenIterator extends ListIterator<Symbol> {
	
	public Symbol getLookahead();
	
	public int nextLookaheadIndex();
	
	public int previousLookaheadIndex();
	
	public void removeLookahead();
	
	public void setLookahead(Symbol e);
	
	public void addLookahead(Symbol e);
}
