package org.andorp.quicky.generator.combinator;


import java.util.List;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;
import org.andorp.quicky.mutable.MutableVectorOf;

/**
 * Randomly generates a list of a given minimum length and maximum length comes from the internal size.
 * 
 * @author and.or
 *
 * @param <T> The type of the elements in the generated list
 */
public final class ListOf<T> implements IGenerator<List<T>> {
    
    private final MutableChoose<Integer> mutableChoose;
    private final MutableVectorOf<T> mutableVectorOf;
    private final int minLength;
    
    /**
     * Minimum list length is set to 0.
     * @param generator Data generator for the generated elements
     */
    public ListOf(IGenerator<T> generator) {
        this(0, generator);
    }
    
    /**
     * @param minLength The minimum length of the generated lists
     * @param generator Data generator for the generated elements
     */
    public ListOf(int minLength, IGenerator<T> generator) {
        Sanity.greaterOrEqualThanZero(minLength, "ListOf: minimum length of the desired list, can't be negative.");
        Sanity.notNull(generator, "ListOf: generator can't be null.");
        
        this.minLength = minLength;
        mutableChoose = new MutableChoose<Integer>(PrimitiveRandomGen.integer());
        mutableVectorOf = new MutableVectorOf<T>(generator);
    }
    
    public List<T> next(RandomAndSize random) {
        final int size = random.size();
        final int max = size < minLength ? minLength : size;
        mutableChoose.setLimits(minLength, max);
        final int k = mutableChoose.next(random);
        mutableVectorOf.setN(k);
        return mutableVectorOf.next(random);
    }
    
}
