package org.andorp.quicky;

import org.andorp.quicky.engine.RandomAndSize;

public interface IGenerator<T> {
    
    T next(RandomAndSize random);
    
}
