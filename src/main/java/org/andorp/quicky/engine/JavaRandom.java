package org.andorp.quicky.engine;

import java.util.Random;

import org.andorp.quicky.IRandom;

public final class JavaRandom implements IRandom {
    
    private Random random;
    private long actualSeed;
    
    public JavaRandom(long seed) {
        random = new Random(seed);
        actualSeed = seed;
    }
    
    public int next() {
        actualSeed = random.nextLong();
        random.setSeed(actualSeed);
        return random.nextInt();
    }
    
    public IRandom split() {
        return new JavaRandom(random.nextLong());
    }
    
    public void setSeed(long seed) {
        actualSeed = seed;
        random.setSeed(seed);
    }
    
    public long getSeed() {
        return actualSeed;
    }
    
}
