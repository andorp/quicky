package org.andorp.quicky.engine;

import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.engine.ControllablePropGenerator;
import org.andorp.quicky.engine.MainLoop;
import org.andorp.quicky.engine.Result;
import org.andorp.quicky.engine.State;
import org.andorp.quicky.engine.StateFactory;
import org.andorp.quicky.engine.SingleTestResultFactory.Command;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;


/**
 * TEST-SUITE: Equivalence partitioning
 * TEST-DESIGN: Equivalence partitioning
 * 
 * Partition on number of successful runs: (P1)
 * - Less test passes than the required successful limit (Quicky test property will FAIL)
 * - Equal test passes as the required successful limit (Quicky test property will PASS)
 * - More than one test passes than the required successful limit (Quicky test property will FAIL)
 * 
 * Partition on when a failed test occurs: (P2)
 * - Sooner then the required limit is reached
 * - Exactly when the required limit is reached
 * - After when the required limit is reached
 * 
 * Partition on number of discarded runs: (P3)
 * - Less test discards than the give up limit
 * - Equal test discards than the give up limit
 * - More than one test passes than the give up limit
 * 
 * Partition on size parameter (P4)
 * - Zero
 * - More than zero
 * 
 * Partition on success parameter (P5)
 * - Zero
 * - More than zero
 * 
 * Partition on size/success ratio (P6)
 * - Less than 1
 * - Equals to 1
 * - Greater than 1
 *
 * Partition on aborted tests (P7)
 * - One failed test is aborted
 * - One discarded test is aborted
 * 
 * @author andorp
 *
 */
public class Stage1TsMainLoopEqPartitionsTest {
    
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
     * P1-1, P4-2, P5-2:
     * Less test passes than the required successful limit (Quicky test property will FAIL)
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * RESULT: Gave up the testing
     */
    @Test(groups = {"STAGE-1"})
    public void successfulRunPartitionLessThanRequired() {
        // Parameters //
        final int success = 100;
        final int size = 100;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willDiscardedIn(99);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.GaveUp , "MainLoop.test doesn't fail if less than required test passed during test cycle");
    }
    
    /**
     * P1-2, P2-3, P4-2, P5-2:
     * Successful runs: Equal test passes as the required successful limit (Quicky test property will PASS)
     * Failed case: After when the required limit is reached 
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * RESULT: Successful testing
     */
    @Test(groups = {"STAGE-1"})
    public void sucsessfulRunPartitionEqualThenRequired() {
        final int success = 1000;
        final int size = 1000;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willFailIn(success + 1);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Success, "MainLoop doesn't successeded if the number of passed tests reached the required");
        Assert.assertEquals(result.numTests , success, "Successful test case number was different");
    }
    
    /**
     * P1-3, P2-3, P4-2, P5-2:
     * Successful runs: More than one test passes than the required successful limit (Quicky test property will FAIL)
     * Failed case: After when the required limit is reached 
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * RESULT: Successful testing
     */
    @Test(groups = {"STAGE-1"})
    public void successfulRunPartitionMoreThanNeeded() {
        final int success = 10000;
        final int size = 300;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willFailIn(success + 2);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Success, "MainLoop doesn't successeded if the number of passed tests was greater than requiered");
        Assert.assertEquals(result.numTests, success, "Successful test case number was different");
    }
    
    /**
     * P1-1, P2-1, P4-2, P5-2:
     * Successful runs: Less test passes than the required successful limit (Quicky test property will FAIL)
     * Failed case: Sooner then the required limit is reached
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * RESULT: Failed testing
     */
    @Test(groups = {"STAGE-1"})
    public void failedTestSoonerThenTheRequiredLimitIsReached() {
        final int success = 20;
        final int size = 30000;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willFailIn(success - 1);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Failure, "MainLoop doesn't fail when a failure test case occurs");
    }
    
    /**
     * P1-1, P2-2, P4-2, P5-2, P6-1
     * Successful runs: Less test passes than the required successful limit (Quicky test property will FAIL)
     * Failed case: Sooner then the required limit is reached
     * Size parameter: More than zero
     * Success parameter: More than zero
     * Size/Success ration: Less than 1
     * 
     * RESULT: Falied testing
     */
    @Test(groups = {"STAGE-1"})
    public void failedInTheLastStep() {
        final int success = 65536;
        final int size = 20;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willFailIn(success);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Failure, "MainLoop doesn't fail when a failure test case occurs");
    }
    
    /**
     * P5-1: Zero Successful test
     * 
     * RESULT: Illegal Argument Exception
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void zeroSuccessTest() {
        final int success = 0;
        final int size = 100;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willFailIn(success);
        
        mainLoop.test(state, generator);
    }
    
    /**
     * P4-1: Zero size test 
     * 
     * RESULT: Illegal Argument Exception
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void zeroSizeTest() {
        final int success = 89;
        final int size = 0;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willFailIn(success);
        
        final Result result = mainLoop.test(state, generator);
    }
    
    /**
     * P1-3, P3-1, P4-2, P5-2
     * Successful runs: More than one test passes than the required successful limit (Quicky test property will FAIL)
     * Discarded tests: Less test discards than the give up limit
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * RESULT: Successful testing
     */
    @Test(groups = {"STAGE-1"})
    public void lessTestDiscardedThanGaveUpLimit() {
        final int success = 900;
        final int size = 200;
        final int gaveUp = 20;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createGaveUpState(success, gaveUp);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator(Command.DISCARD_NEG_NEG);
        generator.willSuccessIn(gaveUp);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Success, "MainLoop doesn't success if there is no less than required discarded message happened: " + result.getClass().toString());
    }
    
    /**
     * P1-3, P3-2, P4-2, P5-2
     * Successful runs: More than one test passes than the required successful limit (Quicky test property will FAIL)
     * Discarded tests: Equal test discards than the give up limit
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * 
     * RESULT: Gave Up 
     */
    @Test(groups = {"STAGE-1"})
    public void equalTestDiscardedThanGaveUpLimit() {
        final int success = 100;
        final int size = 50;
        final int gaveUp = 40;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createGaveUpState(success, gaveUp);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator(Command.DISCARD_NEG_NEG);
        generator.willSuccessIn(gaveUp + 1);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.GaveUp, "MainLoop doesn't success if there is no less than required discarded message happened: " + result.getClass().toString());
    }
    
    /**
     * P1-3, P3-3, P4-2, P5-2
     * Successful runs: More than one test passes than the required successful limit (Quicky test property will FAIL)
     * Discarded tests: More than one test passes than the give up limit
     * Size parameter: More than zero
     * Success parameter: More than zero
     * 
     * RESULT: Gave Up
     */
    @Test(groups = {"STAGE-1"})
    public void greaterThanTestDiscardedThanGaveUpLimit() {
        final int success = 100;
        final int size = 50;
        final int gaveUp = 40;
        
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createGaveUpState(success, gaveUp);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator(Command.DISCARD_NEG_NEG);
        generator.willSuccessIn(gaveUp + 2);
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.GaveUp, "MainLoop doesn't success if there is no less than required discarded message happened: " + result.getClass().toString());
    }
    
    /**
     * P7-1
     * One successful test is aborted
     */
    @Test(groups = {"STAGE-1"})
    public void testSuccessfulAndAborted() {
        final int size = 2;
        final int success = 10;
        final int abortIn = 5;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willAbortIn(abortIn, false, true, true); // Not discarded successful test: discarded, ok, expected //
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.Success, "MainLoop doesn't fail if abort occurs" + result.getClass().getName());
        Assert.assertEquals(result.numTests, abortIn, "MainLoop invalid number of success");
    }
    
    /**
     * P7-2
     * One failed test is aborted
     */
    @Test(groups = {"STAGE-1"})
    public void testDiscardedAndAborted() {
        final int size = 2;
        final int success = 10;
        final int abortIn = 5;
        final StateFactory stateFactory = new StateFactory(defaultArguments(size, success));
        final State state = stateFactory.createSuccessState(success);
        
        final ControllablePropGenerator generator = new ControllablePropGenerator();
        generator.willAbortIn(abortIn, true, true, true); // Discarded successful test: discarded, ok, expected //
        
        final Result result = mainLoop.test(state, generator);
        Assert.assertTrue(result instanceof Result.GaveUp, "MainLoop doesn't fail if abort occurs" + result.getClass().getName());
        Assert.assertEquals(result.numTests, abortIn, "MainLoop invalid number of success");
    }
    
    @AfterTest
    public void destroyEnvironment() {
        mainLoop = null;
    }
}
