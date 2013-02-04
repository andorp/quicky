package org.andorp.quicky.engine;

import org.andorp.quicky.engine.JavaRandom;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage1TsJavaRandomTest {
    
    /**
     * Check if the same seed value generates the same rendom sequence
     */
    @Test(groups = { "STAGE-1" })
    public void testRandomSequences() {
        final long seed = 100;
        int iteration = 1000000;
        final JavaRandom random1 = new JavaRandom(seed);
        final JavaRandom random2 = new JavaRandom(seed);
        
        for(; iteration > 0; --iteration) {
            final int value1 = random1.next();
            final int value2 = random2.next();
            Assert.assertEquals(value1, value2, "Two JavaRandom with the same seed does not generate the same pseudorandom sequence.");
        }
        
    }
    
}
