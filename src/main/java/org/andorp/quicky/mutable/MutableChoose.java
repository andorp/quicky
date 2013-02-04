package org.andorp.quicky.mutable;

import org.andorp.quicky.IMutableGenerator;
import org.andorp.quicky.IRandomGen;
import org.andorp.quicky.engine.RandomAndSize;

public final class MutableChoose<T> implements IMutableGenerator<T> {
    
    private T lower;
    private T upper;
    private final IRandomGen<T> randomGen;
    
    public MutableChoose(IRandomGen<T> randomGen) {
        this.randomGen = randomGen;
    }
    
    public void setLimits(T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
    }
    
    public T next(RandomAndSize random) {
        return randomGen.random(random.random(), lower, upper);
    }
    
}
