package org.andorp.quicky.engine;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.JavaRandom;
import org.andorp.quicky.engine.RandomAndSize;

public final class EngineToolFactory {
    
    public RandomAndSize randomAndSize(IRandom random, int size) {
        return new RandomAndSize(random, size);
    }
    
    public JavaRandom javaRandom(int seed) {
        return new JavaRandom(seed);
    }
    
}
