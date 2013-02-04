package org.andorp.quicky.engine;

import org.andorp.quicky.engine.FailPropGenerator;
import org.andorp.quicky.engine.Prop;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.engine.SingleTestResult;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage1TsFailPropGeneratorTest {
    
    private void hasToFail(SingleTestResult result, String message) {
        Assert.assertEquals(result.ok, false, "FailPropGenerator does not fail at first time.");
        Assert.assertEquals(result.reason , message, "FailPropGenerator does not hold the reason message.");    
    }
    
    /**
     * NO TEST-DESIGN IS REQUIERED
     * The FailPropGenerator has a simple behavior. It will fail immediately. And after
     * having run 1 time if must fail in following times.
     */
    @Test(groups = {"STAGE-1"})
    public void failingProperty() {
        // Parameters //
        final String message = "message";
        final int size = 0;
        final int seed = 0;
        final int value = 0;
        
        // Active elements //
        final RandomAndSize random = new RandomAndSize(new ConstRandom(seed,value), size);
        final FailPropGenerator fpg = new FailPropGenerator(message);
        
        // Test behavior //
        final Prop result1 = fpg.next(random);
        hasToFail(result1.result(), message);
        
        final Prop result2 = fpg.next(random);
        hasToFail(result2.result(), message);
    }
    
    /**
     * NO TEST-DESIGN IS REQUIRED
     * Passing a null object to FailPropGenerator constructor,
     * an IllegalArgumentException has to be thrown.
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityCheckNullReference() {
        new FailPropGenerator(null);
    }
    
    /**
     * NO TEST-DESGIN IS REQUIRED
     * Passing a null object to FailPropGenerator constructor,
     * an IllegalArgumentException has to be thrown.
     */
    @Test(groups = {"STAGE-1"}, expectedExceptions = IllegalArgumentException.class)
    public void sanityCheckEmptyString() {
        new FailPropGenerator("");
    }
    
}
