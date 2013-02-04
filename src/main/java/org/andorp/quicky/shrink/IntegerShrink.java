package org.andorp.quicky.shrink;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.andorp.quicky.IShrink;

public final class IntegerShrink implements IShrink<Integer> {
    
    // @Override
    public Enumeration<Integer> shrink(Object value) {
        if(!(value instanceof Integer)) {
            throw new RuntimeException("IntegerShrink.shrink: invalid type");
        }
        
        final int x = (Integer)value;
        return new ShrinkEnumeration(x);
    }
    
    private static class ShrinkEnumeration implements Enumeration<Integer> {
        
        private final int x;
        private int y;
        private boolean needLast;
        
        ShrinkEnumeration(int _x) {
            x = _x;
            y = x / 2;
            needLast = true;
        }
        
        // @Override
        public boolean hasMoreElements() {
            return (y > 0 && needLast);
        }
        
        // @Override
        public Integer nextElement() {
            while(y != 0) {
                if(filter(y,x)) {
                    int r = x - y;
                    y /= 2;
                    return r;
                } else {
                    y /= 2;
                }
            }
            if (needLast) {
                needLast = false;
                return 0;
            }
            throw new NoSuchElementException();
        }
        
        private boolean filter(int a, int b) {
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
