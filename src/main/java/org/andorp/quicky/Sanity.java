package org.andorp.quicky;

import java.util.Collection;

public final class Sanity {
    
    private Sanity() {
    }
    
    public static void notNull(Object o, String message) {
        if(o == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void notEmpty(String o, String message) {
        if(o == null) {
            throw new IllegalArgumentException(message);
        }
        
        if(o.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void greaterOrEqualThanZero(int value, String message) {
        if(value < 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void nonNegative(int value, String message) {
        if(value < 0) {
            throw new IllegalArgumentException(message);
        }
    }

    
    public static void greaterThanZero(int value, String message) {
        if(value <= 0) {
            throw new IllegalArgumentException(message);
        }
    }
    
    public static void nonEmptyCollection(Collection<?> c, String message) {
        if(c.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
    
}
