package org.andorp.quicky.engine;

import java.util.Random;

import org.andorp.quicky.engine.Arguments;
import org.andorp.quicky.engine.DefaultSizeComputation;
import org.testng.annotations.Test;
import org.testng.Assert;


public final class Stage2TsDefaultSizeComputationTest {
    
    @Test(groups = {"STAGE-2"} )
    public void chaseAfterZero() {
        final Arguments arguments = new Arguments(
                false,   // chatty
                false,   // replay
                Integer.MAX_VALUE,    // maxSize
                Integer.MAX_VALUE, // maxSuccess
                0.0      // maxDiscardRatio
                );
        
        final DefaultSizeComputation dsc = new DefaultSizeComputation(arguments);
        Random random = new Random();
        for(int i = 0; i < 100000; ++i) {
            final int x = roughtyAbs(random.nextInt());
            final int y = roughtyAbs(random.nextInt());
            Assert.assertTrue(dsc.compute(x, y) > 0, "Computation result was less than 1: " + x + ", " + y);
        }
    }
    
    private int roughtyAbs(int i) {
        if(i == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        if(i < 0) {
            return (-1*i);
        }
        return i;
    }
    
}
