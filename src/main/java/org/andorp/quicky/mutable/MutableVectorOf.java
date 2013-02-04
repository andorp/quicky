package org.andorp.quicky.mutable;


import java.util.ArrayList;
import java.util.List;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IMutableGenerator;
import org.andorp.quicky.engine.RandomAndSize;

public final class MutableVectorOf<T> implements IMutableGenerator<List<T>> {
    
    private final IGenerator<T> generator;
    
    private int n;
    
    public MutableVectorOf(IGenerator<T> generator) {
        this.n = 0;
        this.generator = generator;
    }
    
    public void setN(int n) {
        this.n = n;
    }
    
    /* Generates a list of the given length */
    public List<T> next(RandomAndSize random) {
        final ArrayList<T> elements = new ArrayList<T>(n);
        for(int elemIdx = 0; elemIdx < n; ++elemIdx) {
            elements.add(generator.next(random));
        }
        return elements;
    }
    
}
