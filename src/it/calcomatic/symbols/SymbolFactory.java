package it.calcomatic.symbols;

import it.calcomatic.math.Symbol;

public class SymbolFactory {

	private static final String SYMBOL_PACKAGE = "it.calcomatic.symbols";
	
	public static final String[] SYMBOL_LIST = {
		"IntegerSymbol",
		
		"PlusOperator", "MinusOperator", "StarOperator",
		"SlashOperator", "SquareRootOperator", "CaretOperator",
		
		"OpeningRoundBracket", "ClosingRoundBracket"
	};
	
	public Symbol instanceForName(String className) {
		className = SYMBOL_PACKAGE + "." + className;
		Object symbol = null;
		try {
			symbol = Class.forName(className).newInstance();
		} catch (Exception e) {
			symbol = null;
		}
		
		if (symbol instanceof Symbol) {
			return (Symbol) symbol;
		} else {
			return new InvalidSymbol();
		}
	}
}
