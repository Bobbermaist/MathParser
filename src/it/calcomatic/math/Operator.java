package it.calcomatic.math;

public interface Operator extends Symbol {
	
	public int getPriority();
	
	public int getNumArgs();
}
