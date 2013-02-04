package org.andorp.quicky;

import java.util.Collection;

public interface IArbitrary<T> {
    
    IGenerator<T> arbitrary();
    Collection<T> shrink(T value);
    
}
