package org.andorp.quicky.shrink;

import java.util.Enumeration;

import org.andorp.quicky.IShrink;

public final class EnumerationShrink<T> implements IShrink<Enumeration<T>> {

	// @Override
	public Enumeration<Enumeration<T>> shrink(Object value) {
		if(!(value instanceof Enumeration<?>)) {
			throw new IllegalArgumentException("No enumeration");
		}
		Enumeration<T> enumeration = (Enumeration<T>)value;
		// TODO Auto-generated method stub
		return null;
	}

}
