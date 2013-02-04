package org.andorp.quicky.testsuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public final class TestSuite {
    
    public final Method beforeSuite;
    public final Method afterSuite;
    public final Collection<TestProperty> properties;
    public final String name;
    
    public TestSuite(
            Method beforeSuite,
            Collection<TestProperty> properties,
            Method afterSuite,
            String name
            ) {
        this.beforeSuite = beforeSuite;
        this.properties = properties;
        this.afterSuite = afterSuite;
        this.name = name;
    }
    
    public void invokeBeforeSuite(Object instance) throws 
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException 
    {
        if(beforeSuite != null) {
            beforeSuite.invoke(instance, new Object[0]);
        }
    }
    
    public void invokeAfterSuite(Object instance)  throws 
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException
    {
        if(afterSuite != null) {
            afterSuite.invoke(instance, new Object[0]);
        }
        
    }
    
}
