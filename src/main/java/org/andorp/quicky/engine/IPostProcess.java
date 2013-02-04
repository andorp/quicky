package org.andorp.quicky.engine;

import org.andorp.quicky.engine.SingleTestResult;

public interface IPostProcess {
    
    void postProcess(Object[] args, SingleTestResult result);
    
}
