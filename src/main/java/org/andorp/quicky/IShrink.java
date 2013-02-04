package org.andorp.quicky;

import java.util.Enumeration;

public interface IShrink<T> {
    
    Enumeration<T> shrink(Object value);
    
}
