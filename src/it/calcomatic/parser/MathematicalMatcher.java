package it.calcomatic.parser;

import it.calcomatic.math.Symbol;
import it.calcomatic.symbols.SymbolFactory;
import it.calcomatic.symbols.InvalidSymbol;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

public class MathematicalMatcher {
	
	private LinkedHashMap<Pattern, Symbol> symbolTable;
	
	private SymbolFactory factory;
	
	public MathematicalMatcher() {
		this.initTable();
	}
	
	private void addSymbol(String className) {
		Symbol symbol = this.factory.instanceForName(className);
		Pattern pattern = Pattern.compile("^" + symbol.getPattern());
		this.symbolTable.put(pattern, symbol);
	}
	
	private void initTable() {
		this.symbolTable = new LinkedHashMap<Pattern, Symbol>(7);
		this.factory = new SymbolFactory();
		
		String[] list = SymbolFactory.SYMBOL_LIST;
		for (String symbol : list) {
			this.addSymbol(symbol);
		}
		
		this.factory = null; // destroy SymbolFactory
	}
	
	public Token getNextToken(String s) {
		Iterator<Pattern> it = this.symbolTable.keySet().iterator();
		Pattern regex;
		Matcher matcher;
		while (it.hasNext()) {
			regex = it.next();
			matcher = regex.matcher(s);
			if (matcher.find()) {
				return new Token(this.symbolTable.get(regex), matcher.group(0));
			}
		}
		
		Symbol invalid = new InvalidSymbol();
		regex = Pattern.compile(invalid.getPattern());
		matcher = regex.matcher(s);
		matcher.find();
		return new Token(invalid, matcher.group(0));
	}
}
