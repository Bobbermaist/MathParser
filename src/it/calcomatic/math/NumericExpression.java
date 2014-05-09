package it.calcomatic.math;

public class NumericExpression implements Expression {

	private double value;
	
	public NumericExpression(String value) {
		this.value = Double.parseDouble(value);
	}
	
	public double getValue() {
		return this.value;
	}
	
	@Override
	public double solve() {
		return this.getValue();
	}
	
	// TODO TEST!
	public void print() {
		System.out.print(" " + this.value + " ");
	}

}
