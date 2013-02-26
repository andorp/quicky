package org.andorp.quicky.generator.combinator.stated;

import java.util.HashSet;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;


public final class Unique<T> implements IGenerator<T> {

    private final HashSet<T> generatedValueSet;
    private final IGenerator<T> generator;
    private final int tries;

    public Unique(IGenerator<T> generator) {
        this(generator, Integer.MAX_VALUE);
    }

    public Unique(IGenerator<T> generator, int tries) {
        Sanity.notNull(generator, "Unique: Generator was null");
        Sanity.nonNegative(tries, "Unique: Given negative for maximum number of trying");
        this.generator = generator;
        this.tries = tries;
        this.generatedValueSet = new HashSet<T>();
    }

    public T next(RandomAndSize random) throws StatedCombinatorException {
        int t = tries;
        while(t > 0) {
            final T value = generator.next(random);
            if(!generatedValueSet.contains(value)) {
                generatedValueSet.add(value);
                return value;
            }
            --t;
        }
        throw new StatedCombinatorException("Number of trying has reached the limit");
    }

}
