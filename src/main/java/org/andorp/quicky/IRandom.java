package org.andorp.quicky;

public interface IRandom {
    
    void setSeed(long seed);
    long getSeed();
    int next();
    IRandom split();
    
}
