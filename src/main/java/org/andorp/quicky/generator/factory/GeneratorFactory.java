package org.andorp.quicky.generator.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.engine.RandomAndSize;


public final class GeneratorFactory {
    
    private final Map<Class<?>, IGenerator<?>> genForClass;
    
    public GeneratorFactory() {
        genForClass = new HashMap<Class<?>, IGenerator<?>>();
    }
    
    public void registerDataGenerator(IGenerator<?> generator) {
        final Class<?> c = generator.getClass();
        if(genForClass.containsKey(c)) {
            //TODO: Log
            return;
        }
        genForClass.put(c, generator);
    }
    
    private boolean isRegisteredGenerator(Class<?> clss) {
        return genForClass.containsKey(clss);
    }
    
    private IGenerator<?> registereGenerator(Class<?> clss) {
        if(!genForClass.containsKey(clss)) {
            throw new IllegalArgumentException(clss.toString() + " has not have registered data generator");
        }
        return genForClass.get(clss);
    }
    
    public IGenerator<?>[] createGenerator(Class<?> clss) throws GeneratorFactoryException {
        
        final Constructor<?>[] constructors = clss.getConstructors();
        final IGenerator<?>[] generators = new IGenerator<?>[constructors.length];
        int c = 0;
        for(Constructor<?> constructor : constructors) {
            generators[c] = createFromConstructor(constructor);
            c++;
        }
        return generators;
    }
    
    private IGenerator<Object> createFromConstructor(final Constructor<?> c) throws GeneratorFactoryException {
        final Class<?>[] parameters = c.getParameterTypes();
        final IGenerator<?>[] generators = new IGenerator<?>[parameters.length];
        int i = 0;
        for(Class<?> type : parameters) {
            if(genForClass.containsKey(type)) {
                throw new GeneratorFactoryException("parameter has no registered data generator");
            }
            generators[i] = genForClass.get(type);
            i++;
        }
        
        return new IGenerator<Object>() {
            
            // @Override
            public Object next(RandomAndSize random) {
                
                final Object[] args = new Object[generators.length];
                int i = 0;
                for(IGenerator<?> g : generators) {
                    args[i] = g.next(random);
                    i++;
                }
                
                try {
                    return c.newInstance(args);
                } catch (InstantiationException e) {
                    return null;
                } catch (IllegalAccessException e) {
                    return null;
                } catch (IllegalArgumentException e) {
                    return null;
                } catch (InvocationTargetException e) {
                    return null;
                }
                
            }
            
        };
    }
    
}
