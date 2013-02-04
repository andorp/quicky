package org.andorp.quicky.generator.combinator;


import java.util.List;
import java.util.ArrayList;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;

/**
 * Randomly generates a list of a given length, using the given generator
 * 
 * @author and.or
 *
 * @param <T> Type of the elements
 */
public final class VectorOf<T> implements IGenerator<List<T>> {
    
    private final IGenerator<T> generator;
    private final int length;
    
    /**
     * @param length The length of the generated list. It must be greater than zero.
     * @param generator Generates the elements in the result list. It must be not null.
     */
    public VectorOf(int length, IGenerator<T> generator) {
        Sanity.greaterThanZero(length, "VectorOf: length must be greater than zero.");
        Sanity.notNull(generator, "VectorOf: generator can't be null.");
        this.length = length;
        this.generator = generator;
    }
    
    public List<T> next(RandomAndSize random) {
        final ArrayList<T> elements = new ArrayList<T>(length);
        for(int elemIdx = 0; elemIdx < length; ++elemIdx) {
            elements.add(generator.next(random));
        }
        return elements;
    }
    
}
