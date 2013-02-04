package org.andorp.quicky.engine;

import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.engine.ISizeComputation;

public final class DefaultSizeComputation implements ISizeComputation {
    
    private final int maxSize;
    private final int maxSuccess;
    
    public DefaultSizeComputation(Arguments a) {
        maxSize = a.maxSize;
        maxSuccess = a.maxSuccess;
    }
    
    public DefaultSizeComputation(int maxSize, int maxSuccess) {
        this.maxSize = maxSize;
        this.maxSuccess = maxSuccess;
    }
    
    public int compute(int n, int d) {
        Sanity.greaterOrEqualThanZero(n, "DefaultSizeComputation.compute n parameter must be greater than -1, n was " + n);
        Sanity.greaterOrEqualThanZero(d, "DefaultSizeComputation.compute d parameter must be greater than -1, d was " + d);
        
        if(roundTo(n, maxSize) + maxSize <= maxSuccess ||
                n >= maxSuccess ||
                maxSuccess % maxSize == 0
                ) {
            return greaterThanZero(((n % maxSize) + (d / 10)));
        }
        return greaterThanZero(((n % maxSize) * maxSize) / (maxSuccess % maxSize) + (d / 10));
    }
    
    private int roundTo(int n, int m) {
        return (n / m) * m;
    }
    
    private int greaterThanZero(int x) {
        if(x <= 1) {
            return 1;
        }
        
        return x;
    }
    
}
