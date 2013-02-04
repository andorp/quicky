package org.andorp.quicky.shrink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import org.andorp.quicky.IShrink;

public final class DoubleShrink implements IShrink<Double> {
    
    // @Override
    public Enumeration<Double> shrink(Object value) {
        if(!(value instanceof Double)) {
            throw new RuntimeException("DoubleShrink.shrink: invalid object type to shrink");
        }
        ArrayList<Double> shrinks = new ArrayList<Double>();
        double x = (Double)value;
        if(x < 0) {
            shrinks.add(-1.0*x);
        }
        double ax = ((int)x);
        if(Math.abs(ax) < Math.abs(x)) {
            shrinks.add(ax);
        }
        return Collections.enumeration(shrinks);
    }
    
}
