package org.andorp.quicky.generator.primitive;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.JavaRandom;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.BooleanGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage2TsBooleanGeneratorTest {
    
    @Test(groups = { "STAGE-2" })
    private void testBooleanGenerator() {
        final int seed = 1024;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final BooleanGenerator booleanGenerator = new BooleanGenerator();
        
        int falseOccurence = 0;
        int trueOccurence = 0;
        
        for(int size = 1; size <= (Integer.MAX_VALUE / 2); size *= 2) {
            falseOccurence = 0;
            trueOccurence = 0;
            for(int i = 0; i < 100; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                
                if(booleanGenerator.next(randomAndSize)) {
                    ++trueOccurence;
                } else {
                    ++falseOccurence;
                }
            }
            
            Assert.assertTrue(trueOccurence > 0, "There was no true value generated");
            Assert.assertTrue(falseOccurence > 0, "There was no false value generated");
        }
        
    }
    
}
