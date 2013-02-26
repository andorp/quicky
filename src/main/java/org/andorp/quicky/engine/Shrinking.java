package org.andorp.quicky.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.andorp.quicky.IShrink;

public final class Shrinking {

    private final HashMap<Class<?>, IShrink<?>> shrinkers;

    public Shrinking() {
        shrinkers = new HashMap<Class<?>, IShrink<?>>();
    }
    
    public void shrink(final Object o, final Method m, final Object[] args) {
        try {
			Object r = m.invoke(o, args);
			if(r instanceof SingleTestResult) {
				SingleTestResult result = (SingleTestResult)r;
                if(!result.isDiscarded && (result.expect != result.ok)) {
                }
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
