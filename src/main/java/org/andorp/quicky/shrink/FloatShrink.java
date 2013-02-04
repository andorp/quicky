package org.andorp.quicky.shrink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import org.andorp.quicky.IShrink;


public final class FloatShrink implements IShrink<Float> {
    
    // @Override
    public Enumeration<Float> shrink(Object value) {
        if(!(value instanceof Float)) {
            throw new RuntimeException("FloatShrink.shrink: invalid object type to shrink");
        }
        ArrayList<Float> shrinks = new ArrayList<Float>();
        float x = (Float)value;
        if(x < 0) {
            shrinks.add((float)-1.0*x);
        }
        float ax = ((int)x);
        if(Math.abs(ax) < Math.abs(x)) {
            shrinks.add(ax);
        }
        return Collections.enumeration(shrinks);
    }
    
}
