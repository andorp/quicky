package org.andorp.quicky.generator.primitive;


import java.util.Set;
import java.util.HashSet;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.primitive.ByteGenerator;
import org.testng.annotations.Test;
import org.testng.Assert;

public final class Stage2TsByteGeneratorTest {
    
    /**
     * NO TEST-DESIGN IS REQUIRED:
     * - Check if all byte is generated during the generation of test data.
     * - Chase after some exceptions
     */
    @Test(groups = {"STAGE-2"})
    public void testByteGenerator() {
        final int seed = 4890;
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        final ByteGenerator byteGenerator = new ByteGenerator();
        final Set<Byte> byteSet = new HashSet<Byte>();
        for(int size = 1; size < (Integer.MAX_VALUE / 2); size *= 2) {
            for(int i = 0; i < 9182; ++i) {
                final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, size);
                final byte b = byteGenerator.next(randomAndSize);
                checkSizeProperty(b, size);
                insertByteSet(byteSet, b);
                
                final RandomAndSize randomAndSize1 = engineToolFactory.randomAndSize(random, size + 1);
                final byte b1 = byteGenerator.next(randomAndSize1);
                checkSizeProperty(b1, size + 1);
                insertByteSet(byteSet, b1);
            }
        }
        checkByteSet(byteSet);
    }
    
    private void checkByteSet(Set<Byte> byteSet) {
        if(!(byteSet.contains(Byte.MAX_VALUE))) {
            Assert.fail("Value: " + Byte.MAX_VALUE + " was not generated");
        }
        for(byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; ++b) {
            if(!(byteSet.contains(b))) {
                Assert.fail("Value: " + b + " was not generated");
            }
        }
    }
    
    private void insertByteSet(Set<Byte> byteSet, Byte b) {
        if(!(byteSet.contains(b))) {
            byteSet.add(b);
        }
    }
    
    private void checkSizeProperty(byte b, int size) {
        Assert.assertTrue(-size <= b && b <= size, "Generated value is out of range " + b + " " + size);
    }
    
}
