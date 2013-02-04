package org.andorp.quicky.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IShrink;
import org.andorp.quicky.Pair;
import org.andorp.quicky.PreconditionError;
import org.andorp.quicky.RoseTree;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.annotation.DataGenerator;
import org.andorp.quicky.annotation.Property;
import org.andorp.quicky.annotation.Shrink;
import org.andorp.quicky.engine.Prop;



public final class TesteableMethodFactory {
    
    private int maxSuccesAnnotation(final Method propertyMethod) throws TesteableMethodException {
        Annotation[] annotations = propertyMethod.getDeclaredAnnotations();
        for(int i = 0; i < annotations.length; ++i) {
            if(annotations[i] instanceof Property) {
                Property p = (Property)annotations[i];
                return p.runs();
            }
        }
        throw new TesteableMethodException("Method don't have Property annotation!");
    }
    
    public ITesteable<Prop> createTesteable(final Method propertyMethod, final Object methodObj) throws TesteableMethodException {
        final IGenerator<Prop> generator = createGenerator(propertyMethod, methodObj);
        final int maxSuccess = maxSuccesAnnotation(propertyMethod);
        return new ITesteable<Prop>() {
            public IGenerator<Prop> property() {
                return generator;
            }
            
            public boolean exhaustive() {
                return false;
            }
            
            public int maxSuccess() {
                return maxSuccess;
            }
        };
    }
    
    public IGenerator<Prop> createGenerator(final Method propertyMethod, final Object methodObj) throws TesteableMethodException {
        Sanity.notNull(propertyMethod, "propertyMethod can't be null");
        Sanity.notNull(methodObj, "methodObj can't be null");
        // TODO: Check if this is correct //
        try {
            final HashMap<Integer, IGenerator<?>> generators = new HashMap<Integer, IGenerator<?>>();
            final HashMap<Integer, IShrink<?>> shrinks = new HashMap<Integer, IShrink<?>>();
            
            final Annotation[][] annotations = propertyMethod.getParameterAnnotations();
            final int argNum = annotations.length;
            
            for(int i = 0; i < annotations.length; ++i) {
                for(int j = 0; j < annotations[i].length; ++j) {
                    if(annotations[i][j] instanceof DataGenerator) {
                        DataGenerator d = (DataGenerator)annotations[i][j];
                        IGenerator<?> g = d.value().newInstance();
                        generators.put(i, g);
                        continue;
                    }
                    if(annotations[i][j] instanceof Shrink) {
                        Shrink s = (Shrink)annotations[i][j];
                        IShrink<?> shrink = s.value().newInstance();
                        shrinks.put(i, shrink);
                        continue;
                    }
                }
                if(!generators.containsKey(i)) {
                    throw new TesteableMethodException("There is no DataGenerator for local parameter.");
                }
            }
            
            return new IGenerator<Prop>() {
                
                private SingleTestResult runOnce(Object[] args) {
                    boolean expect = true;
                    boolean isDiscarded = false;
                    boolean ok = false;
                    boolean abort = false;
                    String reason = "Passed";
                    try {
                        // Try to run the method. //
                        propertyMethod.invoke(methodObj, args);
                    } catch (InvocationTargetException e) {
                        Throwable t = e.getTargetException();
                        ok = false;
                        if(t instanceof AssertionError) {
                            reason = t.getMessage();
                        } else if (t instanceof PreconditionError) {
                            isDiscarded = true;
                            reason = t.getMessage();
                        }
                    } catch (IllegalArgumentException e) {
                        ok = false;
                        reason = "IllegalArgumentException: " + e.getMessage();
                    } catch (IllegalAccessException e) {
                        ok = false;
                        reason = "IllegalAccessException: " + e.getMessage();
                    }
                    return new SingleTestResult(isDiscarded, ok, expect, reason, abort);
                }
                
                private ArrayList<ArrayList<Object>> tupleShrinkedArguments(
                        ArrayList<ArrayList<Object>> m,
                        Collection<?> v
                        ) {
                    ArrayList<ArrayList<Object>> n = new ArrayList<ArrayList<Object>>();
                    for(ArrayList<Object> col : m) {
                        for(Object vi : v) {
                            ArrayList<Object> newCol = new ArrayList<Object>(col);
                            newCol.add(vi);
                            n.add(newCol);
                        }
                    }
                    return n;
                }
                
                private List<Object[]> shrinkedArgs(final Object[] args) {
                    ArrayList<Object[]> newArgs = new ArrayList<Object[]>();
                    Collection<?>[] shrinkedArgs = new Collection[args.length];
                    
                    final int n = args.length;
                    for(int i = 0; i < n; ++i) {
                        
                        // No shrink for the i-th argument.
                        if(!shrinks.containsKey(i)) {
                            shrinkedArgs[i] = new ArrayList<Object>(0);
                            continue;
                        }
                        IShrink<?> shrinker = shrinks.get(i);
                        //TODO: Use enumeration
                        shrinkedArgs[i] = null; // shrinker.shrink(args[i]);
                    }
                    ArrayList<ArrayList<Object>> newArgsList = new ArrayList<ArrayList<Object>>();
                    newArgsList.add(new ArrayList<Object>());
                    for(int s = 0; s < n; ++s) {
                        newArgsList = tupleShrinkedArguments(newArgsList, shrinkedArgs[s]);
                    }
                    for(ArrayList<Object> params : newArgsList) {
                        newArgs.add(params.toArray());
                    }
                    return newArgs;
                }
                
                private Pair<Object[],RoseTree<SingleTestResult>> runShrinking(Object[] args) {
                    SingleTestResult result = runOnce(args);
                    List<RoseTree<SingleTestResult>> branches = new ArrayList<RoseTree<SingleTestResult>>();
                    if(!result.isDiscarded && (result.expect != result.ok)) {
                        // Failed test.
                        List<Object[]> newArgsList = shrinkedArgs(args);
                        for(Object[] newArgs : newArgsList) {
                            SingleTestResult result2 = runOnce(newArgs);
                            if(result2.ok != result.ok) {
                                continue;
                            }
                            Pair<Object[], RoseTree<SingleTestResult>> shrinkedResult = runShrinking(newArgs);
                            branches.add(shrinkedResult.second());
                            return new Pair<Object[], RoseTree<SingleTestResult>>(
                                    shrinkedResult.first(),
                                    new RoseTree<SingleTestResult>(result, branches)
                                    );
                        }
                    }
                    return new Pair<Object[],RoseTree<SingleTestResult>>(
                            args,
                            new RoseTree<SingleTestResult>(result, branches)
                            );
                }
                
                // @Override
                public Prop next(RandomAndSize random) {
                    Object[] args = new Object[argNum];
                    for(int argi = 0; argi < argNum; ++argi) {
                        args[argi] = generators.get(argi).next(random);
                    }
                    Pair<Object[], RoseTree<SingleTestResult>> result = runShrinking(args);
                    return new Prop(result.second(), result.first());
                }
            };
        } catch (final InstantiationException e) {
            // Null object pattern: Return a Prop generator that returns failure immediately //
            return new FailPropGenerator(e.getMessage());
        } catch (final IllegalAccessException e) {
            // Null object pattern: Return a Prop generator that returns failure immediately //
            return new FailPropGenerator(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Annotation test");
        /*    PropertyRunner pr = new PropertyRunner();
        Class<?>[] paramTypes = new Class<?>[2];
        paramTypes[0] = Integer.TYPE;
        paramTypes[1] = Integer.TYPE;
        ITesteable<Prop> testeable = null;
        try {
    	testeable = pr.createTesteable(Test.class.getMethod("property2", paramTypes));
    	Check checker = new Check();
    	Arguments as = new Arguments();
    	as.chatty = true;
    	as.maxDiscardRatio = 1.0;
    	as.maxSuccess = 10;
    	as.maxSize = 250;
    	as.replay = false;
    	Result r = checker.quickCheckWithResult(as, testeable);
    	String output = null;
    	if(r instanceof Result.Failure) {
    		output = r.output + " " + ((Result.Failure)r).reason;
    	} else {
    		output = r.output;
    	}
    	System.out.println(output);
	} catch (SecurityException e) {
		System.out.println("SecurityException " + e.getMessage());
		return;
	} catch (NoSuchMethodException e) {
		System.out.println("NoSuchMethodException " + e.getMessage());*/
        return;
    }
    
}
