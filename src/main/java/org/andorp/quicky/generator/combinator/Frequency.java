package org.andorp.quicky.generator.combinator;

import java.util.Collection;

import org.andorp.quicky.Choose;
import org.andorp.quicky.CollectionUtils;
import org.andorp.quicky.IGenerator;
import org.andorp.quicky.Pair;
import org.andorp.quicky.PrimitiveRandomGen;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.RandomAndSize;


public final class Frequency<T> implements IGenerator<IGenerator<T>> {
    
    private final Collection<Pair<Integer, IGenerator<T>>> pairs;
    private final Choose<Integer> choose;
    
    public Frequency(Collection<Pair<Integer, IGenerator<T>>> pairs) {
        Sanity.notNull(pairs, "Frequency: pairs can't be null");
        Sanity.nonEmptyCollection(pairs, "Frequency: pairs can't be empty");
        checkPairs(pairs);
        this.pairs = CollectionUtils.shallowCopy(pairs);
        final int total = total(pairs);
        this.choose = new Choose<Integer>(
                PrimitiveRandomGen.integer(),
                0,
                total
                );
    }
    
    /* Chooses one of the given generators, with weighted random distribution. */
    public IGenerator<T> next(RandomAndSize random) {
        return pick(choose.next(random));
    }
    
    private IGenerator<T> pick(int n) {
        for(Pair<Integer, IGenerator<T>> pair : pairs) {
            final int k = pair.first();
            if(n <= k) {
                return pair.second();
            }
            n -= k;
        }
        throw new IllegalArgumentException("Invalid generator list in Frequency was given.");
    }
    
    private static <X> int total(Collection<Pair<Integer,X>> pairs) {
        int sum = 0;
        for(Pair<Integer,?> pair : pairs) {
            sum += pair.first();
        }
        return sum;
    }
    
    private static <X> void checkPairs(Collection<Pair<Integer,X>> pairs) {
        for(Pair<Integer, X> pair : pairs) {
            if(pair.first() <= 0) {
                throw new IllegalArgumentException("Frequency value is less than equal to zero");
            }
            if(pair.second() == null) {
                throw new IllegalArgumentException("Generator is null");
            }
        }
    }
}
