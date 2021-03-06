package it.mathparser.symbols;

import it.mathparser.math.OpeningBracket;

/**
 * Implements an opening round bracket.
 * 
 * @author emiliano
 */
public class OpeningRoundBracket implements OpeningBracket {

	private static final String PATTERN = "\\(";
	
	private String value;
	
	@Override
	public String getPattern() {
		return PATTERN;
	}

	@Override
	public String getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}
