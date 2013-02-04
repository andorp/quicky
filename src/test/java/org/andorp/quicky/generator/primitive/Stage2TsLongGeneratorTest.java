package org.andorp.quicky.generator.primitive;


import java.util.Set;
import java.util.HashSet;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.engine.VarRandom;
import org.andorp.quicky.generator.primitive.LongGenerator;
import org.testng.annotations.Test;
import org.testng.Assert;

public final class Stage2TsLongGeneratorTest {
    
    /**
     * NO TEST DESIGN IS REQUIRED:
     * - Check if all integer value is generated during the generation of test data
     * - Chase after some exception
     */
    
    @Test(groups = {"STAGE-2"})
    public void testLongGenerator() {
        final int seed = 9478;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final VarRandom random = new VarRandom(seed,0);
        final IRandom javaRandom = engineToolFactory.javaRandom(seed);
        final LongGenerator longGenerator = new LongGenerator();
        
        final int increment = (Integer.MAX_VALUE / 1000);
        
        for(int size = 1; size < (Integer.MAX_VALUE - increment); size += increment) {
            random.setValue(size);
            final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
            final long y = longGenerator.next(randomAndSize);
            checkSizeProperty(y, size);
            final RandomAndSize randomAndSize1 = engineToolFactory.randomAndSize(javaRandom, size);
            for(int i = 0; i < 100; ++i) {
                final long y1 = longGenerator.next(randomAndSize1);
                checkSizeProperty(y1, size);
            }
        }
    }
    
    private void checkSizeProperty(long value, int size) {
        Assert.assertTrue(-size <= value && value <= size, "Generated integer is out of range: " + value + " " + size);
    }
    
}
