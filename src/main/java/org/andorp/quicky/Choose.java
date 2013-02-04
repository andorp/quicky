package org.andorp.quicky;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandomGen;
import org.andorp.quicky.engine.RandomAndSize;

public final class Choose<T> implements IGenerator<T> {
    
    private final T lower;
    private final T upper;
    private final IRandomGen<T> randomGen;
    
    public Choose(IRandomGen<T> randomGen, T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
        this.randomGen = randomGen;
    }
    
    /*
     * Generates a random element in the given exlusive range
     */
    public T next(RandomAndSize random) {
        return randomGen.random(random.random(), lower, upper);
    }
    
}
