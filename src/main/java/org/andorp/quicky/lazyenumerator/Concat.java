package org.andorp.quicky.lazyenumerator;

import java.util.Enumeration;

public final class Concat<A> implements Enumeration<A> {

	private final Enumeration<A> as;
	private final Enumeration<A> bs;
	
	public Concat(Enumeration<A> _as, Enumeration<A> _bs) {
		assert _as != null;
		assert _bs != null;
		
		as = _as;
		bs = _bs;
	}
	
	// @Override
	public boolean hasMoreElements() {
		return ((as.hasMoreElements()) || bs.hasMoreElements());
	}

	// @Override
	public A nextElement() {
		if(as.hasMoreElements()) {
			return as.nextElement();
		}
		return bs.nextElement();
	}

}
