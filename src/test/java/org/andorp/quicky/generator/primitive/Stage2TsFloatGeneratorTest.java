package org.andorp.quicky.generator.primitive;


import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.FloatGenerator;
import org.testng.annotations.Test;
import org.testng.Assert;

public final class Stage2TsFloatGeneratorTest {
    
    @Test(groups = {"STAGE-2"})
    public void testFloatGenerator() {
        final int seed = 5912;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final FloatGenerator floatGenerator = new FloatGenerator();
        final int inc = Integer.MAX_VALUE / 1000;
        for(int size = 0; size < (Integer.MAX_VALUE - inc); size += inc) {
            final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
            for(int i = 0; i < 100; ++i) {
                final float f = floatGenerator.next(randomAndSize);
                checkSizeProperty(f, size);
            }
        }
    }
    
    private void checkSizeProperty(float f, int size) {
        Assert.assertTrue(-(size + 1) <= f && f <= (size + 1), "Generated value is not in range: " + f + " " + size);
    }
    
}
