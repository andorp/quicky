package org.andorp.quicky.testsuite;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

import org.andorp.quicky.annotation.AfterSuite;
import org.andorp.quicky.annotation.BeforeProperty;
import org.andorp.quicky.annotation.BeforeSuite;


public final class TestSuiteClassFactory {
    
    private final TestPropertyFactory testPropertyFactory;
    
    public TestSuiteClassFactory() {
        testPropertyFactory = new TestPropertyFactory();
    }
    
    public TestSuite testSuiteFromClass(Class<?> clss) throws TestSuiteCreationException {
        
        final Method[] methods = clss.getMethods();
        Method beforeSuite = null;
        Method afterSuite = null;
        
        // Search the before- and after- suite methods in the test suite class //
        // Only one before- and after- suite methods are allowed in the suite //
        for(Method method : methods) {
            if(isBeforeSuite(method)) {
                if(beforeSuite != null) {
                    throw new TestSuiteCreationException("Test suite class has more than one before suite");
                }
                beforeSuite = method;
                continue;
            }
            
            if(isAfterSuite(method)) {
                if(afterSuite != null) {
                    throw new TestSuiteCreationException("Test suite class has more than one after suite");
                }
                afterSuite = method;
                continue;
            }
        }
        
        final Collection<TestProperty> properties = testPropertyFactory.testProperties(clss);
        return new TestSuite(beforeSuite, properties, afterSuite, clss.getCanonicalName());
    }
    
    private boolean isBeforeSuite(Method method) {
        final Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof BeforeSuite) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isAfterSuite(Method method) {
        final Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof AfterSuite) {
                return true;
            }
        }
        return false;
    }
}
