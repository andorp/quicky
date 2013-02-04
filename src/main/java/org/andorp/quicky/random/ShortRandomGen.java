package org.andorp.quicky.random;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.IRandomGen;

public final class ShortRandomGen implements IRandomGen<Short> {
    
    public Short random(IRandom random, Short lower, Short upper) {
        if(upper < lower)
            throw new IllegalArgumentException("Lower limit is greater than upper limit!");
        
        if(upper == Short.MAX_VALUE && lower == Short.MIN_VALUE) {
            return (short)(random.next());
        }
        
        final int l1 = (upper - lower + 1);
        final short l = (short)l1;
        short x = (short)(random.next() % l);
        if(x < 0) x += l;
        return (short)(lower + x);
    }
    
}
