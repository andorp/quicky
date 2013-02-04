package org.andorp.quicky.generator.primitive;


import java.util.Set;
import java.util.HashSet;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.engine.VarRandom;
import org.andorp.quicky.generator.primitive.IntegerGenerator;
import org.testng.annotations.Test;
import org.testng.Assert;

public final class Stage2TsIntegerGeneratorTest {
    
    /**
     * NO TEST DESIGN IS REQUIRED:
     * - Check if all integer value is generated during the generation of test data
     * - Chase after some exception
     */
    
    @Test(groups = {"STAGE-2"})
    public void testIntegerGenerator() {
        final int seed = 9478;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final VarRandom random = new VarRandom(seed,0);
        final IRandom javaRandom = engineToolFactory.javaRandom(seed);
        final IntegerGenerator integerGenerator = new IntegerGenerator();
        
        final int increment = (Integer.MAX_VALUE / 1000);
        
        for(int size = 1; size < (Integer.MAX_VALUE - increment); size += increment) {
            random.setValue(size);
            final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
            final int y = integerGenerator.next(randomAndSize);
            checkSizeProperty(y, size);
            for(int i = 0; i < 1000; ++i) {
                final RandomAndSize randomAndSize1 = engineToolFactory.randomAndSize(javaRandom, size);
                final int y1 = integerGenerator.next(randomAndSize1);
                checkSizeProperty(y1, size);
            }
        }
    }
    
    private void checkSizeProperty(int value, int size) {
        Assert.assertTrue(-size <= value && value <= size, "Generated integer is out of range: " + value + " " + size);
    }
    
}
