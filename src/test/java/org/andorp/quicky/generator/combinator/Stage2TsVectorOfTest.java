package org.andorp.quicky.generator.combinator;

import java.util.List;

import org.andorp.quicky.IRandom;
import org.andorp.quicky.engine.EngineToolFactory;
import org.andorp.quicky.engine.RandomAndSize;
import org.andorp.quicky.generator.combinator.VectorOf;
import org.testng.Assert;
import org.testng.annotations.Test;


public final class Stage2TsVectorOfTest {
    
    @Test(groups = {"STAGE-2"})
    public void vectorOfTest(){
        
        final int length = 10;
        final int seed = 7037610;
        final int repetition = 10000;
        final Integer number = new Integer(4);
        
        final ConstGenerator<Integer> constGenerator = new ConstGenerator<Integer>(number);
        final EngineToolFactory engineToolFactory = new EngineToolFactory();
        final IRandom random = engineToolFactory.javaRandom(seed);
        
        final VectorOf<Integer> vectorOf = new VectorOf<Integer>(length, constGenerator);
        
        for(int r = 0; r < repetition; ++r) {
            // The r stands for repetition and arbitrary size parameter for the data generator //
            final RandomAndSize randomAndSize = engineToolFactory.randomAndSize(random, r);
            
            final List<Integer> vector = vectorOf.next(randomAndSize);
            checkVector(length, vector, number);
        }
    }
    
    private void checkVector(int length, List<Integer> vs, Integer v) {
        Assert.assertEquals(length, vs.size(), "Generated length was different.");
        for(Integer i : vs) {
            Assert.assertNotNull(i, "Generated value was null");
            Assert.assertEquals(i, v, "Generated value was different");
        }
    }
}
