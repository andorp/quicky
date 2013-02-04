package org.andorp.quicky.engine;

import org.andorp.quicky.IRandom;

public final class VarRandom implements IRandom {
    
    private int value;
    private long seed;
    
    public VarRandom(long seed, int value) {
        this.seed = seed;
        this.value = value;
    }
    
    public void setSeed(long seed) {
        this.seed = seed;
    }
    
    public long getSeed() {
        return seed;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int next() {
        return value;
    }
    
    public IRandom split() {
        return new VarRandom(seed, value);
    }
    
}
