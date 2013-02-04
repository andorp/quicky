package org.andorp.quicky.engine;

import org.andorp.quicky.engine.IPostProcess;
import org.andorp.quicky.engine.SingleTestResult;

public final class PrintPostProcess implements IPostProcess {
    
    public void postProcess(Object args[], SingleTestResult result) {
        
        System.out.println("Shrinked arguments that unsatisfies the given property:");
        for(Object o : args) {
            System.out.println(o.toString());
        }
        
    }
    
}
