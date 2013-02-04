package org.andorp.quicky.shrink;

import java.util.Enumeration;

import org.andorp.quicky.IShrink;

public final class StringShrink implements IShrink<String> {
    
    // @Override
    public Enumeration<String> shrink(Object value) {
        if(!(value instanceof String)) {
            throw new RuntimeException("StringShrink.shrink: no String instance");
        }
        // TODO: Implement string shrink //
        return null;
    }
    
}
