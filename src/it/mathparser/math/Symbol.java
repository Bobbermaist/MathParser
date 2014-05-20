package it.mathparser.math;

/**
 * Generic symbol interface.
 * 
 * A Symbol has a pattern so that it can be recognized
 * and a value that depend on the user input.
 * 
 * @author emiliano
 */
public interface Symbol {

	public String getPattern();
	
	public void setValue(String value);
	
	public String getValue();
}
