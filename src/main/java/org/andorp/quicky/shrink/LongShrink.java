package org.andorp.quicky.shrink;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.andorp.quicky.IShrink;


public final class LongShrink implements IShrink<Long> {
    
    // @Override
    public Enumeration<Long> shrink(Object value) {
        if(!(value instanceof Long)) {
            throw new RuntimeException("LongShrink.shrink: invalid type");
        }        
        long x = (Long)value;
        return new ShrinkEnumeration(x);
    }

    private static class ShrinkEnumeration implements Enumeration<Long> {
        
        private final long x;
        private long y;
        private boolean needLast;
        
        ShrinkEnumeration(long _x) {
            x = _x;
            y = x / 2;
            needLast = true;
        }
        
        // @Override
        public boolean hasMoreElements() {
            return (y > 0 && needLast);
        }
        
        // @Override
        public Long nextElement() {
            while(y != 0) {
                if(filter(y,x)) {
                    long r = (x - y);
                    y /= 2;
                    return r;
                } else {
                    y /= 2;
                }
            }
            if (needLast) {
                needLast = false;
                return (long)0;
            }
            throw new NoSuchElementException();
        }
        
        private boolean filter(long a, long b) {
            boolean x = a >= 0;
            boolean y = b >= 0;
            if(x && y) {
                return a < b;
            }
            if(!x && y) {
                return a > b;
            }
            if(x && !y) {
                return a + b < 0;
            }
            return a + b > 0;
        }
        
    }

    
}
