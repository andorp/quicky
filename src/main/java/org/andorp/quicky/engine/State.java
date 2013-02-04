package org.andorp.quicky.engine;

import org.andorp.quicky.engine.ISizeComputation;
import org.andorp.quicky.engine.ITerminal;

public final class State implements Cloneable{
    
    /**
     * The current terminal
     */
    public ITerminal terminal;
    
    /**
     * Maximum number of successful tests needed
     */
    public int maxSuccessTests;
    
    /**
     * Maximum number of tests that can be discarded
     */
    public int maxDiscardedTests;
    
    /**
     * How to compute the size of test cases from discarded tests
     */
    public ISizeComputation computeSize;
    
    /**
     * The current number of tests that have succeeded
     */
    public int numSuccessTests;
    
    /**
     * The current number of discarded tests
     */
    public int numDiscardedTests;
    
    /**
     * indicates if the property is expected to fail
     */
    public boolean expectedFailure;
    
    /**
     * The random seed
     */
    public long randomSeed;
    
    /**
     * Number of successful shrinking steos so far
     */
    public int numSuccessShrinks;
    
    /**
     * Number of failed shrinking steps since tha last successful shrink
     */
    public int numTryShrinks;
    
    /**
     * Total number of failed shrinking steps
     */
    public int numTotTryShrinks;
    
    @Override
    public Object clone() {
        final State state = new State();
        state.terminal = terminal;
        state.maxSuccessTests = maxSuccessTests;
        state.maxDiscardedTests = maxDiscardedTests;
        state.computeSize = computeSize; 
        state.numSuccessTests = numSuccessTests;
        state.numDiscardedTests = numDiscardedTests;
        state.expectedFailure = expectedFailure;
        state.randomSeed = randomSeed;
        state.numSuccessShrinks = numSuccessShrinks;
        state.numTryShrinks = numTryShrinks;
        state.numTotTryShrinks = numTotTryShrinks;
        return state;
    }
    
}
