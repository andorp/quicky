package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

final class Cons<A> implements Enumeration<A> {

	private A a;
	private final Enumeration<A> as;
	
	public Cons(A _a, Enumeration<A> _as) {
		assert _a != null;
		assert _as != null;
		
		a = _a;
		as = _as;
		
	}
	
	// @Override
	public boolean hasMoreElements() {
		return (a != null || as.hasMoreElements());
	}

	// @Override
	public A nextElement() {
		if (a == null) {
			return as.nextElement();
		}
		
		// This branch supposed to run only once. When the first acess of
		// of the nextElement function.
		A x = a;
		a = null;
		return x;
	}

}
