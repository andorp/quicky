package org.andorp.quicky.engine;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.Sanity;

public final class RandomAndSize {
    
    private final IRandom random;
    private final int size;
    
    RandomAndSize(IRandom random, int size) {
        Sanity.notNull(random, "RandomAndSize: random must not be null.");
        Sanity.greaterOrEqualThanZero(size, "RandomAndSize: size must be greater or equal than 0");
        
        this.random = random;
        this.size = size;
    }
    
    public IRandom random() {
        return random;
    }
    
    public int size() {
        return size;
    }
    
}
