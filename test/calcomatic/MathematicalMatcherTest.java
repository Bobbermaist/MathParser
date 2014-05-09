package calcomatic;

import static org.junit.Assert.*;
import it.calcomatic.parser.MathematicalMatcher;
import it.calcomatic.parser.Token;
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
		Token token = this.matcher.getNextToken("+");
		assertTrue(token.getSymbol() instanceof PlusOperator);
	}
	
	@Test
	public void testMatchMinusOperator() {
		Token token = this.matcher.getNextToken("-");
		assertTrue(token.getSymbol() instanceof MinusOperator);
	}

	@Test
	public void testMatchStarOperator() {
		Token token = this.matcher.getNextToken("*");
		assertTrue(token.getSymbol() instanceof StarOperator);
	}
	
	@Test
	public void testMatchSlashOperator() {
		Token token = this.matcher.getNextToken("/");
		assertTrue(token.getSymbol() instanceof SlashOperator);
	}
	
	@Test
	public void testMatchCaretOperator() {
		Token token = this.matcher.getNextToken("^");
		assertTrue(token.getSymbol() instanceof CaretOperator);
	}
	
	@Test
	public void testMatchSquareRootOperator() {
		Token token = this.matcher.getNextToken("sqrt");
		assertTrue(token.getSymbol() instanceof SquareRootOperator);
	}
	
	@Test
	public void testMatchIntegerSymbol() {
		Token token = this.matcher.getNextToken("0");
		assertTrue(token.getSymbol() instanceof IntegerSymbol);
	}
	
	@Test
	public void testMatchTwoDigitInteger() {
		Token token = this.matcher.getNextToken("11");
		assertEquals("11", token.getInfo());
	}
	
	@Test
	public void testMatchInvalidSymbol() {
		Token token = this.matcher.getNextToken("@");
		assertTrue(token.getSymbol() instanceof InvalidSymbol);
	}
}
