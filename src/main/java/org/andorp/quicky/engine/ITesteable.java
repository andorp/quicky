package org.andorp.quicky.engine;

import org.andorp.quicky.IGenerator;

public interface ITesteable<T> {
    
    IGenerator<Prop> property();
    boolean exhaustive();
    int maxSuccess();
    
}
