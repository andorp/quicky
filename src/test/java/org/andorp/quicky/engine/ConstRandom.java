package org.andorp.quicky.engine;

import org.andorp.quicky.IRandom;

public final class ConstRandom implements IRandom {
    
    private final int value;
    
    private long seed;
    
    public ConstRandom(long seed, int value) {
        this.seed = seed;
        this.value = value;
    }
    
    public void setSeed(long seed) {
        this.seed = seed;
    }
    
    public long getSeed() {
        return seed;
    }
    
    public int next() {
        return value;
    }
    
    public IRandom split() {
        return new ConstRandom(seed, value);
    }
    
}