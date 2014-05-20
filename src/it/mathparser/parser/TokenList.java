package it.mathparser.parser;

import java.util.LinkedList;
import java.util.ListIterator;

import it.mathparser.math.Symbol;

public class TokenList extends LinkedList<Symbol> {

	private static final long serialVersionUID = -4206839435081734780L;

	public TokenListIterator tokenIterator() {
		return this.tokenIterator(0);
	}

	public TokenListIterator tokenIterator(int index) {
		return new TokenListIterator(index);
	}

	private class TokenListIterator implements TokenIterator {

		private ListIterator<Symbol> currentIterator;

		private ListIterator<Symbol> lookaheadIterator = null;

		private Symbol lookahead = null;

		TokenListIterator(int index) {
			this.initCurrentIterator(index);
			this.nextLookahead();
		}

		private void newCurrentIterator() {
			this.initCurrentIterator(this.nextIndex());
		}

		private void initCurrentIterator(int index) {
			this.currentIterator = listIterator(index);
		}

		private boolean newLookaheadIterator() {
			return this.initLookaheadIterator(this.nextLookaheadIndex());
		}

		private boolean initLookaheadIterator(int index) {
			try {
				this.lookaheadIterator = listIterator(index);
			} catch (IndexOutOfBoundsException e) {
				this.lookaheadIterator = null;
				return false;
			}
			return true;
		}

		private void nextLookahead() {
			if (this.lookaheadIterator == null) {
				if (this.initLookaheadIterator(this.nextIndex()))
					this.nextLookahead();
			} else if (this.lookaheadIterator.hasNext()) {
				this.lookahead = this.lookaheadIterator.next();
			} else {
				this.lookahead = null;
				this.lookaheadIterator = null;
			}
		}

		private void previousLookahead() {
			if (this.lookaheadIterator == null) {
				if (this.initLookaheadIterator(this.nextIndex() + 1))
					this.previousLookahead();
			} else if (this.lookaheadIterator.hasPrevious()) {
				this.lookahead = this.lookaheadIterator.previous();
			} else {
				this.lookahead = null;
				this.lookaheadIterator = null;
			}
		}

		@Override
		public Symbol getLookahead() {
			return this.lookahead;
		}

		@Override
		public boolean hasNext() {
			return this.lookahead != null;
		}

		@Override
		public Symbol next() {
			this.nextLookahead();
			return this.currentIterator.next();
		}

		@Override
		public boolean hasPrevious() {
			return this.currentIterator.hasPrevious();
		}

		@Override
		public Symbol previous() {
			this.previousLookahead();
			return this.currentIterator.previous();
		}

		@Override
		public int nextIndex() {
			return this.currentIterator.nextIndex();
		}

		@Override
		public int previousIndex() {
			return this.currentIterator.previousIndex();
		}

		@Override
		public int nextLookaheadIndex() {
			return this.nextIndex() + 1;
		}

		@Override
		public int previousLookaheadIndex() {
			return this.lookaheadIterator.previousIndex();
		}

		@Override
		public void remove() {
			this.currentIterator.remove();
			this.newLookaheadIterator();
		}

		@Override
		public void set(Symbol e) {
			this.currentIterator.set(e);
			this.newLookaheadIterator();
		}

		@Override
		public void add(Symbol e) {
			this.currentIterator.add(e);
			this.newLookaheadIterator();
		}

		@Override
		public void removeLookahead() {
			this.lookaheadIterator.remove();
			this.newCurrentIterator();
		}

		@Override
		public void setLookahead(Symbol e) {
			this.lookaheadIterator.set(e);
			this.newCurrentIterator();
		}

		@Override
		public void addLookahead(Symbol e) {
			this.lookaheadIterator.add(e);
			this.newCurrentIterator();
		}
	}
}