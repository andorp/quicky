package org.andorp.quicky.shrink;

import java.util.Collections;
import java.util.Enumeration;
import java.util.ArrayList;

import org.andorp.quicky.IShrink;


public final class BooleanShrink implements IShrink<Boolean> {
    
    public Enumeration<Boolean> shrink(Object value) {
        if(!(value instanceof Boolean)) {
            throw new RuntimeException("BooleanShrink.shrink: invalid object to shrink");
        }
        boolean b = (Boolean)value;
        ArrayList<Boolean> shrinkedList = new ArrayList<Boolean>();
        if(b) {
            shrinkedList.add(false);
        }
        return Collections.enumeration(shrinkedList);
    }
    
}
