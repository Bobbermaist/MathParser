package calcomatic;

import static org.junit.Assert.*;
import it.calcomatic.math.Symbol;
import it.calcomatic.symbols.InvalidSymbol;
import it.calcomatic.symbols.SymbolFactory;

import org.junit.Before;
import org.junit.Test;

public class SymbolFactoryTest {

	private SymbolFactory factory;
	
	@Before
	public void setUp() throws Exception {
		this.factory = new SymbolFactory();
	}

	private boolean isInvalidSymbol(Object o) {
		return (o instanceof InvalidSymbol);
	}
	
	@Test
	public void testNameNull() {
		assertTrue(this.isInvalidSymbol(this.factory.instanceForName(null)));
	}

	@Test
	public void testNameNotValid() {
		assertTrue(this.isInvalidSymbol(this.factory.instanceForName("DefinitelyNotASymbol")));
	}
	
	@Test
	public void testInvalidClassType() {
		assertTrue(this.isInvalidSymbol(this.factory.instanceForName("SymbolFactory")));
	}
	
	@Test
	public void testSymbolList() {
		String[] list = SymbolFactory.SYMBOL_LIST;
		SymbolFactory factory = new SymbolFactory();
		for (String symbolName : list) {
			Symbol symbol = factory.instanceForName(symbolName);
			assertFalse(this.isInvalidSymbol(symbol));
		}
	}
}
