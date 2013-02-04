package org.andorp.quicky.random;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.IRandomGen;

public final class BooleanRandomGen implements IRandomGen<Boolean> {
    
    public Boolean random(IRandom random, Boolean lower, Boolean upper) {
        
        if(lower == true && upper == false) {
            throw new IllegalArgumentException("Lower limit is greater than upper limit");
        }
        
        if(lower == upper) {
            random.next();
            return lower;
        }
        
        return (random.next() % 2 == 0);
    }
    
}
