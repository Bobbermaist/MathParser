package calcomatic;

import static org.junit.Assert.*;
import it.calcomatic.math.Symbol;
import it.calcomatic.parser.MathematicalMatcher;
import it.calcomatic.symbols.*;

import org.junit.Before;
import org.junit.Test;

public class MathematicalMatcherTest {

	private MathematicalMatcher matcher;
	
	@Before
	public void setUp() throws Exception {
		this.matcher = new MathematicalMatcher();
	}

	@Test
	public void testMatchPlusOperator() {
		Symbol symbol = this.matcher.getNextSymbol("+");
		assertTrue(symbol instanceof PlusOperator);
	}
	
	@Test
	public void testMatchMinusOperator() {
		Symbol symbol = this.matcher.getNextSymbol("-");
		assertTrue(symbol instanceof BinaryMinusOperator);
	}

	@Test
	public void testMatchStarOperator() {
		Symbol symbol = this.matcher.getNextSymbol("*");
		assertTrue(symbol instanceof StarOperator);
	}
	
	@Test
	public void testMatchSlashOperator() {
		Symbol symbol = this.matcher.getNextSymbol("/");
		assertTrue(symbol instanceof SlashOperator);
	}
	
	@Test
	public void testMatchCaretOperator() {
		Symbol symbol = this.matcher.getNextSymbol("^");
		assertTrue(symbol instanceof CaretOperator);
	}
	
	@Test
	public void testMatchSquareRootOperator() {
		Symbol symbol = this.matcher.getNextSymbol("sqrt");
		assertTrue(symbol instanceof SquareRootOperator);
	}
	
	@Test
	public void testMatchIntegerSymbol() {
		Symbol symbol = this.matcher.getNextSymbol("0");
		assertTrue(symbol instanceof IntegerSymbol);
	}
	
	@Test
	public void testMatchTwoDigitInteger() {
		Symbol symbol = this.matcher.getNextSymbol("11");
		assertEquals("11", symbol.getValue());
	}
	
	@Test
	public void testMatchInvalidSymbol() {
		Symbol symbol = this.matcher.getNextSymbol("@");
		assertTrue(symbol instanceof InvalidSymbol);
	}
}
