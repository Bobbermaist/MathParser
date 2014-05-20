package mathparser;

import static org.junit.Assert.*;
import it.mathparser.math.Symbol;
import it.mathparser.parser.TokenIterator;
import it.mathparser.parser.TokenList;
import it.mathparser.symbols.IntegerSymbol;

import org.junit.Before;
import org.junit.Test;

public class TokenListTest {

	private TokenList list;
	
	private TokenIterator iterator;
	
	private IntegerSymbol zero;
	
	private IntegerSymbol one;
	
	private IntegerSymbol two;
	
	@Before
	public void setUp() throws Exception {
		this.list = new TokenList();
		
		this.zero = new IntegerSymbol();
		this.zero.setValue("0");
		this.one = new IntegerSymbol();
		this.one.setValue("1");
		this.two = new IntegerSymbol();
		this.two.setValue("2");
		
		this.list.add(this.zero);
		this.list.add(this.one);
		this.list.add(this.two);
		
		this.iterator = this.list.tokenIterator();
	}
	
	@Test
	public void testIteratorGetFirstLookahead() {
		Symbol zero = this.iterator.getLookahead();
		assertEquals(this.zero.getValue(), zero.getValue());
	}
	
	@Test
	public void testIteratorNext() {
		assertTrue(this.iterator.hasNext());
	}

	@Test
	public void testIteratorFirstNext() {
		Symbol zero = this.iterator.next();
		Symbol one = this.iterator.getLookahead();
		assertEquals(this.zero.getValue(), zero.getValue());
		assertEquals(this.one.getValue(), one.getValue());
	}
	
	@Test
	public void testIteratorSecondNext() {
		this.iterator.next();
		Symbol one = this.iterator.next();
		Symbol two = this.iterator.getLookahead();
		assertEquals(this.one.getValue(), one.getValue());
		assertEquals(this.two.getValue(), two.getValue());
	}
	
	@Test
	public void testIteratorThirdNext() {
		this.iterator.next();
		this.iterator.next();
		Symbol two = this.iterator.next();
		Symbol three = this.iterator.getLookahead();
		assertEquals(this.two.getValue(), two.getValue());
		assertNull(three);
	}
	
	@Test
	public void testIteratorPrevious() {
		assertFalse(this.iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorFirstPrevious() {
		this.iterator.next();
		Symbol zero = this.iterator.previous();
		Symbol one = this.iterator.getLookahead();
		assertEquals(this.zero.getValue(), zero.getValue());
		assertEquals(this.one.getValue(), one.getValue());
	}
	
	@Test
	public void testIteratorSecondPrevious() {
		this.iterator.next();
		this.iterator.next();
		Symbol one = this.iterator.previous();
		Symbol two = this.iterator.getLookahead();
		assertEquals(this.one.getValue(), one.getValue());
		assertEquals(this.two.getValue(), two.getValue());
	}
	
	@Test
	public void testIteratorThirdPrevious() {
		this.iterator.next();
		this.iterator.next();
		this.iterator.next();
		Symbol two = this.iterator.previous();
		Symbol three = this.iterator.getLookahead();
		assertEquals(this.two.getValue(), two.getValue());
		assertNull(three);
	}
	
	@Test
	public void testIteration() {
		Symbol current, lookahead;
		for (int index=0; this.iterator.hasNext(); index++) {
			current = this.iterator.next();
			lookahead = this.iterator.getLookahead();
			switch (index) {
			case 0:
				assertEquals(this.zero.getValue(), current.getValue());
				assertEquals(this.one.getValue(), lookahead.getValue());
			break;
			case 1:
				assertEquals(this.one.getValue(), current.getValue());
				assertEquals(this.two.getValue(), lookahead.getValue());
			break;
			case 2:
				assertEquals(this.two.getValue(), current.getValue());
				assertNull(lookahead);
			break;
			}
		}
		
		assertFalse(this.iterator.hasNext());
	}
}
