package org.andorp.quicky.engine;


import java.util.Random;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.ITerminal;
import org.andorp.quicky.engine.ITesteable;
import org.andorp.quicky.engine.JavaRandom;

public final class Check {
    
    public <T> Result quickCheckWithResult(
            Arguments arg,
            ITesteable<T> testeable
            ) {
        
        ITerminal terminal = null;
        if(arg.chatty) {
            terminal = new SystemTerminal();
        } else {
            terminal = new NullTerminal();
        }
        
        IRandom random = null;
        Random r = new Random();
        long seed = r.nextLong();
        if(arg.replay) {
            // TODO: Create replayable test //
            throw new RuntimeException("Check.quickCheckWithResult: Create replayable test");
        } else {
            random = new JavaRandom(seed);
        }
        
        int maxSuccess = testeable.maxSuccess() > 0 ?
                testeable.maxSuccess() : arg.maxSuccess;
                
                State state = new State();
                state.terminal = terminal;
                state.maxSuccessTests = testeable.exhaustive() ? 1 : maxSuccess ;
                // TODO: Investigate why //
                state.maxDiscardedTests = (int) (testeable.exhaustive() ?
                        arg.maxDiscardRatio :
                            arg.maxDiscardRatio * (double)maxSuccess );
                // TODO: Create replayable test //
                state.computeSize = arg.replay ?
                        createComputeSize((Arguments)arg.clone()) :
                            createComputeSize((Arguments)arg.clone()) ;
                        state.numSuccessTests = 0;
                        state.numDiscardedTests = 0;
                        // TODO: Create collected tests: state.collected = new ArrayList<String>(); ??? //
                        state.expectedFailure = false;
                        state.randomSeed = seed; // TODO: Split the random representation and the seed //
                        state.numSuccessShrinks = 0;
                        state.numTryShrinks = 0;
                        state.numTotTryShrinks = 0;
                        
                        IPostProcess postProcess = new PrintPostProcess();
                        MainLoop engine = new MainLoop(random, postProcess);
                        return engine.test(state, testeable.property());
    }
    
    private ISizeComputation createComputeSize(final Arguments a) {
        return new DefaultSizeComputation(a);
    }
    
}
