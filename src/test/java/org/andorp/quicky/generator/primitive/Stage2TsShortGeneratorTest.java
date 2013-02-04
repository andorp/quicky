package org.andorp.quicky.generator.primitive;


import java.util.Set;
import java.util.HashSet;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.ShortGenerator;
import org.testng.annotations.Test;
import org.testng.Assert;

public final class Stage2TsShortGeneratorTest {
    
    /**
     * NO TEST-DESIGN IS REQUIRED:
     * - Check if all short value is generated during the generation of test data.
     * - Chase after some exceptions
     */
    @Test(groups = {"STAGE-2"})
    public void testShortGenerator() {
        final int seed = 4891;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final ShortGenerator shortGenerator = new ShortGenerator();
        final Set<Short> shortSet = new HashSet<Short>();
        final int increment = (Integer.MAX_VALUE / 100);
        
        for(int size = 1; size < (Integer.MAX_VALUE - increment); size += increment) {
            for(int i = 0; i < Short.MAX_VALUE; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final short s = shortGenerator.next(randomAndSize);
                checkSizeProperty(s, size);
                insertShortIntoSet(shortSet, s);
                
                final RandomAndSize randomAndSize1 = engineToolFactory.randomAndSize(random, size + 1);
                final short s1 = shortGenerator.next(randomAndSize1);
                checkSizeProperty(s1, size);
                insertShortIntoSet(shortSet, s1);
            }
        }
        checkShortSet(shortSet);
    }
    
    private void checkShortSet(Set<Short> shortSet) {
        if(!(shortSet.contains(Short.MAX_VALUE))) {
            Assert.fail("Value: " + Short.MAX_VALUE + " was not generated");
        }
        for(short s = Short.MIN_VALUE; s < Short.MAX_VALUE; ++s) {
            if(!(shortSet.contains(s))) {
                Assert.fail("Value: " + s + " was not generated");
            }
        }
    }
    
    private void insertShortIntoSet(Set<Short> shortSet, Short s) {
        if(!(shortSet.contains(s))) {
            shortSet.add(s);
        }
    }
    
    private void checkSizeProperty(short v, int size) {
        Assert.assertTrue(-size <= v && v <= size, "Generated value is out of range " + v + " " + size);
    }
    
}
