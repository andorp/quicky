package org.andorp.quicky.generator.primitive;


import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.DoubleGenerator;
import org.testng.annotations.Test;
import org.testng.Assert;

public final class Stage2TsDoubleGeneratorTest {
    
    @Test(groups = {"STAGE-2"})
    public void testDoubleGenerator() {
        final int seed = 5910;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final DoubleGenerator doubleGenerator = new DoubleGenerator();
        final int inc = Integer.MAX_VALUE / 1000;
        for(int size = 0; size < (Integer.MAX_VALUE - inc); size += inc) {
            final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
            for(int i = 0; i < 1000; ++i) {
                final double d = doubleGenerator.next(randomAndSize);
                checkSizeProperty(d, size);
            }
        }
    }
    
    private void checkSizeProperty(double d, int size) {
        Assert.assertTrue(-(size + 1) <= d && d <= (size + 1), "Generated value is not in range: " + d + " " + size);
    }
    
}
