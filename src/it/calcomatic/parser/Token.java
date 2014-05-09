package it.calcomatic.parser;

import it.calcomatic.math.Symbol;

public class Token {

	private Symbol symbol;
	
	private String info;
	
	public Token(Symbol s, String i) {
		this.symbol = s;
		this.info = i;
	}
	
	public Symbol getSymbol() {
		return this.symbol;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	@Override
	public String toString() {
		return this.symbol.getClass() + ": " + this.info;
	}
}
