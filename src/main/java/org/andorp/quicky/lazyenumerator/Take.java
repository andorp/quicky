package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;
import java.util.NoSuchElementException;

final class Take<A> implements Enumeration<A> {

	private final Enumeration<A> as;
	private int n;
	
	public Take(int _n, Enumeration<A> _as) {
		assert _as != null;
		assert _n >= 0;
		
		as = _as;
		n = _n;
	}
	
	// @Override
	public boolean hasMoreElements() {
		return n > 0 && as.hasMoreElements();
	}

	// @Override
	public A nextElement() {
		if(n <= 0) {
			throw new NoSuchElementException(); 
		}
		n--;
		return as.nextElement();
	}
	
}
