package it.mathparser.parser;

import it.mathparser.math.Symbol;
import it.mathparser.symbols.InvalidSymbol;
import it.mathparser.symbols.SymbolFactory;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Iterator;

public class MathematicalMatcher {
	
	private LinkedHashMap<Pattern, String> symbolTable;
	
	private SymbolFactory factory;
	
	public MathematicalMatcher() {
		this.initTable();
	}
	
	private void addSymbol(String className) {
		Symbol symbol = this.factory.instanceForName(className);
		Pattern pattern = Pattern.compile("^" + symbol.getPattern());
		this.symbolTable.put(pattern, className);
	}
	
	private void initTable() {
		this.symbolTable = new LinkedHashMap<Pattern, String>(9);
		this.factory = new SymbolFactory();
		
		String[] list = SymbolFactory.SYMBOL_LIST;
		for (String symbol : list) {
			this.addSymbol(symbol);
		}
	}
	
	public Symbol getNextSymbol(String s) {
		Iterator<Pattern> it = this.symbolTable.keySet().iterator();
		Pattern regex;
		Matcher matcher;
		while (it.hasNext()) {
			regex = it.next();
			matcher = regex.matcher(s);
			if (matcher.find()) {
				Symbol symbol = this.factory.instanceForName(this.symbolTable.get(regex));
				symbol.setValue(matcher.group(0));
				return symbol;
			}
		}
		
		InvalidSymbol invalid = new InvalidSymbol();
		regex = Pattern.compile(invalid.getPattern());
		matcher = regex.matcher(s);
		matcher.find();
		invalid.setValue(matcher.group(0));
		return invalid;
	}
}
