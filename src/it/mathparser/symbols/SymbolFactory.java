package it.mathparser.symbols;

import it.mathparser.math.Symbol;

/**
 * The symbol factory provides a way to instantiate
 * mathematical symbols by their simple names,
 * their fully qualified names or from another instance.
 * 
 * @author emiliano
 */
public class SymbolFactory {

	private static final String SYMBOL_PACKAGE = "it.calcomatic.symbols";
	
	public static final String[] SYMBOL_LIST = {
		"IntegerSymbol",
		
		"PlusOperator", "BinaryMinusOperator", "StarOperator",
		"SlashOperator", "SquareRootOperator", "CaretOperator",
		"FactorialOperator",
		
		"OpeningRoundBracket", "ClosingRoundBracket"
	};
	
	public Symbol instanceForName(String className) {
		return this.instanceForFullyQualifiedName(SYMBOL_PACKAGE + "." + className);
	}
	
	public Symbol instanceForFullyQualifiedName(String className) {
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
	
	public Symbol newInstance(Symbol symbol) {
		return this.instanceForFullyQualifiedName(symbol.getClass().toString());
	}
}
