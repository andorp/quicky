package org.andorp.quicky.shrink;

import java.util.Enumeration;
import java.util.List;

import org.andorp.quicky.IShrink;

public final class ListOfShrink<T> implements IShrink<List<T>> {
    
    private final IShrink<T> shrink;
    
    public ListOfShrink(IShrink<T> shrink) {
        // TODO: Sanity check //
        this.shrink = shrink;
    }
    
    // @Override
    public Enumeration<List<T>> shrink(Object value) {
        if(!(value instanceof List<?>)) {
            throw new RuntimeException("ListOfShrink.shrink: not a List<T> object");
        }
        // TODO: Implement it! //
        return null;
    }
    
}
