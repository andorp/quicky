package org.andorp.quicky.random;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.IRandomGen;

public final class LongRandomGen implements IRandomGen<Long> {
    
    public Long random(IRandom random, Long lower, Long upper) {
        if(upper < lower)
            throw new IllegalArgumentException("Lower limit is greater than upper limit!");
        long l = (upper - lower + 1);
        long x = (random.next() % l);
        if(x < 0) x += l;
        return lower + x;
    }
    
}
