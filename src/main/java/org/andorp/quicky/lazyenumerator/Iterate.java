package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;
import org.andorp.quicky.lazyenumerator.Applicable;

final class Iterate<A> implements Enumeration<A> {

	private final Applicable<A,A> f;
	private A x;
	
	public Iterate(Applicable<A,A> _f, A _x) {
		assert _f != null;
		assert _x != null;
		f = _f;
		x = _x;
	}
	
	// @Override
	public boolean hasMoreElements() {
		return true;
	}

	// @Override
	public A nextElement() {
		A y = x;
		x = f.apply(y);
		return y;
	}

}
