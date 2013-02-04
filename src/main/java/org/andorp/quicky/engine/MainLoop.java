package org.andorp.quicky.engine;

import org.andorp.quicky.IGenerator;
import org.andorp.quicky.IRandom;
import org.andorp.quicky.Sanity;
import org.andorp.quicky.engine.IPostProcess;
import org.andorp.quicky.engine.RandomAndSize;

final class MainLoop {
    
    private final IRandom random;
    private final IPostProcess postProcess;
    
    MainLoop(IRandom random,IPostProcess postProcess) {
        Sanity.notNull(random, "MainLoop: random parameter must be not null.");
        Sanity.notNull(postProcess, "MainLoop: postProcess parameter must be not null.");
        
        this.random = random;
        this.postProcess = postProcess;
    }
    
    Result test(
            // The initial state //
            State inputState,
            // The property generator serves as engine //
            IGenerator<Prop> propGen
            ) {
        Sanity.notNull(inputState, "MainLoop.test: state parameter must be not null.");
        Sanity.notNull(propGen, "MainLoop.test: propGen parameter must be not null.");
        Sanity.greaterThanZero(inputState.maxSuccessTests, "MainLoop.test: maxSuccess must be greater than zero");
        
        final State state = (State)inputState.clone();
        final long initRandomSeed = random.getSeed();
        
        while(runTesting(state)) {
            
            final int size = state.computeSize.compute(state.numSuccessTests, state.numDiscardedTests);
            final RandomAndSize randomAndSize = new RandomAndSize(random, size);
            final Prop prop = propGen.next(randomAndSize);
            final SingleTestResult res = prop.result();
            
            if(!res.isDiscarded && res.ok) {
                // Successful test //
                state.numSuccessTests += 1;
                state.expectedFailure = res.expect;
                if (res.abort) {
                    return doneTesting(state);
                }
                continue;
            }
            
            if(res.isDiscarded) {
                // Discarded test //
                state.numDiscardedTests += 1;
                state.expectedFailure = res.expect;
                if(res.abort) {
                    return giveUp(state);
                }
                continue;
            }
            
            if(!res.isDiscarded && !res.ok) {
                // Failed test //
                if(res.expect) {
                    state.terminal.putPart("*** Failed after " + state.numDiscardedTests + " test(s).");
                    postProcess.postProcess(prop.shrinkedArguments(), res);
                } else {
                    state.terminal.putPart("+++ OK, Failed as expected after " + state.numDiscardedTests + " test(s).");
                }
                
                if(!res.expect) {
                    return Result.Success(state.numSuccessTests + 1, state.terminal.output());
                } else {
                    return Result.Failure(
                            state.numSuccessTests + 1,
                            state.terminal.output(),
                            0, //TODO: Numshrinks
                            initRandomSeed,
                            size,
                            res.reason
                            );
                }
            }
        }
        
        if(state.numSuccessTests >= state.maxSuccessTests) {
            return doneTesting(state);
        }
        
        if(state.numDiscardedTests >= state.maxDiscardedTests) {
            return giveUp(state);
        }
        
        throw new RuntimeException("MainLoop.test something went wrong.");
    }
    
    private boolean runTesting(State state) {
        return  state.numSuccessTests < state.maxSuccessTests &&
                state.numDiscardedTests < state.maxDiscardedTests;
    }
    
    private Result doneTesting(State state) {
        if(state.expectedFailure) {
            state.terminal.putPart("+++ OK, passed " + state.numSuccessTests + " tests");
        } else {
            state.terminal.putPart("*** Failed! Passed " + state.numSuccessTests + " tests (expected failure)");
        }
        final String output = state.terminal.output();
        if(state.expectedFailure) {
            return Result.Success(state.numSuccessTests, output);
        } else {
            return Result.NoExpectedFailure(state.numSuccessTests, output);
        }
    }
    
    private Result giveUp(State state) {
        state.terminal.putPart("*** Gave up! Passed only " + state.numSuccessTests + " tests");
        final String output = state.terminal.output();
        return Result.GaveUp(state.numSuccessTests, output);
    }
    
}
