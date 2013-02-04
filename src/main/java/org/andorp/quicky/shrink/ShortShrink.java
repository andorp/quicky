package org.andorp.quicky.shrink;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.andorp.quicky.IShrink;

public final class ShortShrink implements IShrink<Short> {
    
    // @Override
    public Enumeration<Short> shrink(Object value) {
        if(!(value instanceof Short)) {
            throw new RuntimeException("ShortShrink.shrink: invalid type");
        }
        short x = (Short)value;
        return new ShrinkEnumeration(x);
    }
    
    private static class ShrinkEnumeration implements Enumeration<Short> {
        
        private final short x;
        private short y;
        private boolean needLast;
        
        ShrinkEnumeration(short _x) {
            x = _x;
            y = (short)(x / 2);
            needLast = true;
        }
        
        // @Override
        public boolean hasMoreElements() {
            return (y > 0 && needLast);
        }
        
        // @Override
        public Short nextElement() {
            while(y != 0) {
                if(filter(y,x)) {
                    short r = (short)(x - y);
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
        
        private boolean filter(short a, short b) {
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
