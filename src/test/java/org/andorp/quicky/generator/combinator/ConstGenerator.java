package org.andorp.quicky.generator.combinator;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.engine.RandomAndSize;

public final class ConstGenerator<T> implements IGenerator<T> {

    private final T value;
    
    public ConstGenerator(T value) {
        this.value = value;
    }
    
    // @Override
    public T next(RandomAndSize random) {
        return value;
    }

}
