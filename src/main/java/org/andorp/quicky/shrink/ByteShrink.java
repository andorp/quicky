package org.andorp.quicky.shrink;

import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.andorp.quicky.IShrink;


public final class ByteShrink implements IShrink<Byte> {
    
    public Enumeration<Byte> shrink(Object value) {
        if(!(value instanceof Byte)) {
            throw new RuntimeException("ByteShrink.shrink: invalid type");
        }
        byte x = (Byte)value;
        return new ShrinkEnumeration(x);
    }
    
    private static class ShrinkEnumeration implements Enumeration<Byte> {
        
        private final byte x;
        private byte y;
        private boolean needLast;
        
        ShrinkEnumeration(byte _x) {
            x = _x;
            y = (byte)(x / 2);
            needLast = true;
        }
        
        // @Override
        public boolean hasMoreElements() {
            return (y > 0 && needLast);
        }
        
        // @Override
        public Byte nextElement() {
            while(y != 0) {
                if(filter(y,x)) {
                	byte r = (byte)(x - y);
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
        
        private boolean filter(byte a, byte b) {
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
