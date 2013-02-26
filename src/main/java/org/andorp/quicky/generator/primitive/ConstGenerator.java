package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.engine.RandomAndSize;

public final class ConstGenerator<T> implements IGenerator<T> {

	private final T constant;
	
	public ConstGenerator(T _constant) {
		constant = _constant;
	}
	
	public T next(RandomAndSize arg0) {
		return constant;
	}

}