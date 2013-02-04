package org.andorp.quicky.plugins;

import org.andorp.quicky.engine.Result;
import org.andorp.quicky.testsuite.ITestReporter;
import org.andorp.quicky.testsuite.TestProperty;
import org.andorp.quicky.testsuite.TestSuite;
import org.testng.Assert;


public final class TestNGReporter implements ITestReporter {
    
    // @Override
    public void reportPropertyResult(TestProperty property, Result result) {
        if(result instanceof Result.Success) {
            return;
        }
        
        if(result instanceof Result.Failure) {
            Assert.fail(result.output + " Random number generator was initialized with: " + ((Result.Failure)result).usedSeed);
            return;
        }
        
        Assert.fail(result.output);
    }
    
    // @Override
    public void reportException(TestProperty property, Exception e) {
        Assert.fail(property.name + " " + e.getMessage());
    }
    
    // @Override
    public void reportException(TestSuite suite, Exception e) {
        Assert.fail(suite.name + " " + e.getMessage());
    }
    
}
