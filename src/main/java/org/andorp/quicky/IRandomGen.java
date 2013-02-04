package org.andorp.quicky;

import org.andorp.quicky.IRandom;

public interface IRandomGen<T> {
    
    T random(IRandom random, T lower, T upper);
    
}
