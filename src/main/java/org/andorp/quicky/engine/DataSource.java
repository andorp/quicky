package org.andorp.quicky.engine;

import java.util.Enumeration;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandom;
import org.andorp.quicky.Sanity;


public final class DataSource<T> {
    
    private final IGenerator<T> generator;
    
    public DataSource(IGenerator<T> generator) {
        Sanity.notNull(generator, "DataSource: generator can't be null.");
        this.generator = generator;
    }
    
    /**
     * The 'enumerator' creates a lazy enumeration using the given generator and parameters.
     * @param random
     * @param maxSize
     * @param maxElements
     * @return The lazy enumeration of the generated elements.
     */
    public Enumeration<T> enumerator(final IRandom random, final int maxSize, final int maxElements) {
        Sanity.notNull(random, "The random can't be null");
        Sanity.greaterThanZero(maxSize, "The maxSize must be greater than zero");
        Sanity.greaterOrEqualThanZero(maxElements, "The maxElements can't be negative");
        
        return new Enumeration<T>() {
            
            private int n = 0;
            
            // @Override
            public boolean hasMoreElements() {
                return (n < maxElements);
            }
            
            // @Override
            public T nextElement() {
                n++;
                final int size = n % maxSize;
                final RandomAndSize randomAndSize = new RandomAndSize(random, size);
                return generator.next(randomAndSize);
            }
            
        };
        
    }
}
