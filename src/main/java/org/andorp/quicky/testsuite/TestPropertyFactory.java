package org.andorp.quicky.testsuite;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.andorp.quicky.annotation.AfterProperty;
import org.andorp.quicky.annotation.BeforeProperty;
import org.andorp.quicky.annotation.DataGenerator;
import org.andorp.quicky.annotation.Property;


public final class TestPropertyFactory {
    
    public Collection<TestProperty> testProperties(Class<?> clss) throws TestSuiteCreationException {
        
        final Method[] classMethods = clss.getMethods();
        
        Method beforeProperty = null;
        Method afterProperty = null;
        final List<Method> propertyMethods = new ArrayList<Method>(classMethods.length);
        
        // Search through the class after-, before-, and test-properties //
        // Only one after- and before- test properties are allowed //
        for(Method method : clss.getMethods()) {
            
            if(!methodParametersHasDataGeneratorAnnotations(method)) {
                throw new TestSuiteCreationException(method.toString() + " should have all annotated parameters");
            }
            
            if(isProperty(method)) {
                propertyMethods.add(method);
                continue;
            }
            
            if(isBeforeProperty(method)) {
                if(beforeProperty != null) {
                    // BeforyProperty is already found //
                    throw new TestSuiteCreationException(clss.toString() + " has more than one BeforeProperty method defined");
                }
                if(!hasZeroParameters(method)) {
                    throw new TestSuiteCreationException(method.toString() + " has more than zero parameters");
                }
                beforeProperty = method;
                continue;
            }
            
            if(isAfterProperty(method)) {
                if(afterProperty != null) {
                    // AfterProperty is already found //
                    throw new TestSuiteCreationException(clss.toString() + " has more than one AfterProperty method defined");
                }
                if(!hasZeroParameters(method)) {
                    throw new TestSuiteCreationException(method.toString() + " has more than zero parameters");
                }
                afterProperty = method;
                continue;
            }
        }
        
        // Create TestProperty instances //
        final List<TestProperty> testProperties = new ArrayList<TestProperty>(propertyMethods.size());
        for(Method method : propertyMethods) {
            testProperties.add(new TestProperty(beforeProperty, method, afterProperty, method.toGenericString()));
        }
        
        return testProperties;
    }
    
    /**
     * Checks if the given method has BeforeProperty annotation
     */
    private boolean isBeforeProperty(Method method) {
        final Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof BeforeProperty) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the given method has AfterProperty annotation
     */
    private boolean isAfterProperty(Method method) {
        final Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof AfterProperty) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the given method has Property annotation
     */
    private boolean isProperty(Method method) {
        final Annotation[] annotations = method.getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation instanceof Property) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if the parameters of the given method has DataGenerator annotation
     */
    private boolean methodParametersHasDataGeneratorAnnotations(Method method) {
        final Annotation[][] parametersAnnotations = method.getParameterAnnotations();
        for(Annotation[] parameterAnnotations : parametersAnnotations) {
            boolean hasDataGeneratorAnnotation = false;
            for(Annotation annotation : parameterAnnotations) {
                if(annotation instanceof DataGenerator) {
                    hasDataGeneratorAnnotation = true;
                    break;
                }
            }
            if(!hasDataGeneratorAnnotation) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Checks if the method has zero parameters
     */
    private boolean hasZeroParameters(Method method) {
        return method.getParameterTypes().length == 0;
    }
}
