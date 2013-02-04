package org.andorp.quicky.generator.combinator;


import java.util.HashSet;
import java.util.Set;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.mutable.MutableChoose;

/**
 * Randomly generates a HashSet using the given generator.
 * 
 * @author and.or
 *
 * @param <V> Type of the elements
 */
public final class SetOf<V> implements IGenerator<Set<V>> {
    
    private final IGenerator<V> valueGenerator;
    private final MutableChoose<Integer> choose;
    
    public SetOf(IGenerator<V> valueGenerator) {
        Sanity.notNull(valueGenerator, "SetOf: ValueGenerator can't be null.");
        this.valueGenerator = valueGenerator;
        this.choose = new MutableChoose<Integer>(PrimitiveRandomGen.integer());
    }
    
    public Set<V> next(RandomAndSize random) {
        final HashSet<V> set = new HashSet<V>();
        final int size = random.size();
        choose.setLimits(0, size);
        final int numberOfElems = choose.next(random);
        for(int ei = 0; ei < numberOfElems; ++ei) {
            set.add(valueGenerator.next(random));
        }
        return set;
    }
    
}
