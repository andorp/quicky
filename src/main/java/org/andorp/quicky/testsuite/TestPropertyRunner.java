package org.andorp.quicky.testsuite;

import java.lang.reflect.InvocationTargetException;

import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.engine.Check;
import org.andorp.quicky.engine.ITesteable;
import org.andorp.quicky.engine.Result;
import org.andorp.quicky.engine.TesteableMethodException;
import org.andorp.quicky.engine.TesteableMethodFactory;


public final class TestPropertyRunner {
    
    private final TesteableMethodFactory testeableMethodFactory;
    private final Check quickCheck;
    private final Arguments arguments;
    private final ITestReporter testReporter;
    
    public TestPropertyRunner(ITestReporter testReporter, Arguments arg) {
        Sanity.notNull(testReporter, "testReporter can't be null");
        Sanity.notNull(arg, "arg can't be null");
        
        testeableMethodFactory = new TesteableMethodFactory();
        quickCheck = new Check();
        arguments = (Arguments)arg.clone();
        this.testReporter = testReporter;
    }
    
    public void runTestProperty(TestProperty property, Object instance) {
        
        Result result = null;
        try {
            
            property.invokeBeforeProperty(instance);
            
            final ITesteable<?> testeable = testeableMethodFactory.createTesteable(property.property, instance);
            result = quickCheck.quickCheckWithResult(arguments, testeable);
            testReporter.reportPropertyResult(property, result);
            
            property.invokeAfterProperty(instance);
            
        } catch (IllegalAccessException e) {
            testReporter.reportException(property, e);
        } catch (IllegalArgumentException e) {
            testReporter.reportException(property, e);
        } catch (InvocationTargetException e) {
            testReporter.reportException(property, e);
        } catch (TesteableMethodException e) {
            testReporter.reportException(property, e);
        }
        
    }
    
}
