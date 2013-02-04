package org.andorp.quicky.testsuite;

import org.andorp.quicky.engine.Result;

public final class ConsoleTestReporter implements ITestReporter {
    
    // @Override
    public void reportPropertyResult(TestProperty property, Result result) {
        System.out.println(property.name + ": " + result.output);
    }
    
    // @Override
    public void reportException(TestProperty property, Exception e) {
        System.out.println(property.name + " exception: " + e.getMessage());
    }
    
    // @Override
    public void reportException(TestSuite suite, Exception e) {
        System.out.println(suite.name + " exception: " + e.getMessage());
    }
    
}
