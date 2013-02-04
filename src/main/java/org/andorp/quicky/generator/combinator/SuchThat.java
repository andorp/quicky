package org.andorp.quicky.generator.combinator;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.engine.RandomAndSize;

public abstract class SuchThat<T> implements IGenerator<T> {
    
    private final IGenerator<T> generator;
    
    public SuchThat(IGenerator<T> generator) {
        this.generator = generator;
    }
    
    protected abstract boolean predicate(T value);
    
    // Generates a value that satisfies the predicate //
    public T next(RandomAndSize random) {
        T next = null;
        while(!predicate(next = generator.next(random)));
        return next;
    }
    
}
