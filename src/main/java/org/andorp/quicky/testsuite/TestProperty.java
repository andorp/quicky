package org.andorp.quicky.testsuite;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.andorp.quicky.Sanity;


public final class TestProperty {
    
    private final Method beforeProperty;
    public final Method property;
    private final Method afterProperty;
    public final String name;
    
    public TestProperty(
            Method beforeProperty,
            Method property,
            Method afterProperty,
            String name
            ) {
        Sanity.notNull(property, "property can't be null");
        this.beforeProperty = beforeProperty;
        this.property = property;
        this.afterProperty = afterProperty;
        this.name = name;
    }
    
    public void invokeBeforeProperty(Object instance) throws 
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException 
    {
        if(beforeProperty != null) {
            beforeProperty.invoke(instance, new Object[0]);
        }
    }
    
    public void invokeAfterProperty(Object instance) throws 
    IllegalAccessException, 
    IllegalArgumentException, 
    InvocationTargetException
    {
        if(afterProperty != null) {
            afterProperty.invoke(instance, new Object[0]);
        }
    }
    
}
