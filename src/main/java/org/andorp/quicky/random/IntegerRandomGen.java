package org.andorp.quicky.random;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.IRandomGen;

public final class IntegerRandomGen implements IRandomGen<Integer>{
    
    public Integer random(IRandom random, Integer lower, Integer upper) {
        if(upper < lower)
            throw new IllegalArgumentException("Lower limit is greater than upper limit!");
        int l = (upper - lower + 1);
        int x = random.next() % l;
        if(x < 0) x += l;
        return lower + x;
    }
    
}
