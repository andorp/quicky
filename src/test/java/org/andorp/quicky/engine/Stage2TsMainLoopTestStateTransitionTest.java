package org.andorp.quicky.engine;

import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.engine.MainLoop;
import org.andorp.quicky.engine.Result;
import org.andorp.quicky.engine.State;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public final class Stage2TsMainLoopTestStateTransitionTest {
    
    private MainLoop mainLoop; 
    
    @BeforeTest
    public void createEnvironment() {
        // Create environment //
        final ConstRandom random = new ConstRandom(0,0);
        final NullPostProcess postProcess = new NullPostProcess();
        mainLoop = new MainLoop(random, postProcess);
    }
    
    private Arguments defaultArguments(int size, int success) {
        final Arguments arguments = new Arguments(
                false,   // chatty
                false,   // replay
                size,    // maxSize
                success, // maxSuccess
                0.0      // maxDiscardRatio
                );
        return arguments;
    }
    
    /**
     * Check if the discarded tests doesn't make fail result for the test run
     */
    @Test(groups = {"STAGE-2"})
    public void doneTestingSuccess() {
        final int success = 10000;
        final int discarded = 100;
        final int failed = 0;
        final int size = 100;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createGaveUpState(success, discarded);
        
        final RandomStatedPropGenerator generator = new RandomStatedPropGenerator(success, discarded, failed);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Success, "MainLoop doesn't successeded in STAGE-2 random test " + result.getClass().getName());
    }
    
    /**
     * Check if the failed test case does make fail the test run
     */
    @Test(groups = {"STAGE-2"})
    public void doneTestingFailure() {
        final int success = 1000000;
        final int discarded = 0;
        final int failed = 1;
        final int size = 10000;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final RandomStatedPropGenerator generator = new RandomStatedPropGenerator(success, discarded, failed);
        
        final Result result = mainLoop.test(state, generator);
        
        if(generator.failed() == 0) {
            Assert.assertTrue(result instanceof Result.Failure, "MainLoop doesn't successeded in STAGE-2 random test " + result.getClass().getName());
        } else {
            Assert.assertTrue(result instanceof Result.Success, "MainLoop doesn't successeded in STAGE-2 random test " + result.getClass().getName());
        }
    }
    
    /**
     * Chase after exceptions, load test
     */
    @Test(groups = {"STAGE-2"})
    public void doneTestingGiveUp() {
        final int success = 1000000000;
        final int discarded = 1000000000;
        final int failed = 0;
        final int size = 10000;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createGaveUpState(success, discarded);
        
        final RandomStatedPropGenerator generator = new RandomStatedPropGenerator(success, discarded, failed);
        
        final Result result = mainLoop.test(state, generator);
    }
    
    @AfterTest
    public void destroyEnvironment() {
        mainLoop = null;
    }
}
