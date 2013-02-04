package org.andorp.quicky.random;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.IRandomGen;

public final class ByteRandomGen implements IRandomGen<Byte> {
    
    public Byte random(IRandom random, Byte lower, Byte upper) {
        if(upper < lower)
            throw new IllegalArgumentException("Lower limit is greater than upper limit!");
        
        final int l = (upper - lower + 1);
        final byte l1 = (byte)l;
        byte x = (byte)(random.next() % l);
        if(x < 0) x+= l1;
        return new Byte((byte)(lower + x));
    }
    
}
