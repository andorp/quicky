package org.andorp.quicky.testsuite;

import org.andorp.quicky.engine.Result;

public interface ITestReporter {
    
    void reportPropertyResult(TestProperty property, Result result);
    void reportException(TestProperty property, Exception e);
    void reportException(TestSuite suite, Exception e);
    
}
