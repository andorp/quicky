package org.andorp.quicky.testsuite;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.testsuite.TestPropertyRunner;


public final class TestSuiteRunner {
    
    private final TestPropertyRunner testPropertyRunner;
    private final ITestReporter testReporter;
    
    public TestSuiteRunner(ITestReporter testReporter, Arguments arg) {
        this.testPropertyRunner = new TestPropertyRunner(testReporter, arg);
        this.testReporter = testReporter;
    }
    
    public void runTestSuite(TestSuite suite, Map<Class<?>,Object> instances, Object suiteInstance) {
        
        try {
            
            suite.invokeBeforeSuite(suiteInstance);
            for(TestProperty testProperty : suite.properties) {
                final Object testInstance = instances.get(testProperty.property.getDeclaringClass()); 
                testPropertyRunner.runTestProperty(testProperty, testInstance);
            }
            suite.invokeAfterSuite(suiteInstance);
            
        } catch (IllegalAccessException e) {
            testReporter.reportException(suite, e);
        } catch (IllegalArgumentException e) {
            testReporter.reportException(suite, e);
        } catch (InvocationTargetException e) {
            testReporter.reportException(suite, e);
        }
        
    }
    
}
